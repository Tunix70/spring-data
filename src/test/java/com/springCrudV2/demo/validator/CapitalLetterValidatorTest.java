package com.springCrudV2.demo.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CapitalLetterValidatorTest {


    private CapitalLetterValidator capitalLetterValidator = new CapitalLetterValidator();

    @Test
    void shouldReturnTrueIfValueIsValid() {
        String value = "Correct Value";
        //when
        boolean result = capitalLetterValidator.isValid(value, null);
        //than
        assertThat(result).isEqualTo(true);
    }

    @Test
    void shouldReturnFalseIfValueIsNull() {
        String value = null;
        //when
        boolean result = capitalLetterValidator.isValid(value, null);
        //than
        assertThat(result).isEqualTo(false);
    }

    @Test
    void shouldReturnFalseIfValueIsBlank() {
        String value = "";
        //when
        boolean result = capitalLetterValidator.isValid(value, null);
        //than
        assertThat(result).isEqualTo(false);
    }

    @Test
    void shouldReturnFalseIfFirstCharIsLowCase() {
        String value = "value";
        //when
        boolean result = capitalLetterValidator.isValid(value, null);
        //than
        assertThat(result).isEqualTo(false);
    }
}