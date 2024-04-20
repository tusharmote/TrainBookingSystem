package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DeletePassenegerDto;
import com.app.dto.DtoEntityConvertor;
import com.app.dto.PassengerDto;
import com.app.entities.Passenger;
import com.app.service.IPassengerService;

@CrossOrigin
@RestController
@RequestMapping("/passengers")
public class PassengerController
{
	@Autowired
	private IPassengerService passengerService;

	@GetMapping("/")
	public ResponseEntity<?> findAll()
	{
		List<Passenger> list = passengerService.findAll();
		if (list.isEmpty())
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id)
	{
		Passenger findById = passengerService.findById(id);
		if (findById == null)
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success(findById);
	}

	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody PassengerDto dto)
	{
		Passenger passenger = DtoEntityConvertor.fromPassengerDto(dto);
		Passenger passengerToSave = passengerService.save(passenger);
		if (passengerToSave == null)
			return Response.status(HttpStatus.NOT_FOUND);
		return Response.success(passengerToSave);
	}

	
	@DeleteMapping("/deleteByUserID")
	public ResponseEntity<?> deleteByUserIdAndDate(@RequestBody DeletePassenegerDto dto)
	{
		boolean flag = passengerService.deleteByUserIdAndDate(dto);
		if (flag)
			return Response.success("DeletedeSuccessfully..!!");
		return Response.status(HttpStatus.NOT_FOUND);
	}



}
