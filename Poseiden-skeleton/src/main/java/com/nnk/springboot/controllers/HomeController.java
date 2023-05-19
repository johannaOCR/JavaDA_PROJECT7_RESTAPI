package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * Affiche la home page
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }

    /**
     * Redirige vers la list des Bidlists si autorisé
     *
     * @param model
     * @return
     */
    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        return "redirect:/bidList/list";
    }


}
