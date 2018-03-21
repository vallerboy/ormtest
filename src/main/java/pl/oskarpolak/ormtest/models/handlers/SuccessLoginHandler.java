package pl.oskarpolak.ormtest.models.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;
import pl.oskarpolak.ormtest.models.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    final UserService userService;
    final UserRepository repository;

    @Autowired
    public SuccessLoginHandler(UserService userService, UserRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }

    @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);

            userService.setLogin(true);
            userService.setUser(repository.findByLogin(authentication.getName()));
            System.out.println("Login: " + SecurityContextHolder.getContext().getAuthentication().getName());
        }
}
