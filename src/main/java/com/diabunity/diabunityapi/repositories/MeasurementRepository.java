package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> { }