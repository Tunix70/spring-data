package com.springCrudV2.demo.service;


import com.springCrudV2.demo.dao.LanguageRepository;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.mapperDto.LanguageMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    private LanguageRepository languageRepository;
    private LanguageMapperDto languageMapperDto;

    @Autowired
    public LanguageService(LanguageRepository languageRepository, LanguageMapperDto languageMapperDto) {
        this.languageRepository = languageRepository;
        this.languageMapperDto = languageMapperDto;
    }

    public List<LanguageDto> getAll() {
        List<Language> departmentList = languageRepository.findAll();
        return departmentList.stream()
                .map(languageMapperDto::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    public LanguageDto getLanguageById(Long id) {
        Language language = languageRepository.findById(id).orElse(null);
        LanguageDto dto = languageMapperDto.mapToDepartmentDto(language);
        return dto;
    }

    public LanguageDto save(LanguageDto dto) {
        Language language = languageMapperDto.mapToDepartmentEntity(dto);
        Language saveLanguage = languageRepository.save(language);
        dto.setId(saveLanguage.getId());
        return dto;
    }

    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }
}
