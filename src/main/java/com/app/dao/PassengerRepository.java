package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>
{
	Passenger findById(int id);

	List<Passenger> findByDateOfTravelling(LocalDate date);
}
