package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DiceController {

    public int rollDie() {
        return (int) ((Math.random() * (7 - 1)) + 1);
    }

    @GetMapping("/roll-dice")
    public String dice() {
        return "roll-dice";
    }

//    @GetMapping("/roll-dice/{n}")
//    public String rollDie(@PathVariable int n, Model model) {
//        int roll = rollDie();
//        if (roll == n) {
//            model.addAttribute("note", "You guessed the number!");
//        } else {
//            model.addAttribute("note", "You guessed wrong!");
//        }
//        model.addAttribute("guess", n);
//        model.addAttribute("roll", roll);
//        return "roll-dice";
//    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model) {
        ArrayList<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            rolls.add(rollDie());
        }

        int count = 0;
        for (int i = 0; i < rolls.size(); i++) {
            if (rolls.get(i) == n) {
                count++;
            }
        }

        model.addAttribute("rolls", rolls);
        model.addAttribute("count", count);
        model.addAttribute("guess", n);

        return "roll-dice";
    }

}