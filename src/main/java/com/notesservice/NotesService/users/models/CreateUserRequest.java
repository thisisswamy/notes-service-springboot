package com.notesservice.NotesService.users.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreateUserRequest {

    private String username;
    private String  email;
    private String password;
    private String confirmPassword;
}
