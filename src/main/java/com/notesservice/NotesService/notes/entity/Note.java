package com.notesservice.NotesService.notes.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private Date writtendate;
    private String label;

}
