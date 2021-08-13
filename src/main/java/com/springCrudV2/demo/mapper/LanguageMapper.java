package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LanguageMapper {
    public LanguageDto mapToLanguageDto(Language language) {
        LanguageDto dto = new LanguageDto();

        if (language != null) {
            dto.setId(language.getId());
            dto.setName(language.getName());
            return dto;
        } else
            return null;
    }

    public Language mapToLanguageEntity(LanguageDto dto) {
        Language language = new Language();

        if (dto != null) {
            language.setId(dto.getId());
            language.setName(dto.getName());
            return language;
        } else
            return null;
    }
}
