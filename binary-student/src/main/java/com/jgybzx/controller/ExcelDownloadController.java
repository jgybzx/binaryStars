package com.jgybzx.controller;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.jgybzx.entity.Customer;
import com.jgybzx.interceptor.CustomHandler;
import com.jgybzx.utils.BasicConstants;
import com.jgybzx.utils.EasyExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * excle下载
     *
     * @param response
     * @author jgybzx
     * @date 2021/6/29 17:52
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        try {
            int i = 1 / 0;
            List<Customer> customerList = JSON.parseArray(BasicConstants.easyExcelString,Customer.class);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String transformDate = simpleDateFormat.format(new Date());
            String fileName = "咨询人查询" + transformDate;
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
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
        } catch (Exception e) {
            response.reset();
            response.setStatus(500);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
//            throw new BaseException("500", "表格下载失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("sc = ");
            int i = sc.nextInt();
            int frog = frog(i);
            System.out.println(frog);
        }
    }

    public static int frog(int i) {
        if (i == 1) {
            return 1;
        } else if (i == 2) {
            return 2;
        } else {
            return frog(i - 1) + frog(i - 2);
        }
    }
}
