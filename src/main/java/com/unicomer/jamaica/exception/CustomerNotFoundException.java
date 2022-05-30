package com.unicomer.jamaica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -4653739789069308056L;

	public CustomerNotFoundException(String message){
        super(message);
    }

}
