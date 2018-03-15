package pl.oskarpolak.ormtest.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "category")
public class CategoryModel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
}
