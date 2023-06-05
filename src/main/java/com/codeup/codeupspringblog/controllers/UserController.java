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
        return "/login";
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
    public String showProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = userDao.findById(userId);
        model.addAttribute("user", user);
        System.out.println(user.getUsername());
        return "profile";
    }

    @PostMapping("/profile")
    public String changeProfile(@RequestParam(name="email") String email){
        System.out.println("Post mapping hit");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = userDao.findById(userId);
        user.setEmail(email);
        userDao.save(user);
        return "redirect:/profile";
    }


}