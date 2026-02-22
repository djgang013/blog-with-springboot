package com.blog.blog.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog.model.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}