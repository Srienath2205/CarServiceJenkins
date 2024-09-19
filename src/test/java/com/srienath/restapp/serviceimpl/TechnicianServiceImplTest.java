package com.srienath.restapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.srienath.restapp.model.Technician;
import com.srienath.restapp.repo.TechnicianRepository;

class TechnicianServiceImplTest {

    @Mock
    private TechnicianRepository technicianRepository;

    @InjectMocks
    private TechnicianServiceImpl technicianService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Technician technician = new Technician();
        technician.setTechnicianID(1);
        when(technicianRepository.findById(1)).thenReturn(technician);

        Technician result = technicianService.getById(1);
        assertEquals(technician, result);
        verify(technicianRepository).findById(1);
    }

    @Test
    void testGetAll() {
        Technician technician1 = new Technician();
        Technician technician2 = new Technician();
        List<Technician> technicians = Arrays.asList(technician1, technician2);

        when(technicianRepository.findAll()).thenReturn(technicians);

        List<Technician> result = technicianService.getAll();
        assertEquals(2, result.size());
        verify(technicianRepository).findAll();
    }

    @Test
    void testCreate() {
        Technician technician = new Technician();
        technicianService.create(technician);
        verify(technicianRepository).save(technician);
    }

    @Test
    void testUpdate() {
        Technician technician = new Technician();
        technicianService.update(technician);
        verify(technicianRepository).update(technician);
    }

    @Test
    void testDelete() {
        technicianService.delete(1);
        verify(technicianRepository).deleteById(1);
    }
}
