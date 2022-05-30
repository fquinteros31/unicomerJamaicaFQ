package com.unicomer.jamaica.exception;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.unicomer.jamaica.util.Translator;



@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler{

	private Translator translator;
	
	GlobalControllerExceptionHandler(Translator translator){
		this.translator = translator;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleConnversion(RuntimeException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ApiResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
		ApiResponse errorObj = new ApiResponse();
		errorObj.setStatus(HttpStatus.NOT_FOUND.value());
		errorObj.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomerDataIntegrityViolation.class)
	public ResponseEntity<ApiResponse> handleCustomerDataIntegrityViolation(CustomerDataIntegrityViolation ex) {
		ApiResponse errorObj = new ApiResponse();
		errorObj.setStatus(HttpStatus.CONFLICT.value());
		errorObj.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorObj, HttpStatus.CONFLICT);
	}
	

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
		    HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
		    WebRequest request) {
		
		String error = translator.toLocale("invalid.format.json");
	    
	    ApiResponseMessagesList apiError = 
	      new ApiResponseMessagesList(HttpStatus.BAD_REQUEST.value(), error);
	    return new ResponseEntity<>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
		
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        //
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiResponseMessagesList apiError = new ApiResponseMessagesList(HttpStatus.BAD_REQUEST.value(), errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String error = ex.getParameterName() + " parameter is missing";
	    
	    ApiResponseMessagesList apiError = 
	      new ApiResponseMessagesList(HttpStatus.BAD_REQUEST.value(), error);
	    return new ResponseEntity<>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ApiResponseMessagesList> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	    List<String> errors = new ArrayList<>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getName() + " " + 
	          violation.getPropertyPath() + ": " + violation.getMessage());
	    }

	    ApiResponseMessagesList apiError = 
	      new ApiResponseMessagesList(HttpStatus.BAD_REQUEST.value(), errors);
	    return new ResponseEntity<>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String error = 
	      ex.getName() + " should be of type " + ex.getRequiredType().getName();

	    ApiResponseMessagesList apiError = 
	      new ApiResponseMessagesList(HttpStatus.BAD_REQUEST.value(), error);
	    return new ResponseEntity<>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}


}
