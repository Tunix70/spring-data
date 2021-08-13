package com.springCrudV2.demo.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SmallLetterValidatorTest {
    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private SmallLetterValidator smallLetterValidator;

    @Test
    void shouldReturnTrueIfValueIsValid() {
        String value = "Correct";
        //when
        boolean result = smallLetterValidator.isValid(value, constraintValidatorContext);
        //than
        assertThat(result).isEqualTo(true);
    }

    @Test
    void shouldReturnFalseIfValueIsNull() {
        String value = null;
        //when
        boolean result = smallLetterValidator.isValid(value, constraintValidatorContext);
        //than
        assertThat(result).isEqualTo(false);
    }

    @Test
    void shouldReturnFalseIfValueIsBlank() {
        String value = "";
        //when
        boolean result = smallLetterValidator.isValid(value, constraintValidatorContext);
        //than
        assertThat(result).isEqualTo(false);
    }

    @Test
    void shouldReturnFalseIfValueContainsSomethingOtherThanAbc() {
        String value = "123";
        //when
        boolean result = smallLetterValidator.isValid(value, constraintValidatorContext);
        //than
        assertThat(result).isEqualTo(false);
    }

}