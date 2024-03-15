package com.travel.main.place;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Place {
	private int id;
	private int f_id;
	private String placename;
	private String category;
	private String address;
	private Date days;
	private double latitude;
	private double longitude;
	private String image;
}
