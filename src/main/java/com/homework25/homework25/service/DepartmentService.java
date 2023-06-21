package com.homework25.homework25.service;

import com.homework25.homework25.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee minSalary(Integer departmentId);

    Employee maxSalary(Integer departmentId);


    List<Employee> employeesByDepartment(Integer departmentId);

    double salarySumByDepartment(Integer departmentId);

    Map<Integer,List<Employee>> groupEmployeesByDepartment();
}
