package com.example.BlogApplication.Service;

import com.example.BlogApplication.Entity.Category;
import com.example.BlogApplication.Entity.Post;
import com.example.BlogApplication.Entity.User;
import com.example.BlogApplication.Payload.PostDto;
import com.example.BlogApplication.Responce.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {

    PostDto CreatePost(PostDto postDto,long UserId,long CategoryId);

    PostDto UpdatePost(PostDto postDto,long postId);

    PostDto GetById(long postDto);

    PostResponse GetAllPost(Integer pageNumber, Integer pageSize,String sort,String direction);

    List<PostDto> FindPostByUser(long userId);

    List<PostDto> FindPostByCategory(long categoryId);

    void DeletePost(long postId);

    List<PostDto> Search(String Title);
}
