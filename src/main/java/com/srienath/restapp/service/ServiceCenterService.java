package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.ServiceCenter;

public interface ServiceCenterService {
	ServiceCenter getById(int id);

    List<ServiceCenter> getAll();

    void create(ServiceCenter serviceCenter);

    void update(ServiceCenter serviceCenter);

    void delete(int id);
}
