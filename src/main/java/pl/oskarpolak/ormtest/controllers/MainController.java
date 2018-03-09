package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.oskarpolak.ormtest.models.UserModel;
import pl.oskarpolak.ormtest.models.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    @ResponseBody
    public String index(){
        List<UserModel> userModels = userRepository.findByAgeGreaterThan(20);

        return userModels.toString();
    }

}
