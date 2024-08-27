package com.counsellorportal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellorportal.dto.ViewEnqFilterReq;
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

	@PostMapping("/filter-enqs")
	public String filterenq(ViewEnqFilterReq viewEnqFilterReq, HttpServletRequest httpServletRequest, Model model) {
		HttpSession httpSession = httpServletRequest.getSession(false);
		Integer counsellorId = (Integer) httpSession.getAttribute("counsellorId");

		 List<Enquiry> enqsWithFil = enquiryService.getEnqsWithFil(viewEnqFilterReq, counsellorId);
		
		 model.addAttribute("enquiries", enqsWithFil);

		return "viewEnqPage";

	}

	@GetMapping("/view-enquiries")
	public String getEnquriy(HttpServletRequest httpServletRequest, Model model) {
		// get existing session obj

		HttpSession httpSession = httpServletRequest.getSession(false);
		Integer counsellorId = (Integer) httpSession.getAttribute("counsellorId");

		List<Enquiry> enqlist = enquiryService.getAllEnq(counsellorId);

		model.addAttribute("enquiries", enqlist);

		ViewEnqFilterReq viewEnqFilterReq = new ViewEnqFilterReq();
		model.addAttribute("viewEnqFilterReq", viewEnqFilterReq);

		return "viewEnqPage";
	}

	@GetMapping("/enquiry")
	public String pageLoad(Model model) {

		Enquiry en = new Enquiry();
		model.addAttribute("enq", en);

		return "enquiryForm";
	}

	@PostMapping("/addEnquiry")
	// if we use complete variable name then we don't have to use @ModelAttribute
	public String enquiry(@ModelAttribute("enq") Enquiry enq, HttpServletRequest httpServletRequest, Model mode)
			throws Exception {

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