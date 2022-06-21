package com.example.studyapi.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.studyapi.entity.User;
import com.example.studyapi.mapper.UserMapper;
import com.example.studyapi.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
