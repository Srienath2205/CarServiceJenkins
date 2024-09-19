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

import com.srienath.restapp.model.ServiceCenter;
import com.srienath.restapp.repo.ServiceCenterRepository;

class ServiceCenterServiceImplTest {

    @Mock
    private ServiceCenterRepository serviceCenterRepository;

    @InjectMocks
    private ServiceCenterServiceImpl serviceCenterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        ServiceCenter serviceCenter = new ServiceCenter();
        serviceCenter.setServiceCenterName("Test Center");
        when(serviceCenterRepository.findById(1)).thenReturn(serviceCenter);

        ServiceCenter result = serviceCenterService.getById(1);
        assertEquals(serviceCenter, result);
        verify(serviceCenterRepository).findById(1);
    }

    @Test
    void testGetAll() {
        ServiceCenter serviceCenter1 = new ServiceCenter();
        ServiceCenter serviceCenter2 = new ServiceCenter();
        List<ServiceCenter> serviceCenters = Arrays.asList(serviceCenter1, serviceCenter2);
        
        when(serviceCenterRepository.findAll()).thenReturn(serviceCenters);
        
        List<ServiceCenter> result = serviceCenterService.getAll();
        assertEquals(2, result.size());
        verify(serviceCenterRepository).findAll();
    }

    @Test
    void testCreate() {
        ServiceCenter serviceCenter = new ServiceCenter();
        serviceCenterService.create(serviceCenter);
        verify(serviceCenterRepository).save(serviceCenter);
    }

    @Test
    void testUpdate() {
        ServiceCenter serviceCenter = new ServiceCenter();
        serviceCenterService.update(serviceCenter);
        verify(serviceCenterRepository).update(serviceCenter);
    }

    @Test
    void testDelete() {
        serviceCenterService.delete(1);
        verify(serviceCenterRepository).deleteById(1);
    }

}
