package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DepartmentRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.exception.DepartmentAlreadyExistException;
import com.springCrudV2.demo.exception.DepartmentNotFoundException;
import com.springCrudV2.demo.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDto> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream()
                .map(departmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        return departmentMapper.mapToDepartmentDto(department);
    }

    public DepartmentDto save(DepartmentDto dto) {
        if (isExistByName(dto.getName()))
            throw new DepartmentAlreadyExistException();
        Department department = departmentMapper.mapToDepartmentEntity(dto);
        Department saveDepartment = departmentRepository.save(department);
        dto.setId(saveDepartment.getId());
        return dto;
    }

    public void deleteById(Long id) {
        isExistById(id);
        departmentRepository.deleteById(id);
    }

    public Department getEntity(DepartmentDto dto) {
        return departmentMapper.mapToDepartmentEntity(dto);
    }

    private boolean isExistByName(String name) {
        return departmentRepository.existsByName(name);
    }

    public boolean isExistById(Long id) {
        if(!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }
        return true;
    }
}