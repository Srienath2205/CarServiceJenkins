package com.srienath.restapp.repo;

import java.util.List;
import com.srienath.restapp.model.Appointment;

public interface AppointmentRepository {
	public Appointment findById(int id);

    public List<Appointment> findAll();

    public void save(Appointment appointment);

    public void update(Appointment appointment);

    public void deleteById(int id);
}
