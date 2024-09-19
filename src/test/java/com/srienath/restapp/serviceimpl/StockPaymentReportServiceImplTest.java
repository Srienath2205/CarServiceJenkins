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

import com.srienath.restapp.model.StockPaymentReport;
import com.srienath.restapp.repo.StockPaymentReportRepository;

class StockPaymentReportServiceImplTest {

    @Mock
    private StockPaymentReportRepository stockPaymentReportRepository;

    @InjectMocks
    private StockPaymentReportServiceImpl stockPaymentReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        StockPaymentReport report = new StockPaymentReport();
        report.setId(1);
        when(stockPaymentReportRepository.findById(1)).thenReturn(report);

        StockPaymentReport result = stockPaymentReportService.getById(1);
        assertEquals(report, result);
        verify(stockPaymentReportRepository).findById(1);
    }

    @Test
    void testGetAll() {
        StockPaymentReport report1 = new StockPaymentReport();
        StockPaymentReport report2 = new StockPaymentReport();
        List<StockPaymentReport> reports = Arrays.asList(report1, report2);

        when(stockPaymentReportRepository.findAll()).thenReturn(reports);

        List<StockPaymentReport> result = stockPaymentReportService.getAll();
        assertEquals(2, result.size());
        verify(stockPaymentReportRepository).findAll();
    }

    @Test
    void testCreate() {
        StockPaymentReport report = new StockPaymentReport();
        stockPaymentReportService.create(report);
        verify(stockPaymentReportRepository).save(report);
    }

    @Test
    void testUpdate() {
        StockPaymentReport report = new StockPaymentReport();
        stockPaymentReportService.update(report);
        verify(stockPaymentReportRepository).update(report);
    }

    @Test
    void testDelete() {
        stockPaymentReportService.delete(1);
        verify(stockPaymentReportRepository).deleteById(1);
    }
}

