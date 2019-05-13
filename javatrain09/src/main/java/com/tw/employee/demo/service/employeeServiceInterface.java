package com.tw.employee.demo.service;

import com.tw.employee.demo.model.employee;

import java.util.List;
public interface employeeServiceInterface {
    List<employee> getEmployeeList();
    employee getEmployee(Integer id);
    String saveEmployee(employee empl);
    String updateEmployee(Integer id,employee employees);
    String deleteEmployee(Integer id);
}
