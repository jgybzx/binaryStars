package com.jgybzx.controller;

import com.alibaba.fastjson.JSON;
import com.jgybzx.entity.UwExaminationQuestions;
import com.jgybzx.model.Student;
import com.jgybzx.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/7/27 10:22
 */
@RestController
@RequestMapping("mybatis")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("selectAll")
    public String selectAll() {
        List<Student> students = studentService.selectAll();
        logger.info("反参；{}", students);
        return JSON.toJSONString(students);
    }

    @PostMapping("selectAllList")
    public List<Student> selectAllList() {
        //int i = 1 / 0;
        return studentService.selectAll();
    }

    @PostMapping("parseObject")
    public List<Student> parseObject(@RequestBody String str) {
        return JSON.parseArray(str, Student.class);
    }


    @PostMapping("selectByCondition")
    public String selectByCondition(@RequestBody Student student) {
        List<Student> list = studentService.selectByCondition(student);
        return JSON.toJSONString(list);
    }

    @PostMapping("selectStudentAndScore")
    public List<Student> selectStudentAndScore() {
        return studentService.selectStudentAndScore();
    }

    @PostMapping("selectToMap")
    public String selectToMap(@Param("totalScore") int totalScore) {
        List<Map<String, Object>> maps = studentService.selectToMap(totalScore);
        return JSON.toJSONString(maps);
    }

    @PostMapping("selectToMap1")
    public List<Map<String, Object>> selectToMap1(@Param("totalScore") int totalScore) {
        List<Map<String, Object>> maps = studentService.selectToMap(totalScore);
        return maps;
    }

    @PostMapping("batchUpdate")
    public List<UwExaminationQuestions> batchUpdate(){
        List<UwExaminationQuestions> list =  studentService.batchUpdate();
        return list;
    }

    public static void main(String[] args) {
        double mu = 4377;
        
    }

}