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

import com.srienath.restapp.model.Vehicle;
import com.srienath.restapp.repo.VehicleRepository;

class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleID(1);
        when(vehicleRepository.findById(1)).thenReturn(vehicle);

        Vehicle result = vehicleService.getById(1);
        assertEquals(vehicle, result);
        verify(vehicleRepository).findById(1);
    }

    @Test
    void testGetAll() {
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<Vehicle> result = vehicleService.getAll();
        assertEquals(2, result.size());
        verify(vehicleRepository).findAll();
    }

    @Test
    void testCreate() {
        Vehicle vehicle = new Vehicle();
        vehicleService.create(vehicle);
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    void testUpdate() {
        Vehicle vehicle = new Vehicle();
        vehicleService.update(vehicle);
        verify(vehicleRepository).update(vehicle);
    }

    @Test
    void testDelete() {
        vehicleService.delete(1);
        verify(vehicleRepository).deleteById(1);
    }
}
