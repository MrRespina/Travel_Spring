package com.travel.main.ticket;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ticket")
@RequiredArgsConstructor
@Controller
public class MyTicketController {
	private final MyTicketService tService;
	
	@GetMapping("/searchMyTickets")
	public String searchMyTickets(Principal principal,Model model) {
		
		if (principal != null) {
			model.addAttribute("r", principal.getName());
		}
		List<MyTicket> tList = this.tService.getReservedTicket(principal.getName());
		model.addAttribute("tickets",tList);
		model.addAttribute("notSlickCheck",1);
		model.addAttribute("noNavbar",false);
		
		return "show_ticket_form";
		
	}
	
	@GetMapping("/deleteMyTicket")
	public String deleteMyTicket(Principal principal,Model model,@RequestParam(value = "id") int id) {
		
		if (principal != null) {
			model.addAttribute("r", principal.getName());
		}
		this.tService.deleteReservedTicket(id);
		List<MyTicket> tList = this.tService.getReservedTicket(principal.getName());
		model.addAttribute("tickets",tList);
		model.addAttribute("notSlickCheck",1);
		model.addAttribute("noNavbar",false);
		
		return "show_ticket_form";
		
	}
	
}
