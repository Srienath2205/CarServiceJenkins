package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.SuperAdmin;

public interface SuperAdminService {
	SuperAdmin getById(int id);

    List<SuperAdmin> getAll();

    void create(SuperAdmin superAdmin);

    void update(SuperAdmin superAdmin);

    void delete(int id);

	SuperAdmin loginSuperAdmin(String email, String password);
}
