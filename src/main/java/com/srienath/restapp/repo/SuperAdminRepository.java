package com.srienath.restapp.repo;

import com.srienath.restapp.model.SuperAdmin;
import java.util.List;

public interface SuperAdminRepository {

    public SuperAdmin findById(int id);

    public List<SuperAdmin> findAll();

    public void save(SuperAdmin superAdmin);

    public void update(SuperAdmin superAdmin);

    public void deleteById(int id);
    
	public SuperAdmin loginSuperAdmin(String email, String password);

}

