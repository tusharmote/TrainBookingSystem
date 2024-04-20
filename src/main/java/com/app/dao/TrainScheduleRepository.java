package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.TrainSchedule;

public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Integer>
{
	List<TrainSchedule> findByTrainIdAndDateOfTravelling(int id, LocalDate date);;
}
