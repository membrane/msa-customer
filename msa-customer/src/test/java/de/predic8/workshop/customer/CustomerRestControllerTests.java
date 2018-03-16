package de.predic8.workshop.customer;

import de.predic8.workshop.customer.api.CustomerRestController;
import de.predic8.workshop.customer.domain.Customer;
import de.predic8.workshop.customer.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("A customer controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTests {
	@MockBean
	private CustomerService customerService;
	@Autowired
	private MockMvc mvc;

	@DisplayName("provides the list of customers")
	@Test
	public void getCustomers() throws Exception {
		Customer oliver = new Customer("Oliver", "Weiler");
		oliver.setId(1L);

		when(customerService.getCustomers())
			.thenReturn(singletonList(oliver));

		mvc.perform(get("/customers"))
		   .andExpect(status().isOk());
	}
}