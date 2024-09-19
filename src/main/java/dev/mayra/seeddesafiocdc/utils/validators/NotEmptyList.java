package dev.mayra.seeddesafiocdc.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotEmptyListValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyList {
    String message() default "The list must not be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
