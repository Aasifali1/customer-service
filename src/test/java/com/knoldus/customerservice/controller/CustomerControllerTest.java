package com.knoldus.customerservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.customerservice.model.Customer;
import com.knoldus.customerservice.service.CustomerService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;
    
    @MockBean
    private CustomerService customerService;
    
    @Test
    void testGetAllCustomers() throws Exception {
        when(this.customerService.getAllCustomers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/customers");
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    
    @Test
    void testGetAllCustomers2() throws Exception {
        when(this.customerService.getAllCustomers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/customers");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    
    @Test
    void testCreateCustomer() throws Exception {
        when(this.customerService.getAllCustomers()).thenReturn(new ArrayList<>());
        
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setEmail("jane.doe@example.org");
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    
    @Test
    void testDeleteCustomer() throws Exception {
        when(this.customerService.deleteCustomer((Long) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/customers/{customerId}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
    
    @Test
    void testGetCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setEmail("jane.doe@example.org");
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhone("4105551212");
        when(this.customerService.getCustomerById((Long) any())).thenReturn(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/customers/{customerId}", 123L);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212\",\"address\":\"42 Main St\"}"));
    }
}

