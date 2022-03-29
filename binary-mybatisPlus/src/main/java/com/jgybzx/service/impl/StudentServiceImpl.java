package com.jgybzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jgybzx.mapper.StudentMapper;
import com.jgybzx.entity.Student;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jgybzx
 * @date 2020/12/9 10:53
 * @description
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper mapper;

    @Override
    public List<Student> queryAll() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        List<Student> list = mapper.selectList(qw);
        Student student = mapper.selectById("1");
        List<Student> students = new ArrayList<>();
        students.add(student);
        return list;
    }
}
