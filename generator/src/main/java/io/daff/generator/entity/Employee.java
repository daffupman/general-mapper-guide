package io.daff.generator.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "`t_employee`")
public class Employee {
    @Id
    @Column(name = "`emp_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column(name = "`emp_name`")
    private String empName;

    @Column(name = "`emp_salary`")
    private Double empSalary;

    @Column(name = "`emp_age`")
    private Integer empAge;
}