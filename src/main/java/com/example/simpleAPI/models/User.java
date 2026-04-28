package com.example.simpleAPI.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Note> noteList;
}
