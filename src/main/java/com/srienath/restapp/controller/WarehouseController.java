package com.srienath.restapp.controller;

import com.srienath.restapp.model.Warehouse;
import com.srienath.restapp.service.WarehouseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin(origins = "http://localhost:3000")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable int id) {
        Warehouse warehouse = warehouseService.getById(id);
        if (warehouse != null) {
            return warehouse;
        } else {
            throw new RuntimeException("Warehouse not found");
        }
    }

    @GetMapping("/all")
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAll();
    }

    @PostMapping("/add")
    public String createWarehouse(@RequestBody Warehouse warehouse) {
        if (warehouse == null) {
            return "Error: Warehouse data is missing";
        }
        try {
            warehouseService.create(warehouse);
            return "Warehouse created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating Warehouse";
        }
    }

    @PutMapping("/update/{id}")
    public String updateWarehouse(@PathVariable int id, @RequestBody Warehouse warehouse) {
        Warehouse existingWarehouse = warehouseService.getById(id);
        if (existingWarehouse != null) {
            warehouse.setWarehouseID(id);
            try {
                warehouseService.update(warehouse);
                return "Warehouse updated successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error updating Warehouse";
            }
        } else {
            return "Warehouse not found for update";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable int id) {
        Warehouse existingWarehouse = warehouseService.getById(id);
        if (existingWarehouse != null) {
            try {
                warehouseService.delete(id);
                return "Warehouse deleted successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error deleting Warehouse";
            }
        } else {
            return "Warehouse not found for deletion";
        }
    }
}
