package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.ormtest.models.UserModel;
import pl.oskarpolak.ormtest.models.forms.RegisterForm;
import pl.oskarpolak.ormtest.models.repositories.PostRepository;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;
import pl.oskarpolak.ormtest.models.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class MainController {

    final
    UserRepository userRepository;

    final
    PostRepository postRepository;

    final
    UserService userService;


    @Autowired
    public MainController(UserRepository userRepository, PostRepository noteRepository, UserService userService) {
        this.userRepository = userRepository;
        this.postRepository = noteRepository;
        this.userService = userService;
    }

    @ModelAttribute
    public Model startModel(Model model){
        model.addAttribute("user", userService.getUser());
        return model;
    }



    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postRepository.findAllByOrderByIdDesc());
        return "dashboard";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam("login") String login,
                            @RequestParam("password") String password,
                            Model model){
        Optional<UserModel> exist = userRepository.findByLoginAndPassword(login, password);
        if(exist.isPresent()){
            userService.setLogin(true);
            userService.setUser(exist.get());
            return "redirect:/";
        }

        userService.setBadLoginCounter(userService.getBadLoginCounter() + 1);
        model.addAttribute("info", "Bad login or password");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("registerForm") RegisterForm registerForm,
                               Model model){
        if(!registerForm.getPasswordRepeat().equals(registerForm.getPassword())){
            model.addAttribute("info", "Hasła nie są takie same");
            return "register";
        }

        UserService.RegisterStatus registerStatus = userService.register(registerForm);
        if(registerStatus != UserService.RegisterStatus.OK){
            model.addAttribute("info", "Login zajęty");
            return "register";
        }


        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest requestHandler){
        userService.setLogin(false);
        requestHandler.changeSessionId();

        return "redirect:/login";
    }
}
