package dev.mayra.seeddesafiocdc.utils.validators;

import dev.mayra.seeddesafiocdc.model.author.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UniqueValueValidatorTest {

    private UniqueValueValidator validator;
    private EntityManager entityManager;
    private ConstraintValidatorContext context;
    TypedQuery<Boolean> query;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        entityManager = mock(EntityManager.class);
        context = mock(ConstraintValidatorContext.class);
        validator = new UniqueValueValidator(entityManager);
        query = mock(TypedQuery.class);
    }

    @Test
    public void isValid__should_return_true_when_value_is_unique() {
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(false);

        when(entityManager.createQuery(anyString(), eq(Boolean.class))).thenReturn(query);

        UniqueValue uniqueValueAnnotation = mock(UniqueValue.class);
        when(uniqueValueAnnotation.fieldName()).thenReturn("field");
        doReturn(Object.class).when(uniqueValueAnnotation).domainClass();

        validator.initialize(uniqueValueAnnotation);

        boolean isValid = validator.isValid("unique", context);
        assertThat(isValid).isTrue();
    }

    @Test
    public void isValid__should_return_false_when_value_already_exists() {
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(true);

        when(entityManager.createQuery(anyString(), eq(Boolean.class))).thenReturn(query);

        UniqueValue uniqueValueAnnotation = mock(UniqueValue.class);
        when(uniqueValueAnnotation.fieldName()).thenReturn("field");
        doReturn(Object.class).when(uniqueValueAnnotation).domainClass();

        validator.initialize(uniqueValueAnnotation);

        boolean isValid = validator.isValid("not_unique", context);
        assertThat(isValid).isFalse();
    }
}
