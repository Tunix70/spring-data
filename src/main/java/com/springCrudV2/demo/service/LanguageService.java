package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.LanguageRepository;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.exception.LanguageAlreadyExistException;
import com.springCrudV2.demo.exception.LanguageNameNotValidException;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import com.springCrudV2.demo.mapperDto.LanguageMapperDto;
import com.springCrudV2.demo.validator.LanguageDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapperDto languageMapperDto;
    private final LanguageDtoValidator languageDtoValidator;

    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    @Autowired
    public LanguageService(LanguageRepository languageRepository,
                           LanguageMapperDto languageMapperDto,
                           LanguageDtoValidator languageDtoValidator) {
        this.languageRepository = languageRepository;
        this.languageMapperDto = languageMapperDto;
        this.languageDtoValidator = languageDtoValidator;
    }

    public Set<LanguageDto> getAll() {
        List<Language> departmentList = languageRepository.findAll();
        return departmentList.stream()
                .map(languageMapperDto::mapToLanguageDto)
                .collect(Collectors.toSet());
    }

    public LanguageDto getLanguageById(Long id) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
        return languageMapperDto.mapToLanguageDto(language);
    }

    public LanguageDto save(LanguageDto dto) {
        if (!isValid(dto))
            throw new LanguageNotFoundException(dto.getId());
        if (isExist(dto))
            throw new LanguageAlreadyExistException();
        Language language = languageMapperDto.mapToLanguageEntity(dto);
        Language saveLanguage = languageRepository.save(language);
        dto.setId(saveLanguage.getId());
        return dto;
    }

    public void deleteById(Long id) {
        isValidId(id);
        languageRepository.deleteById(id);
    }

    public Language getEntity(LanguageDto dto) {
        if (dto == null) {
            throw new ClassCastException("LANGUAGE");
        } else
            return languageMapperDto.mapToLanguageEntity(dto);
    }

    public boolean isValid(LanguageDto dto) {
        final DataBinder dataBinder = new DataBinder(dto);
        dataBinder.addValidators(languageDtoValidator);
        dataBinder.validate(dto);
        if (dataBinder.getBindingResult().hasErrors()) {
            String errorsMessage = dataBinder.getBindingResult().getAllErrors().stream().
                    map(e -> messageSource.getMessage(e, Locale.getDefault()))
                    .collect(Collectors.joining("\n"));
            throw new LanguageNameNotValidException(errorsMessage);
        }
        return true;
    }

    public boolean isValidId(Long id) {
        return id != null && id > 1L && getLanguageById(id) != null;
    }

    public boolean isExist(LanguageDto dto) {
        return languageRepository.existsByName(dto.getName());
    }
}