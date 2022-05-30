package com.unicomer.jamaica.repository.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.entities.Customer;
import com.unicomer.jamaica.exception.ApiResponse;
import com.unicomer.jamaica.exception.CustomerDataIntegrityViolation;
import com.unicomer.jamaica.exception.CustomerNotFoundException;
import com.unicomer.jamaica.repository.CustomerRepository;
import com.unicomer.jamaica.repository.service.CustomerService;
import com.unicomer.jamaica.util.Translator;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	private ModelMapper modelMapper;
	private Translator translator;
	
	
	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Translator translator) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
		this.translator = translator;
	}
	
	@Override
	public List<CustomerDTO> findAllcustomers() {
		List<Customer> customerList = (List<Customer>) customerRepository.findAll();
		if(!customerList.isEmpty()) {
			return modelMapper.map(customerList, new TypeToken<List<Customer>>() {}.getType());
		}else {
			throw new CustomerNotFoundException(translator.toLocale("customers.not.found"));
		}
	}
	
	@Override
	public CustomerDTO findCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			return modelMapper.map(customer.get(),CustomerDTO.class);
		}else {
			throw new CustomerNotFoundException(translator.toLocale("customer.not.found")+id);
		}
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
		try {
			customer = customerRepository.save(customer);
			return modelMapper.map(customer,CustomerDTO.class);
		} catch (DataIntegrityViolationException e) {
			throw new CustomerDataIntegrityViolation(translator.toLocale("customer.data.integrity.violation"));
		}
	}

	
	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDto) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			Customer customer = modelMapper.map(customerDto, Customer.class);
			customer.setId(id);
			return modelMapper.map(customerRepository.save(customer),CustomerDTO.class);
		}else {
			throw new CustomerNotFoundException(translator.toLocale("customer.not.found")+id);
		}
	}
	

	@Override
	public ApiResponse deleteCustomerById(Long id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			customerRepository.deleteById(id);
			//String message = translator.toLocale("deleted.customer");
			String message = "eliminado";
			return new ApiResponse(HttpStatus.OK.value(), message+id);
		}else {
			throw new CustomerNotFoundException(translator.toLocale("customer.not.found")+id);
		}
		
	}

	

}
