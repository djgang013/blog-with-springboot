package com.blog.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  long id;
    private  String name;
    private String email;
    @OneToMany(mappedBy ="user",cascade=CascadeType.ALL)
    private List<Post> posts;
    
}
