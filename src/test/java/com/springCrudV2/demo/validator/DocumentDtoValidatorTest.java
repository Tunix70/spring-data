package com.springCrudV2.demo.validator;

import com.springCrudV2.demo.dto.DocumentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DocumentDtoValidatorTest {
    private final DocumentDto documentDto = new DocumentDto();
    
    @Mock
    private Errors errors;
    
    @InjectMocks
    private DocumentDtoValidator documentDtoValidator;
    
    @Test
    public void shouldThrowExceptionWhenYearIsPast() {
        //given
        documentDto.setExpiry_date(new Date(1990-02-02));

        //when
        documentDtoValidator.validate(documentDto, errors);

        //than
        verify(errors, times(1)).reject("date.is.in.the.past",  "Date can't be in the past");
    }

    @Test
    public void shouldDoNothingWhenYearIsFuture() {
        //given
        documentDto.setExpiry_date(new Date(2022-02-02));

        //when
        documentDtoValidator.validate(documentDto, errors);

        //than
        verify(errors, times(0)).reject(any());
    }

}