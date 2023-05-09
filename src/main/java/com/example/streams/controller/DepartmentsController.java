package com.example.streams.controller;

import com.example.streams.model.Employee;
import com.example.streams.service.EmployeeBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final EmployeeBookService employeeBookService;


    public DepartmentsController(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam Integer departmentId) {
        return employeeBookService.maxSalary(departmentId);
    }
    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam Integer departmentId) {
        return employeeBookService.minSalary(departmentId);
    }

    @GetMapping("/all")
    public Collection<Employee> printEmployeesByDepartment(@RequestParam(required = false) Integer departmentId) {
        return employeeBookService.printEmployeesByDepartment(departmentId);
    }

}
