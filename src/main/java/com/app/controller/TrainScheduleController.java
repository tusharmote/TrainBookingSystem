package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddScheduleDto;
import com.app.dto.TrainScheduleDto;
import com.app.entities.TrainSchedule;
import com.app.service.ITrainScheduleService;

@CrossOrigin
@RestController
@RequestMapping("/trainSchedule")
public class TrainScheduleController
{
	@Autowired
	private ITrainScheduleService trainScheduleService;

	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody TrainScheduleDto dto)
	{
		TrainSchedule trainSchedule = trainScheduleService.save(dto);
		return Response.success(trainSchedule);
	}

	@PostMapping("/addSchedule")
	public ResponseEntity<?> addSchedule(@RequestBody AddScheduleDto dto)
	{
		boolean isAvailable = trainScheduleService.isTrainScheduleAvailable(dto);
		if (isAvailable)
		{
			TrainSchedule ac = trainScheduleService.addScheduleForAc(dto);
			TrainSchedule nonAc = trainScheduleService.addScheduleForNonAc(dto);
			System.out.println("Sam");
			if (ac != null && nonAc != null)
				return Response.success(ac);
		}
		return Response.error("Schedule already present..!!");
	}

	

}
