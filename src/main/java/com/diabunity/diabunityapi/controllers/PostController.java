package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.BadRequestException;
import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.models.PostResponse;
import com.diabunity.diabunityapi.models.PostsQuantity;
import com.diabunity.diabunityapi.models.UserInfo;
import com.diabunity.diabunityapi.services.PostService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/users/{id}/posts")
    public Object createPost(HttpServletRequest request,
                             @PathVariable(value = "id") String uid,
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

        if (!postService.isUserAllowedToPost(uid)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        post.setId(UUID.randomUUID().toString());
        post.setTimestamp(LocalDateTime.now());

        UserInfo userInfo = new UserInfo(uid);
        post.setUserInfo(userInfo);

        Post postSaved = postService.save(post);

        return new ResponseEntity<>(postSaved, HttpStatus.CREATED);
    }

    //get principal posts
    @GetMapping("/posts")
    public Object getPosts(HttpServletRequest request,
                           @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                           @RequestParam(value = "count", required = false, defaultValue = "false") boolean count) throws Exception {

        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

        if (count) {
            PostsQuantity postsQty = new PostsQuantity(authorizedUser,
                    postService.countPostsByUserId(authorizedUser));
            return new ResponseEntity<>(postsQty, HttpStatus.OK);
        }

        PostResponse response = postService.getPrincipalsPosts(page, size, authorizedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get comments
    @GetMapping("/posts/{post_id}")
    public Object getChildPosts(@PathVariable(value = "post_id") String postId) {
        PostResponse response = postService.getChildPostsDecorated(postId);

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

        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

}
