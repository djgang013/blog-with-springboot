package com.blog.blog.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog.model.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{

}