package com.socialMedia.socialMedia.service;

import com.socialMedia.socialMedia.model.Comment;
import com.socialMedia.socialMedia.model.Post;
import com.socialMedia.socialMedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SocialMediaService {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://20.244.56.144/test";

    @Autowired
    public SocialMediaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<User> getAllUsers() {
        String url = BASE_URL + "/users";
        UsersResponse response = restTemplate.getForObject(url, UsersResponse.class);
        if (response == null || response.getUsers() == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(response.getUsers());
    }


    public List<Post> getPostsByUser(int userId) {
        String url = BASE_URL + "/users/" + userId + "/posts";
        try {
            ResponseEntity<PostsResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, PostsResponse.class);
            if (response.getBody() == null || response.getBody().getPosts() == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(response.getBody().getPosts());
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }

    public List<Comment> getCommentsForPost(int postId) {
        String url = BASE_URL + "/posts/" + postId + "/comments";
        CommentsResponse response = restTemplate.getForObject(url, CommentsResponse.class);
        if (response == null || response.getComments() == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(response.getComments());
    }

    public List<User> getTopUsersByPostCount(int topN) {
        List<User> users = getAllUsers();
        Map<Integer, Long> userIdToPostCount = users.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        u -> (long) getPostsByUser(u.getId()).size()
                ));
        return users.stream()
                .sorted((u1, u2) -> Long.compare(
                        userIdToPostCount.get(u2.getId()),
                        userIdToPostCount.get(u1.getId())))
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<Post> getTopPostsByCommentCount(int topN) {
        List<User> users = getAllUsers();
        List<Post> allPosts = users.stream()
                .flatMap(u -> getPostsByUser(u.getId()).stream())
                .collect(Collectors.toList());
        Map<Integer, Long> postIdToCommentCount = allPosts.stream()
                .collect(Collectors.toMap(
                        Post::getId,
                        p -> (long) getCommentsForPost(p.getId()).size()
                ));
        return allPosts.stream()
                .sorted((p1, p2) -> Long.compare(
                        postIdToCommentCount.get(p2.getId()),
                        postIdToCommentCount.get(p1.getId())))
                .limit(topN)
                .collect(Collectors.toList());
    }

    static class UsersResponse {
        private User[] users;

        public User[] getUsers() {
            return users;
        }
        public void setUsers(User[] users) {
            this.users = users;
        }
    }

    static class PostsResponse {
        private Post[] posts;

        public Post[] getPosts() {
            return posts;
        }
        public void setPosts(Post[] posts) {
            this.posts = posts;
        }
    }

    static class CommentsResponse {
        private Comment[] comments;

        public Comment[] getComments() {
            return comments;
        }
        public void setComments(Comment[] comments) {
            this.comments = comments;
        }
    }
}
