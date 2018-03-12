package pl.oskarpolak.ormtest.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note")
@Data
@NoArgsConstructor
public class NoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_id")
    private int userId;
    private String message;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;
}
