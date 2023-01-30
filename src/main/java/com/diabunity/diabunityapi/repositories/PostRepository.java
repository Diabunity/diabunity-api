package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Post;
import java.time.LocalDateTime;
import java.util.Optional;

import com.diabunity.diabunityapi.models.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

  Page<Post> findPostByParentIdIsNull(Pageable page);

  List<Post> findPostByParentId(String parentId, Sort sort);

  Page<Post> findPostByIdIsIn(List<String> postId, Pageable page);

  Optional<Post> deletePostByIdAndUserInfo(String id, UserInfo userInfo);

  void deletePostByParentId(String postId);

  @Query(value = "{'userInfo.userId': ?0, 'timestamp':{ $gte: ?1, $lte: ?2}}")
  List<Post> findPostByUserIdAndTimestampBetween(String id,
                                                 LocalDateTime startDate,
                                                 LocalDateTime endDate);
}
