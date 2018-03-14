package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.ormtest.models.PostModel;
import pl.oskarpolak.ormtest.models.forms.PostForm;
import pl.oskarpolak.ormtest.models.repositories.PostRepository;
import pl.oskarpolak.ormtest.models.services.UserService;

@Controller
public class PostController {

    final PostRepository postRepository;
    final UserService userService;

    @Autowired
    public PostController(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @GetMapping("/addpost")
    public String addPost(Model model){
        model.addAttribute("postForm", new PostForm());
        return "addpost";
    }

    //todo validate form
    @PostMapping("/addpost")
    public String addPost(@ModelAttribute("postForm") PostForm postForm){
        PostModel postModel = new PostModel(postForm);
        postModel.setUserId(userService.getUserId());

        postRepository.save(postModel);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String onePost(@PathVariable("id") int id,
                          Model model){
        model.addAttribute("post", postRepository.findOne(id));
        return "post";
    }



}
