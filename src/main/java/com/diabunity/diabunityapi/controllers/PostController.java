package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.BadRequestException;
import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.exceptions.NotFoundException;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.services.PostService;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  @Autowired
  private PostService postService;

  @PostMapping("/users/{id}/posts")
  public Object createPost(HttpServletRequest request,
                             @PathVariable(value="id") String uid,
                             @RequestBody Post post,
                             BindingResult errors) throws Exception {

    if (errors.hasErrors()) {
      throw new BadRequestException("Parameters required but not found",
          errors.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.toList()));
    }

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    Post postSaved = postService.save(post);

    return new ResponseEntity<>(postSaved, HttpStatus.CREATED);
  }

  @GetMapping("/users/{id}/posts/{id_post}")
  public Object getPost(HttpServletRequest request,
                           @PathVariable(value="id") String uid,
                           @PathVariable(value="id_post") String postId,
                           BindingResult errors) throws Exception {

    if (errors.hasErrors()) {
      throw new BadRequestException("Parameters required but not found",
          errors.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.toList()));
    }

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    Optional<Post> post = postService.get(postId);

    if (!post.isPresent()) {
      throw new NotFoundException("Post not found with id: " + postId);
    }

    return new ResponseEntity<>(post.get(), HttpStatus.OK);
  }

}
