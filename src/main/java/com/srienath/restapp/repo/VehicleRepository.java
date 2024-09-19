package com.srienath.restapp.repo;

import java.util.List;
import com.srienath.restapp.model.Vehicle;

public interface VehicleRepository {
	
	Vehicle findById(int id);
	
    List<Vehicle> findAll();
    
    void save(Vehicle vehicle);
    
    void update(Vehicle vehicle);
    
    void deleteById(int id);
    
}
