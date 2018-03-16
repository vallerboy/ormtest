package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.oskarpolak.ormtest.models.services.UserService;

@Controller
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public Model addModel(Model model){
        model.addAttribute("user", userService.getUser());
        return model;
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }


}

