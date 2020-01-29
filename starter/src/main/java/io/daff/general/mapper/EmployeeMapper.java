package io.daff.general.mapper;

import io.daff.general.entity.Employee;
import tk.mybatis.mapper.common.Mapper;

/**
 * 具体操作数据库的Mapper接口，需要继承通用Mapper的核心接口:Mapper<T>
 * 泛型为相应的实体类
 *
 * @author daffupman
 * @since 2020/1/26
 */
public interface EmployeeMapper extends Mapper<Employee> {
}
