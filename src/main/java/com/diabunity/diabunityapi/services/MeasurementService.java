package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.*;
import com.diabunity.diabunityapi.repositories.MeasurementRepository;
import com.diabunity.diabunityapi.utils.LinearRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MeasurementService {

    private final static int GLUCOSE_GAP_WARNING = 30;
    static Long VALID_OFFSET_MINUTES = 15L;
    @Autowired
    private UserService userService;
    @Autowired
    private RankingService rankingService;
    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> saveAll(List<Measurement> measurements) {
        Collections.sort(measurements, Comparator.comparing(Measurement::getTimestamp));

        String userID = measurements.get(0).getUserId();
        List<Measurement> measurementsToSave = filterDuplicatedMeasurements(measurements);
        if (measurementsToSave.isEmpty()) {
            return null;
        }

        // calculate the status of each measurement based on the minimum and maximum glucose previously set by the user
        User user = userService.getUser(userID).get();

        final Double minGlucose = user.getMinGlucose();
        final Double maxGlucose = user.getMaxGlucose();

        measurements.forEach(m -> m.setStatus(calculateStatus(m.getMeasurement(), minGlucose, maxGlucose)));

        measurementRepository.saveAll(measurementsToSave);
        updateInTargetForRanking(userID, measurements);

        return measurementsToSave;
    }

    public Tendency calculateTendency(int[] trend) {
        LinearRegression linearRegression = new LinearRegression(trend);

        double followingMeasurePredicted = linearRegression.predict(15);
        int lastMeasure = trend[trend.length - 1];
        if (followingMeasurePredicted > lastMeasure + 2) {
            return Tendency.RISING_QUICKLY;
        } else if (followingMeasurePredicted > lastMeasure + 1) {
            return Tendency.RISING;
        } else if (followingMeasurePredicted > lastMeasure) {
            return Tendency.CHANGING_SLOWLY;
        } else if (lastMeasure > followingMeasurePredicted + 2) {
            return Tendency.FAILING_QUICKLY;
        } else if (lastMeasure > followingMeasurePredicted + 1) {
            return Tendency.FAILING;
        }

        return null;
    }

    private List<Measurement> filterDuplicatedMeasurements(List<Measurement> measurements) {
        String userID = measurements.get(0).getUserId();
        Measurement lastMeasurementSaved = measurementRepository
                .findFirstByUserIdAndSource(userID, MeasurementSource.SENSOR, Sort.by(Sort.Direction.DESC, "timestamp"));

        List<Measurement> measurementsToSave = new ArrayList<>();

        for (Measurement m : measurements) {
            if (lastMeasurementSaved == null
                    || m.getSource().equals(MeasurementSource.MANUAL)
                    || (lastMeasurementSaved.getTimestamp().plusMinutes(VALID_OFFSET_MINUTES).isBefore(m.getTimestamp()))
                    || lastMeasurementSaved.getTimestamp().plusMinutes(VALID_OFFSET_MINUTES).isEqual(m.getTimestamp())) {
                measurementsToSave.add(m);
                lastMeasurementSaved = m;
            }
        }

        return measurementsToSave;
    }

    private void updateInTargetForRanking(String userID, List<Measurement> measurements) {
        Integer month = measurements.get(0).getTimestamp().getMonth().getValue();
        Long measurementsOK = measurements.stream().filter(m -> m.getStatus() == MeasurementStatus.OK).count();
        Integer totalMeasurements = measurements.size();
        rankingService.updateMeasuresInTargetForUserInMonth(userID, month, Math.toIntExact(measurementsOK), totalMeasurements);
    }

    public MeasurementsResponse getAllByUserId(String userID, LocalDateTime from, LocalDateTime to, int page, int size) {
        Pageable pageConfig = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "timestamp"));

        Page<Measurement> pageResult = measurementRepository.findAllByUserIdAndTimestampBetween(userID, from, to, pageConfig);

        List<Measurement> measurements = pageResult.getContent();

        if (measurements.isEmpty()) {
            return new MeasurementsResponse(measurements);
        }

        User user = userService.getUser(userID).get();

        final Double minGlucose = user.getMinGlucose();
        final Double maxGlucose = user.getMaxGlucose();

        return new MeasurementsResponse(measurements,
                average(measurements, minGlucose, maxGlucose),
                calculatePeriodInTarget(measurements),
                pageResult.getTotalPages(),
                pageResult.getTotalElements());
    }

    private MeasurementStatus calculateStatus(Double actualGlucose, Double minGlucose, Double maxGlucose) {
        if (actualGlucose < minGlucose)
            return MeasurementStatus.LOW;
        else if (actualGlucose > (maxGlucose + GLUCOSE_GAP_WARNING))
            return MeasurementStatus.SUPER_HIGH;
        else if (actualGlucose > maxGlucose)
            return MeasurementStatus.HIGH;
        else
            return MeasurementStatus.OK;
    }

    private MeasurementAverage average(List<Measurement> measurements, Double minGlucose, Double maxGlucose) {
        Long count = measurements.stream().count();
        Double sum = measurements.stream().mapToDouble(n -> n.getMeasurement()).sum();

        double avg = sum / count;

        return new MeasurementAverage(avg, calculateStatus(avg, minGlucose, maxGlucose));
    }

    private PeriodInTarget calculatePeriodInTarget(List<Measurement> measurements) {
        Long measurementsOK = measurements.stream().filter(m -> m.getStatus() == MeasurementStatus.OK).count();
        double periodInTargetValue = measurementsOK / Double.valueOf(measurements.size());
        return new PeriodInTarget(periodInTargetValue);
    }
}
