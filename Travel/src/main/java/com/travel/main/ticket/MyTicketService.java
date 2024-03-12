package com.travel.main.ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class MyTicketService {
	
	@Autowired
	MyTicketMapper tMapper;
	public List<MyTicket> getReservedTicket(String username){
		List<MyTicket> mArray = tMapper.findMyTickets(username);
		return mArray;
	}
	
	public void deleteReservedTicket(int id) {
		tMapper.deleteMyTickets(id);
	}

}
