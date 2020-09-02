package com.jgybzx.service.impl;

import com.jgybzx.mappers.StudentMapper;
import com.jgybzx.model.Student;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jgybzx
 * @date 2020/9/2 9:57
 * @description
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper mapper;

    @Override
    public List<Student> queryAll() {
        return mapper.queryAll();
    }
}
