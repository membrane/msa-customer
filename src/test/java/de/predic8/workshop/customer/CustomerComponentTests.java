package de.predic8.workshop.customer;

import de.predic8.workshop.customer.domain.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Disabled("Currently does not work in Jenkins and must be triggered manually")
@TestPropertySource("classpath:application-test.properties")
@DisplayName("A customer component")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CustomerComponentTests {
	@Autowired
	private TestRestTemplate testRestTemplate;
	@LocalServerPort
	int port;

	@DisplayName("creates new customers")
	@Test
	void createCustomer() {
		ResponseEntity<Void> response = testRestTemplate.postForEntity("/customers", new Customer("Oliver", "Weiler"), Void.class);

		assertThat(response.getHeaders().getLocation()).isEqualTo(URI.create("http://localhost:" + port + "/customers/1"));
	}
}