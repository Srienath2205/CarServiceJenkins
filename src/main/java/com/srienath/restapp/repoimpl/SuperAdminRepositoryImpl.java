package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.SuperAdmin;
import com.srienath.restapp.repo.SuperAdminRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SuperAdminRepositoryImpl implements SuperAdminRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public SuperAdmin findById(int id) {
        return entityManager.find(SuperAdmin.class, id);
    }

    @Override
    public List<SuperAdmin> findAll() {
        return entityManager.createQuery("from SuperAdmin", SuperAdmin.class).getResultList();
    }

    @Override
    public void save(SuperAdmin superAdmin) {
        entityManager.persist(superAdmin);
    }

    @Override
    public void update(SuperAdmin superAdmin) {
        entityManager.merge(superAdmin);
    }

    @Override
    public void deleteById(int id) {
        SuperAdmin superAdmin = findById(id);
        if (superAdmin != null) {
            entityManager.remove(superAdmin);
        }
    }
    
    @Override
    public SuperAdmin loginSuperAdmin(String email, String password) {
		Query query1 = entityManager.createQuery("from SuperAdmin s where s.email =?1 and s.password=?2");
		query1.setParameter(1, email);
		query1.setParameter(2, password);
 
		return (SuperAdmin) query1.getSingleResult();
 
	}

}
