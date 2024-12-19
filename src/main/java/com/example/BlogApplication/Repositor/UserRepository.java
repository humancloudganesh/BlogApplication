package com.example.BlogApplication.Repositor;

import com.example.BlogApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
