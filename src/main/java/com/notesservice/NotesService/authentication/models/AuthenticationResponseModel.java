package com.notesservice.NotesService.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthenticationResponseModel {

    private  String accessToken;
    private  String refreshToken;


}
