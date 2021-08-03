package com.springCrudV2.demo.service;


import com.springCrudV2.demo.dao.LanguageRepository;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.mapperDto.LanguageMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapperDto languageMapperDto;

    @Autowired
    public LanguageService(LanguageRepository languageRepository, LanguageMapperDto languageMapperDto) {
        this.languageRepository = languageRepository;
        this.languageMapperDto = languageMapperDto;
    }

    public Set<LanguageDto> getAll() {
        List<Language> departmentList = languageRepository.findAll();
        return departmentList.stream()
                .map(languageMapperDto::mapToLanguageDto)
                .collect(Collectors.toSet());
    }

    public Set<LanguageDto> getSetById(Set<Long> setId) {
        Set<LanguageDto> languageSet = new HashSet<>();
        for (Long id : setId) {
            Language language = languageRepository.getById(id);
            LanguageDto dto = languageMapperDto.mapToLanguageDto(language);
            languageSet.add(dto);
        }
        return languageSet;
    }

    public LanguageDto getLanguageById(Long id) {
        Language language = languageRepository.findById(id).orElse(null);
        return languageMapperDto.mapToLanguageDto(language);
    }

    public LanguageDto save(LanguageDto dto) {
        Language language = languageMapperDto.mapToLanguageEntity(dto);
        Language saveLanguage = languageRepository.save(language);
        dto.setId(saveLanguage.getId());
        return dto;
    }

    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }

    public Language getEntity(LanguageDto dto) {
        return languageMapperDto.mapToLanguageEntity(dto);
    }
}
