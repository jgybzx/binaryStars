package com.jgybzx.service;

import com.jgybzx.model.Student;

import java.util.List;

/**
 * @author jgybzx
 * @date 2020/9/2 9:57
 * @description
 */
public interface StudentService {
    /**
     * 查询全部
     * @return
     */
    List<Student> queryAll();
}
