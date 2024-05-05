package com.notesservice.NotesService.notes.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    private String id;
    private String title;
    private String notesText;
    private Object userId;

}
