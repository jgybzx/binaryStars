package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.aspect.LogAnnotation;
import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import com.jgybzx.service.StudentService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;
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

    //@LogAnnotation
    @PostMapping("queryAll")
    public String queryAll() {
        List<Student> studentList = studentService.queryAll();
        // int i = 1 / 0;
        studentList = studentList.stream().sorted(Comparator.comparing(Student::getBirthday)).collect(Collectors.toList());
        return JsonUtil.toJson(studentList);
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @PostMapping("queryByCondition")
    public String queryByCondition(@RequestBody Map<String, Object> map) {
        StudentDto studentDto = JsonUtil.mapToClass(map, StudentDto.class);
       /* try {
            // 模拟服务器异常，访问超时
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<Student> studentList = studentService.queryByCondition(studentDto);
        return JsonUtil.toJson(studentList);
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
        return studentService.importFile(file);
    }

    @PostMapping("thread")
    public String thread() {
        return studentService.thread();
    }

    @PostMapping("paintingForm")
    public String paintingForm() {
        List<Map<String, Object>> maps = JsonUtil.jsonToListMap(JsonUtil.toJson(studentService.queryAll()));
        List<List<Object>> objects = new ArrayList<>();
        // 获取表头

        objects.add(new ArrayList<>(maps.get(0).keySet()));
        for (Map<String, Object> map : maps) {
            objects.add(map.values().stream().collect(Collectors.toList()));
        }
        draw();
        return JsonUtil.toJson(objects);
    }

    public void draw() {
        // 字体大小
        int fontTitileSize = 15;
        // 横线的行数
        //int totalrow = cellsValue.length+1;
        int totalrow = 7 + 1;
        // 竖线的行数
        int totalcol = 8;
        //if (cellsValue[0] != null) {
        //    totalcol = cellsValue[0].length;
        //}
        // 图片宽度
        int imageWidth = 1024;
        // 行高
        int rowheight = 40;
        // 图片高度
        int imageHeight = totalrow * rowheight + 50;
        // 起始高度
        int startHeight = 10;
        // 起始宽度
        int startWidth = 10;
        // 单元格宽度
        int colwidth = (int) ((imageWidth - 20) / totalcol);
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(new Color(220, 240, 240));

        //画横线
        for (int j = 0; j < totalrow; j++) {
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth, startHeight + (j + 1) * rowheight, startWidth + colwidth * totalcol, startHeight + (j + 1) * rowheight);
        }
        //画竖线
        for (int k = 0; k < totalcol + 1; k++) {
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth + k * colwidth, startHeight + rowheight, startWidth + k * colwidth, startHeight + rowheight * totalrow);
        }
        //设置字体
        Font font = new Font("微软雅黑", Font.BOLD, fontTitileSize);
        graphics.setFont(font);
        //写标题
        String title = "【指标完成进度】";
        graphics.drawString(title, startWidth, startHeight + rowheight - 10);
        // 保存图片
        createImage(image, "D:/test.jpg");
    }

    public void createImage(BufferedImage image, String fileLocation) {
        try {
            FileOutputStream fos = new FileOutputStream(fileLocation);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

