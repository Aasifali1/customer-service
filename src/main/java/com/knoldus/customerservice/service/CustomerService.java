package com.knoldus.customerservice.service;

import com.knoldus.customerservice.model.Customer;
import com.knoldus.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public Customer getCustomerById(Long id){
        return customerRepository.getById(id);
    }
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(Long customerId){
        Map map = new HashMap();
        customerRepository.deleteById(customerId);
        map.put("Deleted", true);
        return ResponseEntity.ok(map);
    }
}
