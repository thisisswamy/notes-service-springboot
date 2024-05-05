package com.notesservice.NotesService.users.controllers;
import com.notesservice.NotesService.users.models.CreateUserRequest;
import com.notesservice.NotesService.users.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Object> save(@RequestBody CreateUserRequest createUserRequest){
        return userService.save(createUserRequest);
    }
    @GetMapping
    public ResponseEntity<Object>  getUsers(){
        return userService.getUsers();

    }

    //TODO
    public ResponseEntity<Object> edit(@PathVariable ObjectId id){
        return userService.edit(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> view(@PathVariable ObjectId id){
        return userService.view(id);
    }


}
