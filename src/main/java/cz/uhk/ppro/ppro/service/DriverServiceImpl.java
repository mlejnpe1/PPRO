package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Driver;
import cz.uhk.ppro.ppro.repository.CarRepository;
import cz.uhk.ppro.ppro.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService{

    private final CarRepository carRepository;
    private DriverRepository driverRepository;

        @Autowired
        public DriverServiceImpl(DriverRepository driverRepository, CarRepository carRepository) {
            this.driverRepository = driverRepository;
            this.carRepository = carRepository;
        }

        @Override
        public List<Driver> getAllDrivers() {
            return driverRepository.findAll();
        }

        @Override
        public Driver getDriverById(long id){
            return driverRepository.findById(id).orElse(null);
        }

        @Override
        public void deleteDriverById(long id){
            Optional<Driver> driver = driverRepository.findById(id);
            if(driver.isPresent()){
                driverRepository.delete(driver.get());
            }
        }

        @Override
        public void saveDriver(Driver driver){
            driverRepository.save(driver);
        }
}
