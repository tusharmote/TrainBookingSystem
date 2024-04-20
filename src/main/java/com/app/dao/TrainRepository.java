package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Train;

public interface TrainRepository extends JpaRepository<Train, Integer>
{
//	Train findById(int id);
	
}
