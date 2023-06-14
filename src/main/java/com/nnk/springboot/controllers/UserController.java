package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.SCHService;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinylog.Logger;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    SCHService schService;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        Logger.info("User list page shown");
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid, Model model) {

        Logger.info("User add page shown");
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.saveUser(user);
            return "redirect:/user/list";
        }

        Logger.info("Added user");
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        user.setPassword("password");
        model.addAttribute("user2Update", user);
        model.addAttribute("role", schService.getRole());
        model.addAttribute("name", schService.getName());

        Logger.info("update User page shown");
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.updateById(id, user);

        Logger.info("updated User");
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        userService.deleteById(id);
        Logger.info("deleted User");
        return "redirect:/user/list";
    }
}
