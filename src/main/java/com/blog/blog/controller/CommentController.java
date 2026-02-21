package com.blog.blog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.model.Comment;
import com.blog.blog.repository.CommentRepository;
import com.blog.blog.repository.PostRepository;


@RequestMapping("/api/comments")
@RestController

public class CommentController {
  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  public CommentController(CommentRepository commentRepository,  PostRepository postRepository) {
    this.commentRepository = commentRepository;
 
    this.postRepository = postRepository;
  }

  @GetMapping
  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }
  @GetMapping("/{id}")
  public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
      return commentRepository.findById(id).map(comment->ResponseEntity.ok(comment)).orElse(ResponseEntity.notFound().build());
  }
  @PostMapping("/post/{postId}")
  public ResponseEntity<Comment> createComment(@PathVariable Long postId,@RequestBody Comment comment) {
      
      
      return postRepository.findById(postId).map(post->{
        comment.setPost(post);
        return ResponseEntity.ok(commentRepository.save(comment));
      }).orElse(ResponseEntity.notFound().build());
  }
   @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteComment(@PathVariable Long id){
    return commentRepository.findById(id).map(comment->{
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }).orElse(ResponseEntity.notFound().build());
}
}
