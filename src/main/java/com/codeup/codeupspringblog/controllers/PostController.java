package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Coffee;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

////  TODO: GET	/posts	posts index page
//    @GetMapping("/posts")
//    @ResponseBody
//    public String allPosts() {
//        return "posts index page";
//    }
//
////  TODO: GET	/posts/{id}	view an individual post
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String viewPost(@PathVariable long id) {
//        return "view an individual post" + id;
//    }
//
////  TODO: GET	/posts/create	view the form for creating a post
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String writePost() {
//        return "view the form for creating a post" +
//                "<form method=POST href='/posts/create'>" +
//                "<input type='submit'></input>" +
//                "</form>";
//    }
//
////  TODO: POST	/posts/create	create a new post
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String submitPost() {
//        return "created a post";
//    }

//  TODO: GET   view all posts
    @GetMapping("/posts/index")
    public String allPosts(Model model) {
        Post userPostOne = new Post("My First Post", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aliquid consectetur delectus dolore molestiae necessitatibus perferendis provident recusandae veritatis voluptas. At consequuntur doloribus, iste odit pariatur praesentium quas sequi suscipit?");
        Post userPostTwo = new Post("My Second Post", "Aperiam atque dolore, facilis ipsum laborum reprehenderit tenetur! Corporis deleniti tenetur voluptate. Aliquid doloremque neque officiis pariatur possimus, provident repellat tenetur vitae? Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
        List<Post> allPosts = new ArrayList<>(List.of(userPostOne, userPostTwo));
        model.addAttribute("posts", allPosts);
        return "/posts/index";
    }

//  TODO: GET   view one post
    @GetMapping("/posts/show")
    public String viewPost(Model model) {
        Post userPost = new Post("My First Post", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aliquid consectetur delectus dolore molestiae necessitatibus perferendis provident recusandae veritatis voluptas. At consequuntur doloribus, iste odit pariatur praesentium quas sequi suscipit?");
        model.addAttribute("post", userPost);
        return "posts/show";
    }



}