package dev.mayra.seeddesafiocdc.utils.validators;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.payment.PaymentRequestDTO;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
import dev.mayra.seeddesafiocdc.utils.exceptions.NotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class ValidStatesIfCountryHasStatesValidator implements ConstraintValidator<ValidStateIfCountryHasStates, PaymentRequestDTO> {

    private final CountryRepository countryRepository;

    public ValidStatesIfCountryHasStatesValidator(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean isValid(PaymentRequestDTO paymentRequestDTO, ConstraintValidatorContext context) {
        Optional<Country> possibleCountry = countryRepository.findById(paymentRequestDTO.getCountryId());

        if (possibleCountry.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Country not found")
                .addPropertyNode("countryId")
                .addConstraintViolation();
            return false;
        }

        Country country = possibleCountry.get();

        if (country.hasStates() && paymentRequestDTO.getStateId() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("State must be selected if the country has states")
                .addPropertyNode("stateId")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
