package com.example.BlogApplication.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String title;

    private String content;

    private String imageNme;

    private Date addeddate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy ="post",cascade = CascadeType.ALL)
    private List<Comments> comments;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Post(long id, String title, String content, String imageNme, Date addeddate, User user, Category category, List<Comments> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageNme = imageNme;
        this.addeddate = addeddate;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }

    public Post() {
    }
}
