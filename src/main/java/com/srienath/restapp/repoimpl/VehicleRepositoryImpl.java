package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Vehicle;
import com.srienath.restapp.repo.VehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VehicleRepositoryImpl implements VehicleRepository {
	 @PersistenceContext
	    private EntityManager entityManager;

	    @Override
	    public Vehicle findById(int id) {
	        return entityManager.find(Vehicle.class, id);
	    }

	    @Override
	    public List<Vehicle> findAll() {
	        return entityManager.createQuery("from Vehicle", Vehicle.class).getResultList();
	    }

	    @Override
	    public void save(Vehicle vehicle) {
	        entityManager.persist(vehicle);
	    }

	    @Override
	    public void update(Vehicle vehicle) {
	        entityManager.merge(vehicle);
	    }

	    @Override
	    public void deleteById(int id) {
	        Vehicle vehicle = findById(id);
	        if (vehicle != null) {
	            entityManager.remove(vehicle);
	        }
	    }
}
