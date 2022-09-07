package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
