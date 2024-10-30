package cz.uhk.ppro.ppro.service;

import java.util.List;
import cz.uhk.ppro.ppro.model.Driver;

public interface DriverService {
    public List<Driver> getAllDrivers();
    public Driver getDriverById(long id);
    public void deleteDriverById(long id);
    void saveDriver(Driver driver);
}
