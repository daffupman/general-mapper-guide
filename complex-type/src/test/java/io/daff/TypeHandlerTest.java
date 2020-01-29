package io.daff;

import io.daff.type.entity.User;
import io.daff.type.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author daffupman
 * @since 2020/1/29
 */
public class TypeHandlerTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService userService = ac.getBean(UserService.class);
        List<User> users = userService.selectAll();
        users.forEach(System.out::println);
        ac.close();
    }
}
