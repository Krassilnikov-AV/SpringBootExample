/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package com.examplespringBoot.controllers;

/**
 * Класс MainContpoller
 */

import com.examplespringBoot.services.URLGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class MainController {
	@Autowired
	private URLGeneratorService urlGeneratorService;

	@GetMapping("/")
	public String greeting(Model model) {
		model.addAttribute("title", "Главная страница");
		return "home";
	}

	//перенаправление ссылки
	@PostMapping("/createLink")
	public String createLink(String originalUrl) {
//		originalUrl
//			.addAttribute("title", "Приветствуем на странице по созданию короткой ссылки");


		String baseUrl = ServletUriComponentsBuilder.fromCurrentRequest()
			.replacePath(null)
			.build()
			.toUriString();
		String shortUrl = urlGeneratorService.createURL(originalUrl, baseUrl);
		return "shortUrl: " + shortUrl;
	}

	//перенаправление ссылки
	@GetMapping("/mainPage")
	public String mainPage(Model model) {
		model.addAttribute("title", "Приветствуем на главной странице сайта");
		return "home";
	}
}