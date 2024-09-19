package com.srienath.restapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.srienath.restapp.model.Appointment;
import com.srienath.restapp.model.Job;
import com.srienath.restapp.model.Technician;
import com.srienath.restapp.repo.JobRepository;

class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Appointment appointment = new Appointment();
        Technician technician = new Technician();
        Job job = new Job(1, appointment, technician, "Pending", LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        when(jobRepository.findById(1)).thenReturn(job);
        
        Job result = jobService.getById(1);
        assertEquals(job, result);
        verify(jobRepository).findById(1);
    }

    @Test
    void testGetAll() {
        Appointment appointment1 = new Appointment();
        Technician technician1 = new Technician();
        Job job1 = new Job(1, appointment1, technician1, "Pending", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        
        Appointment appointment2 = new Appointment();
        Technician technician2 = new Technician();
        Job job2 = new Job(2, appointment2, technician2, "Completed", LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        List<Job> jobs = Arrays.asList(job1, job2);
        when(jobRepository.findAll()).thenReturn(jobs);
        
        List<Job> result = jobService.getAll();
        assertEquals(2, result.size());
        verify(jobRepository).findAll();
    }

    @Test
    void testCreate() {
        Appointment appointment = new Appointment();
        Technician technician = new Technician();
        Job job = new Job(1, appointment, technician, "Pending", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        
        jobService.create(job);
        verify(jobRepository).save(job);
    }

    @Test
    void testUpdate() {
        Appointment appointment = new Appointment();
        Technician technician = new Technician();
        Job job = new Job(1, appointment, technician, "Pending", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        
        jobService.update(job);
        verify(jobRepository).update(job);
    }

    @Test
    void testDelete() {
        jobService.delete(1);
        verify(jobRepository).deleteById(1);
    }
}
