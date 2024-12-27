package com.example.BlogApplication.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String CategoryTitle;

    private String CategoryDescription;

    @OneToMany(mappedBy ="category" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryTitle() {
        return CategoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        CategoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Category(long id, String categoryTitle, String categoryDescription, List<Post> posts) {
        this.id = id;
        CategoryTitle = categoryTitle;
        CategoryDescription = categoryDescription;
        this.posts = posts;
    }

    public Category() {
    }
}
