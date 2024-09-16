package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Appointment;

public interface AppointmentService {
	public Appointment getById(int id);

    public List<Appointment> getAll();

    public void create(Appointment appointment);

    public void update(Appointment appointment);

    public void delete(int id);
}
