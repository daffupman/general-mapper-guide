package io.daff.general;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author daffupman
 * @since 2020/1/26
 */
public class MapperTest {

    private ApplicationContext iocContainer = new ClassPathXmlApplicationContext("spring-context.xml");

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = iocContainer.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}
