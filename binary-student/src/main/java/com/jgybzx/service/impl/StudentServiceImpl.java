package com.jgybzx.service.impl;

import com.jgybzx.JsonUtil;
import com.jgybzx.mappers.StudentMapper;
import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import com.jgybzx.service.StudentService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jgybzx
 * @date 2020/9/2 9:57
 * @description
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper mapper;


    @Override
    public List<Student> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public List<Student> queryByCondition(StudentDto studentDto) {
        return mapper.queryByCondition(studentDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "文件不为空";
        }
        List<Map<String, Object>> dataList = null;
        try {
            dataList = getDataList(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Student> studentList = JsonUtil.mapToListObj(dataList, Student.class);
        int rows = mapper.saveAll(studentList);
        return String.valueOf(rows);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        List<Student> list = new ArrayList<>();
        Student student = new Student("2222", "1212", "1", "1212", "1212", new Date());
        list.add(student);
        mapper.saveAll(list);
        //int i = 1 / 0;
        list.clear();
        Student student1 = new Student("3333", "1212", "1", "1212", "1212", new Date());
        list.add(student1);
        mapper.saveAll(list);
    }

    @Override
    public String save(Map<String, Object> map) {
        Student student = JsonUtil.mapToClass(map, Student.class);
        List<Student> list = new ArrayList<>();
        list.add(student);
        mapper.saveAll(list);
        return "";
    }

    @Override
    public String exportStu() {
        //创建临时文件存放的路径
        String temp = System.getProperty("user.dir");
        List<Student> students = mapper.queryAll();

        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        CellStyle headCellStyle = setHeadCellStyle(xssfWorkbook);
        CellStyle cellStyle = setCellStyle(xssfWorkbook);
        //创建工作表
        XSSFSheet sheet = xssfWorkbook.createSheet();

        xssfWorkbook.setSheetName(0, "学生信息表");
        //创建表头
        XSSFRow head = sheet.createRow(0);
        // 获取对象所有属性
        Field[] declaredFields = Student.class.getDeclaredFields();
        List<String> attribute = Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
        for (int i = 0; i < attribute.size(); i++) {
            XSSFCell cell = head.createCell(i);
            cell.setCellStyle(headCellStyle);
            cell.setCellValue(attribute.get(i));
        }

        // 填充数据（从第二行开始）
        for (int i = 1; i <= students.size(); i++) {
            Student student = students.get(i - 1);
            XSSFRow row = sheet.createRow(i);
            Class<? extends Student> aClass = student.getClass();
            Field[] declaredFields1 = aClass.getDeclaredFields();
            // 创建单元格 填充数据
            for (int num = 0; num < declaredFields1.length; num++) {
                XSSFCell cell = row.createCell(num);
                cell.setCellStyle(cellStyle);
                String attributeValue = "";
                try {
                    Field field = declaredFields1[num];
                    field.setAccessible(true);
                    attributeValue = field.get(student).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                cell.setCellValue(attributeValue);
                sheet.autoSizeColumn((short) num);
            }

        }
        //创建临时文件的目录
        File file = new File(temp);
        if (!file.exists()) {
            file.mkdirs();
        }
        //临时文件路径/文件名
        String downloadPath = file + "\\" + System.currentTimeMillis() + UUID.randomUUID();
        OutputStream outputStream = null;
        try {
            //使用FileOutputStream将内存中的数据写到本地，生成临时文件
            outputStream = new FileOutputStream(downloadPath);
            xssfWorkbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return downloadPath;
    }

    /**
     * 设置表头单元格格式
     *
     * @param xssfWorkbook
     * @return org.apache.poi.ss.usermodel.CellStyle
     * @author jgybzx
     * @date 2021/6/24 16:20
     */
    CellStyle setHeadCellStyle(XSSFWorkbook xssfWorkbook) {
        //创建styleHead
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        //背景色 天蓝色
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //水平居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        XSSFFont font = xssfWorkbook.createFont();
        //加粗
        font.setBold(true);
        //字体大小
        font.setFontHeight((short) 280);
        // 字体
        font.setFontName("宋体");
        cellStyle.setFont(font);
        // 设置边框
        setBorderStyle(cellStyle);
        return cellStyle;
    }

    /**
     * 设置非表头单元格格式
     *
     * @param xssfWorkbook
     * @return org.apache.poi.ss.usermodel.CellStyle
     * @author jgybzx
     * @date 2021/6/24 16:20
     */
    CellStyle setCellStyle(XSSFWorkbook xssfWorkbook) {
        //创建styleHead
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        //水平居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        XSSFFont font = xssfWorkbook.createFont();
        //加粗
        font.setBold(false);
        //字体大小
        font.setFontHeight((short) 240);
        // 字体
        font.setFontName("宋体");
        cellStyle.setFont(font);
        // 设置边框
        setBorderStyle(cellStyle);
        return cellStyle;
    }

    /**
     * 边框样式
     *
     * @param cellStyle
     * @return org.apache.poi.ss.usermodel.CellStyle
     * @author jgybzx
     * @date 2021/6/24 16:14
     */
    private CellStyle setBorderStyle(CellStyle cellStyle) {
        // 底部边框+颜色 BORDER_THIN:细线
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        // 左边边框+颜色
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 右边边框+颜色
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 上边边框+颜色
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return cellStyle;
    }


    /**
     * 解析表格里边的数据，返回类型是 List<Map<String, Object>>
     *
     * @param file
     * @return
     * @throws IOException
     */
    private List<Map<String, Object>> getDataList(MultipartFile file) throws IOException {

        // 文件流转换
        Workbook wb = new XSSFWorkbook(file.getInputStream());

        // 读取数据
        Sheet sheetAt = wb.getSheetAt(0);

        // 得到行数和列数
        int totalRows = sheetAt.getLastRowNum() - 1;
        int totalCells = 0;
        if (totalRows >= 1 && sheetAt.getRow(0) != null) {
            totalCells = sheetAt.getRow(0).getPhysicalNumberOfCells();
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        Row firstRows = sheetAt.getRow(0);
        for (int i = 1; i < totalRows; i++) {
            // 得到每一行 进行数据处理
            Row row = sheetAt.getRow(i);
            if (row == null) {
                continue;
            }
            Map<String, Object> rowList = new HashMap<>(16);
            for (int j = 0; j < totalCells; j++) {
                Cell cell = row.getCell(j);
                String cellValue = "";
                if (cell != null) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        // 数字
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            // 处理数字数据后边都会加上.0的情况
                            double d = cell.getNumericCellValue();
                            NumberFormat nf = NumberFormat.getInstance();
                            cellValue = nf.format(d);
                            break;
                        // 字符串
                        case HSSFCell.CELL_TYPE_STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        // Boolean
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            cellValue = cell.getBooleanCellValue() + "";
                            break;

                        // 空值
                        case HSSFCell.CELL_TYPE_BLANK:
                            cellValue = "";
                            break;
                        // 错误
                        case HSSFCell.CELL_TYPE_ERROR:
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
                // 构建Map
                rowList.put(firstRows.getCell(j).getStringCellValue(), cellValue);
            }
            dataList.add(rowList);
        }
        return dataList;
    }

    public static void main(String[] args) {

        Field[] declaredFields = Student.class.getDeclaredFields();
        List<String> attribute = Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
    }
}
