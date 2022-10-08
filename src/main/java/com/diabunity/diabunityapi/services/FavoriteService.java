package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Favorite;
import com.diabunity.diabunityapi.repositories.FavoriteRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

  @Autowired
  private FavoriteRepository favoriteRepository;

  public List<String> getPostsFavoritesByUser(String userId) {
    List<Favorite> listFavorites = favoriteRepository.findByUserId(userId);

    List<String> result = new ArrayList<>();

    listFavorites.stream().forEach(fav -> result.add(fav.getPostId()));

    return result;
  }

  public List<String> getUsersFavoritesByPost(String postId) {
    List<Favorite> listFavorites = favoriteRepository.findByPostId(postId);

    List<String> result = new ArrayList<>();

    listFavorites.stream().forEach(fav -> result.add(fav.getUserId()));

    return result;
  }

  public Favorite saveFavorite(String userId, String postId) {
    return favoriteRepository.save(new Favorite(userId, postId));
  }

  public void deleteFavorite(String userId, String postId) {
    favoriteRepository.deleteByUserIdAndPostId(userId, postId);
  }

}
