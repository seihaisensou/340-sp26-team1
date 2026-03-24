package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.Customer;
import com.csc340.AccessAble.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDetails.getEmail() != null) {
                customer.setEmail(customerDetails.getEmail());
            }
            if (customerDetails.getFirstname() != null && customerDetails.getLastname() != null) {
                customer.setFirstname(customerDetails.getFirstname());
                customer.setLastname(customerDetails.getLastname());
            }
            if (customerDetails.getLocation() != null) {
                customer.setLocation(customerDetails.getLocation());
            }
            if (customerDetails.getServices() != null) {
                customer.setServices(customerDetails.getServices());
            }
            if (customerDetails.getStatus() != null) {
                customer.setStatus(customerDetails.getStatus());
            }
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}