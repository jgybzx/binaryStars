package com.jgybzx.service;

import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jgybzx
 * @date 2020/9/2 9:57
 * @description
 */
public interface StudentService {
    /**
     * 查询全部
     *
     * @return
     */
    List<Student> queryAll();


    /**
     * 根据条件查询数据，动态参数
     *
     * @param studentDto
     * @return
     */
    List<Student> queryByCondition(StudentDto studentDto);

    /**
     * 读取表格数据 保存到表
     *
     * @param file
     * @return
     */
    String importFile(MultipartFile file);


    /**
     * 事务控制测试
     */
    void testTransaction();

}
