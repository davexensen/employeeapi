package com.davexensen.employee.repository;

import com.davexensen.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.status = true")
    Collection<Employee> findAllActiveEmployees();

    @Query("SELECT e FROM Employee e WHERE e.id = ?1 and e.status = true")
    Optional<Employee> findActiveEmployee(Long id);
}
