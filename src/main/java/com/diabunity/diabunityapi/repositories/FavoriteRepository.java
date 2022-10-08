package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Favorite;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends MongoRepository<Favorite, String> {

  List<Favorite> findByUserId(String userId);

  List<Favorite> findByPostId(String postId);

  void deleteByUserIdAndPostId(String userId, String postId);

}
