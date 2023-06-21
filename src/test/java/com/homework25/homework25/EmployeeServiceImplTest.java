package com.homework25.homework25;

import com.homework25.homework25.exception.EmployeeAlreadyAddedException;
import com.homework25.homework25.exception.EmployeeNotFoundException;
import com.homework25.homework25.model.Employee;
import com.homework25.homework25.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    private final EmployeeServiceImpl service= new EmployeeServiceImpl();

      @Test
    void addEmployeeTest() {
        String firstName = "Alex";
        String lastName = "Clovers";
        Double salary = 55000.0;
        Integer departmentId = 1;
        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        Employee actual = service.add(firstName, lastName, salary, departmentId);
        assertEquals(expected, actual);
    }

    @Test
    void addExistingEmployeeTest() {
        String firstName = "Oleg";
        String lastName = "Mongol";
        Double salary = 35000.0;
        Integer departmentId = 2;
        service.add(firstName, lastName, salary, departmentId);
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                service.add(firstName, lastName, salary, departmentId));
    }

    @Test
    void findEmployeeTest() {
        String firstName = "Ruslan";
        String lastName = "Rambo";
        Double salary = 44000.0;
        Integer departmentId = 3;
        service.add(firstName, lastName, salary, departmentId);
        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        Employee actual = service.find(firstName, lastName);
        assertEquals(expected, actual);
    }

    @Test
    void findNotExistingEmployeeTest() {
        String firstName = "John";
        String lastName = "Wick";
        assertThrows(EmployeeNotFoundException.class, () ->
                service.find(firstName, lastName));

    }

    @Test
    void removeEmployeeTest() {
        String firstName = "Marina";
        String lastName = "Dream";
        Double salary = 144000.0;
        Integer departmentId = 4;
        service.add(firstName, lastName, salary, departmentId);
        service.remove(firstName, lastName);
        assertThrows(EmployeeNotFoundException.class, () -> service.find(firstName, lastName));
    }

    @Test
    void removeNotExistingEmployeeTest() {
        String firstName = "Dart";
        String lastName = "Revan";
        assertThrows(EmployeeNotFoundException.class, () -> service.remove(firstName, lastName));
    }

    @Test
    void getAllEmployeesTest() {
        String firstName1 = "Jorge";
        String lastName1 = "Injungle";
        Double salary1 = 44000.0;
        Integer departmentId1 = 5;

        String firstName2 = "Sveta";
        String lastName2 = "Cabrialeta";
        Double salary2 = 34000.0;
        Integer departmentId2 = 5;

        service.add(firstName1, lastName1, salary1, departmentId1);
        service.add(firstName2, lastName2, salary2, departmentId2);

        Collection<Employee> allEmployees = service.getAll();

        Employee expected1 = new Employee(firstName1, lastName1, salary1, departmentId1);
        Employee expected2 = new Employee(firstName2, lastName2, salary2, departmentId2);

        assertEquals(2, allEmployees.size());
        assertTrue(allEmployees.contains(expected1));
        assertTrue(allEmployees.contains(expected2));
    }

    @Test
    void getAllEmployeesNegativeTest() {
        String firstName1 = "John";
        String lastName1 = "Travolta";
        Double salary1 = 1144000.0;
        Integer departmentId1 = 6;

        String firstName2 = "John";
        String lastName2 = "Malkovich";
        Double salary2 = 1134000.0;
        Integer departmentId2 = 6;

        service.add(firstName1, lastName1, salary1, departmentId1);
        service.add(firstName2, lastName2, salary2, departmentId2);

        Collection<Employee> allEmployees = service.getAll();

        Employee unexpected = new Employee("Unknown", "Person", 0.0, 0);

        assertFalse(allEmployees.isEmpty());
        assertFalse(allEmployees.contains(unexpected));
        assertNotEquals(3,allEmployees.size());
    }

}
