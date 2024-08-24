package com.counsellorportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellorportal.dto.DashboardResponse;
import com.counsellorportal.entites.Counsellor;
import com.counsellorportal.service.CounsellorService;

@Controller
public class CounsellorController {

	private CounsellorService counsellorService;

	public CounsellorController(CounsellorService counsellorService) {
		super();
		this.counsellorService = counsellorService;
	}

	@GetMapping("/")
	public String index(Model model) {

		Counsellor counsellor = new Counsellor();

		// sending data from controller to UI
		model.addAttribute("counsellor", counsellor);
		// returning view name
		return "index";
	}

	@PostMapping("/login")
	public String login(Counsellor counsellor, Model model) {

		Counsellor cou = counsellorService.login(counsellor.getEmail(), counsellor.getPassword());

		if (cou == null) {
			model.addAttribute("errmsg", "Invalid Credentials");
			return "index";
		} else {
			DashboardResponse dashInfo = counsellorService.getDashBoard(cou.getCounsellorId());
			model.addAttribute("dashInfo", dashInfo);
			return "dashboard";
		}
	}

}
