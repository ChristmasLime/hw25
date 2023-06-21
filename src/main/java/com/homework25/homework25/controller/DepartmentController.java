package com.homework25.homework25.controller;

import com.homework25.homework25.model.Employee;
import com.homework25.homework25.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}/salary/min")
    public Employee getMinSalaryInDepartment(@PathVariable("id") Integer departmentId) {
        return service.minSalary(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public Employee getMaxSalaryInDepartment(@PathVariable("id") Integer departmentId) {
        return service.maxSalary(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public double getSalarySumByDepartment(@PathVariable("id") Integer departmentId) {
        return service.salarySumByDepartment(departmentId);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable("id") Integer departmentId) {
        return service.employeesByDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getGroupEmployeesByDepartment() {
        return service.groupEmployeesByDepartment();
    }
}
