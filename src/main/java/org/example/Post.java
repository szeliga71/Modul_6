package org.example;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {



    private long userId;
    private long id;
    private String title;
    private String body;

    @JsonCreator
    public Post(@JsonProperty("userId")long userId, @JsonProperty("id")long id, @JsonProperty("title")String title, @JsonProperty("body")String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userID) {
        this.userId = userID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
