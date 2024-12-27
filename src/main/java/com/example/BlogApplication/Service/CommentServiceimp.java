package com.example.BlogApplication.Service;

import com.example.BlogApplication.Entity.Comments;
import com.example.BlogApplication.Entity.Post;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payload.CommentDto;
import com.example.BlogApplication.Payload.PostDto;
import com.example.BlogApplication.Repositor.CommentsRepository;
import com.example.BlogApplication.Repositor.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CommentServiceimp implements CommentService{

    private CommentsRepository commentsRepository;

    private PostRepository postRepository;

    private ModelMapper modelMapper;
     @Autowired
    public CommentServiceimp(CommentsRepository commentsRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
         this.modelMapper = modelMapper;
     }


    @Override
    public CommentDto CreateComment(CommentDto commentDto, Long id) {

        Post PID =postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("POST","PostId",id));

        Comments comments= this.modelMapper.map(commentDto, Comments.class);
        comments.setPost(PID);

        Comments updatedComment = commentsRepository.save(comments);

        return this.modelMapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public void Delete(Long cid) {
        Comments byId = commentsRepository.findById(cid).orElseThrow(()->new ResourceNotFoundException("Comment","commetId",cid));
        commentsRepository.delete(byId);
    }
}
