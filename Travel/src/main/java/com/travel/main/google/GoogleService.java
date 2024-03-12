package com.travel.main.google;

import java.io.UnsupportedEncodingException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class GoogleService {
	
	public String[] getYoutube(String cityName) throws UnsupportedEncodingException, ParseException{
		
		GoogleApiManager gManager = new GoogleApiManager();
		String[] res = gManager.fetch(cityName);
		return res;
		
	}
	

}
