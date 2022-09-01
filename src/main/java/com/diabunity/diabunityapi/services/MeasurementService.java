package com.diabunity.diabunityapi.services;

import static java.time.temporal.ChronoUnit.MINUTES;

import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.models.MeasurementAverage;
import com.diabunity.diabunityapi.models.MeasurementStatus;
import com.diabunity.diabunityapi.models.MeasurementsResponse;
import com.diabunity.diabunityapi.models.PeriodInTarget;
import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.repositories.MeasurementRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

    private final static int GLUCOSE_GAP_WARNING = 30;

    @Autowired
    private UserService userService;

    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> saveAll(List<Measurement> measurements) {
        Collections.sort(measurements, Comparator.comparing(Measurement::getTimestamp));

        LocalDateTime timestampFrom = measurements.get(0).getTimestamp();
        LocalDateTime timestampTo = measurements.get(measurements.size() - 1).getTimestamp();
        String userID = measurements.get(0).getUserId();

        List<Measurement> retrievedMeasurements = measurementRepository
            .findAllByUserIdAndTimestampBetween(userID, timestampFrom, timestampTo, Sort.by(Sort.Direction.DESC, "timestamp"));

        List<Measurement> measurementsToSave = new ArrayList<>();
        Measurement measurementBefore = null;

        for(Measurement m:measurements) {
            if (measurementBefore != null
                && measurementBefore.getMeasurement().equals(m.getMeasurement())
                && MINUTES.between(measurementBefore.getTimestamp(), m.getTimestamp()) < 15 ) {
                continue;
            } else {
                if (!retrievedMeasurements.stream().anyMatch(r -> r.getTimestamp().equals(m.getTimestamp()))) {
                    measurementsToSave.add(m);
                }
                measurementBefore =  new Measurement(m.getUserId(), m.getMeasurement(), m.getTimestamp(), m.getSource(), m.getComments());
            }
        }

        if (!measurementsToSave.isEmpty()) {
            measurementRepository.saveAll(measurementsToSave);
        }

        return measurementsToSave;
    }

    public MeasurementsResponse getAllByUserId(String userId, LocalDateTime from, LocalDateTime to) {
        List<Measurement> measurements = measurementRepository.findAllByUserIdAndTimestampBetween(userId, from, to, Sort.by(Sort.Direction.DESC, "timestamp"));
        if (measurements.isEmpty()) {
            return new MeasurementsResponse(measurements);
        }

        User user = userService.getUser(userId).get();

        final Double minGlucose = user.getGlucoseMin();
        final Double maxGlucose = user.getGlucoseMax();

        if (minGlucose.isNaN() || maxGlucose.isNaN()) {
            return new MeasurementsResponse(measurements);
        }

        measurements.forEach(m -> {
            m.setStatus(calculateStatus(m.getMeasurement(), minGlucose, maxGlucose));
        });

        return new MeasurementsResponse(measurements,
                average(measurements, minGlucose, maxGlucose),
                calculatePeriodInTarget(measurements));
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

    public MeasurementAverage average(List<Measurement> measurements, Double minGlucose, Double maxGlucose) {
        Long count = measurements.stream().count();
        Double sum = measurements.stream().mapToDouble(n -> n.getMeasurement()).sum();

        double avg = sum / count;

        return new MeasurementAverage(avg, calculateStatus(avg, minGlucose, maxGlucose));
    }

    public PeriodInTarget calculatePeriodInTarget(List<Measurement> measurements) {
        Long measurementsOK = measurements.stream().filter(m -> m.getStatus() == MeasurementStatus.OK).count();
        double periodInTargetValue = measurementsOK / Double.valueOf(measurements.size());
        return new PeriodInTarget(periodInTargetValue);
    }
}
