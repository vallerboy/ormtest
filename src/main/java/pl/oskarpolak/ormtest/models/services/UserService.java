package pl.oskarpolak.ormtest.models.services;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.oskarpolak.ormtest.models.forms.RegisterForm;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;

import javax.websocket.server.ServerEndpoint;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {
    public enum RegisterStatus {
        OK, INVALID_NICKNAME, ERROR;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    final
    UserRepository userRepository;

    @Getter @Setter
    private boolean isLogin;

    public RegisterStatus register(RegisterForm registerForm){

    }
}
