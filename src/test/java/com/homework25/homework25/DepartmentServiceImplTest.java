package com.homework25.homework25;

import com.homework25.homework25.exception.EmployeeNotFoundException;
import com.homework25.homework25.model.Employee;
import com.homework25.homework25.service.DepartmentServiceImpl;
import com.homework25.homework25.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService serviceMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employees.add(new Employee("Will", "Smith", 100000, 1));
        employees.add(new Employee("Bob", "Marley", 120000, 1));
        employees.add(new Employee("Selena", "Gomez", 90000, 2));
        employees.add(new Employee("Pitter", "Parcker", 130000, 2));
    }

    @Test
    void minSalaryTest() {
        when(serviceMock.getAll()).thenReturn(employees);
        Employee actual = departmentService.minSalary(1);

        assertEquals("Will", actual.getFirstName());
        assertEquals(100000, actual.getSalary());

        verify(serviceMock, times(1)).getAll();
    }
    @Test
    void minSalaryExceptionTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        assertThrows(EmployeeNotFoundException.class,()->departmentService.minSalary(3));

        verify(serviceMock, times(1)).getAll();
    }
    @Test
    void maxSalaryTest() {
        when(serviceMock.getAll()).thenReturn(employees);
        Employee actual = departmentService.maxSalary(1);

        assertEquals("Bob",actual.getFirstName());
        assertEquals(120000,actual.getSalary());

        verify((serviceMock), times(1)).getAll();
    }
    @Test
    void maxSalaryExceptionTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.maxSalary(3));

        verify(serviceMock, times(1)).getAll();
    }

    @Test
    void salarySumByDepartmentTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        double actual = departmentService.salarySumByDepartment(1);

        assertEquals(220000,actual);

        verify(serviceMock, times(1)).getAll();
    }

    @Test
    void salarySumByDepartmentNotEqualsTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        double actual = departmentService.salarySumByDepartment(1);

        assertNotEquals(0,actual);

        verify(serviceMock, times(1)).getAll();
    }

    @Test
    void employeesByDepartmentTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        List<Employee> actual = departmentService.employeesByDepartment(1);

        assertEquals(2,actual.size());
        assertEquals("Will",actual.get(0).getFirstName());
        assertEquals("Bob",actual.get(1).getFirstName());
    }
    @Test
    void employeesByDepartmentFalseTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        List<Employee> actual = departmentService.employeesByDepartment(1);

        assertFalse(actual.isEmpty());

        verify(serviceMock, times(1)).getAll();
    }


    @Test
    void groupEmployeesByDepartmentTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        Map<Integer, List<Employee>> actual = departmentService.groupEmployeesByDepartment();

        assertEquals(2,actual.size());
        assertTrue(actual.containsKey(1));
        assertTrue(actual.containsKey(2));
        assertEquals(2,actual.get(1).size());
        assertEquals(2,actual.get(2).size());

        verify(serviceMock, times(1)).getAll();
    }

    @Test
    void groupEmployeesByDepartmentDoubleCallTest() {
        when(serviceMock.getAll()).thenReturn(employees);

        departmentService.groupEmployeesByDepartment();
        departmentService.groupEmployeesByDepartment();

        verify(serviceMock, times(2)).getAll();
    }

}

