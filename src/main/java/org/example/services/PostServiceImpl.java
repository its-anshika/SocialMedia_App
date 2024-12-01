package org.example.services;

import org.example.entities.Post;
import org.example.entities.User;

import java.util.*;

public class PostServiceImpl implements PostService {
    private final UserService userService; // Dependency on UserService
    private final Map<Integer, Post> postIDInforMap = new HashMap<>();
    private final TreeSet<Post> recentPosts = new TreeSet<>(
            (p1, p2) -> {
                int timeComparison = Long.compare(p2.getTime(), p1.getTime());
                return timeComparison != 0 ? timeComparison : Integer.compare(p2.getPostId(), p1.getPostId());
            }
    );

    private static int id = 1;

    public PostServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createPost(int userId, String content) {
        Post post = new Post(id, content, userId, System.currentTimeMillis());
        postIDInforMap.put(id, post);
        id++;
//        User user = userIDInforMap.get(userID);
//        user.getPosts().add(post);
        recentPosts.add(post);
    }

    @Override
    public void deletePost(int userId, int postId) {
        Post post = postIDInforMap.get(postId);
//        User user = userIDInforMap.get(userID);
        if (post != null && post.getUserId() == userId) {
//            user.getPosts().remove(post);
            postIDInforMap.remove(postId);
            recentPosts.remove(post);
        }
    }

    @Override
    public List<Post> getNewsFeed(int userId) {
        //Posts are primarily sorted by timestamp in descending order.
        //If two posts have the same timestamp, they are sorted by postId in descending order.

        List<Post> newsFeed = new ArrayList<>();
//        User user = userService.getUserbyId(userId);
        List<Integer> followedUsers = userService.getFollowings(userId);//user follows followedUsers

        for (Post post: recentPosts) {
            if (followedUsers.contains(post.getUserId())) {
                newsFeed.add(post);
            }
            if(newsFeed.size() > 10)
                break;
        }
        return newsFeed;

    }
}

