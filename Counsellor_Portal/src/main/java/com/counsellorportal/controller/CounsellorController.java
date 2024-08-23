package com.counsellorportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CounsellorController {

	@GetMapping("/")
	public String index(Model model) {

		// returning view name
		return "index";
	}
}
