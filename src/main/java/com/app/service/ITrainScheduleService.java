package com.app.service;

import java.util.List;

import com.app.dto.AddScheduleDto;
import com.app.dto.TrainScheduleDto;
import com.app.entities.TrainSchedule;

public interface ITrainScheduleService {
	
	List<TrainSchedule> findAll();
	TrainSchedule save(TrainScheduleDto dto);
	TrainSchedule addScheduleForAc(AddScheduleDto dto);
	TrainSchedule addScheduleForNonAc(AddScheduleDto dto);
	boolean isTrainScheduleAvailable(AddScheduleDto dto);

}
