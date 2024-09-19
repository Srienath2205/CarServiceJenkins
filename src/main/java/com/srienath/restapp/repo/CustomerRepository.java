package com.srienath.restapp.repo;

import java.util.List;
import com.srienath.restapp.model.Customer;

public interface CustomerRepository {
	public Customer findById(int id);

    public List<Customer> findAll();

    public void save(Customer customer);

    public void update(Customer customer);

    public void deleteById(int id);

	public Customer loginCustomer(String email, String password);

	Customer findByEmail(String email);
}
