package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.BadRequestException;
import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.models.PostResponse;
import com.diabunity.diabunityapi.services.PostService;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  @Autowired
  private PostService postService;

  @PostMapping("/users/{id}/posts")
  public Object createPost(HttpServletRequest request,
                             @PathVariable(value="id") String uid,
                             @Valid @RequestBody Post post,
                             BindingResult errors) throws Exception {

    if (errors.hasErrors()) {
      throw new BadRequestException("Parameters required but not found",
          errors.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.toList()));
    }

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    post.setPostId(UUID.randomUUID().toString());
    post.setTimestamp(LocalDateTime.now());
    post.setUserId(uid);

    Post postSaved = postService.save(post);

    return new ResponseEntity<>(postSaved, HttpStatus.CREATED);
  }

  //get principal posts
  @GetMapping("/posts")
  public Object getPosts(HttpServletRequest request,
                         @RequestParam(value = "page", required=false, defaultValue = "0") int page,
                         @RequestParam(value = "size", required=false, defaultValue = "10") int size) throws Exception {

      PostResponse  response = postService.getPrincipalsPosts(page, size);

      return new ResponseEntity<>(response, HttpStatus.OK);
  }

  //get comments
  @GetMapping("/posts/{post_id}")
  public Object getChildPosts(@PathVariable(value = "post_id") String postId) throws Exception {
    PostResponse response = postService.getChildPosts(postId);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  //get favorites posts
  @GetMapping("/users/{id}/posts/favs")
  public Object getFavoritesPosts(HttpServletRequest request,
                         @PathVariable(value = "id") String uid,
                         @RequestParam(value = "page", required=false, defaultValue = "0") int page,
                         @RequestParam(value = "size", required=false, defaultValue = "10") int size) throws Exception {

    PostResponse response = postService.getFavoritesPost(page, size, uid);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/users/{id}/posts/{post_id}")
  public Object deletePost(HttpServletRequest request,
                           @PathVariable(value = "id") String uid,
                           @PathVariable(value = "post_id") String postId) throws Exception {

   String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    boolean result = postService.delete(postId, uid);

    return new ResponseEntity<>(result? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
  }

}