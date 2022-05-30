package com.unicomer.jamaica.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unicomer.jamaica.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
