package pl.oskarpolak.ormtest.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oskarpolak.ormtest.models.forms.PostForm;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    private String message;
    private String title;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "post")
    List<CommentModel> commentModels;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryModel category;

    public PostModel(PostForm postForm){
        this.message = postForm.getMessage();
        this.title = postForm.getTitle();
    }
}
