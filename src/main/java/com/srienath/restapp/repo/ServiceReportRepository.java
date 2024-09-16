package com.srienath.restapp.repo;

import java.util.List;
import com.srienath.restapp.model.ServiceReport;

public interface ServiceReportRepository {
	public ServiceReport findById(int id);

    public List<ServiceReport> findAll();

    public void save(ServiceReport serviceReport);

    public void update(ServiceReport serviceReport);

    public void deleteById(int id);
}
