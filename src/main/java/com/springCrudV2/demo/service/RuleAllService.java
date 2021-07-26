package com.springCrudV2.demo.service;

import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class RuleAllService {
    private DepartmentService departmentService;
    private PersonService personService;

    private Date date = new Date(1L);

    public RuleAllService(DepartmentService departmentService, PersonService personService) {
        this.departmentService = departmentService;
        this.personService = personService;
    }

    public void run(){
//        departmentService.save(new Department(null, "Main Department"));
//        Department department = departmentService.getDepartmentById(25L);
//        Person person = new Person(null, "Ivan", "Ivanov", date, department);
//        personService.save(person);
//        departmentService.deleteById(18L);
//        personService.deleteById(25L);
//        personService.deleteById(16L);
//        Department department = departmentService.FindByName("CRUD");
        List<Person> department = departmentService.getDepartmentById(23L).getPersonList();
        System.out.println(department);

    }
}
