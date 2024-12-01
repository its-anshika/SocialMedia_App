package org.example.services;

import org.example.entities.Post;

import java.util.List;

public interface PostService {
    void createPost(int userId, String content);
    void deletePost(int userId, int postId);
    List<Post> getNewsFeed(int userId);
}

