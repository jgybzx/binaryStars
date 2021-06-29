package com.jgybzx.interceptor;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;


/**
 * des: 自定义拦截器，来实现自己想要的样式
 *
 * @author jgybzx
 * @date 2021/6/25 9:04
 */
public class CustomHandler extends HorizontalCellStyleStrategy {

    private static final String RECORD = "Record";

    public CustomHandler(WriteCellStyle headWriteCellStyle, WriteCellStyle contentWriteCellStyle) {
        super(headWriteCellStyle, contentWriteCellStyle);
    }

    /**
     * 重写该方法，实现自由控制某一列的样式
     *
     * @author jgybzx
     * @date 2021/6/29 13:57
     */
    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        if (head.getFieldName().endsWith(RECORD)) {
            // 如果该列的名字以 Record 结尾，则进行单独的样式设置
            Workbook wb = cell.getSheet().getWorkbook();
            CellStyle cellStyle = wb.createCellStyle();
            // 左对齐，垂直居中对齐
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // 边框线
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            // 自动换行
            cellStyle.setWrapText(true);
            cell.setCellStyle(cellStyle);
        } else {
            super.setContentCellStyle(cell, head, relativeRowIndex);
        }
    }

    /**
     * 重写该方法，实现在单元格创建之前，就进行列宽固定设置
     *
     * @author jgybzx
     * @date 2021/6/29 14:02
     */
    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.setColumnWidth(11, 40 * 256);
        super.beforeCellCreate(writeSheetHolder, writeTableHolder, row, head, columnIndex, relativeRowIndex, isHead);
    }
}

