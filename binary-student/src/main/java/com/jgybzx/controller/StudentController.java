package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import com.jgybzx.model.TestModel;
import com.jgybzx.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * @return java.lang.String
     * @author jgybzx
     * @date 2021/4/28 10:12
     */
    @PostMapping("queryAll")
    public String queryAll() {
        List<Student> studentList = studentService.queryAll();
        int i = 1 / 0;
        studentList = studentList.stream().sorted(Comparator.comparing(Student::getBirthday)).collect(Collectors.toList());
        return JsonUtil.toJson(studentList);
    }


    /**
     * @param map
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author jgybzx
     * @date 2021/4/28 10:06
     */
    @PostMapping("queryByCondition")
    public Map<String, Object> queryByCondition(@RequestBody Map<String, Object> map) {
        StudentDto studentDto = JsonUtil.mapToClass(map, StudentDto.class);
       /* try {
            // 模拟服务器异常，访问超时
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<Student> studentList = studentService.queryByCondition(studentDto);
        Map<String, Object> result = new HashMap<>(16);
        result.put("studentList", studentList);
        int i = 1 / 0;
        return result;
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
        String rows = studentService.importFile(file);
        return "上传成功，共" + rows + "条数据。";
    }

    @PostMapping("testTransaction")
    public String testTransaction() {
        studentService.testTransaction();
        return "完成";
    }

    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DAY_OF_MONTH, 0);
        Date time = dateFormat.parse(dateFormat.format(nowTime.getTime()));
        Date temp = dateFormat.parse("2021-05-11");
        // 下发日期小于 当前日期-10  temp - time  -1：小于  0；等于 1：大于
        int i = temp.compareTo(time);
        System.out.println("i = " + i);
        List<TestModel> list = new ArrayList<>();
        TestModel testModel = new TestModel();
        testModel.setKey("1");
        testModel.setValue("1");
        list.add(testModel);
        testModel = new TestModel();
        testModel.setKey("2");
        testModel.setValue("2");
        list.add(testModel);
        System.out.println("JsonUtil.toJson(list) = " + JsonUtil.toJson(list));
    }
    //11       10 9 8 7 6 5 4 3 2 1
    // 分支冲突测试
}











