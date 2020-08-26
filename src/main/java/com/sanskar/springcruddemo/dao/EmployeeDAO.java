package com.sanskar.springcruddemo.dao;

import com.sanskar.springcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(Integer id);

    void save(Employee employee);

    void deleteById(Integer id);
}
