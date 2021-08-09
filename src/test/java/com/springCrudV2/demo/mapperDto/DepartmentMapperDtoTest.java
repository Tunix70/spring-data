package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartmentMapperDtoTest {
    private DepartmentMapperDto departmentMapperDto;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldMapToDtoFromEntity() {
       //given
        Department department = new Department(43L, "Department");
        Department department1 = new Department(null, "Null Department");

        //when
        DepartmentDto dto = departmentMapperDto.mapToDepartmentDto(department);
        DepartmentDto dto1 = departmentMapperDto.mapToDepartmentDto(department1);
        //than

    }

    @Test
    void shouldMapToEntityFromDto() {
        //given
        //when
        //than
    }
}