package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Driver;

import java.util.ArrayList;

public interface DriverService {
    public ArrayList<Driver> getAllDrivers();
    public Driver getDriverById(int id);
    public void deleteDriverById(int id);
    void saveDriver(Driver driver);
}
