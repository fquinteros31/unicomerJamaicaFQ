package com.unicomer.jamaica.repository.service;

import java.util.List;

import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.exception.ApiResponse;

public interface CustomerService {
	
	List<CustomerDTO> findAllcustomers();
	CustomerDTO findCustomerById(Long id);
	CustomerDTO saveCustomer(CustomerDTO customerDto);
	CustomerDTO updateCustomer(Long id, CustomerDTO customerDto);
	ApiResponse deleteCustomerById(Long id);

}
