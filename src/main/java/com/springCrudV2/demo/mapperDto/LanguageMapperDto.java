package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.entity.Language;

public class LanguageMapperDto {

    public LanguageDto mapToDepartmentDto(Language language) {
        LanguageDto dto = new LanguageDto();

        if (language != null) {
            dto.setId(language.getId());
            dto.setName(language.getName());
//            dto.setPersonList(language.getPersonList());
            return dto;
        } else
            return null;
    }

    public Language mapToDepartmentEntity(LanguageDto dto) {
        Language language = new Language();

        if (dto != null) {
            language.setId(dto.getId());
            language.setName(dto.getName());
            language.setPersonList(dto.getPersonList());
            return language;
        } else
            return null;
    }
}
