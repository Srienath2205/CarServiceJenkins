package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.ServiceCenter;
import com.srienath.restapp.repo.ServiceCenterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ServiceCenterRepositoryImpl implements ServiceCenterRepository {

	 @PersistenceContext
	    private EntityManager entityManager;

	    @Override
	    public ServiceCenter findById(int id) {
	        return entityManager.find(ServiceCenter.class, id);
	    }

	    @Override
	    public List<ServiceCenter> findAll() {
	        return entityManager.createQuery("from ServiceCenter", ServiceCenter.class).getResultList();
	    }

	    @Override
	    public void save(ServiceCenter serviceCenter) {
	        entityManager.persist(serviceCenter);
	    }

	    @Override
	    public void update(ServiceCenter serviceCenter) {
	        entityManager.merge(serviceCenter);
	    }

	    @Override
	    public void deleteById(int id) {
	        ServiceCenter serviceCenter = findById(id);
	        if (serviceCenter != null) {
	            entityManager.remove(serviceCenter);
	        }
	    }

}
