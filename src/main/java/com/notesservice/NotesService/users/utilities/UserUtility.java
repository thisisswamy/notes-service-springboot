package com.notesservice.NotesService.users.utilities;


import com.notesservice.NotesService.users.entities.Role;
import com.notesservice.NotesService.users.entities.User;
import com.notesservice.NotesService.users.models.CreateUserRequest;
import com.notesservice.NotesService.users.models.UserModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserUtility {


    public User  mapToAppUser(CreateUserRequest model){
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("VIEW_USER", "read only user"));
        user.setRoles(roles);
        user.setUsername(model.getEmail());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        user.setConfirmPassword(model.getConfirmPassword());
        user.setAppUsername(model.getUsername());
        return user;
    }

    public UserModel mapResUser(User user){
        return new UserModel(user.getId(), user.getEmail(), user.getAppUsername());
    }
}
