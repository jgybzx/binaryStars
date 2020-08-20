package com.jgybzx.mapper;

import com.jgybzx.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jgybzx
 * @date 2020/08/18 12:29
 * @description
 */
@Component
public interface StudentMapper {
    /**
     * 查询全部
     * @return
     */
    List<Student> queryAll();

}
