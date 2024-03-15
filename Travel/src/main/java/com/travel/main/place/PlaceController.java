package com.travel.main.place;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travel.main.placeSave.Draftviews;
import com.travel.main.placeSave.PlaceSaveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {
	
	private final FirstsService fService;
	private final PlaceService placeService;
	private final PlaceSaveService placeSaveService;
	
//	@GetMapping("/")
//	public String searchAll(Model model,
//			@RequestParam(value="category", defaultValue="") String category,
//			@RequestParam(value="id", defaultValue="2") int id) {
//		
//		model.addAttribute("id",id);
//		List<Place> searchcategory = this.placeService.searchBycategory(category);
//		model.addAttribute("testDBData", searchcategory);
//		
//		List<Draftviews> select = this.placeSaveService.selectSave();
//		model.addAttribute("select", select);
//		return "place";
//	}
	
	@GetMapping("/searchByName")
	public String searchByName(Model model, 
			@RequestParam(value="placename", defaultValue="") String placename,
			@RequestParam(value="struct", defaultValue="") String struct) {
		List<Place> search = this.placeService.searchByName(placename);
		model.addAttribute("testDBData", search);
		
		List<Draftviews> select = this.placeSaveService.selectSave();
		model.addAttribute("select", select);
		model.addAttribute("struct",struct);
		
		return "place";
	}
	
	@GetMapping("/searchByCategory")
	public String searchByCategory(Model model,
			@RequestParam(value="id",defaultValue="1") String id,
			@RequestParam(value="category", defaultValue="") String category,
			@RequestParam(value="struct", defaultValue="") String struct) {
		
		System.out.println(struct);
		List<Place> searchcategory = this.placeService.searchBycategory(category);
		model.addAttribute("testDBData", searchcategory);
		
		List<Draftviews> select = this.placeSaveService.selectSave();
		model.addAttribute("id",id);
		model.addAttribute("select", select);
		model.addAttribute("struct", struct);
		return "place";
	}
	
	@GetMapping("/startLocation")
	public String getMyApiP1(@RequestParam(value="id",defaultValue="1") String id,
			@RequestParam(value="struct",defaultValue="1") int struct,
			@RequestParam(value="category", defaultValue="") String category,
			RedirectAttributes ra) {
		int i = Integer.parseInt(id);
		Firsts f = fService.getMyXY();
		this.placeService.deleteAll();
		this.placeService.insertApiForLocation("restaurant","historical_landmark","cafe",f);
		List<Place> apiList = this.placeService.selectMol();
		ra.addFlashAttribute("apiData",apiList);
		ra.addFlashAttribute("id",id);
		ra.addFlashAttribute("struct",struct);
		
		return "redirect:/place/searchByCategory?category="+category+"&struct="+struct;
	}
	
	@GetMapping("/startLodging")
	public String getMyApiP2(@RequestParam(value="id",defaultValue="1") String id, 
			@RequestParam(value="struct",defaultValue="2") int struct,
			@RequestParam(value="category", defaultValue="") String category,
			RedirectAttributes ra) {
		int i = Integer.parseInt(id);
		Firsts f = fService.getMyXY();
		this.placeService.deleteAll();
		this.placeService.insertApiForLocation("hotel","guest_house","resort_hotel",f);
		List<Place> apiList = this.placeService.selectMol();
		ra.addFlashAttribute("apiData",apiList);
		ra.addFlashAttribute("id",id);
		ra.addFlashAttribute("struct",struct);
		
		return "redirect:/place/searchByCategory?category="+category+"&struct="+struct;
	}
	
	@GetMapping("/startConvenience")
	public String getMyApiP3(@RequestParam(value="id",defaultValue="1") String id, 
			@RequestParam(value="struct",defaultValue="3") int struct,
			@RequestParam(value="category", defaultValue="") String category,
			RedirectAttributes ra) {
		int i = Integer.parseInt(id);
		Firsts f = fService.getMyXY();
		this.placeService.deleteAll();
		this.placeService.insertApiForLocation("parking","pharmacy","market",f);
		List<Place> apiList = this.placeService.selectMol();
		ra.addFlashAttribute("apiData",apiList);
		ra.addFlashAttribute("id",id);
		ra.addFlashAttribute("struct",struct);
		
		return "redirect:/place/searchByCategory?category="+category+"&struct="+struct;
	}
	
	
	
}
