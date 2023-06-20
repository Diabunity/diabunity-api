package com.diabunity.diabunityapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.diabunity.diabunityapi.models.Feedback;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}
