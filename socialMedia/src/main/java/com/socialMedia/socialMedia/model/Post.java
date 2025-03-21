package com.socialMedia.socialMedia.model;

public class Post {
    private int id;
    private int userid;
    private String content;

    public Post() {}

    public Post(int id, int userid, String content) {
        this.id = id;
        this.userid = userid;
        this.content = content;
    }

    // getters, setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
