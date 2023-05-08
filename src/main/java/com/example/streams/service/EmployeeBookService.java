package com.example.streams.service;

import com.example.streams.model.Employee;

import java.util.Collection;

public interface EmployeeBookService {

    Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAll();

    Employee maxSalary(Integer departmentId);

    Employee minSalary(Integer departmentId);

    Collection<Employee> printEmployeesByDepartment(Integer departmentId);

    Collection<Employee> printEmployees();

}
