package com.notesservice.NotesService.notes.controllers;


import com.notesservice.NotesService.notes.entity.Note;
import com.notesservice.NotesService.notes.entity.RequestNoteModel;
import com.notesservice.NotesService.notes.service.NotesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class NotesController {

    private final NotesService notesService;


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RequestNoteModel note){
        return notesService.save(note);

    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Note note){
        return notesService.update(note);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> search(@PathVariable String id){
        return notesService.search(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> view(@PathVariable String id){
        return notesService.view(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        return notesService.delete(id);
    }

    @DeleteMapping("/all")
    public String deleteAll(){
        return notesService.deleteAll();
    }



}

