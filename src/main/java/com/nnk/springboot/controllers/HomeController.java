package com.nnk.springboot.controllers;

import com.nnk.springboot.services.SCHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinylog.Logger;

@Controller
public class HomeController
{
	@Autowired
	SCHService schService;

	//diplay home
	@GetMapping("/home")
	public String home(@RequestParam(required = false) String role, Model model)
	{
		model.addAttribute("role", schService.getRole());
		model.addAttribute("name", schService.getName());
		Logger.info("home page shown");
		return "home";
	}


}
