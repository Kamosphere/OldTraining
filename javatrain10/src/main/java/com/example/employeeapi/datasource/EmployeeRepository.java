package com.example.employeeapi.datasource;

import com.example.employeeapi.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee createEmployee(Employee employee) throws Exception;
    Employee deleteEmployee(int id) throws Exception;
    Employee updateEmployee(int id, Employee employee) throws Exception;
    Employee getEmployee(int id);
    Employee getEmployee(String name);
    List<Employee> getAllEmployees();
}
