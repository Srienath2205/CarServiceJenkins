package com.srienath.restapp.controller;

import com.srienath.restapp.model.SuperAdmin;
import com.srienath.restapp.service.SuperAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superadmin")
@CrossOrigin(origins = "http://localhost:3000")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperAdmin> getSuperAdminById(@PathVariable int id) {
        SuperAdmin superAdmin = superAdminService.getById(id);
        if (superAdmin != null) {
            return ResponseEntity.ok(superAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SuperAdmin>> getAllSuperAdmins() {
        List<SuperAdmin> superAdmins = superAdminService.getAll();
        return ResponseEntity.ok(superAdmins);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        if (superAdmin == null) {
            return ResponseEntity.badRequest().body("Error: SuperAdmin data is missing");
        }
        try {
            superAdminService.create(superAdmin);
            return ResponseEntity.ok("SuperAdmin created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating SuperAdmin");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSuperAdmin(@PathVariable int id, @RequestBody SuperAdmin superAdmin) {
        SuperAdmin existingSuperAdmin = superAdminService.getById(id);
        if (existingSuperAdmin == null) {
            return ResponseEntity.badRequest().body("SuperAdmin not found for update");
        }
        try {
            superAdmin.setSuperAdminID(id);
            superAdminService.update(superAdmin);
            return ResponseEntity.ok("SuperAdmin updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating SuperAdmin");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSuperAdmin(@PathVariable int id) {
        SuperAdmin existingSuperAdmin = superAdminService.getById(id);
        if (existingSuperAdmin == null) {
            return ResponseEntity.badRequest().body("SuperAdmin not found for deletion");
        }
        try {
            superAdminService.delete(id);
            return ResponseEntity.ok("SuperAdmin deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting SuperAdmin");
        }
    }
   
    @GetMapping("/login/{email}/{password}")
    public SuperAdmin loginSuperAdmin(@PathVariable("email") String email,@PathVariable("password") String password) {
        return superAdminService.loginSuperAdmin(email, password);
    }
}
