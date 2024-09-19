package com.srienath.restapp.controller;

import com.srienath.restapp.model.Customer;
import com.srienath.restapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;
    private final JavaMailSender mailSender;

    public CustomerController(CustomerService customerService, JavaMailSender mailSender) {
        this.customerService = customerService;
        this.mailSender = mailSender;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getById(id);
        if (customer != null) {
            return customer;
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
        //  if (customer.getVehicle() != null && customer.getVehicle().getVehicleID() != 0) {
        //  customer.setVehicle(new Vehicle(customer.getVehicle().getVehicleID()));
        //}
            customerService.create(customer);
            
            try {
                sendRegistrationEmail(customer);
            } catch (MailException e) {
                // Rollback transaction if email sending fails
                customerService.delete(customer.getCustomerID());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email delivery failed. Registration has been rolled back.");
            }
            
            return ResponseEntity.ok("Customer Registered Successfully");
        } catch (Exception e) {
            if (e.getMessage().equals("Email already exists.")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A Customer with this email already exists.");
            }
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Customer Registration Failure");
        }
    }

    private void sendRegistrationEmail(Customer customer) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setSubject("Welcome to AutoCare Hub - Registration Successful");
        String emailBody = String.format(
            "Dear %s,%n%n" +
            "We are pleased to inform you that your registration with AutoCare Hub was successful.%n%n" +
            "Thank you for joining us. You can now access your account and explore our services.%n%n" +
            "If you have any questions or need assistance, feel free to contact us at support@autocarehub.com.%n%n" +
            "Best Regards,%n" +
            "The AutoCare Hub Team%n" +
            "support@autocarehub.com%n" +
            "www.autocarehub.com",
            customer.getName()
        );
        
        message.setText(emailBody);
        mailSender.send(message);
    }


    @PutMapping("/update/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.getById(id);
        if (existingCustomer == null) {
            return "Customer not found for update";
        }

        // Update fields
        if (customer.getName() != null) existingCustomer.setName(customer.getName());
        if (customer.getPassword() != null) existingCustomer.setPassword(customer.getPassword());
        if (customer.getEmail() != null) existingCustomer.setEmail(customer.getEmail());
        if (customer.getAddress() != null) existingCustomer.setAddress(customer.getAddress());
        if (customer.getPhoneNumber() != null) existingCustomer.setPhoneNumber(customer.getPhoneNumber());
//        if (customer.getVehicle() != null) {
//            if (customer.getVehicle().getVehicleID() != 0) {
//                existingCustomer.setVehicle(new Vehicle(customer.getVehicle().getVehicleID()));
//            }
//        }

        try {
            customerService.update(existingCustomer);
            return "Customer updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating customer";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        Customer existingCustomer = customerService.getById(id);
        if (existingCustomer == null) {
            return "Customer not found for deletion";
        }
        try {
            customerService.delete(id);
            return "Customer deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting customer";
        }
    }
    
    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.getByEmail(email);
        if (customer != null) {
            return customer;
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
    
    @GetMapping("/login/{email}/{password}")
    public Customer loginCustomer(@PathVariable("email") String email,@PathVariable("password") String password) {
        return customerService.loginCustomer(email, password);
    }
}

