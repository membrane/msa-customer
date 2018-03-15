package de.predic8.workshop.customer.api;

import de.predic8.workshop.customer.domain.Customer;
import de.predic8.workshop.customer.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
	private final CustomerRepository customerRepository;

	public CustomerRestController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping
	public List<Customer> index() {
		return customerRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Customer customer = customerRepository.findOne(id);

		if (customer == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(customer);
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder) {
		Customer c = customerRepository.save(customer);

		return ResponseEntity.created(uriComponentsBuilder.path("/customers/{id}").buildAndExpand(c.getId()).toUri()).build();
	}
}