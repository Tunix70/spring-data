package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.LanguageRepository;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.exception.LanguageNameNotValidException;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import com.springCrudV2.demo.mapper.LanguageMapper;
import com.springCrudV2.demo.validator.LanguageDtoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {
    private static final Long ID = 1L;
    private static final Language language = new Language(1L, "RU");
    private static final Language language1 = new Language(2L,"EU");
    private static final Language language2 = new Language(3L, "IT");
    private static final LanguageDto dto = new LanguageDto(1L, "RU");
    private static final LanguageDto dto1 = new LanguageDto(2L,"EU");
    private static final LanguageDto dto2 = new LanguageDto(3L, "IT");

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private LanguageMapper languageMapper;

    @InjectMocks
    private LanguageService languageService;

    @Test
    void shouldGetAllListLanguage() {
        //given
        List<Language> languageList = new ArrayList<>();
        languageList.add(language);
        languageList.add(language1);
        languageList.add(language2);

        Set<LanguageDto> expectedList = new HashSet<>();
        expectedList.add(dto);
        expectedList.add(dto1);
        expectedList.add(dto2);

        //when
        when(languageRepository.findAll()).thenReturn(languageList);
        when(languageMapper.mapToLanguageDto(language)).thenReturn(dto);
        when(languageMapper.mapToLanguageDto(language1)).thenReturn(dto1);
        when(languageMapper.mapToLanguageDto(language2)).thenReturn(dto2);
        Set<LanguageDto> resultList = languageService.getAll();

        //then
        assertThat(resultList).isEqualTo(expectedList);
        verify(languageMapper, times(0)).mapToLanguageEntity(any());
        verify(languageMapper, times(3)).mapToLanguageDto(any());
        verify(languageRepository, times(1)).findAll();
    }

    @Test
    void shouldGetLanguageByIdIfIdValid() {
        //when
        when(languageRepository.findById(ID)).thenReturn(java.util.Optional.of(language));
        when(languageMapper.mapToLanguageDto(language)).thenReturn(dto);
        LanguageDto result = languageService.getLanguageById(ID);

        //than
        assertThat(result).isEqualTo(dto);
        verify(languageRepository, times(1)).findById(ID);
        verify(languageMapper, times(1)).mapToLanguageDto(language);
        verify(languageMapper, times(0)).mapToLanguageEntity(any());
    }

    @Test
    void shouldThrowWhenGetLanguageByIdIfIdInvalid() {
        //when
        when(languageRepository.findById(ID)).thenReturn(java.util.Optional.empty());

        //than
        assertThatThrownBy(() -> languageService.getLanguageById(ID))
                .isInstanceOf(LanguageNotFoundException.class);
        verify(languageMapper, times(0)).mapToLanguageEntity(any());
        verify(languageMapper, times(0)).mapToLanguageDto(any());
    }

    @Test
    void shouldSaveLanguageAndReturnSaveLanguage() {
        //when
        when(languageMapper.mapToLanguageEntity(dto)).thenReturn(language);
        when(languageRepository.save(language)).thenReturn(language);
        LanguageDto result = languageService.save(dto);

        //than
        assertThat(result).isEqualTo(dto);
        verify(languageRepository, times(1)).save(language);
        verify(languageMapper, times(1)).mapToLanguageEntity(dto);
        verify(languageMapper, times(0)).mapToLanguageDto(any());
    }

    @Test
    void shouldRemoveLanguageByIdIfItExistInDataBase() {
        //when
        when(languageRepository.existsById(ID)).thenReturn(true);
        languageService.deleteById(ID);

        //than
        verify(languageRepository, times(1)).existsById(any());
        verify(languageRepository, times(1)).deleteById(ID);
    }

    @Test
    void shouldFailWhenRemoveLanguageByIdIfItNotExistInDataBase() {
        //when
        when(languageRepository.existsById(ID)).thenReturn(false);

        //than
        assertThatThrownBy(() -> languageService.deleteById(ID))
                .isInstanceOf(LanguageNotFoundException.class);
    }


    @Test
    void isExist() {
        //when
        when(languageRepository.existsById(any())).thenReturn(true);
        languageService.isExistById(ID);

        //than
        verify(languageRepository, times(1)).existsById(any());
    }
}