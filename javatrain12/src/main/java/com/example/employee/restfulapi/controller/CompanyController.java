package com.example.employee.restfulapi.controller;
import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Company> getCompanylist(){
        return companyRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}/employees")
    public List<Employee>getEmployeelist(@PathVariable Long id){
        return companyRepository.findEmployeesByCompanyId(id);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/page/{page}/pageSize/{pageSize}")
    public Page<Company> getCompanypage(@PathVariable int page,@PathVariable int pageSize){
        return companyRepository.findAll(new PageRequest(page,pageSize));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Company getCompany(@PathVariable Long id){
        return companyRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public Company deleteCompany(@PathVariable Long id) throws Exception{
        if(getCompany(id)==null){
            throw new Exception("Company id "+id+" is not exist");
        }
        companyRepository.deleteById(id);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company saveCompany(Company company) throws Exception{
        if(company.getCompanyName()==null ||company.getEmployeesNumber()==null){
            throw new Exception("Invalid company!");
        }
        return companyRepository.save(company);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Company updateCompany(@PathVariable Long id,@ModelAttribute Company company)throws Exception{
        if(getCompany(id)==null){
            throw new Exception("Company id "+id+" is not found!");
        }
        companyRepository.updateById(company.getCompanyName(), company.getEmployeesNumber(),id);
        return companyRepository.findById(id);
    }

}
