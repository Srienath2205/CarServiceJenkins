package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.ServiceCenter;

public interface ServiceCenterService {
	ServiceCenter getById(int id);

    List<ServiceCenter> getAll();

    void create(ServiceCenter serviceCenter);

    void update(ServiceCenter serviceCenter);

    void delete(int id);

	Object getPendingCount();

	Object getRejectedCount();

	Object getApprovedCount();
	
	List<ServiceCenter> getPendingRequest();
	
	List<ServiceCenter> getApprovedRequest();

	List<ServiceCenter> getRejectedRequest();

	boolean updateApprovedRequest(int serviceCenterID);
	
	boolean updateRejectedRequest(int serviceCenterID);

}
