package com.jgybzx.service;

import com.jgybzx.entity.UwExaminationQuestions;
import com.jgybzx.model.Student;

import java.util.List;
import java.util.Map;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/7/27 10:18
 */
public interface StudentService {
    /**
     * 查询全部
     *
     * @return java.util.List<com.jgybzx.model.Student>
     * @author jgybzx
     * @date 2021/7/27 10:19
     */
    List<Student> selectAll();



    /**
     * 根据条件查询
     *
     * @param student
     * @return java.util.List<com.jgybzx.model.Student>
     * @author jgybzx
     * @date 2021/7/28 10:58
     */
    List<Student> selectByCondition(Student student);

    /**
     * mybatis 练习collection 查询
     *
     * @return java.util.List<com.jgybzx.model.Student>
     * @author jgybzx
     * @date 2021/7/28 15:17
     */
    List<Student> selectStudentAndScore();

    List<Map<String, Object>> selectToMap(int totalScore);

    List<UwExaminationQuestions> batchUpdate();
}
