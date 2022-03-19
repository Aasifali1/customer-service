package com.knoldus.customerservice.controller;

import com.knoldus.customerservice.model.Customer;
import com.knoldus.customerservice.service.CustomerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId")
            Long customerId) {
        return customerService.getCustomerById(customerId);
    }
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId")
            Long customerId){
        System.out.println("Coming inside method");
        return customerService.deleteCustomer(customerId);
    }
}
