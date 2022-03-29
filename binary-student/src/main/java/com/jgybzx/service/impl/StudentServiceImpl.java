package com.jgybzx.service.impl;

import com.alibaba.fastjson.JSON;
import com.jgybzx.entity.Customer;
import com.jgybzx.entity.Student;
import com.jgybzx.entity.StudentDto;
import com.jgybzx.mappers.StudentMapper;
import com.jgybzx.service.StudentService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        List<Student> studentList = JSON.parseArray(JSON.toJSONString(dataList), Student.class);
        int rows = mapper.saveAll(studentList);
        return String.valueOf(rows);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        List<Student> list = new ArrayList<>();
        mapper.saveAll(list);
    }

    @Override
    public String save(Map<String, Object> map) {
        Student student = JSON.parseObject(JSON.toJSONString(map), Student.class);
        List<Student> list = new ArrayList<>();
        list.add(student);
        mapper.saveAll(list);
        return "";
    }

    @Override
    public XSSFWorkbook exportStu(List<Customer> customerList) {
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = xssfWorkbook.createSheet();
        xssfWorkbook.setSheetName(0, "学生信息表");
        //创建表头
        XSSFRow head = sheet.createRow(0);
        CellStyle headCellStyle = setHeadCellStyle(xssfWorkbook);
        // 获取对象所有属性
        // Field[] declaredFields = Customer.class.getDeclaredFields();
        // List<String> attribute = Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
        String[] attribute = {"序号", "客户号", "客户姓名", "客户概述", "客户联系电话", "咨询人姓名", "咨询人联系电话", "客户级别", "销售人员", "未跟踪天数", "末次跟踪日期", "沟通记录", "进度"};
        for (int i = 0; i < attribute.length; i++) {
            XSSFCell cell = head.createCell(i);
            cell.setCellStyle(headCellStyle);
            cell.setCellValue(attribute[i]);
        }

        // 填充数据（从第二行开始）
        CellStyle cellStyle = setCellStyle(xssfWorkbook, true);
        for (int i = 1; i <= customerList.size(); i++) {
            Customer customer = customerList.get(i - 1);
            XSSFRow row = sheet.createRow(i);
            Class<? extends Customer> aClass = customer.getClass();
            Field[] declaredFields1 = aClass.getDeclaredFields();
            // 创建单元格 填充数据
            for (int num = 0; num < declaredFields1.length; num++) {
                sheet.autoSizeColumn((short) num);
                XSSFCell cell = row.createCell(num);
                String attributeValue;

                cell.setCellStyle(cellStyle);
                try {
                    Field field = declaredFields1[num];
                    field.setAccessible(true);
                    if ("communicationRecord".equals(field.getName())) {
                        // 单独设置可能数据量大的单元格，进行固定宽度，自动换行，取消水平居中
                        sheet.setColumnWidth(11, 40 * 256);
                        cell.setCellStyle(setCellStyle(xssfWorkbook, false));
                    }
                    attributeValue = field.get(customer).toString();
                    attributeValue.replace("\\n", "<br>");
                    cell.setCellValue(attributeValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return xssfWorkbook;
    }

    /**
     * 以为是因为反射，浪费了时间，修改之后发现还是慢
     *
     * @param customerList
     * @return org.apache.poi.xssf.usermodel.XSSFWorkbook
     * @author jgybzx
     * @date 2021/6/25 17:56
     */

    @Override
    public XSSFWorkbook exportStu2(List<Customer> customerList) {
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = xssfWorkbook.createSheet();
        xssfWorkbook.setSheetName(0, "学生信息表");
        //创建表头
        XSSFRow head = sheet.createRow(0);
        CellStyle headCellStyle = setHeadCellStyle(xssfWorkbook);
        // 获取对象所有属性
        String[] attribute = {"序号", "客户号", "客户姓名", "客户概述", "客户联系电话", "咨询人姓名", "咨询人联系电话", "客户级别", "销售人员", "未跟踪天数", "末次跟踪日期", "沟通记录", "进度"};
        for (int i = 0; i < attribute.length; i++) {
            XSSFCell cell = head.createCell(i);
            cell.setCellStyle(headCellStyle);
            cell.setCellValue(attribute[i]);
        }

        // 填充数据（从第二行开始）
        CellStyle cellStyle = setCellStyle(xssfWorkbook, true);
        Customer customer;
        for (int i = 1; i <= customerList.size(); i++) {
            customer = customerList.get(i - 1);
            XSSFRow row = sheet.createRow(i);
            // 创建单元格 填充数据
            for (int num = 0; num < 12; num++) {
                sheet.autoSizeColumn((short) num);
                XSSFCell cell = row.createCell(num);
                String attributeValue = null;

                cell.setCellStyle(cellStyle);
                switch (num) {
                    case 0:
                        attributeValue = customer.getNumber();
                        break;
                    case 1:
                        attributeValue = customer.getCustomerNo();
                        break;
                    case 2:
                        attributeValue = customer.getCustomerName();
                        break;
                    case 3:
                        attributeValue = customer.getCustomerDescription();
                        break;
                    case 4:
                        attributeValue = customer.getCustomerPhone();
                        break;
                    case 5:
                        attributeValue = customer.getConsultantName();
                        break;
                    case 6:
                        attributeValue = customer.getConsultantPhone();
                        break;
                    case 7:
                        attributeValue = customer.getCustomerLevel();
                        break;
                    case 8:
                        attributeValue = customer.getAgentName();
                        break;
                    case 9:
                        attributeValue = customer.getNoTrace();
                        break;
                    case 10:
                        attributeValue = customer.getLastTraceDate();
                        break;
                    case 11:
                        attributeValue = customer.getCommunicationRecord();
                        sheet.setColumnWidth(11, 40 * 256);
                        cell.setCellStyle(setCellStyle(xssfWorkbook, false));
                        break;
                    case 12:
                        attributeValue = customer.getStatus();
                        break;
                    default:
                        attributeValue = "";
                        break;
                }
                attributeValue = attributeValue.replace("\\n", "<br>");
                cell.setCellValue(attributeValue);

            }
        }
        return xssfWorkbook;
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
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
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
     * 单元格样式
     *
     * @param xssfWorkbook
     * @param alignmentFlag 是否垂直居中标识
     * @return org.apache.poi.ss.usermodel.CellStyle
     * @author jgybzx
     * @date 2021/6/25 9:52
     */
    CellStyle setCellStyle(XSSFWorkbook xssfWorkbook, boolean alignmentFlag) {
        //创建styleHead
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        if (alignmentFlag) {
            //水平居中设置
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        } else {
            //水平居中设置
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
        }
        // 垂直居中设置
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
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
        //自动换行
        cellStyle.setWrapText(true);
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
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        // 左边边框+颜色
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 右边边框+颜色
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 上边边框+颜色
        cellStyle.setBorderTop(BorderStyle.THIN);
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
