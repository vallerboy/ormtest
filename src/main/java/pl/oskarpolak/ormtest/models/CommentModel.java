package pl.oskarpolak.ormtest.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostModel post;

    private String message;
    private LocalDateTime creationTime;

    public Date getDateAsJavaUtil(){
        return Date.from(creationTime.toInstant(ZoneOffset.UTC));
    }
}
