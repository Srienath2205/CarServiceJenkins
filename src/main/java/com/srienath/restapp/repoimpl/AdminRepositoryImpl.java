package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Admin;
import com.srienath.restapp.repo.AdminRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {

	 @PersistenceContext
	    private EntityManager entityManager;

	    @Override
	    public Admin getById(int id) {
	        return entityManager.find(Admin.class, id);
	    }

	    @Override
	    @SuppressWarnings("unchecked")
	    public List<Admin> getAll() {
	        return entityManager.createQuery("FROM Admin").getResultList();
	    }

	    @Override
	    public void create(Admin admin) {
	        entityManager.persist(admin);
	    }

	    @Override
	    public void update(Admin admin) {
	        entityManager.merge(admin);
	    }

	    @Override
	    public void delete(int id) {
	        Admin admin = entityManager.find(Admin.class, id);
	        if (admin != null) {
	            entityManager.remove(admin);
	        }
	    }
	    
	    @Override
	    public Admin findByEmail(String email) {
	        try {
	            Query query = entityManager.createQuery("SELECT a FROM Admin a WHERE a.email = :email");
	            query.setParameter("email", email);
	            return (Admin) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	    
	    @Override
	    public Admin loginAdmin(String email, String password) {
			Query query1 = entityManager.createQuery("from Admin a where a.email =?1 and a.password=?2");
			query1.setParameter(1, email);
			query1.setParameter(2, password);
	 
			return (Admin) query1.getSingleResult();
	 
		}
}
