package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.repositories.PostRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Post save(Post p) {
    postRepository.save(p);

    return p;
  }

  public Optional<Post> get(String id) {
    return postRepository.findById(id);
  }

}
