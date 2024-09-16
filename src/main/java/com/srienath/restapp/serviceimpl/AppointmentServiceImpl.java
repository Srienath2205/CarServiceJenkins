package com.srienath.restapp.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.srienath.restapp.model.Appointment;
import com.srienath.restapp.repo.AppointmentRepository;
import com.srienath.restapp.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
    private AppointmentRepository appointmentRepository;
    
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
	}

	@Override
    public Appointment getById(int id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public void create(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentRepository.update(appointment);
    }

    @Override
    public void delete(int id) {
        appointmentRepository.deleteById(id);
    }
}
