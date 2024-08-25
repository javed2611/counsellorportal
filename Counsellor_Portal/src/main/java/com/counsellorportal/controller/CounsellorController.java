package com.counsellorportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.counsellorportal.dto.DashboardResponse;
import com.counsellorportal.entites.Counsellor;
import com.counsellorportal.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	public String login(Counsellor counsellor, HttpServletRequest httpServletRequest, Model model) {

		Counsellor cou = counsellorService.login(counsellor.getEmail(), counsellor.getPassword());

		if (cou == null) {
			model.addAttribute("errmsg", "Invalid Credentials");
			return "index";
		} else {
			// valid login then store the counsellorId in session for future purpose.

			HttpSession httpSession = httpServletRequest.getSession(true);
			httpSession.setAttribute("counsellorId", cou.getCounsellorId());

			return "redirect:/dashboard";
		}
	}

	@GetMapping("/dashboard")
	public String displayDB(HttpServletRequest httpServletRequest, Model model) {
		HttpSession httpSession = httpServletRequest.getSession(false);

		Integer counsellorId = (Integer) httpSession.getAttribute("counsellorId");

		DashboardResponse dashInfo = counsellorService.getDashBoard(counsellorId);
		model.addAttribute("dashInfo", dashInfo);
		return "dashboard";
	}

	@GetMapping("/register")
	public String registerForm(Model mode) {

		Counsellor c = new Counsellor();
		mode.addAttribute("counsellor", c);

		return "register";
	}

	@PostMapping("/register")
	public String Register(Counsellor counsellor, Model model) {

		Counsellor checkEmail = counsellorService.findByEmail(counsellor.getEmail());
		if (checkEmail != null) {
			model.addAttribute("ermsg", "Duplicate Email");
			return "register";
		}

		boolean isRegistered = counsellorService.register(counsellor);

		if (isRegistered) {

			model.addAttribute("sumsg", "Registration Done");

		} else {

			model.addAttribute("ermsg", "Registration Failed");

		}
		return "register";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest r) {
		// get existing session and invalidate it
		HttpSession sess = r.getSession(false);
		sess.invalidate();
		// redirect to login page
		return "redirect:/";
	}
}