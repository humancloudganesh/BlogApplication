package com.example.BlogApplication.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {

    private long id;

    @NotEmpty(message = "Category title required !!")
    @Size(min = 3,max = 12,message = "min 3 characters and max 12 characters !!")
    private String CategoryTitle;

    @NotEmpty(message = "category require description")
    private String CategoryDescription;

    public CategoryDto(long id, String categoryTitle, String categoryDescription) {
        this.id = id;
        CategoryTitle = categoryTitle;
        CategoryDescription = categoryDescription;
    }

    public CategoryDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public  String getCategoryTitle() {
        return CategoryTitle;
    }

    public void setCategoryTitle( String categoryTitle) {
        CategoryTitle = categoryTitle;
    }

    public  String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }
}
