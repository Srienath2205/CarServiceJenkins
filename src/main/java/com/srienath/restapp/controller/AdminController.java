package com.srienath.restapp.controller;

import com.srienath.restapp.model.Admin;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.srienath.restapp.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final AdminService adminService;
    private final JavaMailSender mailSender;

    public AdminController(AdminService adminService, JavaMailSender mailSender) {
        this.adminService = adminService;
        this.mailSender = mailSender;
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable int id) {
        Admin admin = adminService.getById(id);
        if (admin != null) {
            return admin;
        } else {
            throw new RuntimeException("Admin not found");
        }
    }

    @GetMapping("/all")
    public List<Admin> getAllAdmins() {
        return adminService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
        try {
            adminService.create(admin);
            try {
                sendRegistrationEmail(admin);
            } catch (MailException e) {
                // Rollback transaction if email sending fails
                adminService.delete(admin.getAdminID());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email delivery failed. Registration has been rolled back.");
            }
            return ResponseEntity.ok("Admin Registered Successfully");
        } catch (Exception e) {
            if (e.getMessage().equals("Email already exists.")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An admin with this email already exists.");
            }
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Admin Registration Failure");
        }
    }
    
    private void sendRegistrationEmail(Admin admin) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(admin.getEmail());
        message.setSubject("Welcome to AutoCare Hub - Registration Confirmation");
        String emailBody = String.format(
                "Dear %s,%n%n" +
                "Congratulations on completing your registration with AutoCare Hub!%n%n" +
                "Your registration has been successfully received. As an admin, you will now be part of our network of service providers.%n%n" +
                "Next Steps:%n" +
                "1. **Service Center Registration**: Please complete the service center registration to set up your center.%n" +
                "2. **Approval Process**: Your registration will be reviewed by our central team. You will receive further instructions and necessary agreements shortly.%n" +
                "3. **Setup**: Once approved, you will receive details on how to receive parts and set up your service center.%n%n" +
                "If you have any questions or need assistance, feel free to reach out to us at support@autocarehub.com.%n%n" +
                "Thank you for joining us! We look forward to working with you.%n%n" +
                "Best Regards,%n" +
                "The AutoCare Hub Team%n" +
                "Email: support@autocarehub.com%n" +
                "Website: www.autocarehub.com",
                admin.getUsername()
            );
        message.setText(emailBody);
        mailSender.send(message);
    }

    @PutMapping("update/{id}")
    public String updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        Admin existingAdmin = adminService.getById(id);
        if (existingAdmin == null) {
            return "Admin not found for update";
        }
        try {
            admin.setAdminID(id);
            adminService.update(admin);
            return "Admin updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating admin";
        }
    }

    @DeleteMapping("delete/{id}")
    public String deleteAdmin(@PathVariable int id) {
        Admin existingAdmin = adminService.getById(id);
        if (existingAdmin == null) {
            return "Admin not found for deletion";
        }
        try {
            adminService.delete(id);
            return "Admin deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting admin";
        }
    }

    @GetMapping("/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        Admin admin = adminService.getByEmail(email);
        if (admin != null) {
            return admin;
        } else {
            throw new RuntimeException("Admin not found");
        }
    }
    
    @GetMapping("/login/{email}/{password}")
    public Admin loginAdmin(@PathVariable("email") String email,@PathVariable("password") String password) {
        return adminService.loginAdmin(email, password);
    }
}

