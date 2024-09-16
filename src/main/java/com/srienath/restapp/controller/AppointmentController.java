package com.srienath.restapp.controller;

import com.srienath.restapp.model.Appointment;
import com.srienath.restapp.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        Appointment appointment = appointmentService.getById(id);
        if (appointment != null) {
            return appointment;
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }

    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAll();
    }

    @PostMapping("/add")
    public String createAppointment(@RequestBody Appointment appointment) {
        try {
            appointmentService.create(appointment);
            return "Appointment created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating appointment";
        }
    }

    @PutMapping("/update/{id}")
    public String updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        Appointment existingAppointment = appointmentService.getById(id);
        if (existingAppointment == null) {
            return "Appointment not found for update";
        }
        try {
            appointment.setAppointmentID(id);
            appointmentService.update(appointment);
            return "Appointment updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating appointment";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable int id) {
        Appointment existingAppointment = appointmentService.getById(id);
        if (existingAppointment == null) {
            return "Appointment not found for deletion";
        }
        try {
            appointmentService.delete(id);
            return "Appointment deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting appointment";
        }
    }
}

