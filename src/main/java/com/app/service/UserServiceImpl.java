package com.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserRepository;
import com.app.dto.DtoEntityConvertor;
import com.app.dto.LoginUserDto;
import com.app.dto.PassengerDto;
import com.app.dto.UserDto;
import com.app.entities.Passenger;
import com.app.entities.Ticket;
import com.app.entities.Train;
import com.app.entities.User;

@Transactional
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private IPassengerService passengerService;

	@Autowired
	private ITrainService trainService;

	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User findById(int id) {
		User user = userRepo.findById(id);
		if (user != null)
			
			return user;
		return null;
	}

	@Override
	public User findByIdUser(int id) {
		User user = userRepo.findById(id);
		if (user != null)
			return user;
		return null;
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepo.findByEmail(email);
		System.out.println(user);
		if (user != null)
			
		return user;
		return null;
	}

	@Override
	public User forgetPassword(LoginUserDto dto) {
		User user = userRepo.findByEmail(dto.getEmail());
		if (user != null) {
			user.setPassword(dto.getPassword());
			//return DtoEntityConvertor.toUserDto(user);
			return user;
		}
		return null;

	}

	@Override
	public User authenticateUser(LoginUserDto loginUser) {
		User user = userRepo.findByEmail(loginUser.getEmail());
		if (user != null) {
			if (loginUser.getEmail().equals(user.getEmail()) && user.getPassword().equals(loginUser.getPassword())) {
				
				return user;
			}
		}
		return null;
	}

	@Override
	public int deleteById(int id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return 1;
		}
		return 0;
	}

	@Override
	public User save(User user) {
	
		List<User> list = userRepo.findAll();
		boolean flag = true;
		for (User u : list) {
			if (u.getEmail().equals(user.getEmail())) {
				flag = false;                     // user is present 
				break;
			}
		}
		
		if (flag) {
			User u = userRepo.save(user);
			if (user != null)
			
				return u;
		}
		return null;
	}

	// add passenger list
	@Override
	public List<Passenger> addPassengerList(PassengerDto[] list) {
		List<Passenger> passList = new ArrayList<Passenger>();

		for (PassengerDto p : list) {
			Passenger savedPass = passengerService.save(DtoEntityConvertor.fromPassengerDto(p));
			passList.add(savedPass);
		}
		return passList;
	}

	@Override
	public int setFareForTicket(PassengerDto[] list, int ticketId) {
		Ticket ticket = ticketService.findById(ticketId);

		List<PassengerDto> listOfPassenger = Arrays.asList(list);
		
		int trainId = listOfPassenger.get(0).getTrainId();
		
		Train train = trainService.findById(trainId);
		
		int fare = 0;
		
		for (PassengerDto ps : listOfPassenger) {
			// Sleeper
			// Seating
			if (ps.getSeatClassName().equals("AC") && ps.getInnerType().equals("Seating")) {
				fare += train.getAcSeatingSeatPrice();
			}
			if (ps.getSeatClassName().equals("AC") && ps.getInnerType().equals("Sleeper")) {
				fare += train.getAcSleeperSeatPrice();
			}
			if (ps.getSeatClassName().equals("NON-AC") && ps.getInnerType().equals("Seating")) {
				fare += train.getNonAcSeatingSeatPrice();
			}
			if (ps.getSeatClassName().equals("NON-AC") && ps.getInnerType().equals("Sleeper")) {
				fare += train.getNonAcSleeperSeatPrice();
			}
		}
		ticket.setTicketAmount(fare);

		return fare;
	}

	@Override
	public List<Ticket> getListOfTicket(int id) {
		User user = userRepo.findById(id);
		List<Passenger> passengerList = user.getPassengerList();
		TreeSet<Ticket> treeSet = new TreeSet<>();
		List<Ticket> list = new ArrayList<Ticket>();
		
		if (!passengerList.isEmpty()) {
			for (Passenger p : passengerList) {
				treeSet.add(p.getTicket());
			}
			treeSet.forEach((s) -> {
				list.add(s);
			});
			return list;
		}
		return null;
	}
}
