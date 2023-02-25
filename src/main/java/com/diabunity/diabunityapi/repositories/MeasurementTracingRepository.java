package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.MeasurementSource;
import com.diabunity.diabunityapi.models.MeasurementTracing;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MeasurementTracingRepository extends MongoRepository<MeasurementTracing, String> {
    @Query(value = "{'userId': ?0, 'timestamp':{ $gte: ?1, $lte: ?2}, 'source': ?3}")
    List<MeasurementTracing> findAllByUserIdAndTimestampBetweenAndSource(String id,
                                                                         LocalDateTime startDate,
                                                                         LocalDateTime endDate,
                                                                         MeasurementSource source);

}
