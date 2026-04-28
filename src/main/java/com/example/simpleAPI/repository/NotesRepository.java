package com.example.simpleAPI.repository;


import com.example.simpleAPI.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {


    Note findNoteById(Long id);

    List<Note> findByUserId(Long userId);
}
