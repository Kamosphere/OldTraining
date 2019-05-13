package com.example.employeeapi.datasource.provider;

import com.example.employeeapi.model.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeDataProvider {
    private List<Employee> employees = Arrays.asList(
            new Employee(0, "小明", 20, "男"),
            new Employee(1, "小红", 19, "女"),
            new Employee(2, "小智", 15, "男"),
            new Employee(3, "小刚", 16, "男"),
            new Employee(4, "小霞", 15, "女")
    );

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public Employee getEmployee(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    public Employee insertEmployee(Employee employee) throws Exception {
        if (employee.getAge() == null || employee.getName() == null || employee.getGender() == null) {
            throw new Exception("Invalid Employee!");
        }
        long userID = employees.get(employees.size() - 1).getId() + 1;
        employees.add(employee.setId(userID));
        return employee;
    }

    public Employee updateEmployee(int id, Employee employee) throws Exception {
        if (employee.getAge() == null || employee.getName() == null || employee.getGender() == null) {
            throw new Exception("Invalid Employee!");
        }

        Employee originalEmployee = getEmployee(id);

        if (originalEmployee == null) {
            throw new Exception("Employee not found by id: " + id);
        }

        return originalEmployee
                .setName(employee.getName())
                .setAge(employee.getAge())
                .setGender(employee.getGender());
    }

    public Employee deleteEmployee(int id) throws Exception {
        Employee employee = getEmployee(id);

        if (employee == null) {
            throw new Exception("Employee not found by id: " + id);
        }

        employees.remove(employee);
        return employee;
    }
}
