package org.example.services;

import org.example.entities.User;

import java.util.List;

public interface UserService {
    User getUserById(int userId);
    List<Integer> getFollowings(int userId);
    void createUser(int userId, String name);
    void follow(int followerId, int followeeId);

//    public List<Post> getPostsByThisUser(int userID)
}
