package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.Admin;
import com.srienath.restapp.repo.AdminRepository;
import com.srienath.restapp.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
    private AdminRepository adminRepository;
    
    public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
    public Admin getById(int id) {
        return adminRepository.getById(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.getAll();
    }

    @Override
    public void create(Admin admin) {
        adminRepository.create(admin);
    }

    @Override
    public void update(Admin admin) {
        adminRepository.update(admin);
    }

    @Override
    public void delete(int id) {
        adminRepository.delete(id);
    }
    
    @Override
    public Admin getByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
    
    public Admin loginAdmin(String email, String password) {
		return adminRepository.loginAdmin(email, password);
	}
}
