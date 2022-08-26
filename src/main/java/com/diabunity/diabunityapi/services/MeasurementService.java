package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.*;
import com.diabunity.diabunityapi.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {

  private final static int GLUCOSE_GAP_WARNING = 30;

  @Autowired
  private UserService userService;

  @Autowired
  private MeasurementRepository measurementRepository;

  public List<Measurement> saveAll(List<Measurement> measurements) {
     return measurementRepository.saveAll(measurements);
  }

  public MeasurementsResponse getAllByUserId(String userId, LocalDateTime from, LocalDateTime to) {
    List<Measurement> measurements =  measurementRepository.findAllByUserIdAndTimestampBetween(userId, from, to);
    User user = userService.getUser(userId).get();

    final Double minGlucose = user.getGlucoseMin();
    final Double maxGlucose = user.getGlucoseMax();

    if (minGlucose.isNaN() || maxGlucose.isNaN()) {
      return new MeasurementsResponse(measurements);
    }

    measurements.forEach(m -> {
      Double actualGlucose = m.getMeasurement();
      m.setStatus(calculateStatus(actualGlucose, minGlucose, maxGlucose));
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

    MeasurementAverage avg = new MeasurementAverage();
    avg.setValue(sum/count);
    avg.setStatus(calculateStatus(avg.getValue(), minGlucose, maxGlucose));

    return avg;
  }

  public PeriodInTarget calculatePeriodInTarget(List<Measurement> m) {
    Long measurementsOK = m.stream().filter(it -> it.getStatus() == MeasurementStatus.OK).count();
    Double periodInTargetValue = Double.valueOf(measurementsOK / m.stream().count());
    return new PeriodInTarget(periodInTargetValue, periodInTargetValue >= 70D ? 1 : 0);
  }

}
