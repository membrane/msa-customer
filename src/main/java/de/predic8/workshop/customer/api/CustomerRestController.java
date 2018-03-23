package de.predic8.workshop.customer.api;

import de.predic8.workshop.customer.domain.Customer;
import de.predic8.workshop.customer.repository.CustomerRepository;
import de.predic8.workshop.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
	private final CustomerService customerService;

	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> index() {
		return customerService.getCustomers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Optional<Customer> customer = customerService.getCustomerById(id);

		return customer.isPresent() ? ResponseEntity.ok(customer.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder) {
		return ResponseEntity
			.created(uriComponentsBuilder.path("/customers/{id}").buildAndExpand(customerService.createCustomer(customer)).toUri())
			.build();
	}
}