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

import com.srienath.restapp.model.SuperAdmin;
import com.srienath.restapp.repo.SuperAdminRepository;

class SuperAdminServiceImplTest {

    @Mock
    private SuperAdminRepository superAdminRepository;

    @InjectMocks
    private SuperAdminServiceImpl superAdminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        SuperAdmin admin = new SuperAdmin();
        admin.setSuperAdminID(1);
        when(superAdminRepository.findById(1)).thenReturn(admin);

        SuperAdmin result = superAdminService.getById(1);
        assertEquals(admin, result);
        verify(superAdminRepository).findById(1);
    }

    @Test
    void testGetAll() {
        SuperAdmin admin1 = new SuperAdmin();
        SuperAdmin admin2 = new SuperAdmin();
        List<SuperAdmin> admins = Arrays.asList(admin1, admin2);

        when(superAdminRepository.findAll()).thenReturn(admins);

        List<SuperAdmin> result = superAdminService.getAll();
        assertEquals(2, result.size());
        verify(superAdminRepository).findAll();
    }

    @Test
    void testCreate() {
        SuperAdmin admin = new SuperAdmin();
        superAdminService.create(admin);
        verify(superAdminRepository).save(admin);
    }

    @Test
    void testUpdate() {
        SuperAdmin admin = new SuperAdmin();
        superAdminService.update(admin);
        verify(superAdminRepository).update(admin);
    }

    @Test
    void testDelete() {
        superAdminService.delete(1);
        verify(superAdminRepository).deleteById(1);
    }

    @Test
    void testLoginSuperAdmin() {
        String email = "admin@example.com";
        String password = "password123";
        SuperAdmin admin = new SuperAdmin();
        admin.setEmail(email);
        admin.setPassword(password);
        
        when(superAdminRepository.loginSuperAdmin(email, password)).thenReturn(admin);

        SuperAdmin result = superAdminService.loginSuperAdmin(email, password);
        assertEquals(admin, result);
        verify(superAdminRepository).loginSuperAdmin(email, password);
    }
}
