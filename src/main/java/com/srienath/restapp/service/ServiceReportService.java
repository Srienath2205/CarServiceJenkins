package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.ServiceReport;

public interface ServiceReportService {
	public ServiceReport getById(int id);

    public List<ServiceReport> getAll();

    public void create(ServiceReport serviceReport);

    public void update(ServiceReport serviceReport);

    public void delete(int id);
}
