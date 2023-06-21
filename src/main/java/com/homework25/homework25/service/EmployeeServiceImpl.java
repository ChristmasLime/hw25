package com.homework25.homework25.service;

import com.homework25.homework25.exception.EmployeeAlreadyAddedException;
import com.homework25.homework25.exception.EmployeeInvalidInput;
import com.homework25.homework25.exception.EmployeeNotFoundException;
import com.homework25.homework25.model.Employee;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private String createKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    @Override
    public Employee add(String firstName, String lastName, Double salary, Integer departmentId) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, salary ,departmentId);
        if (employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(firstName, lastName), employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        validateInput(firstName, lastName);
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validateInput(firstName, lastName);
        if (!employees.containsKey(createKey(firstName,lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(createKey(firstName, lastName));
    }

    @Override
    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private void validateInput(String firstName, String lastName) {
        if (!isAlpha(firstName)&&!isAlpha(lastName)) {
            throw new EmployeeInvalidInput();
        }
    }
}
