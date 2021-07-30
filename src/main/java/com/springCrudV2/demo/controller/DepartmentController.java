package com.springCrudV2.demo.controller;

import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> dtoList = departmentService.getAll();

        return dtoList != null || !dtoList.isEmpty()
                ? new ResponseEntity<>(dtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> getById(@PathVariable("id") Long id) {
        DepartmentDto dto = departmentService.getDepartmentById(id);
        return dto != null
                ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> save(@RequestBody @Valid DepartmentDto departmentDto) {
        DepartmentDto saveDepartment = departmentService.save(departmentDto);
        return departmentDto != null
                ? new ResponseEntity<>(saveDepartment, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> update(@RequestBody @Valid DepartmentDto departmentDto) {
        DepartmentDto updateDepartment = departmentService.save(departmentDto);
        return updateDepartment != null
                ? new ResponseEntity<>(updateDepartment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> deleteById(@PathVariable("id") Long id) {
        departmentService.deleteById(id);

        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
