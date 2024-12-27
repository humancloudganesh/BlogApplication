package com.example.BlogApplication.Responce;

import com.example.BlogApplication.Payload.PostDto;

import java.util.List;

public class PostResponse {

    List<PostDto> Content;

    int pageNumber;

    int pageSize;

    Integer totalPages;

    Boolean isLast;

    public List<PostDto> getContent() {
        return Content;
    }

    public void setContent(List<PostDto> content) {
        Content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public PostResponse(List<PostDto> content, Integer pageNumber, int pageSize, int totalPages, Boolean isLast) {
        Content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public PostResponse() {
    }
}
