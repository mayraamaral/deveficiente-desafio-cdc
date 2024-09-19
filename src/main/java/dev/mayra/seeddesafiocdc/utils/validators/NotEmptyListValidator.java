package dev.mayra.seeddesafiocdc.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List<?>> {

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        return list != null && !list.isEmpty();
    }
}