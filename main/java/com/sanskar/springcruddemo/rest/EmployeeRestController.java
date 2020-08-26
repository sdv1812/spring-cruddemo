package com.sanskar.springcruddemo.rest;

import com.sanskar.springcruddemo.entity.Employee;
import com.sanskar.springcruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") Integer employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (null == employee) {
            throw new RuntimeException("Employee id not found for " + employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(null);
        employeeService.save(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable(name = "employeeId") Integer employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (null == employee) {
            throw new RuntimeException("Employee not found for " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id " + employeeId;
    }
}

