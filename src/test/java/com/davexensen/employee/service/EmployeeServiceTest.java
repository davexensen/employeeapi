package com.davexensen.employee.service;

import com.davexensen.employee.domain.Employee;
import com.davexensen.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getEmployee() {
        Employee employee = Employee.builder()
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        Optional<Employee> mockResult = Optional.of(employee);
        when(employeeRepository.findActiveEmployee(1L)).thenReturn(mockResult);

        Employee employee1 = employeeService.getEmployee(1L);

        assertEquals(employee.getFirstName(), employee1.getFirstName());
    }

    @Test
    void getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder()
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build());
        employees.add(Employee.builder()
            .firstName("Test2")
            .middleInitial("P")
            .lastName("Test2")
            .build());

        when(employeeRepository.findAllActiveEmployees()).thenReturn(employees);

        List<Employee> employeesResult = employeeService.getAllEmployees();

        assertEquals(employees.size(), employeesResult.size());
        assertEquals(employees.get(0).getFirstName(), employeesResult.get(0).getFirstName());
    }

    @Test
    void addEmployee() {
        Employee employee = Employee.builder()
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        when(employeeRepository.save(any())).thenReturn(employee);

        Employee employee1 = employeeService.addEmployee(employee);

        assertEquals(employee.getFirstName(), employee1.getFirstName());
    }

    @Test
    void updateEmployee() {
        Employee employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        Optional<Employee> mockResult = Optional.of(employee);
        when(employeeRepository.findActiveEmployee(1L)).thenReturn(mockResult);
        when(employeeRepository.save(any())).thenReturn(employee);

        Employee employee1 = employeeService.updateEmployee(employee);

        assertEquals(employee.getFirstName(), employee1.getFirstName());
    }

    @Test
    void deleteEmployee() {
        Employee employee = Employee.builder()
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        Optional<Employee> mockResult = Optional.of(employee);
        when(employeeRepository.findActiveEmployee(1L)).thenReturn(mockResult);

        assertEquals(true, employeeService.deleteEmployee(1L));
    }
}
