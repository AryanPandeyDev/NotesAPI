package com.example.simpleAPI.service;


import com.example.simpleAPI.models.Note;
import com.example.simpleAPI.models.User;
import com.example.simpleAPI.repository.NotesRepository;
import com.example.simpleAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Note> getNotesByUserId(Long userId) {
        return notesRepository.findByUserId(userId);
    }

    public Note getNoteById(Long id) {
        return notesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }


    public Note saveNote(Note note) {
        return notesRepository.save(note);
    }

    public Note deleteNote(Long id) {
        Note note = notesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        notesRepository.deleteById(id);
        return note;
    }

    public Note updateNote(Long id, Note newNote) {
        Note existing = notesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        existing.setTitle(newNote.getTitle());
        existing.setContent(newNote.getContent());

        return notesRepository.save(existing);
    }
}
