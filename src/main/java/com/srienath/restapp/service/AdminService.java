package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Admin;

public interface AdminService {
	Admin getById(int id);
    List<Admin> getAll();
    void create(Admin admin) throws Exception;
    void update(Admin admin);
    void delete(int id);
    Admin getByEmail(String email);
	Admin loginAdmin(String email, String password);
}
