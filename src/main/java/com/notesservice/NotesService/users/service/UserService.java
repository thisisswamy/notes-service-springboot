package com.notesservice.NotesService.users.service;

import com.notesservice.NotesService.exceptions.ApplicationException;
import com.notesservice.NotesService.users.entities.User;
import com.notesservice.NotesService.users.models.CreateUserRequest;
import com.notesservice.NotesService.users.models.UserModel;
import com.notesservice.NotesService.users.models.UserProfile;
import com.notesservice.NotesService.users.repos.UseRepository;
import com.notesservice.NotesService.users.utilities.UserUtility;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService {


    private final UseRepository userRepository;

    private final UserUtility userUtility;



    public ResponseEntity<Object> save(CreateUserRequest createUserRequest){
//        Optional<User> exitedUser = userRepository.findByEmail(createUserRequest.getEmail());
//        if(exitedUser.isEmpty()){
//            throw new ApplicationException("User email already exist !!", HttpStatus.BAD_REQUEST);
//
//        }
        User user = userUtility.mapToAppUser(createUserRequest);
        User savedUser = userRepository.save(user);
        UserModel resUser = userUtility.mapResUser(savedUser);
        return ResponseEntity.ok(resUser);
    }

    public ResponseEntity<Object> getUsers() {
        List<UserModel> allUsers = userRepository.findAll().stream().map(userUtility::mapResUser).collect(Collectors.toList());
        return ResponseEntity.ok(allUsers);
    }


    //TODO
    public ResponseEntity<Object> edit(ObjectId id) {
        Optional<User> userById = userRepository.findById(id);
        if(userById.isPresent()){

        }
        return null;
    }

    public ResponseEntity<Object> view(ObjectId id) {
        Optional<User> userById = userRepository.findById(id);
        if(userById.isEmpty()){
            throw new ApplicationException("User not found",HttpStatus.NOT_FOUND);
        }
        UserProfile userProfile = userUtility.mapToUserProfile(userById.get());
        return ResponseEntity.ok(userProfile);
    }
}
