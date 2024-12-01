package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    int userId;
    String name;
    List<Post> posts = new ArrayList<>();
    List<Integer> followers = new ArrayList<>();
    List<Integer> followings = new ArrayList<>();

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public List<Integer> getFollowers() {
        return followers;
    }
    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }
    public List<Integer> getFollowings() {
        return followings;
    }
    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

}
