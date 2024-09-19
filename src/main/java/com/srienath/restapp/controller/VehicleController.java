package com.srienath.restapp.controller;

import com.srienath.restapp.model.Vehicle;
import com.srienath.restapp.service.VehicleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable int id) {
        Vehicle vehicle = vehicleService.getById(id);
        if (vehicle != null) {
            return vehicle;
        } else {
            throw new RuntimeException("Vehicle not found");
        }
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAll();
    }

    @PostMapping("/add")
    public String createVehicle(
            @RequestParam("make") String make,
            @RequestParam("model") String model,
            @RequestParam("year") int year,
            @RequestParam("vin") String vin,
            @RequestParam("customerID") int customerID,
            @RequestParam(value = "vehicleImage", required = false) MultipartFile vehicleImageFile,
            @RequestParam(value = "registrationCertificate", required = false) MultipartFile registrationCertificateFile) {

        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setCustomerID(customerID);
            vehicle.setMake(make);
            vehicle.setModel(model);
            vehicle.setYear(year);
            vehicle.setVin(vin);

            // Handle vehicleImage file upload
            if (vehicleImageFile != null && !vehicleImageFile.isEmpty()) {
                try {
                    byte[] vehicleImageBytes = vehicleImageFile.getBytes();
                    vehicle.setVehicleImage(vehicleImageBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error processing vehicle image file";
                }
            }

            // Handle registrationCertificate file upload
            if (registrationCertificateFile != null && !registrationCertificateFile.isEmpty()) {
                try {
                    byte[] registrationCertificateBytes = registrationCertificateFile.getBytes();
                    vehicle.setRegistrationCertificate(registrationCertificateBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error processing registration certificate file";
                }
            }

            vehicleService.create(vehicle);
            return "Vehicle created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating Vehicle";
        }
    }

    @PutMapping("/update/{id}")
    public String updateVehicle(
            @PathVariable int id,
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "vin", required = false) String vin,
            @RequestParam(value = "vehicleImage", required = false) MultipartFile vehicleImageFile,
            @RequestParam(value = "registrationCertificate", required = false) MultipartFile registrationCertificateFile) {

        Vehicle existingVehicle = vehicleService.getById(id);
        if (existingVehicle == null) {
            return "Vehicle not found for update";
        }

        try {
            if (make != null) {
                existingVehicle.setMake(make);
            }
            if (model != null) {
                existingVehicle.setModel(model);
            }
            if (year != null) {
                existingVehicle.setYear(year);
            }
            if (vin != null) {
                existingVehicle.setVin(vin);
            }
            if (vehicleImageFile != null && !vehicleImageFile.isEmpty()) {
                try {
                    byte[] vehicleImageBytes = vehicleImageFile.getBytes();
                    existingVehicle.setVehicleImage(vehicleImageBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error processing vehicle image file";
                }
            }
            if (registrationCertificateFile != null && !registrationCertificateFile.isEmpty()) {
                try {
                    byte[] registrationCertificateBytes = registrationCertificateFile.getBytes();
                    existingVehicle.setRegistrationCertificate(registrationCertificateBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error processing registration certificate file";
                }
            }

            vehicleService.update(existingVehicle);
            return "Vehicle updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating Vehicle";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable int id) {
        Vehicle existingVehicle = vehicleService.getById(id);
        if (existingVehicle != null) {
            try {
                vehicleService.delete(id);
                return "Vehicle deleted successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error deleting Vehicle";
            }
        } else {
            return "Vehicle not found";
        }
    }
}
