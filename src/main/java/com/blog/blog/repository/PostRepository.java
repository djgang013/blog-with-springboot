package com.blog.blog.repository;

import org.springframework.stereotype.Repository;
import com.blog.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}