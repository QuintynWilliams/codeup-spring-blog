package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;

    public PostController(PostRepository postsDao){
        this.postsDao = postsDao;
    }

//    private UserRepository userDao;
//
//    public UserController(UserRepository userDao){
//        this.userDao = userDao;
//    }


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

/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
/*|<<>><<>><<>> SHOW POSTS <<>><<>><<>>|*/
/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/

//    @GetMapping("/posts/index")
//    public String allPosts(Model model) {
//        Post userPostOne = new Post("My First Post", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aliquid consectetur delectus dolore molestiae necessitatibus perferendis provident recusandae veritatis voluptas. At consequuntur doloribus, iste odit pariatur praesentium quas sequi suscipit?");
//        Post userPostTwo = new Post("My Second Post", "Aperiam atque dolore, facilis ipsum laborum reprehenderit tenetur! Corporis deleniti tenetur voluptate. Aliquid doloremque neque officiis pariatur possimus, provident repellat tenetur vitae? Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
//        List<Post> allPosts = new ArrayList<>(List.of(userPostOne, userPostTwo));
//        model.addAttribute("posts", allPosts);
//        return "/posts/index";
//    }
//
//    @GetMapping("/posts/show")
//    public String viewPost(Model model) {
//        Post userPost = new Post("My First Post", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aliquid consectetur delectus dolore molestiae necessitatibus perferendis provident recusandae veritatis voluptas. At consequuntur doloribus, iste odit pariatur praesentium quas sequi suscipit?");
//        model.addAttribute("post", userPost);
//        return "posts/show";
//    }
    @GetMapping("/posts")
    public String allPosts(Model model){
        List<Post> allPosts = postsDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        Post post = postsDao.findById(id);
        User user = post.getUser();
        model.addAttribute("user", user);
        model.addAttribute("post", post);
        return "posts/show";
    }


/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
/*|<<>><<>><<> CREATE POSTS <>><<>><<>>|*/
/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
    @GetMapping("/create")
    public String createPost(HttpSession session, Model model){
        if (session.getAttribute("user") == null) {
            return "/posts/login";
        }
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(HttpSession session,
                             @ModelAttribute Post post,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body){
        User user = (User) session.getAttribute("user");
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

}