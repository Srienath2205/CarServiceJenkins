package com.srienath.restapp.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.srienath.restapp.model.ServiceReport;
import com.srienath.restapp.repo.ServiceReportRepository;
import com.srienath.restapp.service.ServiceReportService;

@Service
public class ServiceReportServiceImpl implements ServiceReportService {
	private ServiceReportRepository serviceReportRepository;

    public ServiceReportServiceImpl(ServiceReportRepository serviceReportRepository) {
		super();
		this.serviceReportRepository = serviceReportRepository;
	}

	@Override
    public ServiceReport getById(int id) {
        return serviceReportRepository.findById(id);
    }

    @Override
    public List<ServiceReport> getAll() {
        return serviceReportRepository.findAll();
    }

    @Override
    public void create(ServiceReport serviceReport) {
        serviceReportRepository.save(serviceReport);
    }

    @Override
    public void update(ServiceReport serviceReport) {
        serviceReportRepository.update(serviceReport);
    }

    @Override
    public void delete(int id) {
        serviceReportRepository.deleteById(id);
    }
}
