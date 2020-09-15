package com.jgybzx.mappers;

import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jgybzx
 * @date 2020/9/2 9:58
 * @description
 */
@Component
public interface StudentMapper {
    /**
     * 查询全部
     * @return
     */
    List<Student> queryAll();

    /**
     * 根据条件查询  动态参数
     * @param studentDto
     * @return
     */
    List<Student> queryByCondition(StudentDto studentDto);

    /**
     * 保存全部
     * @param studentList
     * @return
     */
    int saveAll(List<Student> studentList);
}
