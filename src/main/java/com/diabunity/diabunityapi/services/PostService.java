package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.*;
import com.diabunity.diabunityapi.repositories.PostRepository;
import java.util.Optional;
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
  private FavoriteService favoriteService;

  @Autowired
  private ReactionService reactionService;

  public Post save(Post p) {
    postRepository.save(p);

    return p;
  }

  public PostResponse getPrincipalsPosts(int page, int size, String userId) {
    Pageable pageConfig = PageRequest.of(page, size,
        Sort.by(Sort.Direction.DESC, "timestamp"));

     Page<Post> posts = postRepository.findPostByParentIdIsNull(pageConfig);

   //set quantity of comments && favorites users for each post
   posts.get().forEach(post -> {
     post.setQtyComments(getChildPosts(post.getId()).getPosts().size());
     post.setUsersFavorites(favoriteService.getUsersFavoritesByPost(post.getId()));

     //get emojis for each post and set selected by user
     List<Emoji> listReactions = reactionService.getReactionsByPostId(post.getId());
     if (listReactions != null) {
       listReactions.stream().forEach(reaction -> {
         Optional<UserReaction> optionalEmoji = reactionService.getUserReactions(userId, post.getId(), reaction.getEmoji());
         if (optionalEmoji.isPresent()) {
           reaction.setSelected(true);
         }
         post.setEmojis(listReactions);
       });
     }
   });

    return new PostResponse(posts.getContent(), new Paging(posts.getTotalPages(), posts.getTotalElements()));
  }

  public PostResponse getFavoritesPost(int page, int size, String userId) {
    Pageable pageConfig = PageRequest.of(page, size,
            Sort.by(Sort.Direction.DESC, "timestamp"));

    List<String> postFavorites = favoriteService.getFavoritesPostsByUser(userId);

    Page<Post> posts =  postRepository.findPostByIdIsIn(postFavorites, pageConfig);

    //set quantity of comments && favorites users for each post
    posts.get().forEach(post -> {
      post.setQtyComments(getChildPosts(post.getId()).getPosts().size());
      post.setUsersFavorites(favoriteService.getUsersFavoritesByPost(post.getId()));

      //get emojis for each post and set selected by user
      List<Emoji> listReactions = reactionService.getReactionsByPostId(post.getId());
      if (listReactions != null) {
        listReactions.stream().forEach(reaction -> {
          Optional<UserReaction> optionalEmoji = reactionService.getUserReactions(userId, post.getId(), reaction.getEmoji());
          if (optionalEmoji.isPresent()) {
            reaction.setSelected(true);
          }
        });
      }
    });

    return new PostResponse(posts.getContent(), new Paging(posts.getTotalPages(), posts.getTotalElements()));

  }

  public PostResponse getChildPosts(String parentId) {
    List<Post> posts = postRepository.findPostByParentId(parentId, Sort.by(Sort.Direction.DESC, "timestamp"));

    return new PostResponse(posts, null);
  }

  public boolean delete(String postId, String userId) {
    Optional<Post> resultDelete = postRepository.deletePostByIdAndUserId(postId, userId);

    if (!resultDelete.isPresent()) {
      return false;
    }

    postRepository.deletePostByParentId(postId);
    return true;
  }

}
