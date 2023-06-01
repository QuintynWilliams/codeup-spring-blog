package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Comment;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.CommentRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;
    private UserRepository usersDao;
    private CommentRepository commentsDao;

    public PostController(PostRepository postsDao, UserRepository usersDao, CommentRepository commentsDao){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.commentsDao = commentsDao;
    }


/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>> SHOW POST ><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/

    @GetMapping("/")
    public String allPosts(Model model) {
        List<Post> allPosts = postsDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model) {
        Post post = postsDao.findById(id);
        User user = post.getUser();
        model.addAttribute("user", user);
        model.addAttribute("post", post);
        return "posts/show";
}

    @PostMapping("/posts/comment")
    public String submitComment(HttpSession session,
                                @RequestParam(name="content") String content,
                                @RequestParam(name="postId") long postId,
                                RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        Post post = postsDao.findById(postId);
        Comment comment = new Comment(content, user, post);
        commentsDao.save(comment);
        redirectAttributes.addAttribute("Id", postId);
        return "redirect:/{Id}";
    }

/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<> CREATE POST <<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
    @GetMapping("/create")
    public String createPost(HttpSession session, Model model) {
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
                             @RequestParam(name = "body") String body) {
        User user = (User) session.getAttribute("user");
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }


/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>> EDIT POST ><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
    @GetMapping("/{id}/edit")
    public String editPost(HttpSession session,
                           @PathVariable long id,
                           Model model) {
        if (session.getAttribute("user") == null) {
            return "/posts/login";
        }
        Post post = postsDao.findById(id);
        model.addAttribute("thingpost", post);
        return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String submitEditPost(HttpSession session,
                                 @ModelAttribute Post thingpost,
                                 @RequestParam(name = "title") String title,
                                 @RequestParam(name = "body") String body) {
        User user = (User) session.getAttribute("user");
        thingpost.setUser(user);
        thingpost.setTitle(title);
        thingpost.setBody(body);
        postsDao.save(thingpost);
        return "redirect:/posts";
    }

}