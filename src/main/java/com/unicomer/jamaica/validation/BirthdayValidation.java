package com.unicomer.jamaica.validation;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class BirthdayValidation implements ConstraintValidator<Birthday, LocalDate>{

	
	
	@Override
    public void initialize(Birthday constraintAnnotation) {
    }
    
	
	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
            return true;
        }
		int edad = calculateAge(value, LocalDate.now());
		return edad >= 18;
	}

	public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
	    return Period.between(birthDate, currentDate).getYears();
	}
	
}
