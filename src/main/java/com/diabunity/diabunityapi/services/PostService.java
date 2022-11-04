package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.*;
import com.diabunity.diabunityapi.auth.UserAuthService;
import com.diabunity.diabunityapi.models.Paging;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.models.PostResponse;
import com.diabunity.diabunityapi.repositories.PostRepository;

import java.util.ArrayList;
import java.util.Optional;

import com.google.common.collect.Iterables;
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

    @Autowired
    private UserAuthService userAuthService;

    public Post save(Post p) {
        postRepository.save(p);
        return p;
    }

    public PostResponse getPrincipalsPosts(int page, int size, String userId) {
        Pageable pageConfig = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "timestamp"));

        Page<Post> posts = postRepository.findPostByParentIdIsNull(pageConfig);
        return buildPostsResponse(posts.getContent(), new Paging(posts.getTotalPages(), posts.getTotalElements()), userId);
    }

    public PostResponse getFavoritesPost(int page, int size, String userId) {
        Pageable pageConfig = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "timestamp"));

        List<String> postFavorites = favoriteService.getFavoritesPostsByUser(userId);
        Page<Post> posts =  postRepository.findPostByIdIsIn(postFavorites, pageConfig);

        return buildPostsResponse(posts.getContent(), new Paging(posts.getTotalPages(), posts.getTotalElements()), userId);
    }

    private PostResponse buildPostsResponse(List<Post> posts, Paging paging, String userIdRequest) {
        posts.forEach(post -> {
            post.setQtyComments(getChildPosts(post.getId()).getPosts().size());
            post.setUsersFavorites(favoriteService.getUsersFavoritesByPost(post.getId()));

            try {
                post.setUser(userAuthService.getUser(post.getUserId()).getDisplayName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            post.setEmojis(createReactionResponse(userIdRequest, post.getId()));
        });

        return new PostResponse(posts, paging);
    }

    private List<Reaction> createReactionResponse(String userId, String postId) {
        List<Reaction> reactionsList = reactionService.getReactionsByPost(postId);
        if (reactionsList == null) {
            return null;
        }
        List<Reaction> result = new ArrayList<>();
        reactionsList.stream().forEach(reaction -> {
            if (reaction.getUserId() == userId) {
                reaction.setSelected(true);
            }
            int indexInResponse = Iterables.indexOf(result, r -> r.getName().equals(reaction.getName()));
            if(indexInResponse >= 0) {
                Reaction reactionAux = result.get(indexInResponse);
                reactionAux.setIndex(reactionAux.getIndex() + 1);
            } else {
                reaction.setIndex(1);
                result.add(reaction);
            }
        });

        return result;
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
