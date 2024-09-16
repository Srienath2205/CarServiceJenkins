package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.ServiceCenter;
import com.srienath.restapp.repo.ServiceCenterRepository;

@Service
public class ServiceCenterService implements com.srienath.restapp.service.ServiceCenterService {
	private ServiceCenterRepository serviceCenterRepository;
	
    public ServiceCenterService(ServiceCenterRepository serviceCenterRepository) {
		super();
		this.serviceCenterRepository = serviceCenterRepository;
	}

	@Override
    public ServiceCenter getById(int id) {
        return serviceCenterRepository.findById(id);
    }

    @Override
    public List<ServiceCenter> getAll() {
        return serviceCenterRepository.findAll();
    }

    @Override
    public void create(ServiceCenter serviceCenter) {
        serviceCenterRepository.save(serviceCenter);
    }

    @Override
    public void update(ServiceCenter serviceCenter) {
        serviceCenterRepository.update(serviceCenter);
    }

    @Override
    public void delete(int id) {
        serviceCenterRepository.deleteById(id);
    }
}
