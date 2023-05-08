package com.example.streams.service;

import com.example.streams.exceptions.EmployeeAlreadyAddedException;
import com.example.streams.exceptions.EmployeeNotFoundException;
import com.example.streams.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeBookServiceImpl implements EmployeeBookService {

    private Map<String, Employee> mapEmployeeBook = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (mapEmployeeBook.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        mapEmployeeBook.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (mapEmployeeBook.containsKey(employee.getFullName())) {
            return mapEmployeeBook.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (mapEmployeeBook.containsKey(employee.getFullName())) {
            return mapEmployeeBook.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(mapEmployeeBook.values());
    }
}
