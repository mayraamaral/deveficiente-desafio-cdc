package dev.mayra.seeddesafiocdc.utils.validators;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @Autowired
    private EntityManager entityManager;

    private String fieldName;
    private Class<?> domainClass;

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

        String jpql = "SELECT COUNT(e) FROM " + domainClass.getName() + " e WHERE e." + fieldName + " = :value";
        Long count = entityManager.createQuery(jpql, Long.class)
            .setParameter("value", value)
            .getSingleResult();

        Assert.state(count <= 1, "More than one entity found with the same value for " + fieldName);

        return count == 0;
    }
}