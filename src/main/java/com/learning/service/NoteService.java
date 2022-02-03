package com.learning.service;

import com.learning.exception.ResourceNotFoundException;
import com.learning.model.Note;
import com.learning.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getNotes() {
       return noteRepository.findAll();
    }

    public Note save(Note note) {
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());
        return noteRepository.save(note);
    }

    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    public Note update(Long noteId, Note noteDetails) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setUpdatedAt(new Date());
        return noteRepository.save(note);
    }

    public boolean delete(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);;

        return true;
    }

    public void test(Long noteId) {
        Note note = null;
        if("test".equalsIgnoreCase(note.getTitle())) {
            /**
             *
             * Write block of code
             *
             */
        }

    }

}
