package io.daff.type.service;

import io.daff.type.entity.User;
import io.daff.type.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daffupman
 * @since 2020/1/29
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
