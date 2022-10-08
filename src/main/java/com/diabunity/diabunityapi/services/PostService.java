package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.auth.UserAuthService;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.models.PostResponse;
import com.diabunity.diabunityapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserAuthService userAuthService;

  public Post save(Post p) {
    postRepository.save(p);

    return p;
  }

  public PostResponse getPrincipalsPosts(int page, int size) {
    Pageable pageConfig = PageRequest.of(page, size,
        Sort.by(Sort.Direction.DESC, "timestamp"));

   Page<Post> posts = postRepository.findPostByParentIdIsNull(pageConfig);

   //set quantity of comments && username for each post
   posts.get().forEach(post -> {
     post.setQtyComments(getChildPosts(post.getId()).getPosts().size());
     post.setUsername(userAuthService.getUserNameById(post.getUserId()));
   });

  return new PostResponse(posts.getContent(), posts.getTotalPages(), posts.getTotalElements());
  }

  public PostResponse getChildPosts(String parentId) {
    List<Post> posts = postRepository.findPostByParentId(parentId, Sort.by(Sort.Direction.DESC, "timestamp"));

    //set quantity of username for each comment
    posts.forEach(post -> {
      post.setUsername(userAuthService.getUserNameById(post.getUserId()));
    });

    return new PostResponse(posts, 0, 0);
  }

  public boolean delete(String id, String userId) {
    Post postToDelete = postRepository.findPostByIdAndUserId(id, userId);

    if (postToDelete == null) {
      return false;
    }

    postRepository.deletePostByIdAndUserId(id, userId);
    return true;
  }

}
