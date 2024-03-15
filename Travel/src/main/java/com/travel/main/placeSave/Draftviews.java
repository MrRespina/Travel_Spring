package com.travel.main.placeSave;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Draftviews {
	
	private int id;
	private int f_id;
	private String name;
	private String category;
	private String addr;
	private Date dDay;
	private double lat;
	private double lng;
	private String photo;
	private Date createdAt;
	private Date updatedAt;
	
}
