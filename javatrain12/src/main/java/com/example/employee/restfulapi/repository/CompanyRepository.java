package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    //获取company列表
    List<Company> findAll();

    //获取某个company
    Company findById(Long id);

    //获取company下所有employee
    @Query("select e from Employee e where e.companyId=?1")
    List<Employee> findEmployeesByCompanyId(Long id);

    //分页查询
    Page<Company> findAll(Pageable pageable);

    //添加company
    Company save(Company company);

    //修改company
    @Modifying
    @Transactional
    @Query("update Company c set c.companyName=?1,c.employeesNumber=?2 where c.id=?3")
    int updateById(String companyName,Integer emplyeesNumber,Long id);

    //删除company及名下所有employee
    @Transactional
    void deleteById(Long id);
}
