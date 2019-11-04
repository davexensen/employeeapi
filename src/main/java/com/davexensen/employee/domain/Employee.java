package com.davexensen.employee.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends AbstractAuditingEntity {

    @Id
    @SequenceGenerator(name = "emp_sequence", sequenceName = "emp_sequence", allocationSize=1)
    @GeneratedValue(strategy = SEQUENCE, generator = "emp_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

    @Column(name = "middle_initial", length = 1)
    private String middleInitial;

    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "date_of_employment", nullable = false)
    private LocalDate dateOfEmployment;

    @Column(name = "status", nullable = false)
    private Boolean status;
}
