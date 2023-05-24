package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

 //    TODO: GET	/posts	posts index page
    @GetMapping("/posts")
    @ResponseBody
    public String allPosts() {
        return "posts index page";
    }

 //    TODO: GET	/posts/{id}	view an individual post
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost(@PathVariable long id) {
        return "view an individual post";
    }

 //    TODO: GET	/posts/create	view the form for creating a post
    @GetMapping("/posts/create")
    @ResponseBody
    public String writePost() {
        return "view the form for creating a post" + "<form method=POST href='/posts/create'>" +
                "<input type='submit'></input>" + "</form>";
    }

 //    TODO: POST	/posts/create	create a new post
    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String submitPost() {
        return "created a post";
    }


}