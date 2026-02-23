package com.blog.blog.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.model.Post;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.UserRepository;

@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public PostController(PostRepository postRepository, UserRepository userRepository){
        this.postRepository =postRepository;
        this.userRepository = userRepository;
    }
   @GetMapping
   public List<Post> GetAllPosts() {
       return postRepository.findAll();
   }
   @GetMapping("/{id}")
   public ResponseEntity<Post> getPostById(@PathVariable Long id){
    return postRepository.findById(id).map(post ->ResponseEntity.ok(post)).orElse(ResponseEntity.notFound().build());
   }
   @PostMapping("/user/{userId}")
   public ResponseEntity<Post> createpost(@PathVariable Long userId,@RequestBody Post post){
   return userRepository.findById(userId).map(user->{
    post.setUser(user);
    return ResponseEntity.ok(postRepository.save(post));
   }).orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Post> createPostAuthenticated(@RequestBody Post post, Authentication authentication){
       String email = authentication.getName();
       return userRepository.findByEmail(email).map(user -> {
           post.setUser(user);
           return ResponseEntity.ok(postRepository.save(post));
       }).orElse(ResponseEntity.status(401).build());
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id){
        return postRepository.findById(id)
        .map(post ->{
            postRepository.delete(post);
            return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());}
   
   
}
