package com.springCrudV2.demo.controller;

import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<List<Department>> getAll() {
        List<Department> departmentList = departmentService.getAll();

        return departmentList != null || !departmentList.isEmpty()
                ? new ResponseEntity<>(departmentList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> getById(@PathVariable("id") Long id) {
        Department department = departmentService.getDepartmentById(id);
        return department != null
                ? new ResponseEntity<>(department, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> save(@RequestBody @Valid Department department) {
        HttpHeaders headers = new HttpHeaders();
        Department saveDepartment = departmentService.save(department);

        return department != null
                ? new ResponseEntity<>(saveDepartment, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> update(@RequestBody @Valid Department department) {
        HttpHeaders headers = new HttpHeaders();
        departmentService.save(department);
        return department.getId() != null
                ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
                : new ResponseEntity<>(department, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> deleteById(@PathVariable("id") Long id) {
        departmentService.deleteById(id);

        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
