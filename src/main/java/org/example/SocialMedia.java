package org.example.Entities;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

//Posts are primarily sorted by timestamp in descending order.
//If two posts have the same timestamp, they are sorted by postId in descending order.

public class SocialMedia {
//    Pair<Long,Post> pairs;
    Map<Integer, User> userIDInforMap = new HashMap<Integer, User>();
    Map<Integer, Post> postIDInforMap = new HashMap<Integer, Post>();
//    PriorityQueue<Pair<Long,Post>> recentPosts = new PriorityQueue<>(Collections.reverseOrder());
    TreeSet<Post> recentPosts = new TreeSet<>(
            (p1, p2) -> {
                // Sort by time descending, fallback to postId for uniqueness
                int timeComparison = Long.compare(p2.getTime(), p1.getTime());
                return timeComparison != 0 ? timeComparison : Integer.compare(p2.getPostId(), p1.getPostId());
            }
    );
    static int id=1;//postID
    public static class Post{
//        private int postId;
//        private String content;
//        private int userId;
//        private long time;
//
//        public Post(int postId, String content, int userId, Long time) {
//            this.postId = postId;
//            this.content = content;
//            this.userId = userId;
//            this.time = time;
//        }
//        public int getPostId() {
//            return postId;
//        }
//        public void setPostId(int postId) {
//            this.postId = postId;
//        }
//        public String getContent() {
//            return content;
//        }
//        public void setContent(String content) {
//            this.content = content;
//        }
//        public int getUserId() {
//            return userId;
//        }
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//        public Long getTime() {
//            return time;
//        }
//        public void setTime(Long time) {
//            this.time = time;
//        }
    }
    public static class User {
        int userId;
        String name;
        List<Post> posts = new ArrayList<>();
        List<Integer> followers = new ArrayList<>();
        List<Integer> followings = new ArrayList<>();
//        PriorityQueue<Post> latestPosts = new PriorityQueue<>(); //newsFeed

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
    public void createUser(int userId, String name) {
        User user = new User(userId, name);
        user.getFollowings().add(userId);//follows himself
        userIDInforMap.put(userId, user);
    }
    public void createPost(int userID, String content){
        Post post = new Post(id, content, userID, System.currentTimeMillis());
        postIDInforMap.put(id, post);
        id++;

        User user = userIDInforMap.get(userID);
        user.getPosts().add(post);
        recentPosts.add(post);
    }
    public void deletePost(int userID, int postID){
        Post post = postIDInforMap.get(postID);
        User user = userIDInforMap.get(userID);
        if (post != null && post.getUserId() == userID) {
            user.getPosts().remove(post);
            postIDInforMap.remove(postID);
//            userIDInforMap.remove(userID);
            recentPosts.remove(post);
        }
    }
    public void follow(int followerID, int followeeID){
        User follower = userIDInforMap.get(followerID);
        User followee = userIDInforMap.get(followeeID);
        followee.getFollowers().add(followerID);
        follower.getFollowings().add(followeeID);
    }
    public List<Post> getPostsByThisUser(int userID){
        User user = userIDInforMap.get(userID);
        return user.getPosts();
    }
    public List<Post> getNewsFeed(int userID) {
        List<Post> newsFeed = new ArrayList<>();
        User user = userIDInforMap.get(userID);
        List<Integer> followedUsers = user.getFollowings();//user follows followedUsers
        for (Post post: recentPosts) {

            if (followedUsers.contains(post.getUserId())) {
                newsFeed.add(post);
            }
            if(newsFeed.size() > 10)
                break;
        }
        return newsFeed;
    }

    public static void main(String[] args) {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.createUser(1, "Alice");
        socialMedia.createUser(2, "Bob");

        socialMedia.createPost(1, "Post 1 from Alice");
        socialMedia.createPost(2, "Post 2 from Bob");
        socialMedia.createPost(1, "Post 3 from Alice");
        socialMedia.createPost(2, "Post 4 from Bob");
//        socialMedia.deletePost(1,1);
        socialMedia.follow(1,2);

        List<Post> news = socialMedia.getNewsFeed(1);
        System.out.println("News Feed of this user:" );
        for(Post p: news) {
            System.out.println(p.getContent());
        }
        System.out.println("\n");

        List<Post> personalPosts = socialMedia.getPostsByThisUser(1);
        System.out.println("Posts of this user:" );
        for(Post p: personalPosts) {
            System.out.println(p.getContent());
        }
    }
}
