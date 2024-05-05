package com.notesservice.NotesService.notes.repo;

import com.notesservice.NotesService.notes.entity.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepository extends MongoRepository<Note, String> {
//    Note findById(Object id);
}
