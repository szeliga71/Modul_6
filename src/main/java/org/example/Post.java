package org.example;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Post {


    private long userId;
    private long id;
    private String title;
    private String body;

    @JsonCreator
    public Post(@JsonProperty("userId") long userId, @JsonProperty("id") long id, @JsonProperty("title") String title, @JsonProperty("body") String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post() {
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


    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Post post))
            return false;
        if (o == null || getClass() != o.getClass())
            return false;
        return getUserId() == post.getUserId() &&
                getId() == post.getId() &&
                Objects.equals(getTitle(), post.getTitle()) &&
                Objects.equals(getBody(), post.getBody());
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(getUserId());
        result = 31 * result + Long.hashCode(getId());
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getBody());
        return result;
    }
    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
