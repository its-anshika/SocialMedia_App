package org.example.entities;

public class Post {
    private int postId;
    private String content;
    private int userId;
    private long time;

    public Post(int postId, String content, int userId, Long time) {
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.time = time;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
}
