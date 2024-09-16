package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Job;
import com.srienath.restapp.repo.JobRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class JobRepositoryImpl implements JobRepository {
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Job findById(int id) {
        return entityManager.find(Job.class, id);
    }

    @Override
    public List<Job> findAll() {
        return entityManager.createQuery("from Job", Job.class).getResultList();
    }

    @Override
    public void save(Job job) {
        entityManager.persist(job);
    }

    @Override
    public void update(Job job) {
        entityManager.merge(job);
    }

    @Override
    public void deleteById(int id) {
        Job job = findById(id);
        if (job != null) {
            entityManager.remove(job);
        }
    }
}
