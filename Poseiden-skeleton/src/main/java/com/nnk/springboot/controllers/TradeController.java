package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {
    @Autowired
    private TradeService tradeService;

    /**
     * Affiche la liste des Trade
     *
     * @param model
     * @return
     */
    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.getAllTrade());
        return "trade/list";
    }

    /**
     * Affiche le formulaire de création d'un Trade
     *
     * @param bid
     * @return
     */
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    /**
     * Valide les données du formulaire d'ajout de Trade et fait appel au TradeService pour la sauvegarde
     *
     * @param trade
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.addTrade(trade);
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    /**
     * Affiche le formulaire de modification d'un Trade donné
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Trade> trade = tradeService.getTradeById(id);
        if (trade.isPresent()) {
            model.addAttribute("trade", trade.get());
            return "trade/update";
        }
        return "trade/list";
    }

    /**
     * Valide les données du formulaire de modification du Trade et fait appel au TradeService pour la sauvegarde
     *
     * @param id
     * @param trade
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.updateTrade(trade);
            return "redirect:/trade/list";
        }
        return "redirect:/trade/update/{id}";
    }

    /**
     * Supprime un Trade donné
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTrade(id);
        return "redirect:/trade/list";
    }
}
