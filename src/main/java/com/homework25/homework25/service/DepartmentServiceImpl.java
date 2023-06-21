package com.homework25.homework25.service;

import com.homework25.homework25.exception.EmployeeNotFoundException;
import com.homework25.homework25.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService service;

    public DepartmentServiceImpl(EmployeeService service) {
        this.service = service;
    }

    @Override
    public Employee minSalary(Integer departmentId) {
        return service.getAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee maxSalary(Integer departmentId) {
        return service.getAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public double salarySumByDepartment(Integer departmentId) {
        return service.getAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public List<Employee> employeesByDepartment(Integer departmentId) {
        return service.getAll().stream()
                .filter(e->e.getDepartmentId()==departmentId)
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer,List<Employee>> groupEmployeesByDepartment() {
        return service.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
