package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Vehicle;

public interface VehicleService {
	public Vehicle getById(int id);

    public List<Vehicle> getAll();

    public void create(Vehicle vehicle);

    public void update(Vehicle vehicle);

    public void delete(int id);
}
