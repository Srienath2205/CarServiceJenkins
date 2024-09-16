package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Technician;
import com.srienath.restapp.repo.TechnicianRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TechnicianRepositoryImpl implements TechnicianRepository {
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Technician findById(int id) {
        return entityManager.find(Technician.class, id);
    }

    @Override
    public List<Technician> findAll() {
        return entityManager.createQuery("from Technician", Technician.class).getResultList();
    }

    @Override
    public void save(Technician technician) {
        entityManager.persist(technician);
    }

    @Override
    public void update(Technician technician) {
        entityManager.merge(technician);
    }

    @Override
    public void deleteById(int id) {
        Technician technician = findById(id);
        if (technician != null) {
            entityManager.remove(technician);
        }
    }
}
