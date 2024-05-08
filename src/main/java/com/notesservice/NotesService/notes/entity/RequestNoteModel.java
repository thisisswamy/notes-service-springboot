package com.notesservice.NotesService.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RequestNoteModel {
    private String title;
    private String notesText;
    private Object userId;
}
