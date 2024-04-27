package com.notesservice.NotesService.users.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Role implements GrantedAuthority {

    private String roleName;
    private String roleDescription;

    @Override
    public String getAuthority() {
        return null;
    }
}
