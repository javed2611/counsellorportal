package com.counsellorportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellorportal.entites.Enquiry;
import com.counsellorportal.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	private EnquiryService enquiryService;

	public EnquiryController(EnquiryService enquiryService) {
		this.enquiryService = enquiryService;
	}

	@GetMapping("/enquiry")
	public String pageLoad(Model model) {

		Enquiry en = new Enquiry();
		model.addAttribute("enq", en);

		return "enquiryForm";
	}

	@PostMapping("/addEnquiry")
	public String enquiry(Enquiry enq, HttpServletRequest httpServletRequest, Model mode) throws Exception {

		HttpSession httpSession = httpServletRequest.getSession(false);
		Integer counsellorId = (Integer) httpSession.getAttribute("counsellorId");

		boolean isSave = enquiryService.saveEnq(enq, counsellorId);

		if (isSave) {
			mode.addAttribute("sucmsg", "Enquiry Added");
		} else {
			mode.addAttribute("errmsg", "Enquiry Not Added");
		}

		return "enquiryForm";
	}

}
