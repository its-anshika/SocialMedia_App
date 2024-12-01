package org.example;

import org.example.services.PostService;
import org.example.services.UserService;
import org.example.entities.Post;

import java.util.List;

public class SocialMedia {
    private final PostService postService;
    private final UserService userService;

    public SocialMedia(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    public void createUser(int userId, String name) {
        userService.createUser(userId, name);
    }

    public void createPost(int userId, String content) {
        postService.createPost(userId, content);
    }

    public void deletePost(int userId, int postId) {
        postService.deletePost(userId, postId);
    }

    public void follow(int followerId, int followeeId) {
        userService.follow(followerId, followeeId);
    }

    public List<Post> getNewsFeed(int userId) {
        return postService.getNewsFeed(userId);
    }
}
