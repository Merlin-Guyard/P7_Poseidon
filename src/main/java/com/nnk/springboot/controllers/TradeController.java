package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.SCHService;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    SCHService schService;

    //list display
    @GetMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        model.addAttribute("trades", tradeService.getAll());
        Logger.info("Trade list page shown");
        return "trade/list";
    }

    //add display
    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade, Model model) {

        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        Logger.info("Trade add page shown");
        return "trade/add";
    }

    //add a new trade
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "trade/add";
        }

        tradeService.saveTrade(trade);

        Logger.info("Added a Trade");
        return "redirect:/trade/list";
    }

    //update display
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Logger.info("Trade update page shown");
        model.addAttribute("trade2Update", tradeService.getById(id));
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        return "trade/update";
    }

    //update a trade
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "/trade/update";
        }

        tradeService.updateById(id, trade);
        Logger.info("updated a Trade");
        return "redirect:/trade/list";
    }

    //delete a trade
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        tradeService.deleteById(id);

        Logger.info("deleted a Trade");
        return "redirect:/trade/list";
    }
}
