package com.cap.rewards;

import com.cap.rewards.controller.CustomerController;
import com.cap.rewards.model.Customer;
import com.cap.rewards.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CustomerControllerTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerController customerController;

	private AutoCloseable closeable;
	@BeforeEach
	void setUp() {
		// openMocks returns AutoCloseable, so store it
		closeable = MockitoAnnotations.openMocks(this);
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() throws Exception {
		// close mocks after each test
		closeable.close();
	}

	@Test
	void testUpdateCustomer_Success() {
		Long id = 1L;
		Customer existing = new Customer();
		existing.setId(id);
		existing.setName("Old Name");
		existing.setEmail("old@email.com");

		Customer updated = new Customer();
		updated.setId(id);
		updated.setName("New Name");
		updated.setEmail("new@email.com");

		when(customerRepository.findById(id)).thenReturn(Optional.of(existing));
		when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Customer result = customerController.update(id, updated);

		assertEquals("New Name", result.getName());
		assertEquals("new@email.com", result.getEmail());

		verify(customerRepository).findById(id);
		verify(customerRepository).save(existing);
	}

	@Test
	void testUpdateCustomer_NotFound() {
		Long id = 99L;
		Customer updated = new Customer();
		updated.setId(id);
		updated.setName("Name");
		updated.setEmail("email@email.com");

		when(customerRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> customerController.update(id, updated));

		verify(customerRepository).findById(id);
		verify(customerRepository, never()).save(any(Customer.class));
	}
}