package com.unicomer.jamaica.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.exception.ApiResponse;
import com.unicomer.jamaica.repository.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
@Validated
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers(){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllcustomers());
	}
	
	@GetMapping("/customerById/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable @Min(1) Long id){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerById(id));
	}
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customerDto));
	}
	
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDto, @PathVariable  @Min(1) Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerDto));

    }
	
	@DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<ApiResponse> deleteCustomerById(@PathVariable @Min(1) Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomerById(id));
    }

}
