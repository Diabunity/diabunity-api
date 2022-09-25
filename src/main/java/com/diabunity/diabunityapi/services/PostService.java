package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.models.PostResponse;
import com.diabunity.diabunityapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Post save(Post p) {
    postRepository.save(p);

    return p;
  }

  public PostResponse getPrincipalsPosts(int page, int size) {
    Pageable pageConfig = PageRequest.of(page, size,
        Sort.by(Sort.Direction.DESC, "timestamp"));

   Page<Post> posts = postRepository.findPostByParentIdIsNull(pageConfig);

  return new PostResponse(posts.getContent(), posts.getTotalPages(), posts.getTotalElements());
  }

  public PostResponse getChildPosts(String parentId, int page, int size) {
    Pageable pageConfig = PageRequest.of(page, size,
        Sort.by(Sort.Direction.DESC, "timestamp"));

    Page<Post> posts = postRepository.findPostByParentId(parentId, pageConfig);

    return new PostResponse(posts.getContent(), posts.getTotalPages(), posts.getTotalElements());
  }

  public void delete(String id) {
    postRepository.deleteById(id);
  }

}
