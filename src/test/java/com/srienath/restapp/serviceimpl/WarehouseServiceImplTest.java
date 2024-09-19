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

import com.srienath.restapp.model.Warehouse;
import com.srienath.restapp.repo.WarehouseRepository;

class WarehouseServiceImplTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseID(1);
        when(warehouseRepository.findById(1)).thenReturn(warehouse);

        Warehouse result = warehouseService.getById(1);
        assertEquals(warehouse, result);
        verify(warehouseRepository).findById(1);
    }

    @Test
    void testGetAll() {
        Warehouse warehouse1 = new Warehouse();
        Warehouse warehouse2 = new Warehouse();
        List<Warehouse> warehouses = Arrays.asList(warehouse1, warehouse2);

        when(warehouseRepository.findAll()).thenReturn(warehouses);

        List<Warehouse> result = warehouseService.getAll();
        assertEquals(2, result.size());
        verify(warehouseRepository).findAll();
    }

    @Test
    void testCreate() {
        Warehouse warehouse = new Warehouse();
        warehouseService.create(warehouse);
        verify(warehouseRepository).save(warehouse);
    }

    @Test
    void testUpdate() {
        Warehouse warehouse = new Warehouse();
        warehouseService.update(warehouse);
        verify(warehouseRepository).update(warehouse);
    }

    @Test
    void testDelete() {
        warehouseService.delete(1);
        verify(warehouseRepository).deleteById(1);
    }
}
