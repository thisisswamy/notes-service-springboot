package com.notesservice.NotesService.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UrlPathHelper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    private  final Map<String, String> data = new HashMap<>();

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handle(HttpServletRequest request, HttpServletResponse responseApplicationException, ApplicationException applicationException){

        String path = new UrlPathHelper().getPathWithinApplication(request);
        LocalDateTime current = LocalDateTime.now();
        data.put("status", applicationException.getHttpStatus().toString());
        data.put("timestamp", current.toString());
        data.put("message", applicationException.getMessage());
        data.put("url", path);
        return ResponseEntity.badRequest().body(data);
    }
}
