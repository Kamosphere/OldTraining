package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //获取employee列表
    List<Employee> findAll();

    //获取某个employee
    Employee findById(Long id);

    //获取某个性别的employee
    List<Employee> findByGender(String gender);

    //分页查询
    Page<Employee> findAll(Pageable pageable);

    //添加employee
    Employee save(Employee employee);

    //修改employee
    @Modifying
    @Transactional
    @Query("update Employee e set e.name = ?1, e.age = ?2, e.gender = ?3, e.salary = ?4, e.companyId = ?5 where e.id = ?6")
    int updateById(String name,Integer age,String gender,Integer salary,Long companyId,Long id);

    //删除employee
    @Transactional
    void deleteById(Long id);
}
