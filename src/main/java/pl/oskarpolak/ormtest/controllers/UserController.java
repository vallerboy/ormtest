package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarpolak.ormtest.models.services.UploadService;
import pl.oskarpolak.ormtest.models.services.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class UserController {

    final UserService userService;
    final UploadService uploadService;

    @Autowired
    public UserController(UserService userService, UploadService uploadService) {
        this.userService = userService;
        this.uploadService = uploadService;
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

    @PostMapping("/user/avatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file,
                               RedirectAttributes model){
        if(!isValidContentType(file.getContentType())){
            model.addFlashAttribute("info", "Nie przyjmujemy takich plików");
            return "redirect:/profile";
        }
        try {
            uploadService.upload(file.getBytes(), userService.getUser().getLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/profile";
    }

    private boolean isValidContentType(String content){
        content = content.toUpperCase();
        return content.contains("JPEG") || content.contains("JPG");
    }
}

