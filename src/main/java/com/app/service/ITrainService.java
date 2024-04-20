package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import com.app.dto.StartAndDestCityDto;
import com.app.entities.Train;
import com.app.entities.TrainSchedule;

public interface ITrainService {
	
	List<Train> findAll();
	Train findById(int id);
	int deleteById(int id);
	Train save(Train t);
	Train saveWithTotalCount(Train t);
	List<TrainSchedule> getListOfTrainSchedule(int id);
	Train specificTrain(StartAndDestCityDto dto);
	List<String> listOfdestCity(String from);
	List<String> listOfstartCity();
	TreeSet<LocalDate> selectDate(int id);
	

}
