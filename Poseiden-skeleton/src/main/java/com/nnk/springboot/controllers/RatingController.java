package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RatingController {
    @Autowired
    private RatingService ratingService;

    /**
     * Affiche la liste des Rating
     *
     * @param model
     * @return
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.getAllRating());
        return "rating/list";
    }

    /**
     * Affiche le formulaire pour ajouter un Rating
     *
     * @param rating
     * @return
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Valide les données du formulaire d'ajout de Rating et fait appel au RatingService pour la sauvegarde
     *
     * @param rating
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.addRating(rating);
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    /**
     * Affiche le formulaire de modification d'un Rating
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Rating> rating = ratingService.getRatingById(id);
        if (rating.isPresent()) {
            model.addAttribute("rating", rating.get());
            return "rating/update";
        }
        model.addAttribute("rating", ratingService.getRatingById(id));
        return "rating/list";
    }

    /**
     * Valide les données du formulaire de modification de Rating et fait appel au RatingService sauvegarder les modifications
     *
     * @param id
     * @param rating
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.updateRating(rating);
            return "redirect:/rating/list";
        }
        return "redirect:/rating/update/{id}";
    }

    /**
     * Supprime un Rating donné
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}
