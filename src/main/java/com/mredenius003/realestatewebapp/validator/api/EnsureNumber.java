package com.mredenius003.realestatewebapp.validator.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mredenius003.realestatewebapp.validator.EnsureNumberValidator;

@Constraint(validatedBy = EnsureNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnsureNumber {

    String message() default "{PasswordMatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean decimal() default false;

}
