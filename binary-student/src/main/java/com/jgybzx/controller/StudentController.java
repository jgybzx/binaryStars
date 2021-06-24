package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.model.Region;
import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import com.jgybzx.service.RegionService;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        List<Region> regions = regionService.queryAll();
        String pCode = "0";

        List<Map<String, Object>> extracted = extracted(regions, pCode);
        List<Student> studentList = studentService.queryAll();
        int i = 1 / 0;
        studentList = studentList.stream().sorted(Comparator.comparing(Student::getBirthday)).collect(Collectors.toList());
        return JsonUtil.toJson(studentList);
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

    @GetMapping("export")
    public void export(HttpServletResponse response) {
        String name = "学生信息表.xlsx";
        //避免文件名中文乱码，将UTF8打散重组成ISO-8859-1编码方式
        name = new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        // 设置响应头类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 让浏览器下载文件
        response.setHeader("Content-Disposition", "attachment;filename=\"" + name + "\"");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String downloadPath = studentService.exportStu();
        //根据临时文件的路径创建File对象，FileInputStream读取时需要使用
        File file = new File(downloadPath);
        try {
            //通过FileInputStream读临时文件，ServletOutputStream将临时文件写给浏览器
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            int len = -1;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b);
            }
            //刷新
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输入输出流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //最后才能，删除临时文件，如果流在使用临时文件，file.delete()是删除不了的
        file.delete();
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











