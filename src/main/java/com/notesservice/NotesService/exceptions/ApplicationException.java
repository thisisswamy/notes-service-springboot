package com.notesservice.NotesService.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public ApplicationException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus =httpStatus;
        this.message = message;
    }
   public  ApplicationException(String message){
        super(message);
    }


}
