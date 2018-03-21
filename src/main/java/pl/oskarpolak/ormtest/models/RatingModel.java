package pl.oskarpolak.ormtest.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rating")
@NoArgsConstructor
public class RatingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "post_id")
    private int postId;

    public RatingModel(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
