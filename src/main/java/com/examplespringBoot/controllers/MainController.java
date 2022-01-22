/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package com.examplespringBoot.controllers;

/**
 * Класс MainContpoller
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String greeting(Model model) {
		model.addAttribute("title", "Главная страница");
		return "home";
	}

}