package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Measurement;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {
  List<Measurement> findAllByUserIdAndTimestampBetween(String id, LocalDateTime startDate, LocalDateTime endDate);
}