package de.predic8.workshop.customer.service;

import de.predic8.workshop.customer.domain.Customer;
import de.predic8.workshop.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	public Long createCustomer(Customer customer) {
		return customerRepository.save(customer).getId();
	}
}