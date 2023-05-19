package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {
    @Autowired
    private RuleNameService ruleNameService;

    /**
     * Affiche toutes les RuleName
     *
     * @param model
     * @return
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.getAllRuleName());
        return "ruleName/list";
    }

    /**
     * Affiche le formulaire permettant l'ajout d'une RuleName
     *
     * @param bid
     * @return
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Valide les données du formulaire d'ajout de RuleName et fait appel au RuleNameService pour la sauvegarde
     *
     * @param ruleName
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.addRuleName(ruleName);
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    /**
     * Affiche le formulaire d'une RuleName donnée
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<RuleName> ruleName = ruleNameService.getRuleNameById(id);
        if (ruleName.isPresent()) {
            model.addAttribute("ruleName", ruleName.get());
            return "ruleName/update";
        }
        return "ruleName/list";
    }

    /**
     * Valide les données du formulaire de modification d'une RuleName et fait appel au RuleNameService pour la sauvegarde
     *
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.updateRuleName(ruleName);
            return "redirect:/ruleName/list";
        }
        return "redirect:/ruleName/update/{id}";
    }

    /**
     * Supprime une RuleName donnée
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleName(id);
        return "redirect:/ruleName/list";
    }
}
