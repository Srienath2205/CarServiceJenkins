package com.srienath.restapp.controller;

import com.srienath.restapp.model.Admin;
import com.srienath.restapp.model.ServiceCenter;
import com.srienath.restapp.service.ServiceCenterService;
import com.srienath.restapp.service.AdminService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/servicecenters")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceCenterController {

    private final ServiceCenterService serviceCenterService;
    private final AdminService adminService;
    private final JavaMailSender mailSender;


    public ServiceCenterController(ServiceCenterService serviceCenterService, AdminService adminService, JavaMailSender mailSender) {
        this.serviceCenterService = serviceCenterService;
        this.adminService = adminService;
        this.mailSender = mailSender;
    }

    @GetMapping("/{id}")
    public ServiceCenter getServiceCenterById(@PathVariable int id) {
        ServiceCenter serviceCenter = serviceCenterService.getById(id);
        if (serviceCenter != null) {
            return serviceCenter;
        } else {
            throw new RuntimeException("Service Center not found");
        }
    }

    @GetMapping("/all")
    public List<ServiceCenter> getAllServiceCenters() {
        return serviceCenterService.getAll();
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addServiceCenter(
            @RequestParam("serviceCenterName") String serviceCenterName,
            @RequestParam("address") String address,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("status") String status,
            @RequestParam("adminID") int adminID,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("serviceCenterImage") MultipartFile serviceCenterImage,
            @RequestParam("businessRegistrationCertificate") MultipartFile businessRegistrationCertificate,
            @RequestParam("insuranceDocument") MultipartFile insuranceDocument,
            @RequestParam("ownerIdentityProof") MultipartFile ownerIdentityProof) {

        try {
            // Fetch Admin entity
            Admin admin = adminService.getById(adminID);
            if (admin == null) {
                return ResponseEntity.status(404).body("Admin not found");
            }

            // Create ServiceCenter instance
            ServiceCenter serviceCenter = new ServiceCenter();
            serviceCenter.setServiceCenterName(serviceCenterName);
            serviceCenter.setAddress(address);
            serviceCenter.setDescription(description);
            serviceCenter.setLocation(location);
            serviceCenter.setAdmin(admin); // Set fetched Admin entity
            serviceCenter.setEmail(admin.getEmail());
            serviceCenter.setStatus(status);

            serviceCenter.setPhoneNumber(admin.getPhoneNumber());
            serviceCenter.setServiceCenterImage(serviceCenterImage.getBytes());
            serviceCenter.setBusinessRegistrationCertificate(businessRegistrationCertificate.getBytes());
            serviceCenter.setInsuranceDocument(insuranceDocument.getBytes());
            serviceCenter.setOwnerIdentityProof(ownerIdentityProof.getBytes());

            // Save the service center
//          sendRegistrationEmail(serviceCenter);
            serviceCenterService.create(serviceCenter);

            return ResponseEntity.status(200).body("Service Center registered successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing files");
        }
    }

//    private void sendRegistrationEmail(ServiceCenter serviceCenter) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(serviceCenter.getEmail()); // Admin's email
//        message.setSubject("Service Center Registration Status - AutoCare Hub");
//        String emailBody = String.format(
//                "Dear %s,%n%n" +
//                "We have received your registration for the Service Center '%s' located at '%s'.%n%n" +
//                "Your registration is currently being processed and is awaiting warehouse approval.%n%n" +
//                "You will receive further updates once the warehouse approval process is complete.%n%n" +
//                "If you have any questions or need further assistance, please do not hesitate to contact us at support@autocarehub.com.%n%n" +
//                "Thank you for your patience and cooperation.%n%n" +
//                "Best Regards,%n" +
//                "The AutoCare Hub Team%n" +
//                "Email: support@autocarehub.com%n" +
//                "Website: www.autocarehub.com",
//                serviceCenter.getAdmin().getUsername(), // Admin's name
//                serviceCenter.getServiceCenterName(),
//                serviceCenter.getAddress()
//            );
//        message.setText(emailBody);
//        mailSender.send(message);
//    }

    @PutMapping("/update/{id}")
    public String updateServiceCenter(
            @PathVariable int id,
            @RequestParam("serviceCenterName") String serviceCenterName,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("status") String status,
            @RequestParam("approvalDate") String approvalDate,
            @RequestParam("rating") int rating,
            @RequestParam(value = "serviceCenterImage", required = false) MultipartFile serviceCenterImage,
            @RequestParam(value = "businessRegisterCertificate", required = false) MultipartFile businessRegisterCertificate,
            @RequestParam(value = "insuranceDocument", required = false) MultipartFile insuranceDocument,
            @RequestParam(value = "ownerIdentityProof", required = false) MultipartFile ownerIdentityProof,
            @RequestParam(value = "warehouseAgreement", required = false) MultipartFile warehouseAgreement,
            @RequestParam("adminID") int adminID) {

        ServiceCenter existingServiceCenter = serviceCenterService.getById(id);
        if (existingServiceCenter == null) {
            return "Service Center not found for update";
        }

        try {
            existingServiceCenter.setServiceCenterName(serviceCenterName);
            existingServiceCenter.setAddress(address);
            existingServiceCenter.setPhoneNumber(phoneNumber);
            existingServiceCenter.setEmail(email);
            existingServiceCenter.setLocation(location);
            existingServiceCenter.setDescription(description);
            existingServiceCenter.setStatus(status);

            // Convert the approval date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(approvalDate);
            existingServiceCenter.setApprovalDate(date);
            existingServiceCenter.setRating(rating);

            if (serviceCenterImage != null && !serviceCenterImage.isEmpty()) {
                existingServiceCenter.setServiceCenterImage(serviceCenterImage.getBytes());
            }
            if (businessRegisterCertificate != null && !businessRegisterCertificate.isEmpty()) {
                existingServiceCenter.setBusinessRegistrationCertificate(businessRegisterCertificate.getBytes());
            }
            if (insuranceDocument != null && !insuranceDocument.isEmpty()) {
                existingServiceCenter.setInsuranceDocument(insuranceDocument.getBytes());
            }
            if (ownerIdentityProof != null && !ownerIdentityProof.isEmpty()) {
                existingServiceCenter.setOwnerIdentityProof(ownerIdentityProof.getBytes());
            }
            if (warehouseAgreement != null && !warehouseAgreement.isEmpty()) {
                existingServiceCenter.setWarehouseAgreement(warehouseAgreement.getBytes());
            }

            existingServiceCenter.setAdmin(adminService.getById(adminID));

            serviceCenterService.update(existingServiceCenter);
            return "Service Center updated successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing files";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid approval date format";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating Service Center";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteServiceCenter(@PathVariable int id) {
        ServiceCenter existingServiceCenter = serviceCenterService.getById(id);
        if (existingServiceCenter == null) {
            return "Service Center not found for deletion";
        }
        try {
            serviceCenterService.delete(id);
            return "Service Center deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting Service Center";
        }
    }
}
