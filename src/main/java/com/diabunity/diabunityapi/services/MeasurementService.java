package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.models.MeasurementStatus;
import com.diabunity.diabunityapi.repositories.MeasurementRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

  private final static int GLUCOSE_GAP_WARNING = 10;

  @Autowired
  private MeasurementRepository measurementRepository;

  public List<Measurement> saveAll(List<Measurement> measurements) {
     return measurementRepository.saveAll(measurements);
  }

  public List<Measurement> getAllByUserId(String id, LocalDateTime from, LocalDateTime to) {
    return measurementRepository.findAllByUserIdAndTimestampBetween(id, from, to);
  }

  public void setMeasurementsStatus(List<Measurement> measurements, Double minGlucose, Double maxGlucose ) {
    measurements.forEach(m -> {
      Double actualGlucose = m.getMeasurement();
      if (actualGlucose >= (maxGlucose - GLUCOSE_GAP_WARNING) && actualGlucose <= maxGlucose)
        m.setStatus(MeasurementStatus.WARNING);
      else if (actualGlucose <= (minGlucose + GLUCOSE_GAP_WARNING) && actualGlucose >= minGlucose)
        m.setStatus(MeasurementStatus.WARNING);
      else if (actualGlucose < minGlucose || actualGlucose > maxGlucose )
        m.setStatus(MeasurementStatus.WRONG);
      else
        m.setStatus(MeasurementStatus.OK);
    });
  }

}
