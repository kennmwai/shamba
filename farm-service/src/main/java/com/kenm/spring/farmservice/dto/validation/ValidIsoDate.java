/**
 *
 */
package com.kenm.spring.farmservice.dto.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kenm.spring.farmservice.dto.validation.impl.IsoDateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * @author User
 *
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsoDateValidator.class)
public @interface ValidIsoDate {

    String message() default "Invalid ISO date format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
