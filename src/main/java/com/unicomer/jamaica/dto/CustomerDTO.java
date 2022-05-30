package com.unicomer.jamaica.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicomer.jamaica.validation.Birthday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

	@JsonProperty(value =  "id", access = JsonProperty.Access.READ_ONLY)
	private Long id;
	@NotNull
	@JsonProperty("First Name")
	private String firstName;

	@NotNull
	@JsonProperty("Last Name")
	private String lastName;

	@JsonProperty("Birthday")
	@Past()
	@Birthday(message = "{invalid.birthday}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;
	
	@JsonProperty("Gender")
	private String gender;
	@Pattern(regexp="(^$|[0-9]{10})", message = "{invalid.cellPhone}")
	@JsonProperty("Cellphone")
	private String cellPhone;
	@Pattern(regexp="(^$|[0-9]{10})", message = "{invalid.homePhone}")
	@JsonProperty("Home phone")
	private String homePhone;
	
	@JsonProperty("Address Home")
	private String addressHome;
	
	@JsonProperty("Profession")
	private String profession;

	@Min(0)
	@JsonProperty("Incomes")
	private Double incomes;

}
