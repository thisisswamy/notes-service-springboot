package com.notesservice.NotesService.notes.service;


import com.notesservice.NotesService.exceptions.ApplicationException;
import com.notesservice.NotesService.notes.entity.Note;
import com.notesservice.NotesService.notes.entity.RequestNoteModel;
import com.notesservice.NotesService.notes.repo.NotesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;
    public ResponseEntity<Object> save(RequestNoteModel note) {
        Note userNote= new Note();
        try{
            Date date = Calendar.getInstance().getTime();
            userNote.setNotesText(note.getNotesText());
            userNote.setTitle(note.getTitle());
            userNote.setUserId(note.getUserId());
            userNote.setWrittendate(date);
            userNote.setLabel(note.getLabel());
            userNote = notesRepository.save(userNote);
        }catch (Exception e){
            throw new ApplicationException("Failed to save notes !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(userNote);
    }

    public ResponseEntity<Object> update(Note note) {
        Date date = Calendar.getInstance().getTime();
        note.setWrittendate(date);
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

    public String deleteAll() {
        try{
            notesRepository.deleteAll();
            return "Deleted all notes";
        }catch (Exception e){
            return "Failed to delete all";
        }
    }

}
