package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.Favorite;
import com.diabunity.diabunityapi.services.FavoriteService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.models.PostResponse;

import com.diabunity.diabunityapi.services.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FavoriteController {

  @Autowired
  private FavoriteService favoriteService;

  @Autowired
  private PostService postService;

  @GetMapping("/users/{id}/posts/favs")
  public Object getFavorites(HttpServletRequest request,
                             @RequestParam(value = "page", required=false, defaultValue = "0") int page,
                             @RequestParam(value = "size", required=false, defaultValue = "10") int size) throws Exception {

      String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
      PostResponse response = postService.getFavoritesPost(page, size, authorizedUser);
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

  @DeleteMapping("/users/{user_id}/favs/posts/{post_id}")
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
