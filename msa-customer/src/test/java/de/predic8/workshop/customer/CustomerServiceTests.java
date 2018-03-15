package de.predic8.workshop.customer;

import de.predic8.workshop.customer.domain.Customer;
import de.predic8.workshop.customer.repository.CustomerRepository;
import de.predic8.workshop.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("A customer service")
public class CustomerServiceTests {
	private CustomerRepository customerRepository;

	@BeforeEach
	public void setUp() {
		customerRepository = mock(CustomerRepository.class);
	}

	@DisplayName("provides the list of customers")
	@Test
	public void getCustomers() {
		CustomerService customerService = new CustomerService(customerRepository);

		Customer oliver = new Customer("Oliver", "Weiler");
		oliver.setId(1L);

		Customer thomas = new Customer("Thomas", "Bayer");
		thomas.setId(1L);

		when(customerRepository.findAll()).thenReturn(asList(oliver, thomas));

		assertThat(customerService.getCustomers()).isEqualTo(asList(oliver, thomas));
	}

	@ParameterizedTest
	@MethodSource("customers")
	@DisplayName("provides customers by ID")
	public void getCustomerById(Long id, Customer customer) {
		CustomerService customerService = new CustomerService(customerRepository);

		when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

		assertThat(customerService.getCustomerById(id)).isEqualTo(Optional.of(customer));
	}

	static Stream<Arguments> customers() {
		Customer oliver = new Customer("Oliver", "Weiler");
		oliver.setId(1L);

		Customer thomas = new Customer("Thomas", "Bayer");
		oliver.setId(2L);

		return Stream.of(Arguments.of(1L, oliver), Arguments.of(2L, thomas));
	}
}