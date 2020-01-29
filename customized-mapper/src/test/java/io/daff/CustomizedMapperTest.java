package io.daff;

import io.daff.entity.Employee;
import io.daff.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author daffupman
 * @since 2020/1/29
 */
public class CustomizedMapperTest {

    private EmployeeService employeeService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
        this.employeeService = ac.getBean(EmployeeService.class);
    }

    @Test
    public void testCustomizedMapper() {
        List<Employee> employees = this.employeeService.selectAll();
        employees.forEach(System.out::println);
    }

    @Test
    public void testExtendsMapper() {
        List<Employee> employees = Arrays.asList(
                new Employee(8, "zj", null, null),
                new Employee(9, "jj", 45000d, null)
        );
        this.employeeService.batchUpdateSelective(employees);
    }

    @Test
    public void testCache() {
        this.employeeService.selectAll().forEach(System.out::println);
        this.employeeService.selectAll().forEach(System.out::println);
    }
}
