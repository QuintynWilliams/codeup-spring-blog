package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository userDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("users") User user, HttpSession session){
        if (session.getAttribute("user") == null) {
            return "/posts/login";
        }
        return "redirect:/posts";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("users") User user, HttpSession session,
                            @RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password){
        User loggingUser = userDao.findByUsername(username);
        if (loggingUser == null) {
            return "redirect:/login";
        } else {
            session.setAttribute("user", loggingUser);
            return "redirect:/posts";
        }
    }

}