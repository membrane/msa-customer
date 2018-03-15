package de.predic8.workshop.customer.repository;

import de.predic8.workshop.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}