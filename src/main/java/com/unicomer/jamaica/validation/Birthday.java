package com.unicomer.jamaica.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
    ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdayValidation.class)
@Documented
public @interface Birthday {
	
	String message() default "{invalid.birthday}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
