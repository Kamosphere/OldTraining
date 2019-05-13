package com.example.employeeapi.datasource;

import com.example.employeeapi.datasource.provider.EmployeeDataProvider;
import com.example.employeeapi.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestEmployeeRepository implements EmployeeRepository {
    private EmployeeDataProvider employeeDataProvider = new EmployeeDataProvider();

    @Override
    public Employee createEmployee(Employee employee) throws Exception {
        return employeeDataProvider.insertEmployee(employee);
    }

    @Override
    public Employee deleteEmployee(int id) throws Exception {
        return employeeDataProvider.deleteEmployee(id);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) throws Exception {
        return employeeDataProvider.updateEmployee(id, employee);
    }

    @Override
    public Employee getEmployee(String name) {
        return employeeDataProvider.getEmployee(name);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeDataProvider.getEmployee(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDataProvider.getEmployees();
    }
}
