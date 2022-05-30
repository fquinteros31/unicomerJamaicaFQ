package com.unicomer.jamaica.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name = "BIRTHDAY")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "CELL_PHONE")
	private String cellPhone;
	
	@Column(name = "HOME_PHONE")
	private String homePhone;
	
	@Column(name = "ADDRESS_HOME")
	private String addressHome;
	
	@Column(name = "PROFESSION")
	private String profession;
	
	@Column(name = "INCOMES")
	private Double incomes;

}
