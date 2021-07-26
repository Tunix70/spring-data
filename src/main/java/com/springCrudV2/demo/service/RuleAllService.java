package com.springCrudV2.demo.service;

import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Person;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class RuleAllService {
    private DepartmentService departmentService;
    private PersonSerice personSerice;

    private Date date = new Date(1L);

    public RuleAllService(DepartmentService departmentService, PersonSerice personSerice) {
        this.departmentService = departmentService;
        this.personSerice = personSerice;
    }

    public void run(){
//        departmentService.save(new Department(null, "CRUD Department"));
        Department department = departmentService.getDepartmentById(22L);
        Person person = new Person(null, "Nikita", "Persov", date, department);
        personSerice.save(person);
//        departmentService.deleteById(19L);
//        personSerice.deleteById(23L);
//        personSerice.deleteById(16L);
    }
}
