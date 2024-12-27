package com.example.BlogApplication.Controller;

import com.example.BlogApplication.Payload.CommentDto;
import com.example.BlogApplication.Responce.ApiResponse;
import com.example.BlogApplication.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionUsageException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentCotroller {

    private CommentService commentService;
    @Autowired
    public CommentCotroller(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{id}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        CommentDto commentDto1= commentService.CreateComment(commentDto, id);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{cid}")
    public ResponseEntity<?> deleteComment(@PathVariable Long cid) {
        commentService.Delete(cid);
        return new ResponseEntity<>(new ApiResponse("DELETED SUCCESSFULLY",true), HttpStatus.OK);
    }
}
