package io.daff.general;

import io.daff.general.entity.Employee;
import io.daff.general.service.EmployeeService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通用mapper的方法测试
 *
 * @author daffupman
 * @since 2020/1/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class EmployeeMapperTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testSelectOne() {
        // 封装查询条件的对象
        Employee employee = new Employee();
//        employee.setName("bob");
        employee.setAge(22);
        // 执行查询
        Employee result = employeeService.getOne(employee);
        System.out.println(result);
    }

    @Test
    public void testSelect() {
        Employee employee = new Employee();
        List<Employee> employeeList = employeeService.select(employee);
        employeeList.forEach(System.out::println);
    }

    @Test
    public void testSelectAll() {
        List<Employee> employeeList = employeeService.selectAll();
        employeeList.forEach(System.out::println);
    }

    @Test
    public void testSelectCount() {
        int cnt = employeeService.selectCount();
        System.out.println(cnt);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Employee employee = new Employee(3, null, null, null);
        Employee e = employeeService.getEmployeeById(employee);
        System.out.println(e);
    }

    @Test
    public void testExistsWithByPrimaryKey() {
        Employee employee = new Employee(3, null, null, null);
        Boolean exists = employeeService.existsWithPrimaryKey(employee);
        System.out.println(exists);
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee(null, "wangzhengjin", 35000d, 25);
        int rows = employeeService.insert(employee);
        System.out.println("rows：" + rows + "\n返回的自增id为：" + employee.getId());
    }

    @Test
    public void testInsertSelective() {
        Employee employee = new Employee(null, "daff", 50000d, null);
        int rows = employeeService.insertSelective(employee);
        System.out.println("rows：" + rows + "\n返回的自增id为：" + employee.getId());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Employee e = new Employee(9, "ajin", null, 30);
        int rows = employeeService.updateByPrimarySelective(e);
        System.out.println("影响行数：" + rows);
    }

    @Test
    public void testSelectByExample() {
        // WHERE (emp_salary > ? and emp_age < ?) OR (emp_salary < ? AND emp_age > ?)

        // 创建Example对象，即where子句
        Example example = new Example(Employee.class);
        // 设置排序
        example.orderBy("age").desc().orderBy("salary").asc();
        // 设置去重
        example.setDistinct(true);
        // 设置查询出的列名
        example.selectProperties("name", "age", "salary");

        // 通过Example对象创建Criteria对象
        Example.Criteria c1 = example.createCriteria();
        Example.Criteria c2 = example.createCriteria();
        // 在Criteria对象分别设置查询条件
        c1.andGreaterThan("salary", 10000d).andLessThanOrEqualTo("age", 30);
        c2.andLessThan("salary", 5000d).andGreaterThanOrEqualTo("age", 30);
        example.or(c2);
        List<Employee> employeeList = employeeService.selectByExample(example);
        employeeList.forEach(System.out::println);
    }

    @Test
    public void testSelectOneByExample() {
    }

    @Test
    public void testSelectByRowBounds() {
        int pageNo = 3;
        int pageSize = 5;

        int index = (pageNo - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(index, pageSize);
        List<Employee> employeeList = employeeService.getAllEmployees(rowBounds);
        employeeList.forEach(System.out::println);
    }
}