package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DepartmentMapperDtoTest {
    private DepartmentMapperDto departmentMapperDto = new DepartmentMapperDto();

    @Test
    @DisplayName("Method should map from entity to dto")
    void shouldMapToDtoFromEntity() {
       //given
        Department department = new Department(43L, "Department");
        Department department1 = null;

        //when
        DepartmentDto dto = departmentMapperDto.mapToDepartmentDto(department);
        DepartmentDto dto1 = departmentMapperDto.mapToDepartmentDto(department1);

        //than
        assertThat(dto.getId()).isEqualTo(department.getId());
        assertThat(dto.getName()).isEqualTo(department.getName());

        assertThat(dto1).isNull();
    }

    @Test
    @DisplayName("Method should map from dto to entity")
    void shouldMapToEntityFromDto() {
        //given
        DepartmentDto dto = new DepartmentDto(54L, "Department DTO");
        DepartmentDto dto1 = null;

        //when
        Department department = departmentMapperDto.mapToDepartmentEntity(dto);
        Department department1 = departmentMapperDto.mapToDepartmentEntity(dto1);

        //than
        assertThat(department.getId()).isEqualTo(dto.getId());
        assertThat(department.getName()).isEqualTo(dto.getName());

        assertThat(department1).isNull();
    }
}