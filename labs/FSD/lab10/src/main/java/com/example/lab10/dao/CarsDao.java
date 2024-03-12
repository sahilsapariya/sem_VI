package com.example.lab10.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab10.entities.Cars;

public interface CarsDao extends JpaRepository<Cars, Long> {

}