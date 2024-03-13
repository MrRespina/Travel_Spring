package com.travel.main;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://ec2-52-78-155-140.ap-northeast-2.compute.amazonaws.com:3000",
		"http://ec2-52-78-155-140.ap-northeast-2.compute.amazonaws.com:3001" }) 
@RequiredArgsConstructor
@Controller
public class TravelController {
	
	@GetMapping("/")
	public String home(Model model,Principal principal) {
		
		model.addAttribute("data","hello!");
		if (principal != null) {
			model.addAttribute("r", principal.getName());
		}
		model.addAttribute("noNavbar",false);
		return "index";
		
	}
	
	// html include를 위해서 필요헌 페이지를 매핑해주었음.
	@GetMapping("/first_page.html")
	public String getCategory1() {
		return "first_page";
	}
	
}
