package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    public List<Car> getAllCars();
    public Car getCarById(long id);
    public void deleteCarById(long id);
    void saveCar(Car car);
}
