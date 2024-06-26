package com.notesservice.NotesService.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthenticationLogger {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    //Cross-Concern
    //              <method-return-type> . <method name with packages>                                . <method-name>
    //                 |                                 |                                                 |  /Method arguments
    @Before("execution(* com.notesservice.NotesService.authentication.controllers.AuthenticationController.*(..))")
    public void authAuthLogger(JoinPoint jp){ //JoinPoint - holds the object of aspect
        System.err.println("Logger Auth"+ jp.toString());
    }

    
}
