package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.ServiceReport;
import com.srienath.restapp.repo.ServiceReportRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ServiceReportRepositoryImpl implements ServiceReportRepository {
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public ServiceReport findById(int id) {
        return entityManager.find(ServiceReport.class, id);
    }

    @Override
    public List<ServiceReport> findAll() {
        return entityManager.createQuery("from ServiceReport", ServiceReport.class).getResultList();
    }

    @Override
    public void save(ServiceReport serviceReport) {
        entityManager.persist(serviceReport);
    }

    @Override
    public void update(ServiceReport serviceReport) {
        entityManager.merge(serviceReport);
    }

    @Override
    public void deleteById(int id) {
        ServiceReport serviceReport = findById(id);
        if (serviceReport != null) {
            entityManager.remove(serviceReport);
        }
    }
}
