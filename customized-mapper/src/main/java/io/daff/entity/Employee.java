package io.daff.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`t_employee`")
public class Employee implements Serializable {
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