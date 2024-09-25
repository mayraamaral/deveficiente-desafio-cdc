package dev.mayra.seeddesafiocdc.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
@ReportAsSingleViolation
@Constraint(validatedBy = {})
public @interface DocumentValidador {
    String message() default "Invalid document";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
