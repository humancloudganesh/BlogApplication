package com.example.BlogApplication.Repositor;

import com.example.BlogApplication.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
}
