package com.springCrudV2.demo.validator;

import com.springCrudV2.demo.dto.LanguageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class LanguageDtoValidatorTest {
    private final LanguageDto dto = new LanguageDto();

    @Mock
    private Errors errors;

    @InjectMocks
    private LanguageDtoValidator languageDtoValidator;

    @Test
    public void shouldThrowExceptionWhenNameLongConsistOf35Symbols() {
        //given
        dto.setId(1L);
        dto.setName("Russiaaaaaaaaaaaaaaaaaaaaaaaaaaaaan");

        //when
        languageDtoValidator.validate(dto, errors);

        //than
        verify(errors, times(1)).reject("length.beyond.borders", "Language name length must be between 2 and 30 characters");
    }

    @Test
    public void shouldThrowExceptionWhenNameStartWithSmallLetter() {
        //given
        dto.setId(1L);
        dto.setName("russian");

        //when
        languageDtoValidator.validate(dto, errors);

        //than
        verify(errors, times(1)).reject("capital.letters", "Language name must start with capital letters");
    }

    @Test
    public void shouldThrowExceptionIfNameContainOfSomethingBesideSymbol() {
        //given
        dto.setId(1L);
        dto.setName("russ4ian");

        //when
        languageDtoValidator.validate(dto, errors);

        //than
        verify(errors, times(1)).reject("small.letters", "Language name must contain only small letters after first letter");
    }

    @Test
    public void shouldDoNothingWhenValidateDone() {
        //given
        dto.setId(1L);
        dto.setName("Russian");

        //when
        languageDtoValidator.validate(dto, errors);

        //than
        verify(errors, times(0)).reject(any());
    }
}