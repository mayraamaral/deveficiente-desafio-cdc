package dev.mayra.seeddesafiocdc.utils.validators;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private final EntityManager entityManager;

    private String fieldName;
    private Class<?> domainClass;

    public UniqueValueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String jpql = "SELECT COUNT(e) > 0 FROM "
            .concat(domainClass.getName())
            .concat(" e WHERE e.")
            .concat(fieldName)
            .concat(" = :value");

        boolean alreadyExists = entityManager.createQuery(jpql, Boolean.class)
            .setParameter("value", value)
            .getSingleResult();

        return !alreadyExists;
    }
}