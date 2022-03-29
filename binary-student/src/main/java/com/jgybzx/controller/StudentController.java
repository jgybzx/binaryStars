package com.jgybzx.controller;

import com.alibaba.fastjson.JSON;
import com.jgybzx.entity.Customer;
import com.jgybzx.entity.Region;
import com.jgybzx.entity.Student;
import com.jgybzx.entity.StudentDto;
import com.jgybzx.service.RegionService;
import com.jgybzx.service.StudentService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
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

    @Autowired
    private RegionService regionService;

    /**
     * @return java.lang.String
     * @author jgybzx
     * @date 2021/4/28 10:12
     */
    @PostMapping("queryAll")
    public String queryAll() {
//        List<Region> regions = regionService.queryAll();
        String pCode = "0";

//        List<Map<String, Object>> extracted = extracted(regions, pCode);
        List<Student> studentList = studentService.queryAll();
//        int i = 1 / 0;
        studentList = studentList.stream().sorted(Comparator.comparing(Student::getBirthday)).collect(Collectors.toList());
        return JSON.toJSONString(studentList);
    }

    private List<Map<String, Object>> extracted(List<Region> regions, String pCode) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < regions.size(); i++) {
            Region temp = regions.get(i);
            if (temp.getpCode().equals(pCode)) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", temp.getName());
                map.put("code", temp.getCode());
                map.put("pCode", temp.getpCode());
                map.put("subMenus", extracted(regions, regions.get(i).getCode()));
                list.add(map);
            }
        }
        return list;
    }


    /**
     * @param map
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author jgybzx
     * @date 2021/4/28 10:06
     */
    @PostMapping("queryByCondition")
    public Map<String, Object> queryByCondition(@RequestBody Map<String, Object> map) {
        StudentDto studentDto =JSON.parseObject(JSON.toJSONString(map), StudentDto.class);
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

    @PostMapping("export")
    public void export(HttpServletResponse response, @RequestBody List<Customer> customerList) throws IOException {
        String name = "人员信息表.xlsx";
        //避免文件名中文乱码，将UTF8打散重组成ISO-8859-1编码方式
        name = new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        // 设置响应头类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 让浏览器下载文件
        response.setHeader("Content-Disposition", "attachment;filename=\"" + name + "\"");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        // XSSFWorkbook workbook = studentService.exportStu(customerList);
        XSSFWorkbook workbook = studentService.exportStu2(customerList);
        try {
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (response.getOutputStream() != null) {
                response.getOutputStream().close();
            }
        }
    }

    @PostMapping("testTransaction")

    public String testTransaction() {
        studentService.testTransaction();
        return "完成";
    }

    public static void main(String[] args) throws ParseException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(list.get(i) + ",");


                i++;
                if (i == list.size()) {
                    break;
                }
            }
            System.out.println();
        }
    }
    //11       10 9 8 7 6 5 4 3 2 1
    // 分支冲突测试


}











