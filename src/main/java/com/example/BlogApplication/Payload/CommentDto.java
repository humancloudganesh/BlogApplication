package com.example.BlogApplication.Payload;

public class CommentDto {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public CommentDto(String content) {
        this.content = content;
    }

    public CommentDto() {
    }
}
