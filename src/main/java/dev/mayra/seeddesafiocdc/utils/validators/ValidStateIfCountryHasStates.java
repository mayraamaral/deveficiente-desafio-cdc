package dev.mayra.seeddesafiocdc.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidStatesIfCountryHasStatesValidator.class })
public @interface ValidStateIfCountryHasStates {
    String message() default "State must be selected if the country has states";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
