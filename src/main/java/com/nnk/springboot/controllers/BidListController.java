package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

    @Autowired
    BidListService bidListService;

    @Autowired
    SCHService schService;

    //list display
    @GetMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        model.addAttribute("bidLists", bidListService.getAll());
        Logger.info("BidList list page shown");
        return "bidList/list";
    }

    //add display
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid, Model model) {

        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        Logger.info("BidList add page shown");
        return "bidList/add";
    }

    //add a new bid
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "bidList/add";
        }

        bidListService.saveBid(bid);

        Logger.info("Added a BidList");
        return "redirect:/bidList/list";
    }

    //update display
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Logger.info("Bidlist update page shown");
        model.addAttribute("bidList", bidListService.getById(id));
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        return "bidList/update";
    }

    //update a bid
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("role", schService.getRole());
            model.addAttribute("name", schService.getName());
            return "/bidList/update";
        }

        bidListService.updateById(id, bidList);
        Logger.info("updated a BidList");
        return "redirect:/bidList/list";
    }

    //delete a bid
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        bidListService.deleteById(id);

        Logger.info("deleted a BidList");
        return "redirect:/bidList/list";
    }
}
