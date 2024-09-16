package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.SuperAdmin;
import com.srienath.restapp.repo.SuperAdminRepository;
import com.srienath.restapp.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
	
    private SuperAdminRepository superAdminRepository;
       
    public SuperAdminServiceImpl(SuperAdminRepository superAdminRepository) {
		super();
		this.superAdminRepository = superAdminRepository;
	}

	@Override
    public SuperAdmin getById(int id) {
        return superAdminRepository.findById(id);
    }

    @Override
    public List<SuperAdmin> getAll() {
        return superAdminRepository.findAll();
    }

    @Override
    public void create(SuperAdmin superAdmin) {
        superAdminRepository.save(superAdmin);
    }

    @Override
    public void update(SuperAdmin superAdmin) {
        superAdminRepository.update(superAdmin);
    }

    @Override
    public void delete(int id) {
        superAdminRepository.deleteById(id);
    }
    
    @Override
    public SuperAdmin loginSuperAdmin(String email, String password) {
		return superAdminRepository.loginSuperAdmin(email, password);
	}
}
