package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.models.MeasurementAverage;
import com.diabunity.diabunityapi.models.MeasurementSource;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Measurement lastMeasurementSaved = measurementRepository
            .findFirstByUserId(measurements.get(0).getUserId(), Sort.by(Sort.Direction.DESC, "timestamp"));

        List<Measurement> measurementsToSave = new ArrayList<>();

        for(Measurement m:measurements) {
            if (lastMeasurementSaved == null
                || m.getSource().equals(MeasurementSource.MANUAL)
                || (lastMeasurementSaved.getTimestamp().plusMinutes(15L).isBefore(m.getTimestamp()))
                || lastMeasurementSaved.getTimestamp().plusMinutes(15L).isEqual(m.getTimestamp())) {
                measurementsToSave.add(m);
                lastMeasurementSaved = m;
            }
        }

        if (!measurementsToSave.isEmpty()) {
            measurementRepository.saveAll(measurementsToSave);
        }

        return measurementsToSave;
    }

    public MeasurementsResponse getAllByUserId(String userId, LocalDateTime from, LocalDateTime to, int page, int size) {
        Pageable pageConfig = PageRequest.of(page, size,
            Sort.by(Sort.Direction.DESC, "timestamp"));

        Page<Measurement> pageResult = measurementRepository.findAllByUserIdAndTimestampBetween(userId, from, to, pageConfig);

        List<Measurement> measurements = pageResult.getContent();

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
