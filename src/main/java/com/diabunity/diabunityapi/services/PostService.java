package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.*;
import com.diabunity.diabunityapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private FavoriteService favoriteService;

  @Autowired
  private ReactionService reactionService;

  public Post save(Post p) {
    postRepository.save(p);

    return p;
  }

  public PostResponse getPrincipalsPosts(int page, int size) {
    Pageable pageConfig = PageRequest.of(page, size,
        Sort.by(Sort.Direction.DESC, "timestamp"));

   Page<Post> posts = postRepository.findPostByParentIdIsNull(pageConfig);

   //set quantity of comments && favorites users for each post
   posts.get().forEach(post -> {
     post.setQtyComments(getChildPosts(post.getPostId()).getPosts().size());
     post.setUsersFavorites(favoriteService.getUsersFavoritesByPost(post.getPostId()));
   });

  return new PostResponse(posts.getContent(), posts.getTotalPages(), posts.getTotalElements());
  }

  public PostResponse getFavoritesPost(int page, int size, String userId) {
    Pageable pageConfig = PageRequest.of(page, size,
            Sort.by(Sort.Direction.DESC, "timestamp"));

    List<String> postFavorites = favoriteService.getPostsFavoritesByUser(userId);

    Page<Post> posts =  postRepository.findPostByPostIdIsIn(postFavorites, pageConfig);

    //List<UserReaction> userReactions = null;

    posts.get().forEach(post -> {
      post.setQtyComments(getChildPosts(post.getPostId()).getPosts().size());
      post.setUsersFavorites(favoriteService.getUsersFavoritesByPost(post.getPostId()));

      //get emojis for each post and set selected by user
      List<Emoji> listReactions = reactionService.getReactionsByPostId(post.getPostId());
      listReactions.stream().forEach(reaction -> {
          Optional<UserReaction> optionalEmoji = reactionService.getUserReactions(userId, post.getPostId(), reaction.getEmoji());
          if (optionalEmoji.isPresent()) {
            reaction.setSelected(true);
          }
      });
    });

    return new PostResponse(posts.getContent(), posts.getTotalPages(), posts.getTotalElements());

  }

  public PostResponse getChildPosts(String parentId) {
    List<Post> posts = postRepository.findPostByParentId(parentId, Sort.by(Sort.Direction.DESC, "timestamp"));

    return new PostResponse(posts, 0, 0);
  }

  public boolean delete(String postId, String userId) {
    Post postToDelete = postRepository.findPostByPostIdAndUserId(postId, userId);

    if (postToDelete == null) {
      return false;
    }

    postRepository.deletePostByPostIdAndUserId(postId, userId);
    return true;
  }

}
