package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DepartmentRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.mapperDto.DepartmentMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapperDto departmentMapperDto;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapperDto departmentMapperDto) {
        this.departmentRepository = departmentRepository;
        this.departmentMapperDto = departmentMapperDto;
    }

    public List<DepartmentDto> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream()
                .map(departmentMapperDto::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        return departmentMapperDto.mapToDepartmentDto(department);
    }

    public DepartmentDto save(DepartmentDto dto) {
        Department department = departmentMapperDto.mapToDepartmentEntity(dto);
        Department saveDepartment = departmentRepository.save(department);
        dto.setId(saveDepartment.getId());
        return dto;
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department getEntity(DepartmentDto dto) {
        return departmentMapperDto.mapToDepartmentEntity(dto);
    }
}
