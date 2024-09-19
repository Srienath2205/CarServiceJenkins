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

import com.srienath.restapp.model.ServiceReport;
import com.srienath.restapp.repo.ServiceReportRepository;

class ServiceReportServiceImplTest {

    @Mock
    private ServiceReportRepository serviceReportRepository;

    @InjectMocks
    private ServiceReportServiceImpl serviceReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        ServiceReport serviceReport = new ServiceReport();
        serviceReport.setId(1);
        when(serviceReportRepository.findById(1)).thenReturn(serviceReport);

        ServiceReport result = serviceReportService.getById(1);
        assertEquals(serviceReport, result);
        verify(serviceReportRepository).findById(1);
    }

    @Test
    void testGetAll() {
        ServiceReport serviceReport1 = new ServiceReport();
        ServiceReport serviceReport2 = new ServiceReport();
        List<ServiceReport> serviceReports = Arrays.asList(serviceReport1, serviceReport2);

        when(serviceReportRepository.findAll()).thenReturn(serviceReports);

        List<ServiceReport> result = serviceReportService.getAll();
        assertEquals(2, result.size());
        verify(serviceReportRepository).findAll();
    }

    @Test
    void testCreate() {
        ServiceReport serviceReport = new ServiceReport();
        serviceReportService.create(serviceReport);
        verify(serviceReportRepository).save(serviceReport);
    }

    @Test
    void testUpdate() {
        ServiceReport serviceReport = new ServiceReport();
        serviceReportService.update(serviceReport);
        verify(serviceReportRepository).update(serviceReport);
    }

    @Test
    void testDelete() {
        serviceReportService.delete(1);
        verify(serviceReportRepository).deleteById(1);
    }
}
