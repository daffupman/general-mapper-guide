package io.daff.general.service;

import io.daff.general.entity.Employee;
import io.daff.general.mapper.EmployeeMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author daffupman
 * @since 2020/1/26
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getOne(Employee employee) {
        return employeeMapper.selectOne(employee);
    }

    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    public List<Employee> select(Employee employee) {
        return employeeMapper.select(employee);
    }

    public int selectCount() {
        Employee employee = new Employee();
        return employeeMapper.selectCount(employee);
    }

    public Employee getEmployeeById(Employee employee) {
        return employeeMapper.selectByPrimaryKey(employee);
    }

    public Boolean existsWithPrimaryKey(Employee employee) {
        return employeeMapper.existsWithPrimaryKey(employee);
    }

    public int insert(Employee employee) {
        return employeeMapper.insert(employee);
    }

    public int insertSelective(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }

    public int updateByPrimarySelective(Employee e) {
        return employeeMapper.updateByPrimaryKeySelective(e);
    }

    public List<Employee> selectByExample(Example example) {
        return employeeMapper.selectByExample(example);
    }

    public List<Employee> getAllEmployees(RowBounds rowBounds) {
        return employeeMapper.selectByRowBounds(null, rowBounds);
    }
}
