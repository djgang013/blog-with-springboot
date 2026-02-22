package com.blog.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import com.blog.blog.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User request){
    return userRepository.findByEmail(request.getEmail())
    .map(user ->{
        if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
            String token =jwtService.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).build();
    })
    .orElse(ResponseEntity.status(401).build());
}

}
