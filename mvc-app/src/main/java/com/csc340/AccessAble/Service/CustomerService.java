package com.csc340.AccessAble.Service;

import com.csc340.AccessAble.Entities.Customer;
import com.csc340.AccessAble.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "src/main/resources/static/customerpfp/";

    public Customer createCustomer(Customer customer) {
        customer.setRole("CUSTOMER");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
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
            if (customerDetails.getFirstName() != null) {
                customer.setFirstName(customerDetails.getFirstName());
            }
            if (customerDetails.getLastName() != null) {
                customer.setLastName(customerDetails.getLastName());
            }
            if (customerDetails.getLocation() != null) {
                customer.setLocation(customerDetails.getLocation());
            }
            if (customerDetails.getServices() != null) {
                customer.setServices(customerDetails.getServices());
            }
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void saveProfilePicture(Customer customer, MultipartFile profilePicture) {
    if (profilePicture == null || profilePicture.isEmpty()) {
      return; // No picture uploaded, skip saving
    }
    String originalFileName = profilePicture.getOriginalFilename();
    try {
      if (originalFileName != null && originalFileName.contains(".")) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = String.valueOf(customer.getId()) + "." + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        InputStream inputStream = profilePicture.getInputStream();

        Files.createDirectories(Paths.get(UPLOAD_DIR));// Ensure directory exists
        Files.copy(inputStream, filePath,
            StandardCopyOption.REPLACE_EXISTING);// Save picture file
        customer.setProfilePicturePath(fileName);
        customerRepository.save(customer);// Update student with picture path
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}