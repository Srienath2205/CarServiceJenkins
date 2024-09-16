package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.Vehicle;
import com.srienath.restapp.repo.VehicleRepository;
import com.srienath.restapp.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
	private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
	}

	@Override
    public Vehicle getById(int id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public void create(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleRepository.update(vehicle);
    }

    @Override
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }
}
