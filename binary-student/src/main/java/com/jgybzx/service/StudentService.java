package com.jgybzx.service;

import com.jgybzx.entity.Customer;
import com.jgybzx.entity.Student;
import com.jgybzx.entity.StudentDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    String save(Map<String, Object> map);

    XSSFWorkbook exportStu(List<Customer> customerList);

    XSSFWorkbook exportStu2(List<Customer> customerList);
}
