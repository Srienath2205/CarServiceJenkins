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

import com.srienath.restapp.model.Admin;
import com.srienath.restapp.repo.AdminRepository;

class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Admin admin = new Admin(1, "adminUser", "password", "admin@example.com", "1234567890");
        when(adminRepository.getById(1)).thenReturn(admin);
        
        Admin result = adminService.getById(1);
        assertEquals(admin, result);
        verify(adminRepository).getById(1);
    }

    @Test
    void testGetAll() {
        List<Admin> admins = Arrays.asList(
            new Admin(1, "adminUser1", "password", "admin1@example.com", "1234567890"),
            new Admin(2, "adminUser2", "password", "admin2@example.com", "0987654321")
        );
        when(adminRepository.getAll()).thenReturn(admins);
        
        List<Admin> result = adminService.getAll();
        assertEquals(2, result.size());
        assertEquals("adminUser1", result.get(0).getUsername());
        assertEquals("adminUser2", result.get(1).getUsername());
        verify(adminRepository).getAll();
    }

    @Test
    void testCreate() throws Exception {
        Admin admin = new Admin(1, "adminUser", "password", "admin@example.com", "1234567890");
        when(adminRepository.findByEmail(admin.getEmail())).thenReturn(null);
        
        adminService.create(admin);
        verify(adminRepository).create(admin);
    }

    @Test
    void testCreate_throwsExceptionWhenEmailExists() {
        Admin admin = new Admin(1, "adminUser", "password", "admin@example.com", "1234567890");
        when(adminRepository.findByEmail(admin.getEmail())).thenReturn(admin);
        
        Exception exception = assertThrows(Exception.class, () -> {
            adminService.create(admin);
        });
        assertEquals("Email already exists.", exception.getMessage());
        verify(adminRepository, never()).create(admin);
    }

    @Test
    void testUpdate() {
        Admin admin = new Admin(1, "adminUser", "password", "admin@example.com", "1234567890");
        adminService.update(admin);
        verify(adminRepository).update(admin);
    }

    @Test
    void testDelete() {
        adminService.delete(1);
        verify(adminRepository).delete(1);
    }

    @Test
    void testGetByEmail() {
        Admin admin = new Admin(1, "adminUser", "password", "admin@example.com", "1234567890");
        when(adminRepository.findByEmail("admin@example.com")).thenReturn(admin);
        
        Admin result = adminService.getByEmail("admin@example.com");
        assertEquals(admin, result);
        verify(adminRepository).findByEmail("admin@example.com");
    }

    @Test
    void testLoginAdmin() {
        Admin admin = new Admin(1, "adminUser", "password", "admin@example.com", "1234567890");
        when(adminRepository.loginAdmin("admin@example.com", "password")).thenReturn(admin);
        
        Admin result = adminService.loginAdmin("admin@example.com", "password");
        assertEquals(admin, result);
        verify(adminRepository).loginAdmin("admin@example.com", "password");
    }
}
