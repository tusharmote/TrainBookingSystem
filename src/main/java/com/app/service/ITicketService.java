package com.app.service;

import java.util.List;

import com.app.entities.Ticket;

public interface ITicketService {
	
	List<Ticket> findAll();
	Ticket save(Ticket t);
	Ticket findById(int id);
	int deleteTicket(int id);

}
