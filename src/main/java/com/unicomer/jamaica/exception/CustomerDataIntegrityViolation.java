package com.unicomer.jamaica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerDataIntegrityViolation extends RuntimeException{
	private static final long serialVersionUID = -4653739789069308056L;

	public CustomerDataIntegrityViolation(String message){
        super(message);
    }

}
