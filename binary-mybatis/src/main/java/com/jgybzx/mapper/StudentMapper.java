package com.jgybzx.mapper;


import java.util.List;
import java.util.Map;

import com.jgybzx.model.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

    List<Student> selectByCondition(Student student);

    List<Student> selectStudentAndScore();

    @MapKey("id")
    List<Map<String, Object>> selectToMap(int totalScore);

    List<Student> selectAll();
}