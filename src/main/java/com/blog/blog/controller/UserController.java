package com.blog.blog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;


@RequestMapping("/api/user")
@RestController
public class UserController {
    
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository =userRepository;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserBYId(@PathVariable Long id){
         return userRepository.findById(id).map(user ->ResponseEntity.ok().body(user)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
       return userRepository.save(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userDetails){
        return userRepository.findById(id)
        .map(user ->{
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }).orElse(ResponseEntity.notFound().build());}
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        return userRepository.findById(id)
        .map(user ->{
            userRepository.delete(user);
            return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());}
    
}
