package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurveService;
import com.nnk.springboot.services.SCHService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.tinylog.Logger;

@Controller
public class CurveController {

    @Autowired
    CurveService curveService;

    @Autowired
    SCHService schService;

    //list display
    @GetMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curvePoints", curveService.getAll());
        Logger.info("CurvePoint list page shown");
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        return "curvePoint/list";
    }

    //add display
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint, Model model) {

        Logger.info("CurvePoint add page shown");
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        return "curvePoint/add";
    }

    //add a new curve point
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "curvePoint/add";
        }

        curveService.saveCurvePoint(curvePoint);

        Logger.info("Added a CurvePoint");
        return "redirect:/curvePoint/list";
    }

    //update display
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Logger.info("CurvePoint update page shown");
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        model.addAttribute("curvePoint2Update", curveService.getById(id));
        return "curvePoint/update";
    }

    //update a curvePoint
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "/curvePoint/update";
        }

        curveService.updateById(id, curvePoint);
        Logger.info("updated a CurvePoint");
        return "redirect:/curvePoint/list";
    }

    //delete a curvepoint
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        curveService.deleteById(id);

        Logger.info("deleted a CurvePoint");
        return "redirect:/curvePoint/list";
    }
}
