package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Customer;
import com.srienath.restapp.repo.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void deleteById(int id) {
        Customer customer = findById(id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }
    
    @Override
    public Customer findByEmail(String email) {
    	try {
            Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.email = :email");
            query.setParameter("email", email);
            return (Customer) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }    }
    
    @Override
    public Customer loginCustomer(String email, String password) {
		Query query1 = entityManager.createQuery("from Customer c where c.email =?1 and c.password=?2");
		query1.setParameter(1, email);
		query1.setParameter(2, password);
 
		return (Customer) query1.getSingleResult();
 
	}
}
