package com.jgybzx.service.impl;

import com.jgybzx.JsonUtil;
import com.jgybzx.mappers.StudentMapper;
import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import com.jgybzx.service.StudentService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

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


}
