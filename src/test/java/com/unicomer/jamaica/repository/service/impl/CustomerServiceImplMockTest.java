package com.unicomer.jamaica.repository.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.unicomer.jamaica.JsonDummies;
import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.entities.Customer;
import com.unicomer.jamaica.exception.CustomerDataIntegrityViolation;
import com.unicomer.jamaica.exception.CustomerNotFoundException;
import com.unicomer.jamaica.repository.CustomerRepository;
import com.unicomer.jamaica.repository.service.CustomerService;
import com.unicomer.jamaica.util.Translator;

@SpringJUnitConfig
class CustomerServiceImplMockTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private ModelMapper modelMapper; 
	
	@Mock
	private Translator translator;
	
	@MockBean
    private CustomerService customerService;

    private List<CustomerDTO> customerDTOList;
    private List<Customer> customerList;
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, modelMapper, translator);
    }

    @Test
    void findAllcustomers_ok() {
    	
    	
    	customerDTOList = JsonDummies.getCustomersDTODummySize4();
    	customerList = JsonDummies.getCustomersDummySize4();
    	
    	when(customerRepository.findAll()).thenReturn(customerList);
    	when(customerService.findAllcustomers()).thenReturn(customerDTOList);


    	assertEquals(4, customerService.findAllcustomers().size());
    }
    
    @Test
    void findAllcustomers_when_not_found() {
    	assertThrows(CustomerNotFoundException.class, () -> customerService.findAllcustomers());
    }
    

    @Test
    void findCustomerById() {
    	Long id = 1L;
    	CustomerDTO customerDTO = JsonDummies.getCustomerDTODummyOk1();
    	Optional<Customer> customer = Optional.of(JsonDummies.getCustomerDummyOk1());
    	when(customerRepository.findById(id)).thenReturn(customer);
    	when(customerService.findCustomerById(id)).thenReturn(customerDTO);
    	assertEquals(customerDTO, customerService.findCustomerById(id));
    }

    @Test
    void findCustomerById_when_not_found() {
    	assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById(0L));
    }

    @Test
    void saveCustomer() {
    	CustomerDTO customerDTO = JsonDummies.getCustomerDTODummyOk1();
    	Customer customer = JsonDummies.getCustomerDummyOk1();
    	when(customerRepository.save(customer)).thenReturn(customer);
    	when(customerService.saveCustomer(customerDTO)).thenReturn(customerDTO);
    	assertEquals(customerDTO, customerService.saveCustomer(customerDTO));
    }
    
    @Test
    void saveCustomer_error() {
    	CustomerDTO customerDTO = JsonDummies.getCustomerDTODummyOk1();
    	when(customerRepository.save(any(Customer.class))).thenThrow(DataIntegrityViolationException.class);
    	when(customerService.saveCustomer(customerDTO)).thenThrow(CustomerDataIntegrityViolation.class);
    	assertThrows(CustomerDataIntegrityViolation.class, () -> customerService.saveCustomer(customerDTO));
    }

    @Test
    void updateCustomer() {
    	Long id = 1L;
    	CustomerDTO customerDTO = JsonDummies.getCustomerDTODummyOk1();
    	Customer customer = JsonDummies.getCustomerDummyOk1();
    	Optional<Customer> customerOptional = Optional.of(JsonDummies.getCustomerDummyOk1());
    	
    	when(customerRepository.findById(id)).thenReturn(customerOptional);
    	when(customerRepository.save(customer)).thenReturn(customer);
    	when(modelMapper.map(customerDTO, Customer.class)).thenReturn(customer);
    	when(customerService.updateCustomer(id, customerDTO)).thenReturn(customerDTO);
    	
    	assertEquals(customerDTO, customerService.updateCustomer(id, customerDTO));
    	
    }
    
    
    @Test
    void updateCustomer_not_found() {
    	Long id = 0L;
    	CustomerDTO customerDTO = JsonDummies.getCustomerDTODummyOk1();
    	assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(id, customerDTO));
    }
    
    
    @Test
    void deleteCustomerById() {
    	 Long id=2L;

    	 Optional<Customer> customerOptional = Optional.of(JsonDummies.getCustomerDummyOk1());
    	 when(customerRepository.findById(id)).thenReturn(customerOptional);
    	 customerService.deleteCustomerById(id);

         verify(customerRepository, times(1)).deleteById(eq(id));
    	
    }
    
    @Test
    void deleteCustomerById_not_found() {
    	Long id = 0L;
    	assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomerById(id));
    }
}