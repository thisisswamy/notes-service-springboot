package com.notesservice.NotesService.authentication.service;


import com.notesservice.NotesService.authentication.jwt.JwtUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtilService jwtUtilService;



}
