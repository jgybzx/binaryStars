package com.jgybzx.service.impl;

import com.jgybzx.mapper.StudentMapper;
import com.jgybzx.model.Student;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.List;

/**
 * @author jgybzx
 * @date 2020/08/18 12:27
 * @description
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper mapper;
    /**
     * 查询全部
     * @return
     */
    @Override
    public String queryAll() {
        List<Student> studentList = mapper.queryAll();
        return null;
    }
}
