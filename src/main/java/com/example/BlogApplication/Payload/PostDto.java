package com.example.BlogApplication.Payload;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PostDto implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String title;

    private String content;

    private String imageNme;

    private Date addeddate;

    private UserDto user;

    private CategoryDto category;

    private List<CommentDto> comments;

    public PostDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageNme() {
        return imageNme;
    }

    public void setImageNme(String imageNme) {
        this.imageNme = imageNme;
    }

    public Date getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(Date addeddate) {
        this.addeddate = addeddate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public PostDto(long id, String title, String content, String imageNme, Date addeddate, UserDto user, CategoryDto category, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageNme = imageNme;
        this.addeddate = addeddate;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }
}
