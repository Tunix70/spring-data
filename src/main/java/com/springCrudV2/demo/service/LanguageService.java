package com.springCrudV2.demo.service;


import com.springCrudV2.demo.dao.LanguageRepository;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.mapperDto.LanguageMapperDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    private LanguageRepository languageRepository;
    private List<LanguageDto> languageDtos;
    private LanguageDto dto;
    private LanguageMapperDto languageMapperDto = new LanguageMapperDto();

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<LanguageDto> getAll() {
        List<Language> departmentList = languageRepository.findAll();
        languageDtos = departmentList.stream()
                .map(languageMapperDto::mapToDepartmentDto)
                .collect(Collectors.toList());
        return languageDtos;
    }

    public LanguageDto getLanguageById(Long id) {
        Language language = languageRepository.findById(id).orElse(null);
        dto = languageMapperDto.mapToDepartmentDto(language);
        return dto;
    }

    public LanguageDto save(LanguageDto dto) {
        Language language = languageMapperDto.mapToDepartmentEntity(dto);
        languageRepository.save(language);
        return dto;
    }

    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }

}
