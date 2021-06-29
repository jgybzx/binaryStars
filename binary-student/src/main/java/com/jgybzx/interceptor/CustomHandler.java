package com.jgybzx.interceptor;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @Author shixiaohao
 * @Date 2020/10/28 18:18
 * @Description: CustomHandler
 */
public class CustomHandler extends HorizontalCellStyleStrategy {


    public CustomHandler(WriteCellStyle headWriteCellStyle, List<WriteCellStyle> contentWriteCellStyleList) {
        super(headWriteCellStyle, contentWriteCellStyleList);
    }

    public CustomHandler(WriteCellStyle headWriteCellStyle, WriteCellStyle contentWriteCellStyle) {
        super(headWriteCellStyle, contentWriteCellStyle);
    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        if (head.getFieldName().endsWith("Record")) {
            Workbook wb = cell.getSheet().getWorkbook();
            Sheet sheet = wb.createSheet();
            sheet.setColumnWidth(11, 40 * 256);
            CellStyle cellStyle = wb.createCellStyle();

            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);

            cellStyle.setWrapText(true);

            cell.setCellStyle(cellStyle);

        } else {
            super.setContentCellStyle(cell, head, relativeRowIndex);
        }
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.setColumnWidth(11, 40 * 256);
        super.beforeCellCreate(writeSheetHolder, writeTableHolder, row, head, columnIndex, relativeRowIndex, isHead);
    }
}

