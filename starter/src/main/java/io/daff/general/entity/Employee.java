package io.daff.general.entity;

import javax.persistence.*;

/**
 * 员工实体类
 *
 * @author daffupman
 * @since 2020/1/26
 */
// @Table注解：将数据库表名与实体类做映射。默认实体类名与表名一致
@Table(name = "t_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer id;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "emp_age")
    private Integer age;

    @Column(name = "emp_salary")
    private Double salary;

    public Employee() {
    }

    public Employee(Integer id, String name, Double salary, Integer age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
