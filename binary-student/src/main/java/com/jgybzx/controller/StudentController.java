package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/9/1 15:31
 * @description
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("queryAll")
    public String queryAll() {
        List<Student> studentList = studentService.queryAll();
        return JsonUtil.toJson(studentList);
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @PostMapping("queryByCondition")
    public String queryByCondition(@RequestBody Map<String, Object> map) {
        StudentDto studentDto = JsonUtil.mapToClass(map, StudentDto.class);
       /* try {
            // 模拟服务器异常，访问超时
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<Student> studentList = studentService.queryByCondition(studentDto);
        return JsonUtil.toJson(studentList);
        /**
         * {
         *   "id": "901",
         *   "name": "张老大",
         *   "sex": "男",
         *   "department": "计算机系",
         *   "address": "北京市海淀区",
         *   "birthday": "1995-05-13 00:00:00"
         * }
         */
    }

    @PostMapping("import")
    public String importData(@RequestParam("file") MultipartFile file) {
        return studentService.importFile(file);
    }

}

