package com.unicomer.jamaica;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.unicomer.jamaica.dto.CustomerDTO;
import com.unicomer.jamaica.entities.Customer;

public class JsonDummies {
	
	public static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
		@Override
		public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
		}
	}).create();

	private static String json1 = "{\"id\":1,\"firstName\":\"Francisco\",\"lastName\":\"Quinteros\",\"birthday\":\"1989-07-31\",\"gender\":\"Male\",\"cellPhone\":\"5699141873\",\"homePhone\":\"\",\"addressHome\":\"BourbonStreet\",\"profession\":\"Software developer\",\"incomes\":1000.2}";
	private static String json2 = "{\"id\":2,\"firstName\":\"Lissete\",\"lastName\":\"Valenzuela\",\"birthday\":\"1993-12-12\",\"gender\":\"Female\",\"cellPhone\":\"5699123443\",\"homePhone\":\"5622299977\",\"addressHome\":\"DixonStreet\",\"profession\":\"Nurse\",\"incomes\":9000.5}";
	private static String json3 = "{\"id\":3,\"firstName\":\"Yolanda\",\"lastName\":\"Gonzalez\",\"birthday\":\"1963-12-12\",\"gender\":\"Female\",\"cellPhone\":\"5699122343\",\"homePhone\":\"\",\"addressHome\":\"Rompe Hielos Street\",\"profession\":\"\",\"incomes\":0}";
	private static String json4 = "{\"id\":4,\"firstName\":\"Sergio\",\"lastName\":\"Torres\",\"birthday\":\"1956-12-23\",\"gender\":\"Male\",\"cellPhone\":\"5699124454\",\"homePhone\":\"5622255344\",\"addressHome\":\"Anda Street\",\"profession\":\"Analyst\",\"incomes\":0}";


	public static Customer getCustomerDummyOk1() {
		Customer customer = gson.fromJson(json1, Customer.class);
		return customer;
	}
	
	public static Customer getCustomerDummyOk2() {
		Customer customer = gson.fromJson(json2, Customer.class);
		return customer;
	}
	
	public static Customer getCustomerDummyOk3() {
		Customer customer = gson.fromJson(json3, Customer.class);
		return customer;
	}
	
	public static Customer getCustomerDummyOk4() {
		Customer customer = gson.fromJson(json4, Customer.class);
		return customer;
	}
	
	public static List<Customer> getCustomersDummySize4() {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(getCustomerDummyOk1());
		customerList.add(getCustomerDummyOk2());
		customerList.add(getCustomerDummyOk3());
		customerList.add(getCustomerDummyOk4());
		return customerList;
	}
	
	
	public static CustomerDTO getCustomerDTODummyOk1() {
		CustomerDTO customerDTO = gson.fromJson(json1, CustomerDTO.class);
		return customerDTO;
	}
	
	public static CustomerDTO getCustomerDTODummyOk2() {
		CustomerDTO customerDTO = gson.fromJson(json2, CustomerDTO.class);
		return customerDTO;
	}
	
	public static CustomerDTO getCustomerDTODummyOk3() {
		CustomerDTO customerDTO = gson.fromJson(json3, CustomerDTO.class);
		return customerDTO;
	}
	
	public static CustomerDTO getCustomerDTODummyOk4() {
		CustomerDTO customerDTO = gson.fromJson(json4, CustomerDTO.class);
		return customerDTO;
	}
	
	public static List<CustomerDTO> getCustomersDTODummySize4() {
		List<CustomerDTO> customerList = new ArrayList<>();
		customerList.add(getCustomerDTODummyOk1());
		customerList.add(getCustomerDTODummyOk2());
		customerList.add(getCustomerDTODummyOk3());
		customerList.add(getCustomerDTODummyOk4());
		return customerList;
	}

}
