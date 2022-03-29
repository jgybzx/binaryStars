package com.jgybzx.service.impl;

import com.jgybzx.entity.UwExaminationQuestions;
import com.jgybzx.mapper.UwExaminationQuestionsMapper;
import com.jgybzx.model.Student;
import com.jgybzx.mapper.StudentMapper;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/7/27 10:18
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询全部
     *
     * @return java.util.List<com.jgybzx.model.Student>
     * @author jgybzx
     * @date 2021/7/27 10:20
     */
    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }


    @Override
    public List<Student> selectByCondition(Student student) {
        return studentMapper.selectByCondition(student);

    }

    @Override
    public List<Student> selectStudentAndScore() {

        return studentMapper.selectStudentAndScore();
    }

    @Override
    public List<Map<String, Object>> selectToMap(int totalScore) {

        return studentMapper.selectToMap(totalScore);
    }

    @Autowired
    private UwExaminationQuestionsMapper uwExaminationQuestionsMapper;

    @Override
    public List<UwExaminationQuestions> batchUpdate() {
        List<UwExaminationQuestions> list = uwExaminationQuestionsMapper.selectAll();
        for (UwExaminationQuestions uwExaminationQuestions : list) {
            uwExaminationQuestionsMapper.update(uwExaminationQuestions.getNumb(),uwExaminationQuestions.getId());
        }

        return list;
    }
}
