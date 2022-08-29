package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Measurement;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {
    @Query(value = "{'timestamp':{ $gte: ?1, $lte: ?2}}")
    List<Measurement> findAllByUserIdAndTimestampBetween(String id, LocalDateTime startDate, LocalDateTime endDate, Sort sort);
}