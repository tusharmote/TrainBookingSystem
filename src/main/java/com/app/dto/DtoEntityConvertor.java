package com.app.dto;

import org.springframework.stereotype.Component;

import com.app.entities.Passenger;
import com.app.entities.Ticket;
import com.app.entities.Train;
import com.app.entities.TrainSchedule;
import com.app.entities.User;

@Component
public class DtoEntityConvertor
{

	
	public static TrainSchedule toTrainScheduleDto(TrainScheduleDto dto)
	{
		TrainSchedule cmt = new TrainSchedule();
		cmt.setId(dto.getId());
		Train train = new Train();

		train.setId(dto.getTrainId());
		cmt.setTrain(train);

		cmt.setDateOfTravelling(dto.getDateOfTravelling());
		cmt.setSeatClassName(dto.getSeatClassName());
		cmt.setSeatingSeatCount(dto.getSeatingSeatCount());
		cmt.setSleeperSeatCount(dto.getSleeperSeatCount());
		cmt.setSeatingSeatPrice(dto.getSeatingSeatPrice());
		cmt.setSleeperSeatPrice(dto.getSleeperSeatPrice());
		cmt.setTotalSeatCount(dto.getTotalSeatCount());
		return cmt;
	}

	public static Passenger fromPassengerDto(PassengerDto dto)
	{
		Passenger passenger = new Passenger();
		passenger.setId(dto.getId());
		
		User user = new User();
		Train train = new Train();
		Ticket ticket = new Ticket();

		user.setId(dto.getUserId());
		passenger.setUser(user);

		train.setId(dto.getTrainId());
		passenger.setTrain(train);

		ticket.setId(dto.getTicketId());
		passenger.setTicket(ticket);

		passenger.setAge(dto.getAge());
		passenger.setDateOfTravelling(dto.getDateOfTravelling());
		passenger.setFirstName(dto.getFirstName());
		passenger.setLastName(dto.getLastName());
		passenger.setGender(dto.getGender());
		passenger.setInnerType(dto.getInnerType());
		passenger.setSeatClassName(dto.getSeatClassName());
		System.out.println(dto.getSeatClassName());
		return passenger;
	}
	

	public static TrainSchedule addScheduleForAc(AddScheduleDto dto, Train train)
	{
		TrainSchedule trainSchedule = new TrainSchedule();

		trainSchedule.setDateOfTravelling(dto.getDateOfTravelling());
		trainSchedule.setSeatClassName("AC");
		trainSchedule.setSeatingSeatCount(train.getAcSeatingSeatCount());
		trainSchedule.setSeatingSeatPrice(train.getAcSeatingSeatPrice());
		trainSchedule.setSleeperSeatCount(train.getAcSleeperSeatCount());
		trainSchedule.setSleeperSeatPrice(train.getAcSleeperSeatPrice());
		trainSchedule.setTotalSeatCount(trainSchedule.getSeatingSeatCount() + trainSchedule.getSleeperSeatCount());
		trainSchedule.setTrain(train);

		return trainSchedule;
	}
	
	public static TrainSchedule addScheduleForNonAc(AddScheduleDto dto, Train train)
	{
		TrainSchedule trainSchedule = new TrainSchedule();
		trainSchedule.setDateOfTravelling(dto.getDateOfTravelling());
		trainSchedule.setSeatClassName("NON-AC");
		trainSchedule.setSeatingSeatCount(train.getNonAcSeatingSeatCount());
		trainSchedule.setSeatingSeatPrice(train.getNonAcSeatingSeatPrice());
		trainSchedule.setSleeperSeatCount(train.getNonAcSleeperSeatCount());
		trainSchedule.setSleeperSeatPrice(train.getNonAcSleeperSeatPrice());
		trainSchedule.setTotalSeatCount(trainSchedule.getSeatingSeatCount() + trainSchedule.getSleeperSeatCount());
		trainSchedule.setTrain(train);
		return trainSchedule;
	}
}
