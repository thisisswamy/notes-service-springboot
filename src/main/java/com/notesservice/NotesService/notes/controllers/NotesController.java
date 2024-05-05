package com.notesservice.NotesService.notes.controllers;


import com.notesservice.NotesService.notes.entity.Note;
import com.notesservice.NotesService.notes.service.NotesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
@CrossOrigin
public class NotesController {

    private final NotesService notesService;


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Note note){
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


}

