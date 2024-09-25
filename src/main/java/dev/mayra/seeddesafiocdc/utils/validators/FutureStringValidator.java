package dev.mayra.seeddesafiocdc.utils.validators;

import dev.mayra.seeddesafiocdc.utils.date.DateUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FutureStringValidator implements ConstraintValidator<FutureString, String> {

    @Override
    public void initialize(FutureString constraintAnnotation) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }
        try {
            LocalDate localDate = DateUtils.fromStringFormattedToLocalDate(date);
            return localDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}