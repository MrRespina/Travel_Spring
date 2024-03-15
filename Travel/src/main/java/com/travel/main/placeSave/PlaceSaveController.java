package com.travel.main.placeSave;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travel.main.place.Place;
import com.travel.main.place.PlaceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/save")
@RequiredArgsConstructor
public class PlaceSaveController {
	
	private final PlaceService placeService;
	private final PlaceSaveService placeSaveService;
	
	@GetMapping("/")
	public String findAll(Model model, @RequestParam(value="category", defaultValue="") String category) {
		List<Draftviews> selectSave = this.placeSaveService.selectSave();
		model.addAttribute("select", selectSave);
		
		List<Place> searchcategory = this.placeService.searchBycategory(category);
		model.addAttribute("testDBData", searchcategory);

		List<Place> selectMol = this.placeService.getTest();
		model.addAttribute("testDBData", selectMol);
		
		
		return "place";
	}
	
	@GetMapping("/insert")
	public String insertSave(@RequestParam("image") String photo,
			@RequestParam("placename") String name,
			@RequestParam("address") String addr,
			@RequestParam("category") String category,
			@RequestParam("latitude") Double lat,
			@RequestParam("longitude") Double lng,
			@RequestParam("id") int id,
			@RequestParam("f_id") int f_id,
			@RequestParam("struct") int struct) {
		Draftviews save = new Draftviews();
		save.setF_id(f_id);
		save.setPhoto(photo);
		save.setName(name);
		save.setAddr(addr);
		save.setCategory(category);
		save.setLat(lat);
		save.setLng(lng);
		this.placeService.deleteMolWithId(id);
		this.placeSaveService.insertSave(save);
		
		return "redirect:/place/searchByCategory?category="+category+"&struct="+struct;
	}
	
	@GetMapping("/select")
	public List<Draftviews> selectSave(Model model) {
		List<Draftviews> select = this.placeSaveService.selectSave();
		model.addAttribute("select", select);
		return select;
	}
	
	@GetMapping("/delete")
	public String deleteSave(@RequestParam("id") int id,
			@RequestParam("category") String category,
			@RequestParam("struct") int struct) {
		
		this.placeSaveService.deleteSave(id);
		
		return "redirect:/place/searchByCategory?category="+category+"&struct="+struct;
	}
}
