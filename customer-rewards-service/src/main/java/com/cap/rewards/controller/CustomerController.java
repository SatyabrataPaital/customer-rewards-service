package com.cap.rewards.controller;

import com.cap.rewards.model.Customer;
import com.cap.rewards.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        log.info("Fetching customer with id {}", id);
        return customerRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer updated) {
        Customer existing = customerRepository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        return customerRepository.save(existing);
    }




    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}