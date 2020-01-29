package io.daff.mapper;

import io.daff.base.BaseMapper;
import io.daff.entity.Employee;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author daffupman
 * @since 2020/1/29
 */
@CacheNamespace
public interface EmployeeMapper extends BaseMapper<Employee> {

}
