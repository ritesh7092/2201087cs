package com.socialMedia.socialMedia.controller;

import com.socialMedia.socialMedia.model.Post;
import com.socialMedia.socialMedia.model.User;
import com.socialMedia.socialMedia.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final SocialMediaService socialMediaService;

    @Autowired
    public AnalyticsController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("/top-users")
    public List<User> getTopUsers(@RequestParam(defaultValue = "5") int limit) {
        return socialMediaService.getTopUsersByPostCount(limit);
    }

    @GetMapping("/top-posts")
    public List<Post> getTopPosts(@RequestParam(defaultValue = "5") int limit) {
        return socialMediaService.getTopPostsByCommentCount(limit);
    }
}

