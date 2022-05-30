package com.unicomer.jamaica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicomer.jamaica.JsonDummies;
import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.exception.ApiResponse;
import com.unicomer.jamaica.repository.service.CustomerService;

@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@MockBean
	private CustomerService customerService;
	
	private List<CustomerDTO> customerDTOList;
    private CustomerDTO customerDTO;
    ObjectMapper objectMapper;
    
    private String baseUrl = "/api/v1";
	
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	objectMapper = new ObjectMapper();
    }

    @Test
    void getCustomers_ok() throws Exception {
    	String uriTest = baseUrl+"/customers";
    	customerDTOList = JsonDummies.getCustomersDTODummySize4();
    	when(customerService.findAllcustomers()).thenReturn(customerDTOList);
        this.mockMvc.perform(get(uriTest)).andDo(print()).andExpect(status().isOk());
        
        verify(customerService).findAllcustomers();
    	
    }

    @Test
    void getCustomerById() throws Exception {
    	
    	String uriTest = baseUrl+"/customerById/1";
    	customerDTO = JsonDummies.getCustomerDTODummyOk1();
    	when(customerService.findCustomerById(1L)).thenReturn(customerDTO);
        this.mockMvc.perform(get(uriTest)).andDo(print()).andExpect(status().isOk());
        
        verify(customerService).findCustomerById(any(Long.class));
    }

    @Test
    void saveCustomer() throws Exception {
    	String uriTest = baseUrl+"/saveCustomer/";
    	customerDTO = new CustomerDTO();
    	customerDTO.setFirstName("Francisco");
    	customerDTO.setLastName("Quinteros");
    	String requestJson=objectMapper.writeValueAsString(customerDTO);
    	
    	mockMvc.perform(post(uriTest).contentType(MediaType.APPLICATION_JSON)
    	        .content(requestJson))
    	        .andExpect(status().isCreated());
        
        verify(customerService).saveCustomer(any(CustomerDTO.class));
    }
    
    @Test
    void saveCustomer_validate_error() throws Exception {
    	String uriTest = baseUrl+"/saveCustomer/";
    	customerDTO = new CustomerDTO();
    	customerDTO.setFirstName("Francisco");
    	String requestJson=objectMapper.writeValueAsString(customerDTO);
    	
    	
    	mockMvc.perform(post(uriTest).contentType(MediaType.APPLICATION_JSON)
    	        .content(requestJson))
    	        .andExpect(status().isBadRequest());
        
    }

    @Test
    void updateCustomer() throws Exception {
    	Long id = 1L;
    	String uriTest = baseUrl+"/updateCustomer/"+id;
    	customerDTO = new CustomerDTO();
    	customerDTO.setFirstName("Francisco");
    	customerDTO.setLastName("Quinteros");
    	String requestJson=objectMapper.writeValueAsString(customerDTO);
    	
    	when(customerService.updateCustomer(id, customerDTO)).thenReturn(customerDTO);
    	
    	mockMvc.perform(put(uriTest).contentType(MediaType.APPLICATION_JSON)
    	        .content(requestJson))
    	        .andExpect(status().isOk());
        
        verify(customerService).updateCustomer(id, customerDTO);
    }
    
    @Test
    void updateCustomer_error_validations() throws Exception {
    	Long id = 1L;
    	String uriTest = baseUrl+"/updateCustomer/"+id;
    	customerDTO = new CustomerDTO();
    	customerDTO.setFirstName("Francisco");
    	customerDTO.setCellPhone("1223aaa");
    	String requestJson=objectMapper.writeValueAsString(customerDTO);
    	
    	when(customerService.updateCustomer(id, customerDTO)).thenReturn(customerDTO);
    	
    	mockMvc.perform(put(uriTest).contentType(MediaType.APPLICATION_JSON)
    	        .content(requestJson))
    	        .andExpect(status().isBadRequest());
        
    }

    @Test
    void deleteCustomerById() throws Exception {
    	ApiResponse apiResponse = new ApiResponse(200,"deleted");
    	Long id = 2L;
    	String uriTest = baseUrl+"/deleteCustomerById/"+id;
    	when(customerService.deleteCustomerById(id)).thenReturn(apiResponse);
    	
    	this.mockMvc.perform(MockMvcRequestBuilders
                .delete(uriTest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        
        verify(customerService).deleteCustomerById(any(Long.class));
    }
}