package pl.oskarpolak.ormtest.models.services;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pl.oskarpolak.ormtest.models.UserModel;
import pl.oskarpolak.ormtest.models.UserType;
import pl.oskarpolak.ormtest.models.forms.RegisterForm;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;

import javax.websocket.server.ServerEndpoint;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {
    public enum RegisterStatus {
        OK, BUSY_LOGIN, ERROR;
    }

    final
    UserRepository userRepository;

    @Getter @Setter
    private boolean isLogin;
    @Getter @Setter
    private int badLoginCounter;
    @Getter @Setter
    private UserModel user;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public RegisterStatus register(RegisterForm registerForm){
        if(userRepository.existsByLogin(registerForm.getLogin())){
            return RegisterStatus.BUSY_LOGIN;
        }

        UserModel newUserModel = new UserModel(registerForm);
        newUserModel.setUserType(UserType.USER);

        userRepository.save(newUserModel);
        return RegisterStatus.OK;
    }
}
