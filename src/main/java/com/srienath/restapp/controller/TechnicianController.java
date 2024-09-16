package com.srienath.restapp.controller;

import com.srienath.restapp.model.Technician;
import com.srienath.restapp.service.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/technician")
@CrossOrigin(origins = "http://localhost:3000")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("/{id}")
    public Technician getTechnicianById(@PathVariable int id) {
        Technician technician = technicianService.getById(id);
        if (technician != null) {
            return technician;
        } else {
            throw new RuntimeException("Technician not found");
        }
    }

    @GetMapping("/all")
    public List<Technician> getAllTechnicians() {
        return technicianService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTechnician(
            @RequestParam("name") String name,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("email") String email,
            @RequestParam("skills") String skills,
            @RequestParam("rating") int rating,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        Technician technician = new Technician();
        technician.setName(name);
        technician.setContactNumber(contactNumber);
        technician.setEmail(email);
        technician.setSkills(skills);
        technician.setRating(rating);

        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                technician.setProfileImage(profileImage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Error processing profile image file");
            }
        }

        try {
            technicianService.create(technician);
            return ResponseEntity.ok("Technician created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error creating Technician");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTechnician(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("email") String email,
            @RequestParam("skills") String skills,
            @RequestParam("rating") int rating,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        Technician existingTechnician = technicianService.getById(id);
        if (existingTechnician == null) {
            return ResponseEntity.badRequest().body("Technician not found for update");
        }

        try {
            existingTechnician.setName(name);
            existingTechnician.setContactNumber(contactNumber);
            existingTechnician.setEmail(email);
            existingTechnician.setSkills(skills);
            existingTechnician.setRating(rating);

            if (profileImage != null && !profileImage.isEmpty()) {
                try {
                    existingTechnician.setProfileImage(profileImage.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(500).body("Error processing profile image file");
                }
            }

            technicianService.update(existingTechnician);
            return ResponseEntity.ok("Technician updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error updating Technician");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTechnician(@PathVariable int id) {
        Technician existingTechnician = technicianService.getById(id);
        if (existingTechnician == null) {
            return ResponseEntity.badRequest().body("Technician not found for deletion");
        }
        try {
            technicianService.delete(id);
            return ResponseEntity.ok("Technician deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting Technician");
        }
    }
}
