package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Affiche la liste des utilisateurs
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    /**
     * Affiche le formulaire de création d'un utilisateur
     *
     * @param user
     * @return
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    /**
     * Valide les données du formulaire d'ajout d'un utilisateur et fait appel au UserService pour la sauvegarde
     *
     * @param user
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.addUser(user);
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Affiche le formulaire de modification d'un utilisateur
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Valide les données du formulaire de modification d'un utilisateur et fait appel au UserService pour la sauvegarde
     *
     * @param id
     * @param user
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (!result.hasErrors()) {
            /** Hash du mot de passe via BCryptPasswordEncoder avant enregistrement */
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.updateUser(user);
            model.addAttribute("users", userService.getAllUsers());
            return "redirect:/user/list";
        }
        return "user/update/{id}";
    }

    /**
     * Supprime un utilisateur donné
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }
}
