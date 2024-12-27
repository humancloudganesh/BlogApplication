package com.example.BlogApplication.Service;

import com.example.BlogApplication.Payload.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    public CommentDto CreateComment(CommentDto commentDto,Long id);

    public void Delete(Long cid);
}
