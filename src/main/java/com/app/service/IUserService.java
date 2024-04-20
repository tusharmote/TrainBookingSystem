package com.app.service;

import java.util.List;

import com.app.dto.LoginUserDto;
import com.app.dto.PassengerDto;
import com.app.dto.UserDto;
import com.app.entities.Passenger;
import com.app.entities.Ticket;
import com.app.entities.User;

public interface IUserService {

	List<User> findAll();
	
	User findById(int id);
	
	User findByIdUser(int id);
	
	User findByEmail(String email);
	
	User forgetPassword(LoginUserDto dto);
	
	User authenticateUser(LoginUserDto loginUser);
	
	int deleteById(int id);
	
	User save(User user);
	
	List<Passenger> addPassengerList(PassengerDto[] list);
	int setFareForTicket(PassengerDto[] list, int ticketId);
	List<Ticket> getListOfTicket(int id);
	
	
}
