package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.LanguageRepository;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import com.springCrudV2.demo.mapper.LanguageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {
    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private LanguageMapper languageMapper;

    @InjectMocks
    private LanguageService languageService;

    private Language language;
    private Language language1;
    private Language language2;
    private LanguageDto dto;
    private LanguageDto dto1;
    private LanguageDto dto2;

    @BeforeEach
    void setUp() {
        language = new Language(1L, "RU");
        language1 = new Language(2L,"EU");
        language2 = new Language(3L, "IT");
        dto = new LanguageDto(1L, "RU");
        dto1 = new LanguageDto(2L,"EU");
        dto2 = new LanguageDto(3L, "IT");
    }

    @Test
    void shouldGetAllListLanguage() {
        //given
        List<Language> languageList = new ArrayList<>();
        languageList.add(language);
        languageList.add(language1);
        languageList.add(language2);

        Set<LanguageDto> dtotList = new HashSet<>();
        dtotList.add(dto);
        dtotList.add(dto1);
        dtotList.add(dto2);

        when(languageRepository.findAll()).thenReturn(languageList);
        when(languageMapper.mapToLanguageDto(language)).thenReturn(dto);
        when(languageMapper.mapToLanguageDto(language1)).thenReturn(dto1);
        when(languageMapper.mapToLanguageDto(language2)).thenReturn(dto2);

        //when
        Set<LanguageDto> resultList = languageService.getAll();

        //than
        assertThat(resultList).isEqualTo(dtotList);
        verify(languageMapper, times(0)).mapToLanguageEntity(any());
        verify(languageMapper, times(3)).mapToLanguageDto(any());
        verify(languageRepository, times(1)).findAll();
    }

    @Test
    void shouldGetLanguageByIdIfIdValid() {
        //given
        when(languageRepository.findById(1L)).thenReturn(java.util.Optional.of(language));
        when(languageMapper.mapToLanguageDto(language)).thenReturn(dto);

        //when
        languageService.getLanguageById(1L);

        //than
        verify(languageRepository, times(1)).findById(1L);
        verify(languageMapper, times(1)).mapToLanguageDto(language);
        verify(languageMapper, times(0)).mapToLanguageEntity(any());
    }

    @Test
    void shouldThrowWhenGetLanguageByIdIfIdInvalid() {
        //when
        when(languageRepository.findById(1L)).thenThrow(new LanguageNotFoundException(1L));

        //than
        assertThatThrownBy(() -> languageService.getLanguageById(1L))
                .isInstanceOf(LanguageNotFoundException.class);
        verify(languageMapper, times(0)).mapToLanguageEntity(any());
        verify(languageMapper, times(0)).mapToLanguageDto(any());
    }

    @Test
    void shouldSaveLanguageAndReturnSaveLanguage() {
        //given
        when(languageMapper.mapToLanguageEntity(dto)).thenReturn(language);
        when(languageRepository.save(language)).thenReturn(language);

        //when
        languageService.save(dto);

        //than
        verify(languageRepository, times(1)).save(language);
        verify(languageMapper, times(1)).mapToLanguageEntity(dto);
        verify(languageMapper, times(0)).mapToLanguageDto(any());
    }

    @Test
    void shouldRemoveLanguageByIdIfItExistInDataBase() {
        //when
        when(languageRepository.existsById(1L)).thenReturn(true);
        doNothing().when(languageRepository).deleteById(1L);
        languageService.deleteById(1L);

        //than
        verify(languageRepository, times(1)).existsById(any());
        verify(languageRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldFailWhenRemoveLanguageByIdIfItNotExistInDataBase() {
        //when
        when(languageRepository.existsById(1L)).thenReturn(false);

        //than
        assertThatThrownBy(() -> languageService.deleteById(1L))
                .isInstanceOf(LanguageNotFoundException.class);
    }

    @Test
    void isValid() {

    }

    @Test
    void isExist() {
        //when
        when(languageRepository.existsById(any())).thenReturn(true);
        languageService.isExistById(1L);

        //than
        verify(languageRepository, times(1)).existsById(any());
    }
}