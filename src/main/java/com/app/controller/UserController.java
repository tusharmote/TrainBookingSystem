package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DeletePassenegerDto;
import com.app.dto.LoginUserDto;
import com.app.dto.PassengerDto;
import com.app.dto.UserDto;
import com.app.entities.Passenger;
import com.app.entities.Ticket;
import com.app.entities.User;
import com.app.service.IPassengerService;
import com.app.service.IUserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController
{
	@Autowired
	private IUserService userService;

	@Autowired
	private IPassengerService passengerService;

	@GetMapping("/")
	public ResponseEntity<?> findAll()
	{
		List<User> list = userService.findAll();
		if (list.isEmpty())
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success(list);
	}

	@PostMapping("/findByEmail")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginUserDto loginUser)
	{
		User user = userService.authenticateUser(loginUser);
		if (user == null)
			return Response.error("Invalid Login details..!!");
		return Response.success(user);
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestBody LoginUserDto loginUser)
	{
		User user = userService.forgetPassword(loginUser);
		if (user == null)
			return Response.error("Invalid user... !!");
		return Response.success(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id)
	{
		User user = userService.findById(id);
		if (user == null)
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success(user);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id)
	{
		int noOfRowsDeleted = userService.deleteById(id);
		if (noOfRowsDeleted == 0)
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success("no. of rows deleted is " + noOfRowsDeleted);
	}

	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody User user)
	{
		User u = userService.save(user);
		if (u == null)
			return Response.error("User already exist..!!");
		return Response.success(u);
	}



	
	@PostMapping("/addPassengerList/{ticketId}")
	public ResponseEntity<?> addPassengerList(@RequestBody PassengerDto[] list, @PathVariable("ticketId") int ticketId)
	{
		System.out.println(ticketId);
		int fareOfTicket = userService.setFareForTicket(list, ticketId);
		List<Passenger> passList = userService.addPassengerList(list);
		if (passList.isEmpty())
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success(fareOfTicket);
	}

	

	//passenger list by userId
	@PutMapping("/getListOfPassengers/{id}")
	public ResponseEntity<?> getListOfPassengers(@PathVariable("id") int id)
	{
		User user = userService.findByIdUser(id);
		if (user != null)
		{
			List<Passenger> passengerList = user.getPassengerList();
			if (passengerList.isEmpty())
			{
				System.out.println("Empty..!!");
				return Response.error("List is Empty...!!");
			}
			return Response.success(passengerList);
		}
		return Response.status(HttpStatus.NOT_FOUND);
	}

	//list of tickets by userID
	@PutMapping("/getListOfTickets/{id}")
	public ResponseEntity<?> getListOfTickets(@PathVariable("id") int id)
	{
		List<Ticket> listOfTicket = userService.getListOfTicket(id);
		if (listOfTicket == null)
			return Response.error("List Not available..!!");
		return Response.success(listOfTicket);

	}
	
	// for future use passenger list by particular train id;
		@GetMapping("/listByTrainID")
		public ResponseEntity<?> listByUserIdAndDate(@RequestBody DeletePassenegerDto dto)
		{
			List<Passenger> list = passengerService.listByUserIdAndDate(dto);
			if (list.isEmpty())
				return Response.status(HttpStatus.NOT_FOUND);
			return Response.success(list);
		}

		@PutMapping("/{id}")
		public ResponseEntity<?> save(@PathVariable("id") int id, @RequestBody User user)
		{
			  
			user.setId(id);
			User u = userService.save(user);
			if (u == null)
				return Response.status(HttpStatus.NOT_FOUND);
			return Response.success(u);
		}

}
