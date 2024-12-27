package com.example.BlogApplication.Repositor;

import com.example.BlogApplication.Entity.Category;
import com.example.BlogApplication.Entity.Post;
import com.example.BlogApplication.Entity.User;
import com.example.BlogApplication.Payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    public List<Post> findPostByUser(User User);

    public List<Post> findPostByCategory(Category Category);

    public List<Post> findByTitleContaining(String Title);
}
