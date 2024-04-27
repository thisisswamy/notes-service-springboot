package com.notesservice.NotesService.users.repos;

import com.notesservice.NotesService.users.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseRepository  extends MongoRepository<User, Object> {
    User findByEmail(String email);
}
