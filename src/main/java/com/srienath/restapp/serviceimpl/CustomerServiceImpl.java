package com.srienath.restapp.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.srienath.restapp.model.Customer;
import com.srienath.restapp.repo.CustomerRepository;
import com.srienath.restapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	    private CustomerRepository customerRepository;
	    
	    public CustomerServiceImpl(CustomerRepository customerRepository) {
			super();
			this.customerRepository = customerRepository;
		}

		@Override
	    public Customer getById(int id) {
	        return customerRepository.findById(id);
	    }

	    @Override
	    public List<Customer> getAll() {
	        return customerRepository.findAll();
	    }

	    @Override
	    public void create(Customer customer) {
	        customerRepository.save(customer);
	    }

	    @Override
	    public void update(Customer customer) {
	        customerRepository.update(customer);
	    }

	    @Override
	    public void delete(int id) {
	        customerRepository.deleteById(id);
	    }
	    
	    public Customer loginCustomer(String email, String password) {
			return customerRepository.loginCustomer(email, password);
		}
}
