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

import com.srienath.restapp.model.Customer;
import com.srienath.restapp.repo.CustomerRepository;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Customer customer = new Customer(1, "John Doe", "password123", "john@example.com", "123 Main St", "1234567890");
        when(customerRepository.findById(1)).thenReturn(customer);
        
        Customer result = customerService.getById(1);
        assertEquals(customer, result);
        verify(customerRepository).findById(1);
    }

    @Test
    void testGetAll() {
        List<Customer> customers = Arrays.asList(
            new Customer(1, "John Doe", "password123", "john@example.com", "123 Main St", "1234567890"),
            new Customer(2, "Jane Doe", "password456", "jane@example.com", "456 Elm St", "0987654321")
        );
        when(customerRepository.findAll()).thenReturn(customers);
        
        List<Customer> result = customerService.getAll();
        assertEquals(2, result.size());
        verify(customerRepository).findAll();
    }

    @Test
    void testCreate() throws Exception {
        Customer customer = new Customer(1, "John Doe", "password123", "john@example.com", "123 Main St", "1234567890");
        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(null);
        
        customerService.create(customer);
        verify(customerRepository).save(customer);
    }

    @Test
    void testCreate_throwsExceptionWhenEmailExists() {
        Customer customer = new Customer(1, "John Doe", "password123", "john@example.com", "123 Main St", "1234567890");
        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(customer);
        
        Exception exception = assertThrows(Exception.class, () -> {
            customerService.create(customer);
        });
        assertEquals("Email already exists.", exception.getMessage());
        verify(customerRepository, never()).save(customer);
    }

    @Test
    void testUpdate() {
        Customer customer = new Customer(1, "John Doe", "password123", "john@example.com", "123 Main St", "1234567890");
        
        customerService.update(customer);
        verify(customerRepository).update(customer);
    }

    @Test
    void testDelete() {
        customerService.delete(1);
        verify(customerRepository).deleteById(1);
    }

    @Test
    void testGetByEmail() {
        Customer customer = new Customer(1, "John Doe", "password123", "john@example.com", "123 Main St", "1234567890");
        when(customerRepository.findByEmail("john@example.com")).thenReturn(customer);
        
        Customer result = customerService.getByEmail("john@example.com");
        assertEquals(customer, result);
        verify(customerRepository).findByEmail("john@example.com");
    }

    @Test
    void testLoginCustomer() {
        Customer customer = new Customer(1, "John Doe", "password123", "john@example.com", "123 Main St", "1234567890");
        when(customerRepository.loginCustomer("john@example.com", "password123")).thenReturn(customer);
        
        Customer result = customerService.loginCustomer("john@example.com", "password123");
        assertEquals(customer, result);
        verify(customerRepository).loginCustomer("john@example.com", "password123");
    }
}
