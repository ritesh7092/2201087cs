package com.socialMedia.socialMedia.controller;

import com.socialMedia.socialMedia.model.Post;
import com.socialMedia.socialMedia.model.User;
import com.socialMedia.socialMedia.model.Comment;
import com.socialMedia.socialMedia.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return socialMediaService.getAllUsers();
    }


    @GetMapping("/users/{userId}/posts")
    public List<Post> getPostsByUser(@PathVariable int userId) {
        return socialMediaService.getPostsByUser(userId);
    }


    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getCommentsForPost(@PathVariable int postId) {
        return socialMediaService.getCommentsForPost(postId);
    }
}

