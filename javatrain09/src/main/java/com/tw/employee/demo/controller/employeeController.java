package com.tw.employee.demo.controller;

import com.tw.employee.demo.model.employee;
import com.tw.employee.demo.service.employeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employees")
public class employeeController {
    @Autowired
    private employeeServiceInterface employeeService;

    @RequestMapping(method=RequestMethod.GET)
    public List<employee> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    @RequestMapping(method=RequestMethod.POST)
    public String saveEmployee(@ModelAttribute employee empl){
        return employeeService.saveEmployee(empl);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public employee getEmployee(@PathVariable Integer id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateEmployee(@PathVariable Integer id, @ModelAttribute employee empl){
        return employeeService.updateEmployee(id, empl);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Integer id){
        return employeeService.deleteEmployee(id);
    }
}
