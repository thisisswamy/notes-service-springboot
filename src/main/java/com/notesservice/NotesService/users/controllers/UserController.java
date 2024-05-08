package com.notesservice.NotesService.users.controllers;
import com.notesservice.NotesService.users.models.CreateUserRequest;
import com.notesservice.NotesService.users.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
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
    public ResponseEntity<Object> edit(@PathVariable String id){
        return userService.edit(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> view(@PathVariable String id){
        return userService.view(id);
    }
    @GetMapping("/userBy/{email}")
    public ResponseEntity<Object> viewByMail(@PathVariable String email){
        return userService.viewByMail(email);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllUsers(){
        return userService.deleteAllUsers();
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(){
        return userService.profile();

    }




}
