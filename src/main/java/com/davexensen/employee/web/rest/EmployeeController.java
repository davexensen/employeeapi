package com.davexensen.employee.web.rest;

import com.davexensen.employee.domain.Employee;
import com.davexensen.employee.security.AuthoritiesConstants;
import com.davexensen.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id)
    {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
    {
        Employee newEmployee = employeeService.addEmployee(employee);

        if (newEmployee == null) {
            return ResponseEntity.badRequest().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newEmployee.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee)
    {
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        if (updatedEmployee == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @Secured(AuthoritiesConstants.USER)
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id)
    {
        Boolean result = employeeService.deleteEmployee(id);
        if (!result) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
