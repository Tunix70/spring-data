package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapperDto {
    public LanguageDto mapToDepartmentDto(Language language) {
        LanguageDto dto = new LanguageDto();

        if (language != null) {
            dto.setId(language.getId());
            dto.setName(language.getName());
            return dto;
        } else
            return null;
    }

    public Language mapToDepartmentEntity(LanguageDto dto) {
        Language language = new Language();

        if (dto != null) {
            language.setId(dto.getId());
            language.setName(dto.getName());
            return language;
        } else
            return null;
    }
}
