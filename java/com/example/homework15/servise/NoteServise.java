package com.example.homework15.servise;

import com.example.homework15.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServise {

    private final List<Note> notes = new ArrayList<>();

    public List<Note> listAll() {
        return notes;
    }
    public Note add(Note note) {
        note.setId(generateUniqueId());
        notes.add(note);
        return note;
    }
    public void update(Note updatedNote) {
        Optional<Note> existingNote = notes.stream()
                .filter(note -> note.getId().equals(updatedNote.getId()))
                .findFirst();

        if (existingNote.isPresent()) {
            Note noteToUpdate = existingNote.get();
            noteToUpdate.setTitle(updatedNote.getTitle());
            noteToUpdate.setContent(updatedNote.getContent());
        } else {
            throw new IllegalArgumentException("Note not found with id: " + updatedNote.getId());
        }
    }
    public Note getById(long id) {
        Optional<Note> foundNote = notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();

        return foundNote.orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + id));
    }
    private Long generateUniqueId() {
        return System.currentTimeMillis();
    }
}
