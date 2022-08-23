package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.models.MeasurementAverage;
import com.diabunity.diabunityapi.models.MeasurementStatus;
import com.diabunity.diabunityapi.repositories.MeasurementRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

  private final static int GLUCOSE_GAP_WARNING = 30;

  @Autowired
  private MeasurementRepository measurementRepository;

  public List<Measurement> saveAll(List<Measurement> measurements) {
     return measurementRepository.saveAll(measurements);
  }

  public List<Measurement> getAllByUserId(String id, LocalDateTime from, LocalDateTime to) {
    return measurementRepository.findAllByUserIdAndTimestampBetween(id, from, to);
  }

  public void calculateMeasurementsStatus(List<Measurement> measurements, Double minGlucose, Double maxGlucose) {
    measurements.forEach(m -> {
      Double actualGlucose = m.getMeasurement();
      m.setStatus(calculateStatus(actualGlucose, minGlucose, maxGlucose));
    });
  }

  public MeasurementAverage average(List<Measurement> measurements, Double minGlucose, Double maxGlucose) {
    Long count = measurements.stream().count();
    Double sum = measurements.stream().mapToDouble(n -> n.getMeasurement()).sum();

    MeasurementAverage avg = new MeasurementAverage();
    avg.setValue(sum/count);
    avg.setStatus(calculateStatus(avg.getValue(), minGlucose, maxGlucose));

    return avg;
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

}
