package com.notesservice.NotesService.authentication.controllers;

import com.notesservice.NotesService.authentication.jwt.JwtUtilService;
import com.notesservice.NotesService.authentication.models.AuthenticationRequestModel;
import com.notesservice.NotesService.authentication.models.AuthenticationResponseModel;
import com.notesservice.NotesService.authentication.service.AuthenticationService;
import com.notesservice.NotesService.exceptions.ApplicationException;
import com.notesservice.NotesService.users.entities.User;
import com.notesservice.NotesService.users.repos.UseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtilService jwtUtilService;

    private final AuthenticationService authenticationService;

    private final UseRepository useRepository;


    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequestModel authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        var userByEmail = useRepository.findByEmail(authRequest.getUsername())
                        .orElseThrow(()-> new ApplicationException("User not found !",HttpStatus.BAD_REQUEST));

        String accessToken = jwtUtilService.generateToken(userByEmail);
        String refreshToken = jwtUtilService.generateRefreshToken(userByEmail);
        userByEmail.setTokenExpired(false);
        return ResponseEntity.ok(
                new AuthenticationResponseModel(
                        accessToken,refreshToken
                )
        );

    }
}
