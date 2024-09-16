package com.srienath.restapp.repo;

import com.srienath.restapp.model.Admin;
import java.util.List;

public interface AdminRepository {
    public Admin getById(int id);
    public List<Admin> getAll();
    public void create(Admin admin);
    public void update(Admin admin);
    public void delete(int id);
    Admin findByEmail(String email);
	public Admin loginAdmin(String email, String password);

}


