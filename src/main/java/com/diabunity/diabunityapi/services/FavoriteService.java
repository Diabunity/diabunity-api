package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Favorite;
import com.diabunity.diabunityapi.repositories.FavoriteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

  @Autowired
  private FavoriteRepository favoriteRepository;

  public List<Favorite> getFavoritesByUser(String userId) {
    return favoriteRepository.findByUserId(userId);
  }

  public Favorite saveFavorite(String userId, String postId) {
    return favoriteRepository.save(new Favorite(userId, postId));
  }

  public void deleteFavorite(String userId, String postId) {
    favoriteRepository.deleteByUserIdAndPostId(userId, postId);
  }

}
