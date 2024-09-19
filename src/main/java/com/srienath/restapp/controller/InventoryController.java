package com.srienath.restapp.controller;

import com.srienath.restapp.model.Inventory;
import com.srienath.restapp.service.InventoryService;
import com.srienath.restapp.service.ServiceCenterService;
import com.srienath.restapp.service.StockPaymentReportService;
import com.srienath.restapp.service.SuperAdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ServiceCenterService serviceCenterService;
    private final SuperAdminService superAdminService;
    private final StockPaymentReportService stockPaymentReportService;

    public InventoryController(InventoryService inventoryService, ServiceCenterService serviceCenterService,
			SuperAdminService superAdminService, StockPaymentReportService stockPaymentReportService) {
		super();
		this.inventoryService = inventoryService;
		this.serviceCenterService = serviceCenterService;
		this.superAdminService = superAdminService;
		this.stockPaymentReportService = stockPaymentReportService;
	}

	@GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable int id) {
        Inventory inventory = inventoryService.getById(id);
        if (inventory != null) {
            return inventory;
        } else {
            throw new RuntimeException("Inventory not found");
        }
    }

    @GetMapping("/all")
    public List<Inventory> getAllInventories() {
        return inventoryService.getAll();
    }

    @PostMapping("/add")
    public String createInventory(
            @RequestParam("partName") String partName,
            @RequestParam("description") String description,
            @RequestParam("quantityInStock") int quantityInStock,
            @RequestParam("reorderLimit") int reorderLimit,
            @RequestParam("price") double price,
            @RequestParam("serviceCenterID") int serviceCenterID,
            @RequestParam("superAdminID") int superAdminID,
            @RequestParam(value = "partImage", required = false) MultipartFile partImage) {
        
        Inventory inventory = new Inventory();
        inventory.setPartName(partName);
        inventory.setDescription(description);
        inventory.setQuantityInStock(quantityInStock);
        inventory.setReorderLimit(reorderLimit);
        inventory.setPrice(price);
         inventory.setServiceCenter(serviceCenterService.getById(serviceCenterID));
         inventory.setSuperadmin(superAdminService.getById(superAdminID));

        if (partImage != null && !partImage.isEmpty()) {
            try {
                inventory.setPartImage(partImage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return "Error processing image file";
            }
        }

        try {
            inventoryService.create(inventory);
            return "Inventory created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating inventory";
        }
    }

    @PutMapping("/update/{id}")
    public String updateInventory(
            @PathVariable int id,
            @RequestParam("partName") String partName,
            @RequestParam("description") String description,
            @RequestParam("quantityInStock") int quantityInStock,
            @RequestParam("reorderLimit") int reorderLimit,
            @RequestParam("price") double price,
            @RequestParam("serviceCenterID") int serviceCenterID,
            @RequestParam("superAdminID") int superAdminID,
            @RequestParam("stockPaymentReportID") int stockPaymentReportID,
            @RequestParam(value = "partImage", required = false) MultipartFile partImage) {

        Inventory existingInventory = inventoryService.getById(id);
        if (existingInventory == null) {
            return "Inventory not found for update";
        }

        try {
            existingInventory.setPartName(partName);
            existingInventory.setDescription(description);
            existingInventory.setQuantityInStock(quantityInStock);
            existingInventory.setReorderLimit(reorderLimit);
            existingInventory.setPrice(price);

             existingInventory.setServiceCenter(serviceCenterService.getById(serviceCenterID));
             existingInventory.setSuperadmin(superAdminService.getById(superAdminID));
             existingInventory.setStockPaymentReport(stockPaymentReportService.getById(stockPaymentReportID));

            if (partImage != null && !partImage.isEmpty()) {
                existingInventory.setPartImage(partImage.getBytes());
            }

            inventoryService.update(existingInventory);
            return "Inventory updated successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing image file";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating inventory";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteInventory(@PathVariable int id) {
        Inventory existingInventory = inventoryService.getById(id);
        if (existingInventory == null) {
            return "Inventory not found for deletion";
        }
        try {
            inventoryService.delete(id);
            return "Inventory deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting inventory";
        }
    }
}

