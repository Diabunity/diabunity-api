package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.Favorite;
import com.diabunity.diabunityapi.models.FavoriteResponse;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.services.FavoriteService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FavoriteController {

  @Autowired
  private FavoriteService favoriteService;

  @GetMapping("/users/{id}/posts/favs")
  public Object getFavorites(HttpServletRequest request,
                             @PathVariable(value="id") String uid) throws Exception {

   String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    List<String> postsFavorites = favoriteService.getPostsFavoritesByUser(uid);

    FavoriteResponse response = new FavoriteResponse(uid, postsFavorites);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @PostMapping("/users/{user_id}/posts/favs")
  public Object saveFavorite(HttpServletRequest request,
                             @PathVariable(value="user_id") String uid,
                             @RequestBody Post post) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    Favorite response = favoriteService.saveFavorite(uid, post.getId());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/users/{user_id}/posts/{post_id}/favs")
  public Object deleteFavorite(HttpServletRequest request,
                             @PathVariable(value="user_id") String uid,
                             @PathVariable(value="post_id") String postId) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    favoriteService.deleteFavorite(uid, postId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
