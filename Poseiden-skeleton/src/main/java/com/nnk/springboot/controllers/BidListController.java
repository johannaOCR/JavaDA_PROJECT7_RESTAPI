package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class BidListController {
    @Autowired
    BidListService bidListService;

    /**
     * Affiche la liste des bidlists enregistrées
     *
     * @param model
     * @return
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        List<BidList> bidList = bidListService.getAllBidList();
        model.addAttribute("bidList", bidList);
        return "bidList/list";
    }

    /**
     * affiche le formulaire pour ajouter une Bidlist
     *
     * @param bid
     * @return
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Valide les données du formulaire et enregistre si OK
     *
     * @param bid
     * @param result
     * @return
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            bidListService.addBidList(bid);
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    /**
     * Affiche le formulaire pour mettre à jour la Bidlist
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListService.getBidListById(id);
        if (bid != null) {
            model.addAttribute("bid", bid);
            return "bidList/update";
        }
        return "bidList/list";
    }

    /**
     * Valide et effectue les modifications demandées du formulaire
     *
     * @param id
     * @param bidList
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        if (!result.hasErrors()) {
            bidListService.updateBidList(id, bidList);
            return "redirect:/bidList/list";
        }
        return "redirect:/bidList/update/{id}";
    }

    /**
     * Supprime une bidList donnée
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
