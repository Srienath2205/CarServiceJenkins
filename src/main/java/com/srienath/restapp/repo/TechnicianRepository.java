package com.srienath.restapp.repo;

import java.util.List;
import com.srienath.restapp.model.Technician;

public interface TechnicianRepository {
	public Technician findById(int id);

    public List<Technician> findAll();

    public void save(Technician technician);

    public void update(Technician technician);

    public void deleteById(int id);
}
