package com.davexensen.employee.service;

import com.davexensen.employee.domain.Employee;
import com.davexensen.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Long id) {
        log.debug("Getting employee by id ({})", id);

        Optional<Employee> employee = employeeRepository.findActiveEmployee(id);

        return employee.orElse(null);
    }

    public List<Employee> getAllEmployees() {
        log.debug("Getting all employees");
        return new ArrayList<>(employeeRepository.findAllActiveEmployees());
    }

    public Employee addEmployee(Employee employee) {
        //Override status so delete is only done through DELETE call
        employee.setStatus(true);
        Employee newEmployee = employeeRepository.save(employee);

        log.debug("Added employee id ({})", newEmployee.getId());

        return newEmployee;
    }

    public Employee updateEmployee(Employee employee) {
        if (getEmployee(employee.getId()) == null) {
            log.debug("Could not find employee by id ({})", employee.getId());
            return null;
        }

        //Override status so delete is only done through DELETE call
        employee.setStatus(true);
        Employee updatedEmployee = employeeRepository.save(employee);

        log.debug("Updated employee id ({})", updatedEmployee.getId());

        return updatedEmployee;
    }

    public Boolean deleteEmployee(Long id) {
        log.debug("Deleting employee id ({})", id);

        Employee employee = getEmployee(id);

        if (employee == null) {
            log.debug("Could not find employee by id ({})", id);
            return false;
        }

        //Set status to false when "deleting" employee
        employee.setStatus(false);
        employeeRepository.save(employee);

        return true;
    }
}
