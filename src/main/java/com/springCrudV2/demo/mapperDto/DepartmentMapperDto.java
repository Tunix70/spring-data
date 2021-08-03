package com.springCrudV2.demo.mapperDto;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapperDto {

    public DepartmentDto mapToDepartmentDto(Department department) {
        DepartmentDto dto = new DepartmentDto();

        if (department != null) {
            dto.setId(department.getId());
            dto.setName(department.getName());
            return dto;
        } else
            return null;
    }

    public Department mapToDepartmentEntity(DepartmentDto dto) {
        Department department = new Department();

        if (dto != null) {
            department.setId(dto.getId());
            department.setName(dto.getName());
            return department;
        } else
            return null;
    }
}
