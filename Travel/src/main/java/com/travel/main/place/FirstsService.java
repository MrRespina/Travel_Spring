package com.travel.main.place;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirstsService {
	
	private final FirstsMapper fMapper;
	
	// select
	public Firsts getMyXY() {
		
		Firsts f = new Firsts();
		f = fMapper.getMyXY();
		return f;
		
	}

}
