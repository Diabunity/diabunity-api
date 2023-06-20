package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.MeasurementsInTarget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementsInTargetRepository extends MongoRepository<MeasurementsInTarget, String> {

    List<MeasurementsInTarget> findByMonth(Integer month);

    Optional<MeasurementsInTarget> findByUserIDAndMonth(String userID, Integer month);
}
