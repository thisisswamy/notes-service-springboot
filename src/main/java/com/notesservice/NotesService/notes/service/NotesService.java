package com.notesservice.NotesService.notes.service;


import com.notesservice.NotesService.exceptions.ApplicationException;
import com.notesservice.NotesService.notes.entity.Note;
import com.notesservice.NotesService.notes.repo.NotesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;
    public ResponseEntity<Object> save(Note note) {
//        Optional<Note> existedNote = notesRepository.findById(note.getId());
//        if(existedNote.isPresent()){
//            throw new ApplicationException("Note already existed !", HttpStatus.BAD_REQUEST);
//        }
        Note note1=null;
        try{
           note1 = notesRepository.save(note);
        }catch (Exception e){
            throw new ApplicationException("Failed to save notes !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(note1);
    }

    public ResponseEntity<Object> update(Note note) {
        Note save = notesRepository.save(note);
        return ResponseEntity.ok(save);
    }

    public ResponseEntity<Object> search(String id) {
        List<Note> notesList = notesRepository.findAll()
                .stream().filter(note->note.getUserId().equals(id)).collect(Collectors.toList());
        return ResponseEntity.ok(notesList);
    }

    public ResponseEntity<Object> view(String id) {
        Optional<Note> note = notesRepository.findById(id);
        return ResponseEntity.ok(note.isPresent() ? note.get() : "");
    }

    public ResponseEntity<Object> delete(String id) {
        try{
            notesRepository.deleteById(id);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
        return ResponseEntity.ok("Deleted.");
    }
}
