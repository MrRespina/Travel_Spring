package com.travel.main.place;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Firsts {

	private int id;
	private String name;
	private BigDecimal lat;
	private BigDecimal lng;
	private String start_day;
	private String end_day;
	private String search;
	private Date createdAt;
	private Date updatedAt;
	
}
