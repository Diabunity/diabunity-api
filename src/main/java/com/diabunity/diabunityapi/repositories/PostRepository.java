package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

  Page<Post> findPostByParentIdIsNull(Pageable page);

  List<Post> findPostByParentId(String parentId, Sort sort);

  Page<Post> findPostByPostIdIsIn(List<String> postId, Pageable page);

  Post findPostByPostIdAndUserId(String id, String userId);

  void deletePostByPostIdAndUserId(String id, String userId);
}
