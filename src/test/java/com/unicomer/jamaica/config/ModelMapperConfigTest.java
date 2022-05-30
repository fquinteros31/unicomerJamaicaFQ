package com.unicomer.jamaica.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.unicomer.jamaica.JsonDummies;
import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.entities.Customer;

@SpringJUnitConfig
class ModelMapperConfigTest {


    private ModelMapper modelMapper;

    @Mock
    private ModelMapper modelMapperMock;

    CustomerDTO customerDTO;
    Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
    }

    @Test
    void when_ConvertsToDTO() {

        customer = JsonDummies.getCustomerDummyOk1();
        
        System.out.println("el customer "+customer);

        customerDTO = modelMapper.map(customer, CustomerDTO.class);

        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
        assertEquals(customer.getBirthday(), customerDTO.getBirthday());
        assertEquals(customer.getGender(), customerDTO.getGender());
        assertEquals(customer.getCellPhone(), customerDTO.getCellPhone());
        assertEquals(customer.getHomePhone(), customerDTO.getHomePhone());
        assertEquals(customer.getAddressHome(), customerDTO.getAddressHome());
        assertEquals(customer.getProfession(), customerDTO.getProfession());
        assertEquals(customer.getIncomes(), customerDTO.getIncomes());
    }

    @Test
    void when_ConvertsToEntity() {
        customerDTO = JsonDummies.getCustomerDTODummyOk1();

        customer = modelMapper.map(customerDTO, Customer.class);

        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
        assertEquals(customer.getBirthday(), customerDTO.getBirthday());
        assertEquals(customer.getGender(), customerDTO.getGender());
        assertEquals(customer.getCellPhone(), customerDTO.getCellPhone());
        assertEquals(customer.getHomePhone(), customerDTO.getHomePhone());
        assertEquals(customer.getAddressHome(), customerDTO.getAddressHome());
        assertEquals(customer.getProfession(), customerDTO.getProfession());
        assertEquals(customer.getIncomes(), customerDTO.getIncomes());
    }

    @Test
    void when_ConvertsToDTOMockito() {

        customerDTO = JsonDummies.getCustomerDTODummyOk1();
        customer = JsonDummies.getCustomerDummyOk1();

        when(modelMapperMock.map(customer,CustomerDTO.class)).thenReturn(customerDTO);
        CustomerDTO customerDtoMock = modelMapperMock.map(customer,CustomerDTO.class);


        assertEquals(customer.getId(), customerDtoMock.getId());
        assertEquals(customer.getFirstName(), customerDtoMock.getFirstName());
        assertEquals(customer.getLastName(), customerDtoMock.getLastName());
        assertEquals(customer.getBirthday(), customerDtoMock.getBirthday());
        assertEquals(customer.getGender(), customerDtoMock.getGender());
        assertEquals(customer.getCellPhone(), customerDtoMock.getCellPhone());
        assertEquals(customer.getHomePhone(), customerDtoMock.getHomePhone());
        assertEquals(customer.getAddressHome(), customerDtoMock.getAddressHome());
        assertEquals(customer.getProfession(), customerDtoMock.getProfession());
        assertEquals(customer.getIncomes(), customerDtoMock.getIncomes());

    }

    @Test
    void when_ConvertsToEntityMockito() {

        customerDTO = JsonDummies.getCustomerDTODummyOk1();
        customer = JsonDummies.getCustomerDummyOk1();

        when(modelMapperMock.map(customerDTO,Customer.class)).thenReturn(customer);
        Customer customerMock = modelMapperMock.map(customerDTO,Customer.class);

        assertEquals(customerMock.getId(), customerDTO.getId());
        assertEquals(customerMock.getFirstName(), customerDTO.getFirstName());
        assertEquals(customerMock.getLastName(), customerDTO.getLastName());
        assertEquals(customerMock.getBirthday(), customerDTO.getBirthday());
        assertEquals(customerMock.getGender(), customerDTO.getGender());
        assertEquals(customerMock.getCellPhone(), customerDTO.getCellPhone());
        assertEquals(customerMock.getHomePhone(), customerDTO.getHomePhone());
        assertEquals(customerMock.getAddressHome(), customerDTO.getAddressHome());
        assertEquals(customerMock.getProfession(), customerDTO.getProfession());
        assertEquals(customerMock.getIncomes(), customerDTO.getIncomes());

    }

}