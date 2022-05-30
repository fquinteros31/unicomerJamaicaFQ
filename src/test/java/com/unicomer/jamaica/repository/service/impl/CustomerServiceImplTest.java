package com.unicomer.jamaica.repository.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.unicomer.jamaica.JsonDummies;
import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.exception.CustomerNotFoundException;
import com.unicomer.jamaica.repository.CustomerRepository;
import com.unicomer.jamaica.repository.service.CustomerService;
import com.unicomer.jamaica.util.Translator;

@SpringJUnitConfig
@SpringBootTest
class CustomerServiceImplTest {

    private CustomerService customerService;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private Translator translator;

    private List<CustomerDTO> customerDTOList;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
    	customerService = new CustomerServiceImpl(customerRepository, modelMapper, translator);
    }
    
    @Test
    void saveCustomer_ok() {
        customerDTO = JsonDummies.getCustomerDTODummyOk1();
        CustomerDTO customer2DTO = JsonDummies.getCustomerDTODummyOk2();
        CustomerDTO customer3DTO = JsonDummies.getCustomerDTODummyOk3();
        CustomerDTO customer4DTO = JsonDummies.getCustomerDTODummyOk4();
        assertNotNull(customerService.saveCustomer(customerDTO));
        assertNotNull(customerService.saveCustomer(customer2DTO));
        assertNotNull(customerService.saveCustomer(customer3DTO));
        assertNotNull(customerService.saveCustomer(customer4DTO));
    }

    @Test
    void findAllcustomers() {
        customerDTOList = customerService.findAllcustomers();

        assertNotNull(customerDTOList);

    }

    @Test
    void findCustomerById() {
        CustomerDTO customerDTO = customerService.findCustomerById(1L);
        assertNotNull(customerDTO);
    }

    @Test
    void findCustomerById_error() {
        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById(0L));
    }

    /*
    @Test
    void updateCustomer() {

        customerDTO = JsonDummies.getCustomerDTODummyOk1();
        customerDTO.setIncomes(300D);
        assertNotNull(customerService.updateCustomer(1L,customerDTO));

    }
    */

    @Test
    void updateCustomer_error() {

        customerDTO = JsonDummies.getCustomerDTODummyOk1();
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(0L,customerDTO));
    }

    /*
    @Test
    void deleteCustomerById() {
        assertNotNull(customerService.deleteCustomerById(2L));
    }
    */
    

    @Test
    void deleteCustomerById_error() {
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomerById(0L));
    }
}