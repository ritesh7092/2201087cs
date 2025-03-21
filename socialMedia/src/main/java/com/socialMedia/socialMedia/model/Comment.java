package com.socialMedia.socialMedia.model;

public class Comment {
    private int id;
    private int postid;
    private String content;

    public Comment() {}

    public Comment(int id, int postid, String content) {
        this.id = id;
        this.postid = postid;
        this.content = content;
    }

    // getters, setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPostid() {
        return postid;
    }
    public void setPostid(int postid) {
        this.postid = postid;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

