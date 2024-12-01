package org.example.services;

import org.example.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final Map<Integer, User> userIDInforMap = new HashMap<>();

    @Override
    public User getUserById(int userId){
        return userIDInforMap.get(userId);
    }

    @Override
    public List<Integer> getFollowings(int userId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        return user.getFollowings();
    }

    @Override
    public void createUser(int userId, String name) {
        User user = new User(userId, name);
        user.getFollowings().add(userId);//follows himself
        userIDInforMap.put(userId, user);
    }

    @Override
    public void follow(int followerId, int followeeId) {
        User follower = userIDInforMap.get(followerId);
        User followee = userIDInforMap.get(followeeId);
        if (follower != null && followee != null) {
            follower.getFollowings().add(followeeId);
        }
    }
}

