package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DepartmentRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.mapperDto.DepartmentMapperDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentDto dto;
    private List<DepartmentDto> departmentDtos;
    private DepartmentMapperDto departmentMapperDto = new DepartmentMapperDto();

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        departmentDtos = departmentList.stream()
                .map(departmentMapperDto::mapToDepartmentDto)
                .collect(Collectors.toList());
        return departmentDtos;
    }

    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        dto = departmentMapperDto.mapToDepartmentDto(department);
        return dto;
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = departmentMapperDto.mapToDepartmentEntity(departmentDto);
        departmentRepository.save(department);
        return departmentDto;
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
