package com.notesservice.NotesService.users.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private Object reference;
    private String email;
    private String userName;


}
