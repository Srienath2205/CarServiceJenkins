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

import com.srienath.restapp.model.Inventory;
import com.srienath.restapp.repo.InventoryRepository;

class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Inventory inventory = new Inventory(1, "Part A", null, "Description A", 10, 5, 100.0, null, null, null);
        when(inventoryRepository.getById(1)).thenReturn(inventory);
        
        Inventory result = inventoryService.getById(1);
        assertEquals(inventory, result);
        verify(inventoryRepository).getById(1);
    }

    @Test
    void testGetAll() {
        List<Inventory> inventories = Arrays.asList(
            new Inventory(1, "Part A", null, "Description A", 10, 5, 100.0, null, null, null),
            new Inventory(2, "Part B", null, "Description B", 15, 3, 150.0, null, null, null)
        );
        when(inventoryRepository.getAll()).thenReturn(inventories);
        
        List<Inventory> result = inventoryService.getAll();
        assertEquals(2, result.size());
        verify(inventoryRepository).getAll();
    }

    @Test
    void testCreate() {
        Inventory inventory = new Inventory(1, "Part A", null, "Description A", 10, 5, 100.0, null, null, null);
        
        inventoryService.create(inventory);
        verify(inventoryRepository).create(inventory);
    }

    @Test
    void testUpdate() {
        Inventory inventory = new Inventory(1, "Part A", null, "Description A", 10, 5, 100.0, null, null, null);
        
        inventoryService.update(inventory);
        verify(inventoryRepository).update(inventory);
    }

    @Test
    void testDelete() {
        inventoryService.delete(1);
        verify(inventoryRepository).delete(1);
    }
}

