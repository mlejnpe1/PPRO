package cz.uhk.ppro.ppro.repository;

import cz.uhk.ppro.ppro.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    
}
