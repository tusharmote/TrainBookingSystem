package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TicketRepository;
import com.app.entities.Ticket;

@Transactional
@Service
public class TicketServiceImpl implements ITicketService
{
	@Autowired
	private TicketRepository ticketRepo;
	
	@Override
	public List<Ticket> findAll()
	{
		return ticketRepo.findAll();
	}
	
	@Override
	public Ticket save(Ticket t)
	{
		return ticketRepo.save(t);
	}

	@Override
	public Ticket findById(int id)
	{
		return ticketRepo.findById(id);
	}
	
	@Override
	public int deleteTicket(int id)
	{
		ticketRepo.deleteById(id);
		return 1;
	}
	
}
