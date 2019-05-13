package com.example.employee.restfulapi.controller;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/employees")
public class EmployeeController {
    //在此处完成Employee API
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployeelist(){
        return employeeRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/male")
    public List<Employee> getMaleEmployeelist(){
        return employeeRepository.findByGender("male");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/page/{page}/pageSize/{pageSize}")
    public Page<Employee> getEmployeepage(@PathVariable int page,@PathVariable int pageSize){
        return employeeRepository.findAll(new PageRequest(page,pageSize));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public Employee deleteEmployee(@PathVariable Long id) throws Exception{
        if(getEmployee(id)==null){
            throw new Exception("Employee id "+id+" is not found!");
        }
        employeeRepository.deleteById(id);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee saveEmployee(Employee employee) throws Exception {
        if (employee.getName() == null || employee.getAge() == null || employee.getGender() == null || employee.getSalary() == null || employee.getCompanyId() == null) {
            throw new Exception("Invalid Employee!");
        }
        return employeeRepository.save(employee);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Employee updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) throws Exception {
        if (getEmployee(id) == null) {
            throw new Exception("Employee id "+id+" is not found!");
        }
        employeeRepository.updateById(employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary(), employee.getCompanyId(),id);
        return employeeRepository.findById(id);
    }

}
