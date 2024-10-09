package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CarService {

    public ArrayList<Car> getAllCars();
    public Car getCarById(int id);
    public void deleteCarById(int id);
    void saveCar(Car car);
}
