package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Post;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

  @Query(value = "{'timestamp':{ $gte: ?0, $lte: ?1}}")
  Page<Post> findPostByParentIdIsNull(LocalDateTime startDate, LocalDateTime endDate, Pageable page);

}
