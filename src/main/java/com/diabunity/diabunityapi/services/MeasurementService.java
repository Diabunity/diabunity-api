package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.repositories.MeasurementRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

  @Autowired
  private MeasurementRepository measurementRepository;

  public List<Measurement> saveAll(List<Measurement> measurements) {
     return measurementRepository.saveAll(measurements);
  }

}
