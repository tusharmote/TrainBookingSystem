package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TrainRepository;
import com.app.dto.StartAndDestCityDto;
import com.app.entities.Train;
import com.app.entities.TrainSchedule;

@Transactional
@Service
public class TrainServiceImpl implements ITrainService {
	@Autowired
	private TrainRepository trainRepo;

	@Override
	public List<Train> findAll() {
		return trainRepo.findAll();
	}

	@Override
	public Train findById(int id) {
		return trainRepo.findById(id).get();
	}

	@Override
	public int deleteById(int id) {
		if (trainRepo.existsById(id)) {
			trainRepo.deleteById(id);
			return 1;
		}
		return 0;
	}

	@Override
	public Train save(Train t) {
		Train train = trainRepo.save(t);
		if (train != null)
			return train;
		return null;
	}

	@Override
	public Train saveWithTotalCount(Train t) {
		t.setTotalSeatCount(t.getAcSeatingSeatCount() + t.getAcSleeperSeatCount() + t.getNonAcSeatingSeatCount()
				+ t.getNonAcSleeperSeatCount());
		Train train = trainRepo.save(t);
		if (train != null)
			return train;
		return null;
	}

	@Override
	public List<TrainSchedule> getListOfTrainSchedule(int id) {
		Train train = trainRepo.findById(id).get();
		return train.getTrainScheduleList();
	}

	@Override
	public Train specificTrain(StartAndDestCityDto dto) {
		List<Train> list = trainRepo.findAll();
		for (Train s : list) {
			if (s.getStartCity().equals(dto.getStartCity()) && s.getDestCity().equals(dto.getDestCity())) {
				return s;
			}
		}
		return null;
	}

	@Override
	public List<String> listOfdestCity(String sourceCity) {
		List<Train> list = trainRepo.findAll();
		List<String> listOfDestCity = new ArrayList<String>();
		for (Train s : list) {
			if (s.getStartCity().equals(sourceCity))
				listOfDestCity.add(s.getDestCity());
		}
		if (!listOfDestCity.isEmpty())
			return listOfDestCity;
		return null;
	}

	@Override
	public List<String> listOfstartCity() {
		TreeSet<String> treeSet = new TreeSet<String>();
		List<Train> list = trainRepo.findAll();
		List<String> listOfStartCity = new ArrayList<String>();

		for (Train t : list) {
			treeSet.add(t.getStartCity());
		}
		treeSet.forEach((s) -> {
			listOfStartCity.add(s);
		});

		if (!listOfStartCity.isEmpty())
			return listOfStartCity;
		return null;
	}

	@Override
	public TreeSet<LocalDate> selectDate(int id) {
		Train train = trainRepo.findById(id).get();
		List<TrainSchedule> listOfTrainSchedule = train.getTrainScheduleList();

		TreeSet<LocalDate> dates = new TreeSet<LocalDate>();

		for (TrainSchedule c : listOfTrainSchedule) {
			dates.add(c.getDateOfTravelling());
		}
		return dates;
	}

}
