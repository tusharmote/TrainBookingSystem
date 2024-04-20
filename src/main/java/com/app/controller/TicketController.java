package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.entities.Ticket;
import com.app.service.ITicketService;

@CrossOrigin
@RestController
@RequestMapping("/tickets")
public class TicketController
{


	@Autowired
	private ITicketService ticketServise;

	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Ticket t)
	{
		Ticket ticket = ticketServise.save(t);
		if (ticket != null)
			return Response.success(t);
		return Response.error("Nod added..!!");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTicket(@PathVariable int id)
	{
		int noOfRowsDeleted = ticketServise.deleteTicket(id);
		return Response.success("No of rows deleted " + noOfRowsDeleted);
	}

	

}
