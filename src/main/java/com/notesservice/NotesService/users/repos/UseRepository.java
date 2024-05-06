package com.notesservice.NotesService.users.repos;

import com.notesservice.NotesService.users.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UseRepository  extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
