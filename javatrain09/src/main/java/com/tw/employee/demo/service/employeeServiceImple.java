package com.tw.employee.demo.service;

import com.tw.employee.demo.model.employee;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class employeeServiceImple implements employeeServiceInterface {
    private static final String employeesList = "[{'id': 1,'name': '小明','age': 20,'gender': '男'}," +
            "{'id': 2,'name': '小红','age': 19,'gender': '女'}," +
            "{'id': 3,'name': '小智','age': 15,'gender': '男'}," +
            "{'id': 4,'name': '小刚','age': 16,'gender': '男'}," +
            "{'id': 5,'name': '小霞','age': 15,'gender': '女'}]";
    private Map<Integer,employee> employees=new ConcurrentHashMap<>();
    private JSONArray jsonArray=JSONArray.fromObject(employeesList);


    public employeeServiceImple() {
        List<employee> list=JSONArray.toList(jsonArray,new employee(),new JsonConfig());
        list.forEach(item->
            employees.put(item.getId(),item)
        );
    }

    private boolean ifEmployeeEmpty(employee empl){
        return empl.getId() != null && empl.getAge() != null && empl.getName() != null && empl.getGender() != null;
    }

    @Override
    public List<employee> getEmployeeList(){
        return new ArrayList<>(employees.values());
    }

    @Override
    public employee getEmployee(Integer id){
        return employees.getOrDefault(id,null);
    }

    @Override
    public String saveEmployee(employee empl){
        if(ifEmployeeEmpty(empl)){
            employees.put(empl.getId(),empl);
            return "success";
        }
        else return "invalid";
    }

    @Override
    public String updateEmployee(Integer id,employee empl){
        if(!ifEmployeeEmpty(empl)){
            return "invalid";
        }
        if(!employees.containsKey(id)){
            return "not found";
        }
        employee newempl=employees.get(id);
        newempl.setAge(empl.getAge());
        newempl.setGender(empl.getGender());
        newempl.setId(empl.getId());
        newempl.setName(empl.getName());
        employees.put(id, empl);
        return "success";
    }

    @Override
    public String deleteEmployee(Integer id){
        if(!employees.containsKey(id)){
            return "not found";
        }
        employees.remove(id);
        return "success";
    }
}
