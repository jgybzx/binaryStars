package com.jgybzx.interceptor;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shixiaohao
 * @Date 2020/10/28 18:18
 * @Description: CustomHandler
 */
public class CustomHandler extends AbstractColumnWidthStyleStrategy {

    private static final int MAX_COLUMN_WIDTH = 5000;
    private Map<Integer, Map<Integer, Integer>> CACHE = new HashMap(8);

    public CustomHandler() {
    }

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> cellDataList, Cell cell, Head head, Integer integer, Boolean isHead) {
        boolean needSetWidth = isHead || !CollectionUtils.isEmpty(cellDataList);
      /*  if (needSetWidth) {
            Map<Integer, Integer> maxColumnWidthMap = (Map) CACHE.get(writeSheetHolder.getSheetNo());
            if (maxColumnWidthMap == null) {
                maxColumnWidthMap = new HashMap(16);
                CACHE.put(writeSheetHolder.getSheetNo(), maxColumnWidthMap);
            }

            Integer columnWidth = dataLength(cellDataList, cell, isHead);
            if (columnWidth >= 0) {
                if (columnWidth > 255) {
                    columnWidth = 255;
                }

                Integer maxColumnWidth = (Integer) ((Map) maxColumnWidthMap).get(cell.getColumnIndex());
                if (maxColumnWidth == null || columnWidth > maxColumnWidth) {
                    ((Map) maxColumnWidthMap).put(cell.getColumnIndex(), columnWidth);
                    writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
                }

            }
        }*/
        if (needSetWidth) {
            // 所有是head的cell设置固定的宽度 MAX_COLUMN_WIDTH
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), MAX_COLUMN_WIDTH);
            // 设置默认行高
            // writeSheetHolder.getCachedSheet().setDefaultRowHeightInPoints();
        }

    }

    private Integer dataLength(List<CellData> cellDataList, Cell cell, Boolean isHead) {
        if (isHead) {
            return cell.getStringCellValue().getBytes().length;
        } else {
            CellData cellData = (CellData) cellDataList.get(0);
            CellDataTypeEnum type = cellData.getType();
            if (type == null) {
                return -1;
            } else {
                switch (type) {
                    case STRING:
                        return cellData.getStringValue().getBytes().length;
                    case BOOLEAN:
                        return cellData.getBooleanValue().toString().getBytes().length;
                    case NUMBER:
                        return cellData.getNumberValue().toString().getBytes().length;
                    default:
                        return -1;
                }
            }
        }
    }

    public static WriteCellStyle getHeadStyle() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");//设置字体名字
        headWriteFont.setFontHeightInPoints((short) 13);//设置字体大小
        headWriteFont.setBold(true);//字体加粗
        headWriteCellStyle.setWriteFont(headWriteFont); //在样式用应用设置的字体;

        // 样式
        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);//设置底边框;
        headWriteCellStyle.setBottomBorderColor((short) 0);//设置底边框颜色;
        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);  //设置左边框;
        headWriteCellStyle.setLeftBorderColor((short) 0);//设置左边框颜色;
        headWriteCellStyle.setBorderRight(BorderStyle.THIN);//设置右边框;
        headWriteCellStyle.setRightBorderColor((short) 0);//设置右边框颜色;
        headWriteCellStyle.setBorderTop(BorderStyle.THIN);//设置顶边框;
        headWriteCellStyle.setTopBorderColor((short) 0); //设置顶边框颜色;


        headWriteCellStyle.setWrapped(true);  //设置自动换行;


        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);//设置水平对齐的样式为居中对齐;
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);  //设置垂直对齐的样式为居中对齐;


        //        headWriteCellStyle.setShrinkToFit(true);//设置文本收缩至合适

        return headWriteCellStyle;
    }

    public static WriteCellStyle getContentStyle() {
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        // 背景绿色
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 设置字体
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints((short) 12);//设置字体大小
        contentWriteFont.setFontName("宋体"); //设置字体名字
        contentWriteCellStyle.setWriteFont(contentWriteFont);//在样式用应用设置的字体;

        //设置样式;
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);//设置底边框;
        contentWriteCellStyle.setBottomBorderColor((short) 0);//设置底边框颜色;
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);  //设置左边框;
        contentWriteCellStyle.setLeftBorderColor((short) 0);//设置左边框颜色;
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);//设置右边框;
        contentWriteCellStyle.setRightBorderColor((short) 0);//设置右边框颜色;
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);//设置顶边框;
        contentWriteCellStyle.setTopBorderColor((short) 0); ///设置顶边框颜色;

        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);// 水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        contentWriteCellStyle.setWrapped(true); //设置自动换行;

        contentWriteCellStyle.setDataFormat((short) 49);//设置单元格格式是：文本格式，方式长数字文本科学计数法

        contentWriteCellStyle.setShrinkToFit(true);//设置文本收缩至合适

        return contentWriteCellStyle;
    }

}

