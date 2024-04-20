package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.PassengerRepository;
import com.app.dto.DeletePassenegerDto;
import com.app.entities.Passenger;
import com.app.entities.Train;

@Transactional
@Service
public class PassengerServiceImpl implements IPassengerService {
	
	
	@Autowired
	private PassengerRepository passengerRepo;

	@Override
	public List<Passenger> findAll() {
		List<Passenger> list = passengerRepo.findAll();
		return list;
	}

	@Override
	public Passenger findById(int id) {
		Passenger p = passengerRepo.findById(id);
		return p;
	}

	@Override
	public List<Passenger> findByDateOfTravelling(LocalDate date) {
		List<Passenger> list = passengerRepo.findByDateOfTravelling(date);
		return list;
	}

	@Override
	public Passenger save(Passenger p) {
		Passenger passenger = passengerRepo.save(p);
		if (passenger != null)
			return passenger;
		return null;
	}

	// delete single passenger
	@Override
	public void deleteById(int id) {
		passengerRepo.deleteById(id);
	}

	// delete by specific user & travelling date
	@Override
	public boolean deleteByUserIdAndDate(DeletePassenegerDto dto) {
		List<Passenger> list = passengerRepo.findAll();
		boolean flag = false;
		for (Passenger p : list) {
			System.out.println(dto.getId() == p.getUser().getId());
			System.out.println(dto.getDateOfTravelling().equals(p.getDateOfTravelling()));
			if (dto.getId() == p.getUser().getId() && dto.getDateOfTravelling().equals(p.getDateOfTravelling())) {
				passengerRepo.deleteById(p.getId());
				flag = true;
			}
		}

		if (flag)
			return true;
		return false;
	}

	@Override
	public List<Passenger> listByUserIdAndDate(DeletePassenegerDto dto) {
		List<Passenger> list = passengerRepo.findAll();
		List<Passenger> passList = new ArrayList<Passenger>();

		for (Passenger p : list) {
			if (dto.getId() == p.getUser().getId()
					&& dto.getDateOfTravelling().toString().equals(p.getDateOfTravelling().toString())) {
				passList.add(p);

			}
		}
		return passList;
	}

}
