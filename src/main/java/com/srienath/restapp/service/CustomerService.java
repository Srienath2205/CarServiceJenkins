package com.srienath.restapp.service;

import java.util.List;
import com.srienath.restapp.model.Customer;

public interface CustomerService {
	public Customer getById(int id);

    public List<Customer> getAll();

    public void create(Customer customer) throws Exception;

    public void update(Customer customer);

    public void delete(int id);

	public Customer loginCustomer(String email, String password);

	Customer getByEmail(String email);
}
