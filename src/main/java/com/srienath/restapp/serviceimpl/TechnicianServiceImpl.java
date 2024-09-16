package com.srienath.restapp.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.srienath.restapp.model.Technician;
import com.srienath.restapp.repo.TechnicianRepository;
import com.srienath.restapp.service.TechnicianService;

@Service
public class TechnicianServiceImpl implements TechnicianService {
	private TechnicianRepository technicianRepository;

    public TechnicianServiceImpl(TechnicianRepository technicianRepository) {
		super();
		this.technicianRepository = technicianRepository;
	}

	@Override
    public Technician getById(int id) {
        return technicianRepository.findById(id);
    }

    @Override
    public List<Technician> getAll() {
        return technicianRepository.findAll();
    }

    @Override
    public void create(Technician technician) {
        technicianRepository.save(technician);
    }

    @Override
    public void update(Technician technician) {
        technicianRepository.update(technician);
    }

    @Override
    public void delete(int id) {
        technicianRepository.deleteById(id);
    }
}
