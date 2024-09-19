package com.srienath.restapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.srienath.restapp.model.Appointment;
import com.srienath.restapp.repo.AppointmentRepository;

class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Appointment appointment = new Appointment(1, null, null, new Date(), new Time(System.currentTimeMillis()), "Service A", "Scheduled");
        when(appointmentRepository.findById(1)).thenReturn(appointment);
        
        Appointment result = appointmentService.getById(1);
        assertEquals(appointment, result);
        verify(appointmentRepository).findById(1);
    }

    @Test
    void testGetAll() {
        List<Appointment> appointments = Arrays.asList(
            new Appointment(1, null, null, new Date(), new Time(System.currentTimeMillis()), "Service A", "Scheduled"),
            new Appointment(2, null, null, new Date(), new Time(System.currentTimeMillis()), "Service B", "Scheduled")
        );
        when(appointmentRepository.findAll()).thenReturn(appointments);
        
        List<Appointment> result = appointmentService.getAll();
        assertEquals(2, result.size());
        verify(appointmentRepository).findAll();
    }

    @Test
    void testCreate() {
        Appointment appointment = new Appointment(1, null, null, new Date(), new Time(System.currentTimeMillis()), "Service A", "Scheduled");
        
        appointmentService.create(appointment);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void testUpdate() {
        Appointment appointment = new Appointment(1, null, null, new Date(), new Time(System.currentTimeMillis()), "Service A", "Scheduled");
        
        appointmentService.update(appointment);
        verify(appointmentRepository).update(appointment);
    }

    @Test
    void testDelete() {
        appointmentService.delete(1);
        verify(appointmentRepository).deleteById(1);
    }
}

