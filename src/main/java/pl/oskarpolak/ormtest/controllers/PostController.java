package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.ormtest.models.forms.PostForm;
import pl.oskarpolak.ormtest.models.repositories.PostRepository;

@Controller
public class PostController {

    final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/addpost")
    public String addPost(Model model){
        model.addAttribute("postForm", new PostForm());
        return "addpost";
    }

    @PostMapping("/addpost")
    public String addPost(@ModelAttribute("postForm") PostForm postForm){
         //todo validate form

    }
    //test



}
