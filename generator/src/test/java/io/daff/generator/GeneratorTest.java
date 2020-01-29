package io.daff.generator;

import io.daff.generator.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author daffupman
 * @since 2020/1/29
 */
public class GeneratorTest {

    private SqlSession sqlSession;

    @Before
    public void init() {
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = sqlSessionFactoryBuilder.build(inputStream);
            this.sqlSession = factory.openSession();
            MapperHelper mapperHelper = new MapperHelper();
            mapperHelper.processConfiguration(sqlSession.getConfiguration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employeeMapper.selectAll().forEach(System.out::println);
    }

}
