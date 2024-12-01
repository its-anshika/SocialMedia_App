package org.example;

 import java.util.List;
 import java.util.Scanner;

 import org.example.SocialMedia;
 import org.example.entities.Post;

import org.example.services.PostService;
import org.example.services.PostServiceImpl;
import org.example.services.UserService;
import org.example.services.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to Facebook News Feed!");

        // Initialize services
        UserService userService = new UserServiceImpl();
        PostService postService = new PostServiceImpl(userService);

        // Create SocialMedia instance with injected services
        SocialMedia socialMedia = new SocialMedia(postService, userService);

        // Create users
        socialMedia.createUser(1, "Alice");
        socialMedia.createUser(2, "Bob");

        // Create posts
        socialMedia.createPost(1, "Post 1 from Alice");
        socialMedia.createPost(2, "Post 2 from Bob");
        socialMedia.createPost(1, "Post 3 from Alice");
        socialMedia.createPost(2, "Post 4 from Bob");

        // Follow users
        socialMedia.follow(1, 2);
        socialMedia.follow(2, 1);

        // Using Scanner to take user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID to fetch the news feed:");
        int userId = scanner.nextInt();

        // Get and print the news feed for Alice
        List<Post> newsFeed = postService.getNewsFeed(userId);
        if (newsFeed.isEmpty()) {
            System.out.println("No posts available for this user.");
        }
        else {
            System.out.println("News Feed for User ID " + userId + ":");
            for (Post post : newsFeed) {
                System.out.println(post.getContent());
            }
        }

        // Delete a post by Alice
        socialMedia.deletePost(1, 1);

        // Get and print the news feed after post deletion
        newsFeed = socialMedia.getNewsFeed(1);
        System.out.println("\nAfter deleting Post 1 from Alice:");
        for (Post post : newsFeed) {
            System.out.println(post.getContent());
        }
    }
}
