package com.customer.demo.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CustomerValidator.class)
public @interface ValidateCustomer {

    public String message() default "Invalid Pattern";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
