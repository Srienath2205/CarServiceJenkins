package com.srienath.restapp.repo;

import com.srienath.restapp.model.ServiceCenter;
import java.util.List;

public interface ServiceCenterRepository {

    public ServiceCenter findById(int id);

    public List<ServiceCenter> findAll();

    public void save(ServiceCenter serviceCenter);

    public void update(ServiceCenter serviceCenter);

    public void deleteById(int id);
}
