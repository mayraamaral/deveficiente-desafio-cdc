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
    TypedQuery<Long> query;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        entityManager = mock(EntityManager.class);
        context = mock(ConstraintValidatorContext.class);
        validator = new UniqueValueValidator(entityManager);
        query = mock(TypedQuery.class);
    }

    @Test
    public void shouldReturnTrueWhenValueIsUnique() {
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(0L);

        when(entityManager.createQuery(anyString(), eq(Long.class))).thenReturn(query);

        UniqueValue uniqueValueAnnotation = mock(UniqueValue.class);
        when(uniqueValueAnnotation.fieldName()).thenReturn("email");
        doReturn(Author.class).when(uniqueValueAnnotation).domainClass();

        validator.initialize(uniqueValueAnnotation);

        boolean isValid = validator.isValid("unique@example.com", context);
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenValueAlreadyExists() {
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(1L);

        when(entityManager.createQuery(anyString(), eq(Long.class))).thenReturn(query);

        UniqueValue uniqueValueAnnotation = mock(UniqueValue.class);
        when(uniqueValueAnnotation.fieldName()).thenReturn("email");
        doReturn(Author.class).when(uniqueValueAnnotation).domainClass();

        validator.initialize(uniqueValueAnnotation);

        boolean isValid = validator.isValid("not_unique@example.com", context);
        assertThat(isValid).isFalse();
    }
}
