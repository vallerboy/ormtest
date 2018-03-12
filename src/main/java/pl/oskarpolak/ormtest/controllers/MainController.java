package pl.oskarpolak.ormtest.controllers;


import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.ormtest.models.UserModel;
import pl.oskarpolak.ormtest.models.forms.RegisterForm;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;
import pl.oskarpolak.ormtest.models.services.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {

    final
    UserRepository userRepository;

    final
    UserService userService;

    @Autowired
    public MainController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
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
        boolean exist = userRepository.existsByLoginAndPassword(login, password);
        if(exist){
            userService.setLogin(true);
            return "redirect:/";
        }
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


    }
}
