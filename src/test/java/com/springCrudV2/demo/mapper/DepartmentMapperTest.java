package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DepartmentMapperTest {
    private DepartmentMapper departmentMapper = new DepartmentMapper();
    private static Long ID = 43L;
    private static String NAME = "Department";


    @Test
    @DisplayName("Method should map from Department entity to Department dto")
    void shouldMapToDtoFromEntity() {
       //given
        Department department = new Department(ID, NAME);
        DepartmentDto expected = new DepartmentDto(ID, NAME);

        //when
        DepartmentDto result = departmentMapper.mapToDepartmentDto(department);

        //than
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Method should map from Department entity to Department dto and return null")
    void shouldGetNullWhenMapToDtoFromEntityWhereEntityIsNull() {
        //given
        Department department = null;

        //when
        DepartmentDto result = departmentMapper.mapToDepartmentDto(department);

        //than
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Method should map from Department dto to Department entity")
    void shouldMapToEntityFromDto() {
        //given
        DepartmentDto dto = new DepartmentDto(ID, NAME);
        Department expected = new Department(ID, NAME);

        //when
        Department department = departmentMapper.mapToDepartmentEntity(dto);

        //than
        assertThat(department).isEqualTo(expected);

    }

    @Test
    @DisplayName("Method should map from Department dto to Department entity")
    void shouldReturnNullWhenMapToEntityFromDtoWhereDtoIsNull() {
        //given
        DepartmentDto dto = null;

        //when
        Department department = departmentMapper.mapToDepartmentEntity(dto);

        //than
        assertThat(department).isNull();
    }
}