package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    SCHService schService;

    //list display
    @GetMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        model.addAttribute("ratings", ratingService.getAll());
        Logger.info("Rating list page shown");
        return "rating/list";
    }

    //add display
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating, Model model) {

        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        Logger.info("Rating add page shown");
        return "rating/add";
    }

    //add a new rating
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "rating/add";
        }

        ratingService.saveRating(rating);

        Logger.info("Added a Rating");
        return "redirect:/rating/list";
    }

    //update display
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Logger.info("Rating update page shown");
        model.addAttribute("rating2Update", ratingService.getById(id));
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        return "rating/update";
    }

    //update a rating
    @PostMapping("/rating/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "/rating/update";
        }

        ratingService.updateById(id, rating);
        Logger.info("updated a Rating");
        return "redirect:/rating/list";
    }

    //delete a rating
    @GetMapping("/rating/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        ratingService.deleteById(id);

        Logger.info("deleted a Rating");
        return "redirect:/rating/list";
    }
}
