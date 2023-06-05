package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>> USER LOGIN <<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }
    @PostMapping("/login")
    public String loginSessionSetter(Model model, HttpSession session){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("user", user);
        System.out.println(user.getUsername() + " is logged in");
        return "/login";
    }


/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<> USER LOGOUT <<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
    @GetMapping("/logout")
    public String login(HttpSession session){
        if (session.getAttribute("user") != null) {
            session.invalidate();
            return "/posts/login";
        }
        return "redirect:/posts";
    }


/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<> USER REGISTER >><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name = "password") String password){
        password = passwordEncoder.encode(password);
        userDao.save(new User(username, email, password));
        return "redirect:/posts";
    }

/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<> USER PROFILE <>><<>><<>><<>><<>><<>><<>><<>><|*/
/*|><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><|*/
    @GetMapping("/profile")
    public String userProfile(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "profile";
    }


}