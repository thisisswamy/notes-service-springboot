package com.notesservice.NotesService.users.service;

import com.notesservice.NotesService.exceptions.ApplicationException;
import com.notesservice.NotesService.users.entities.User;
import com.notesservice.NotesService.users.models.CreateUserRequest;
import com.notesservice.NotesService.users.models.UserModel;
import com.notesservice.NotesService.users.repos.UseRepository;
import com.notesservice.NotesService.users.utilities.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UseRepository useRepository;

    private final UseRepository userRepository;

    private final UserUtility userUtility;


    public ResponseEntity<Object> save(CreateUserRequest createUserRequest){
        User exitedUser = userRepository.findByEmail(createUserRequest.getEmail());
        if(exitedUser !=null){
            throw new ApplicationException("User email already exist !!", HttpStatus.BAD_REQUEST);
        }
        User user = userUtility.mapToAppUser(createUserRequest);
        User savedUser = userRepository.save(user);
        UserModel resUser = userUtility.mapResUser(savedUser);
        return ResponseEntity.ok(resUser);
    }
}
