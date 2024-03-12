package com.travel.main.ticket;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyTicketMapper {
	
	public List<MyTicket> findMyTickets(String username);
	public void deleteMyTickets(int id);

}
