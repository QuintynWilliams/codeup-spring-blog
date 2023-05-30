package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.AttributedString;
import java.util.List;

@Controller
public class UserController {

    private UserRepository userDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }


/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
/*|<<>><<>><<>> USER LOGIN <<>><<>><<>>|*/
/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session){
        if (session.getAttribute("user") == null) {
            return "/posts/login";
        }
        return "redirect:/posts";
    }

    @PostMapping("/login")
    public String userLogin(HttpSession session,
                            @RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password){
        User user = userDao.findByUsername(username);

        if (user == null) {
            return "redirect:/login";
        } else if (!user.getPassword().equals(password)) {
            return "redirect:/login";
        } else {
            session.setAttribute("user", user);
            return "redirect:/posts";
        }
    }

/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
/*|<<>><<>><<>  USER LOGOUT <>><<>><<>>|*/
/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
    @GetMapping("/logout")
    public String login(HttpSession session){
        if (session.getAttribute("user") != null) {
            session.invalidate();
            return "/posts/login";
        }
        return "redirect:/posts";
    }


/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
/*|<<>><<>><<  USER REGISTER >><<>><<>>|*/
/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
    @GetMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, HttpSession session){
        if (session.getAttribute("user") == null) {
            return "/posts/register";
        }
        return "redirect:/posts";
    }

    @PostMapping("/register")
    public String createUser(@RequestParam(name = "username") String username,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "conf_password") String conf_password
                             ){
        if (!password.equals(conf_password)) {
            return "redirect:/register";
        }
        User newUser = new User(username, email, password);
        userDao.save(newUser);
        return "redirect:/login";
    }


/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
/*|<<>><<>><<> USER PROFILE <>><<>><<>>|*/
/*|<<>><<>><<>><<>><<>><<>><<>><<>><<>>|*/
    @GetMapping("/profile")
    public String userProfile(@ModelAttribute("user") User user,
                              HttpSession session,
                              Model model){
        if (session.getAttribute("user") == null) {
            return "/posts/register";
        }
        model.addAttribute("user", user);
        return "/posts/profile";
    }


}