package com.homework25.homework25.service;

import com.homework25.homework25.model.Employee;
import java.util.Collection;

public interface EmployeeService {

    Employee add(String firstName, String lastName, Double salary,Integer departmentId );

    Employee find(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Collection<Employee> getAll();
}
