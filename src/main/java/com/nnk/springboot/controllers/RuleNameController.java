package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {

    @Autowired
    RuleNameService ruleNameService;

    @Autowired
    SCHService schService;

    //list display
    @GetMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        model.addAttribute("ruleNames", ruleNameService.getAll());
        Logger.info("RuleName list page shown");
        return "ruleName/list";
    }

    //add display
    @GetMapping("/ruleName/add")
    public String addRuleNameForm(RuleName ruleName, Model model) {

        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        Logger.info("RuleName add page shown");
        return "ruleName/add";
    }

    //add a new bid
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "ruleName/add";
        }

        ruleNameService.saveRuleName(ruleName);

        Logger.info("Added a RuleName");
        return "redirect:/ruleName/list";
    }

    //update display
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Logger.info("RuleName update page shown");
        model.addAttribute("ruleName2Update", ruleNameService.getById(id));
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        return "ruleName/update";
    }

    //update a bid
    @PostMapping("/ruleName/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "/ruleName/update";
        }

        ruleNameService.updateById(id, ruleName);
        Logger.info("updated a RuleName");
        return "redirect:/ruleName/list";
    }

    //delete a bid
    @GetMapping("/ruleName/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        ruleNameService.deleteById(id);

        Logger.info("deleted a RuleName");
        return "redirect:/ruleName/list";
    }
}
