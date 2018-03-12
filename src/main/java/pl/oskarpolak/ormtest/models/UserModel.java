package pl.oskarpolak.ormtest.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oskarpolak.ormtest.models.forms.RegisterForm;

import javax.persistence.*;

@Entity
@Table(name = "test")
@Data
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    //@Column(name = "jakasNazwaKolumnyZBazyDanych")
    private int age;

    public UserModel(RegisterForm registerForm){
        login = registerForm.getLogin();
        password = registerForm.getPassword();
        age = registerForm.getAge();
    }
}
