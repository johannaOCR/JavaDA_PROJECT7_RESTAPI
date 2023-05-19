package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    /**
     * Affiche la liste des CurvePoint
     *
     * @param model
     * @return
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePointList", curvePointService.getAllCurvePoint());
        return "curvePoint/list";
    }

    /**
     * Affiche le formulaire pour la création d'une CurvePoint
     *
     * @param curvePoint
     * @return
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    /**
     * Valide les données envoyées par le formulaire de création et ajoute le CurvePoint si valide
     *
     * @param curvePoint
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curvePointService.addCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    /**
     * Affiche le formulaire d'edition du CurvePoint donné
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<CurvePoint> curvePoint = curvePointService.getCurvePointById(id);
        if (curvePoint.isPresent()) {
            model.addAttribute("curvePoint", curvePoint.get());
            return "curvePoint/update";
        }
        return "redirect:/curvePoint/list";
    }

    /**
     * Valide les données envoyées par le formulaire de modification et modifie le CurvePoint si valide
     *
     * @param id
     * @param curvePoint
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curvePointService.updateCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "redirect:/curvePoint/update/{id}";
    }

    /**
     * Supprime le CurvePoint donné
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
