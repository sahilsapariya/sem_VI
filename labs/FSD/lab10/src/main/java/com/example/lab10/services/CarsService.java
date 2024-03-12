package com.example.lab10.services;

import com.example.lab10.entities.Cars;

import java.util.List;

public interface CarsService {
    public List<Cars> getCars();
    public Cars getCar(long id);
    public Cars addCar(Cars car);
    public Cars updateCar(Cars car);
    public void deleteCar(long id);
}
