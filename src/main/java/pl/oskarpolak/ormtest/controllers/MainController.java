package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.ormtest.models.UserModel;
import pl.oskarpolak.ormtest.models.forms.RegisterForm;
import pl.oskarpolak.ormtest.models.repositories.NoteRepository;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;
import pl.oskarpolak.ormtest.models.services.UserService;

import java.util.Optional;

@Controller
public class MainController {

    final
    UserRepository userRepository;

    final
    NoteRepository noteRepository;

    final
    UserService userService;


    @Autowired
    public MainController(UserRepository userRepository, NoteRepository noteRepository, UserService userService) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("notes", noteRepository.findAllByUserId(userService.getUserId()));
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
            userService.setUserId(exist.get().getId());
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

        userService.setLogin(true);
        return "redirect:/";
    }
}
