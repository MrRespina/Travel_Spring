package com.travel.main.google;

import java.io.UnsupportedEncodingException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = {"http://ec2-52-78-155-140.ap-northeast-2.compute.amazonaws.com:3000",
		"http://ec2-52-78-155-140.ap-northeast-2.compute.amazonaws.com:3001" }) 
@Controller
public class GoogleController {
	
	@Autowired
	GoogleService gService;
	
	@GetMapping("/api/getYoutube")
	public String getYoutube(@RequestParam("cityName") String cityName,Model model) throws UnsupportedEncodingException, ParseException {
		String[] res = gService.getYoutube(cityName);
		
		for(int i=0;i<5;i++) {
			
			if(res[i]==null) {
				model.addAttribute("status","!!!올바른 국가/도시 입력할 것!!!");
				break;
			}
			
			model.addAttribute("video"+i,res[i]);
			
		}
		return "youtube_content_form";
		
	}
	
	@GetMapping("/youtube_content_form.html")
	public String getGoogleForm(Model model) {
		// https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=5&order=viewCount&key=AIzaSyAfq7o_43XZjyNTG--H6F9sqeNYjXamaJA&q=tokyo-travel-guide
		return "youtube_content_form";
	}

}