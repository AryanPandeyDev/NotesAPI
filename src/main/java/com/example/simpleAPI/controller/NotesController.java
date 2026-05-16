package com.example.simpleAPI.controller;


import com.example.simpleAPI.dto.NoteDTO;
import com.example.simpleAPI.models.Note;
import com.example.simpleAPI.service.NotesService;
import com.example.simpleAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @Autowired
    private UserService userService;

    @GetMapping("/getUserNotes/{userId}")
    ResponseEntity<List<Note>> getUserNotes(@PathVariable  Long userId) {
        List<Note> notes = notesService.getNotesByUserId(userId);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("getNote/{noteId}")
    ResponseEntity<?> getNote(@PathVariable Long noteId) {
        try {
            Note note = notesService.getNoteById(noteId);
            return ResponseEntity.ok(note);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }


    @PostMapping("/saveUserNote/{userId}")
    ResponseEntity<Note> saveUserNote(@Valid @RequestBody NoteDTO noteDTO, @PathVariable Long userId) {
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getTitle());
        notesService.saveNote(userId,note);
        return ResponseEntity.ok(note);
    }


    @DeleteMapping("/deleteNote/{noteId}")
    ResponseEntity<?> deleteNote(@PathVariable Long noteId) {
        try {
            Note note = notesService.deleteNote(noteId);
            return ResponseEntity.ok(note);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/updateNote/{noteId}")
    ResponseEntity<?> updateNote(@Valid @RequestBody NoteDTO noteDTO, @PathVariable Long noteId) {
        try {
            Note note = new Note();
            note.setTitle(noteDTO.getTitle());
            note.setContent(noteDTO.getContent());
            return ResponseEntity.ok(notesService.updateNote(noteId,note));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
