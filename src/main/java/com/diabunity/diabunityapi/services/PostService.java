package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.auth.UserAuthService;
import com.diabunity.diabunityapi.aws.FileService;
import com.diabunity.diabunityapi.models.*;
import com.diabunity.diabunityapi.plans.ConfigurationPlan;
import com.diabunity.diabunityapi.plans.configs.IConfigurationPlan;
import com.diabunity.diabunityapi.repositories.PostRepository;
import com.google.common.collect.Iterables;
import com.google.firebase.auth.UserRecord;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ConfigurationPlan configurationPlan;

    public Post save(Post p) {
        if (p.getUserInfo().getImagePath() != null && !p.getUserInfo().getImagePath().isEmpty()) {
            p.getUserInfo().setImagePath(fileService.upload(p.getUserInfo().getImagePath(), p.getId()));
        }
        return postRepository.save(p);
    }

    public boolean delete(String postId, String userId) {
        UserInfo userInfo = new UserInfo(userId);
        Optional<Post> resultDelete = postRepository.deletePostByIdAndUserInfo(postId, userInfo);

        if (!resultDelete.isPresent()) {
            return false;
        }

        postRepository.deletePostByParentId(postId);
        return true;
    }

    public PostResponse getPrincipalsPosts(int page, int size, String userId) {
        Pageable pageConfig = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "timestamp"));

        Page<Post> posts = postRepository.findPostByParentIdIsNull(pageConfig);
        return decoratePosts(posts.getContent(), new Paging(posts.getTotalPages(), posts.getTotalElements()), userId);
    }

    public PostResponse getFavoritesPost(int page, int size, String userId) {
        Pageable pageConfig = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "timestamp"));

        List<String> postFavorites = favoriteService.getFavoritesPostsByUser(userId);
        Page<Post> posts = postRepository.findPostByIdIsIn(postFavorites, pageConfig);

        return decoratePosts(posts.getContent(), new Paging(posts.getTotalPages(), posts.getTotalElements()), userId);
    }

    public PostResponse getChildPosts(String parentId) {
        List<Post> posts = fetchChildPosts(parentId);
        return new PostResponse(posts, null);
    }

    public PostResponse getChildPostsDecorated(String parentId) {
        List<Post> posts = fetchChildPosts(parentId);
        posts.forEach(post -> {
            try {
                UserRecord userFirebase = (userAuthService.getUser(post.getUserInfo().getUserId()));
                post.setUserInfo(new UserInfo(userFirebase.getDisplayName(),
                                userFirebase.getPhotoUrl()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return new PostResponse(posts, null);
    }

    private PostResponse decoratePosts(List<Post> posts, Paging paging, String userIdRequest) {
        posts.forEach(post -> {
            post.setQtyComments(getChildPosts(post.getId()).getPosts().size());
            post.setUsersFavorites(favoriteService.getUsersFavoritesByPost(post.getId()));

            try {
                UserRecord userFirebase = (userAuthService.getUser(post.getUserInfo().getUserId()));
                post.setUserInfo(new UserInfo(userFirebase.getDisplayName(),
                        userFirebase.getPhotoUrl()));
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
            if (reaction.getUserId().equals(userId)) {
                reaction.setSelected(true);
            }

            int indexInResponse = Iterables.indexOf(result, r -> r.getName().equals(reaction.getName()));
            if (indexInResponse >= 0) {
                Reaction reactionAux = result.get(indexInResponse);
                reactionAux.setIndex(reactionAux.getIndex() + 1);
                if(reaction.isSelected()) {
                    reactionAux.setSelected(true);
                }
            } else {
                reaction.setIndex(1);
                result.add(reaction);
            }
        });

        return result;
    }

    private List<Post> fetchChildPosts(String parentId) {
        return postRepository.findPostByParentId(parentId, Sort.by(Sort.Direction.DESC, "timestamp"));
    }

    public boolean isUserAllowedToPost(String userId) {
        IConfigurationPlan plan =  configurationPlan.getConfiguration(false);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),0,0);
        LocalDateTime todayFinish = todayStart.withHour(23).withMinute(59);
        int postOfDayCount = postRepository.findAllByUserIdAndTimestampBetween(userId,todayStart, todayFinish).size();

        if (postOfDayCount >= plan.getMaxPostsAllowedOfTheDay()) {
            return false;
        }

        return true;
    }

}
