package com.notesservice.NotesService.users.utilities;


import com.notesservice.NotesService.users.entities.Role;
import com.notesservice.NotesService.users.entities.User;
import com.notesservice.NotesService.users.models.CreateUserRequest;
import com.notesservice.NotesService.users.models.UserModel;
import com.notesservice.NotesService.users.models.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserUtility {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User  mapToAppUser(CreateUserRequest model){
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Date date = Calendar.getInstance().getTime();
        roles.add(new Role("VIEW_USER", "read only user"));
        user.setRoles(roles);
        user.setUsername(model.getEmail());
        user.setEmail(model.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        user.setConfirmPassword(bCryptPasswordEncoder.encode(model.getConfirmPassword()));
        user.setAppUsername(model.getUsername());
        user.setCreatedAt(date);
        user.setModifiedAt(date);
        return user;
    }

    public UserModel mapResUser(User user){
        Date time = Calendar.getInstance().getTime();
        return new UserModel(user.getId(), user.getEmail(),user.getAppUsername(), time, time);
    }
    public UserProfile mapToUserProfile(User user){
        return new UserProfile(
                user.getId(),
                user.getAppUsername() ,
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

}
