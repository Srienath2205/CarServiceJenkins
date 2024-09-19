package com.srienath.restapp.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.srienath.restapp.model.ServiceCenter;
import com.srienath.restapp.repo.ServiceCenterRepository;

@Service
public class ServiceCenterServiceImpl implements com.srienath.restapp.service.ServiceCenterService {
	private ServiceCenterRepository serviceCenterRepository;
	
    public ServiceCenterServiceImpl(ServiceCenterRepository serviceCenterRepository) {
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
    
    @Override
	public boolean updateApprovedRequest(int serviceCenterID) {
		return serviceCenterRepository.updateEmailApproved(serviceCenterID);
	}
 
	@Override
	public boolean updateRejectedRequest(int serviceCenterID) {
		return serviceCenterRepository.updateEmailRejected(serviceCenterID);
	}
	
	@Override
	public List<ServiceCenter> getPendingRequest() {
		return serviceCenterRepository.getPendingRequest();
	}
 
	@Override
	public List<ServiceCenter> getApprovedRequest() {
		return serviceCenterRepository.getApprovedRequest();
	}
 
	@Override
	public List<ServiceCenter> getRejectedRequest() {
		return serviceCenterRepository.getRejectedRequest();
	}
	
	@Override
	public Object getPendingCount() {
		return serviceCenterRepository.getPendingCount();
	}
	
	@Override
	public Object getApprovedCount() {
		return serviceCenterRepository.getApprovedCount();
	}
 
	@Override
	public Object getRejectedCount() {
		return serviceCenterRepository.getRejectedCount();
	}
	
	 public List<String> getDistinctLocations() {
	        List<ServiceCenter> servicecenter = serviceCenterRepository.findAllCentersWithDistinctLocations();
	        if (servicecenter != null) {
	            return servicecenter.stream()
	                              .map(ServiceCenter::getLocation)
	                              .filter(location -> location != null && !location.isEmpty())
	                              .distinct()
	                              .collect(Collectors.toList());
	        }
	        return Collections.emptyList();
	    }
}
