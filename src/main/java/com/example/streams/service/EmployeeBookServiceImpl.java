package com.example.streams.service;

import com.example.streams.exceptions.EmployeeAlreadyAddedException;
import com.example.streams.exceptions.EmployeeNotFoundException;
import com.example.streams.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeBookServiceImpl implements EmployeeBookService {

    private Map<String, Employee> mapEmployeeBook = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary) {
        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        mapEmployeeBook.put(key, new Employee(firstName, lastName, departmentId, salary));
        return employee;
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            return mapEmployeeBook.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            return mapEmployeeBook.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(mapEmployeeBook.values());
    }

    @Override
    public Employee maxSalary(Integer departmentId) {
        List<Employee> employeeList = mapEmployeeBook.values().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .collect(Collectors.toList());
        return employeeList.stream()
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .get();
    }

    @Override
    public Employee minSalary(Integer departmentId) {
        List<Employee> employeeList = mapEmployeeBook.values().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .collect(Collectors.toList());
        return employeeList.stream()
                .min(Comparator.comparingDouble(employee -> employee.getSalary()))
                .get();
    }

    @Override
    public Collection<Employee> printEmployeesByDepartment(Integer departmentId) {
        List<Employee> employeeList = mapEmployeeBook.values().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(employeeList);
    }

    @Override
    public Collection printEmployees() {
        Map<Integer, Employee> employeeMap = mapEmployeeBook.values().stream()
                .collect(Collectors.toMap(employee -> employee.getDepartmentId(), value -> value));
        return Collections.unmodifiableCollection(employeeMap.entrySet());
    }
}
