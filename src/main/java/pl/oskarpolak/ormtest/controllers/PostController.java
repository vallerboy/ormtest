package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.ormtest.models.CommentModel;
import pl.oskarpolak.ormtest.models.PostModel;
import pl.oskarpolak.ormtest.models.forms.PostForm;
import pl.oskarpolak.ormtest.models.repositories.CommentRepository;
import pl.oskarpolak.ormtest.models.repositories.PostRepository;
import pl.oskarpolak.ormtest.models.services.UserService;

@Controller
public class PostController {

    final PostRepository postRepository;
    final UserService userService;
    final CommentRepository commentRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserService userService, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
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
        postModel.setUser(userService.getUser());

        postRepository.save(postModel);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String onePost(@PathVariable("id") int id,
                          Model model){
        model.addAttribute("post", postRepository.findOne(id));
        model.addAttribute("comments", commentRepository.findByPostIdOrderByIdDesc(id));

        return "post";
    }

    @PostMapping("/comment/{id}")
    public String oneComment(@RequestParam("comment") String comment,
                             @PathVariable("id") int id){

        if(comment != null && !comment.isEmpty()){
            CommentModel commentModel = new CommentModel();
            commentModel.setMessage(comment);
            commentModel.setPostId(id);
            commentModel.setUser(userService.getUser());

            commentRepository.save(commentModel);
        }

        return "redirect:/post/"+id;
    }



}
