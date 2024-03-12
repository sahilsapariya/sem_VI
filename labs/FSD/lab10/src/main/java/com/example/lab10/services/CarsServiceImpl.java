package com.example.lab10.services;

import com.example.lab10.dao.CarsDao;
import com.example.lab10.entities.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    public CarsDao carsDao;
    public CarsServiceImpl() {

    }

    @Override
    public List<Cars> getCars() {
        return carsDao.findAll();
    }

    @Override
    public Cars getCar(long id) {
        List<Cars> cars = carsDao.findAll();

        for (Cars car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Cars addCar(Cars car) {
        return carsDao.save(car);
    }

    @Override
    public Cars updateCar(Cars car) {
        return carsDao.save(car);
    }

    @Override
    public void deleteCar(long id) {
        try {
            Cars car = carsDao.getReferenceById(id);
            carsDao.delete(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
