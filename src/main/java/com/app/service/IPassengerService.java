package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.dto.DeletePassenegerDto;
import com.app.entities.Passenger;

public interface IPassengerService {
	
	List<Passenger> findAll();
	Passenger findById(int id);
	List<Passenger> findByDateOfTravelling(LocalDate date);
	Passenger save(Passenger p);
	void deleteById(int id);
	boolean deleteByUserIdAndDate(DeletePassenegerDto dto);
	List<Passenger> listByUserIdAndDate(DeletePassenegerDto dto);

}
