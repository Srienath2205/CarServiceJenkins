package com.srienath.restapp.repo;

import java.util.List;
import com.srienath.restapp.model.Vehicle;

public interface VehicleRepository {
	public Vehicle findById(int id);

    public List<Vehicle> findAll();

    public void save(Vehicle vehicle);

    public void update(Vehicle vehicle);

    public void deleteById(int id);
}
