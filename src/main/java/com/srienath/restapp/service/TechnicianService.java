package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Technician;

public interface TechnicianService {
	public Technician getById(int id);

    public List<Technician> getAll();

    public void create(Technician technician);

    public void update(Technician technician);

    public void delete(int id);
}
