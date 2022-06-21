package com.example.studyapi.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.studyapi.entity.Blog;
import com.example.studyapi.mapper.BlogMapper;
import com.example.studyapi.service.BlogService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
