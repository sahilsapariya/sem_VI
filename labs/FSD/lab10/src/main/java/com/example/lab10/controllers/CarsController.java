package com.example.lab10.controllers;

import com.example.lab10.entities.Cars;
import com.example.lab10.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000" , allowedHeaders = "*" , allowCredentials = "true")
public class CarsController {
    @Autowired
    public CarsService carsService;

    @GetMapping("")
    public List<Cars> getCars() {
        return carsService.getCars();
    }

    @GetMapping("/{carId}")
    public Cars getCar(@PathVariable long carId) {
        return carsService.getCar(carId);
    }

    @PostMapping("")
    public Cars addCar(@RequestBody Cars car) {
        return carsService.addCar(car);
    }

    @PutMapping("")
    public Cars updateCar(@RequestBody Cars car) {
        return carsService.updateCar(car);
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable long carId) {
        this.carsService.deleteCar(carId);
    }
}
