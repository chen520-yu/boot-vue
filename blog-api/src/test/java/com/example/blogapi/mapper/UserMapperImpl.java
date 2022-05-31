package com.example.blogapi.mapper;


import com.example.blogapi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperImpl {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Test
    public void test(){

        User user = new User();

        user.setUsername("root");
        user.setPassword("123");
        user.setStatus(5);

        userMapper.insert(user);

    }

}
