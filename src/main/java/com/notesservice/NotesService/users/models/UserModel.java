package com.notesservice.NotesService.users.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String reference;
    private String email;
    private String userName;
    private List<String> roles;
    private Date createdAt;
    private Date modifiedAt;


}
