package com.travel.main.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyTicket {
	
	private int id;
	private int u_id;
	private String username;
	private String company;
	private String toLocation;
	private String inTime;
	private String goLocation;
	private String outTime;
	private String layover;
	private String price;

}
