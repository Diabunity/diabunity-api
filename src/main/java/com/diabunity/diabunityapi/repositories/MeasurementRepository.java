package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.models.MeasurementSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {
    @Query(value = "{'userId': ?0, 'timestamp':{ $gte: ?1, $lte: ?2}}")
    Page<Measurement> findAllByUserIdAndTimestampBetween(String id,
                                                         LocalDateTime startDate,
                                                         LocalDateTime endDate,
                                                         Pageable page);

    Measurement findFirstByUserIdAndSource(String userId, MeasurementSource source, Sort sort);
}