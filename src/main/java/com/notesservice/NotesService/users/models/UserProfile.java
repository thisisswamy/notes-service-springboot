package com.notesservice.NotesService.users.models;

import com.notesservice.NotesService.users.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserProfile{
    private Object id;
    private String username;
    private String email;
    private Set<Role> roles;
    private Date createdAt;
    private Date modifiedAt;
}
