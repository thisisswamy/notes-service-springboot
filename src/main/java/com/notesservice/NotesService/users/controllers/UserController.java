package com.notesservice.NotesService.users.controllers;
import com.notesservice.NotesService.users.models.CreateUserRequest;
import com.notesservice.NotesService.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    public ResponseEntity<Object> save(@RequestBody CreateUserRequest createUserRequest){
        return userService.save(createUserRequest);
    }


}
