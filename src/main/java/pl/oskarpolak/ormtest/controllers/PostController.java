package pl.oskarpolak.ormtest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.ormtest.models.CommentModel;
import pl.oskarpolak.ormtest.models.PostModel;
import pl.oskarpolak.ormtest.models.RatingModel;
import pl.oskarpolak.ormtest.models.UserType;
import pl.oskarpolak.ormtest.models.forms.PostForm;
import pl.oskarpolak.ormtest.models.repositories.CategoryRepository;
import pl.oskarpolak.ormtest.models.repositories.CommentRepository;
import pl.oskarpolak.ormtest.models.repositories.PostRepository;
import pl.oskarpolak.ormtest.models.repositories.RatingRepository;
import pl.oskarpolak.ormtest.models.services.UserService;

import javax.transaction.Transactional;

@Controller
public class PostController {

    final PostRepository postRepository;
    final UserService userService;
    final CommentRepository commentRepository;
    final CategoryRepository categoryRepository;
    final RatingRepository ratingRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserService userService, CommentRepository commentRepository, CategoryRepository categoryRepository, RatingRepository ratingRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.categoryRepository = categoryRepository;
        this.ratingRepository = ratingRepository;
    }


    @ModelAttribute
    public Model startModel(Model model){
        model.addAttribute("user", userService.getUser());
        return model;
    }

    @GetMapping("/addpost")
    public String addPost(Model model){
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("categories", categoryRepository.findAll());

        return "addpost";
    }

    //todo validate form
    @PostMapping("/addpost")
    public String addPost(@ModelAttribute("postForm") PostForm postForm){
        PostModel postModel = new PostModel(postForm);
        postModel.setUser(userService.getUser());
        postModel.setCategory(categoryRepository.findByName(postForm.getCategory()));

        postRepository.save(postModel);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String onePost(@PathVariable("id") int id,
                          Model model){
        model.addAttribute("post", postRepository.findOne(id));

        return "post";
    }

    @PostMapping("/comment/{id}")
    public String oneComment(@RequestParam("comment") String comment,
                             @PathVariable("id") int id){

        if(comment != null && !comment.isEmpty()){
            CommentModel commentModel = new CommentModel();
            commentModel.setMessage(comment);

            PostModel postModel = new PostModel();
            postModel.setId(id);

            commentModel.setPost(postModel);
            commentModel.setUser(userService.getUser());

            commentRepository.save(commentModel);
        }

        return "redirect:/post/"+id;
    }


    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") int id){
        if(userService.getUser().getUserType() != UserType.ADMIN){
            return "redirect:/";
        }

        postRepository.delete(id);
        return "redirect:/";
    }

    @GetMapping("/delete/{postId}/{commentId}")
    public String deletePost(@PathVariable("postId") int postId,
                             @PathVariable("commentId") int commentId){
        if(userService.getUser().getUserType() != UserType.ADMIN){
            return "redirect:/post/" + postId;
        }

        commentRepository.delete(commentId);
        return "redirect:/post/" + postId;
    }


    @GetMapping("/rating/add/{postId}")
    @Transactional
    public String addRate(@PathVariable("postId") int postId){
        if(ratingRepository.existsByUserIdAndPostId(userService.getUser().getId(), postId)){
           return "redirect:/";
        }

        PostModel postModel = postRepository.findOne(postId);
        postModel.setRating(postModel.getRating() + 1);
        postRepository.save(postModel);

        RatingModel ratingModel = new RatingModel(userService.getUser().getId(), postId);
        ratingRepository.save(ratingModel);

        return "redirect:/";
    }

    @GetMapping("/rating/reduce/{postId}")
    @Transactional
    public String reduceRate(@PathVariable("postId") int postId){
        if(ratingRepository.existsByUserIdAndPostId(userService.getUser().getId(), postId)){
            return "redirect:/";
        }

        PostModel postModel = postRepository.findOne(postId);
        postModel.setRating(postModel.getRating() - 1);
        postRepository.save(postModel);

        RatingModel ratingModel = new RatingModel(userService.getUser().getId(), postId);
        ratingRepository.save(ratingModel);

        return "redirect:/";
    }

}
