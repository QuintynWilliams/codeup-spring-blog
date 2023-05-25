package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdController {

    private final AdRepository adsDao;

//  Dependency injection: Enables method access of JpaRepo by adsDao.methodName()
    public AdController (AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String adIndex(Model model) {
        model.addAttribute("ads", adsDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/create")
    public String createAdForm() {
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description) {
        Ad ad = new Ad(title, description);
        adsDao.save(ad);
        return "redirect:/ads";
    }

}