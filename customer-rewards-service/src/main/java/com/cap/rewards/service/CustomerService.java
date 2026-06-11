package com.cap.rewards.service;

import com.cap.rewards.model.Customer;
import com.cap.rewards.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 🔹 Create or Insert a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // 🔹 Insert multiple customers at once
    public List<Customer> addCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    // 🔹 Read all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 🔹 Read single customer by ID
    public Customer getCustomerById(Long id) {
        log.debug("Looking up get customer with id {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    // 🔹 Update customer
    public Customer updateCustomer(Long id, Customer updated) {
        log.debug("Looking up update customer with id {}", id);
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());

        return customerRepository.save(existing);
    }

    // 🔹 Delete customer
    public void deleteCustomer(Long id) {
        log.debug("Looking up delete customer with id {}", id);
        customerRepository.deleteById(id);
    }
}
