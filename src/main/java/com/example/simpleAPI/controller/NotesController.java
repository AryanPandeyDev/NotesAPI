package com.example.simpleAPI.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @GetMapping("/getUserNotes/{userId}")
    List<String> getNotes( @PathVariable  int userId) {

    }
}
