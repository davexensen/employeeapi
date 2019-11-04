package com.davexensen.employee.web.rest;

import com.davexensen.employee.EmployeeapiApp;
import com.davexensen.employee.domain.Employee;
import com.davexensen.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class EmployeeControllerTest {
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllEmployees() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder()
            .id(1L)
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build());
        employees.add(Employee.builder()
            .id(2L)
            .firstName("Test2")
            .middleInitial("P")
            .lastName("Test2")
            .build());

        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(2,responseEntity.getBody().size());
    }

    @Test
    void getEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        when(employeeService.getEmployee(1L)).thenReturn(employee);

        ResponseEntity<Employee> responseEntity = employeeController.getEmployee(1L);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Test",responseEntity.getBody().getFirstName());
    }

    @Test
    void getEmployeeFailed() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(employeeService.getEmployee(1L)).thenReturn(null);

        ResponseEntity<Employee> responseEntity = employeeController.getEmployee(1L);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    void addEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Object> responseEntity = employeeController.addEmployee(employee);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals("/1",responseEntity.getHeaders().getLocation().getPath());
    }

    @Test
    void addEmployeeFailed() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(null);

        ResponseEntity<Object> responseEntity = employeeController.addEmployee(employee);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void updateEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Object> responseEntity = employeeController.updateEmployee(employee);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void updateEmployeeFailed() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .middleInitial("P")
            .lastName("Test")
            .build();

        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(null);

        ResponseEntity<Object> responseEntity = employeeController.updateEmployee(employee);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void deleteEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(employeeService.deleteEmployee(1L)).thenReturn(true);

        ResponseEntity<Object> responseEntity = employeeController.deleteEmployee(1L);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void deleteEmployeeFailed() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(employeeService.deleteEmployee(1L)).thenReturn(false);

        ResponseEntity<Object> responseEntity = employeeController.deleteEmployee(1L);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}
