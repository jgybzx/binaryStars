package com.jgybzx.controller;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.jgybzx.JsonUtil;
import com.jgybzx.interceptor.CustomHandler;
import com.jgybzx.model.Customer;
import com.jgybzx.utils.BasicConstants;
import com.jgybzx.utils.EasyExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.alibaba.excel.EasyExcelFactory.write;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/6/25 14:23
 */
@RestController
@RequestMapping("excel")
public class ExcelDownloadController {
    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        List<Customer> customerList = JsonUtil.jsonToList(BasicConstants.easyExcelString, Customer.class);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String transformDate = simpleDateFormat.format(new Date());
        // TODO: 2021/6/29  接口响应文件名中文乱码，待修改
        String fileName = "咨询人查询" + transformDate;
        //避免文件名中文乱码，将UTF8打散重组成ISO-8859-1编码方式
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 标题样式
        WriteCellStyle headWriteCellStyle = EasyExcelUtils.getHeadStyle();
        // 内容样式
        WriteCellStyle contentWriteCellStyle = EasyExcelUtils.getContentStyle();
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        CustomHandler customHandler = new CustomHandler(headWriteCellStyle, contentWriteCellStyle);
        write(response.getOutputStream(), Customer.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(customHandler)
                .sheet("信息").doWrite(customerList);

    }

}
