package com.jgybzx.controller.holiday;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author Jgybzx
 * @date 2021/9/23 15:39
 * des
 */
public class HolidayUtil {

    public static String doGet(String url) {
        try {
            CloseableHttpClient build = HttpClientBuilder.create().build();
            //发送get请求
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = build.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String url = "https://v.juhe.cn/calendar/month?year-month=2020-12&key=73777cdcc5bef04f4cfb47afdabc4a09";
        //String s = HolidayUtil.doGet(url);
        String result = "{\n" +
                "\t\"reason\":\"Success\",\n" +
                "\t\"result\":{\n" +
                "\t\t\"data\":{\n" +
                "\t\t\t\"year\":\"2020\",\n" +
                "\t\t\t\"year-month\":\"2020-12\",\n" +
                "\t\t\t\"holiday\":\"[{\\\"desc\\\":\\\"2021年1月1日至3日放假，共3天\\\",\\\"festival\\\":\\\"2021-1-1\\\",\\\"list\\\":[{\\\"date\\\":\\\"2021-1-1\\\",\\\"status\\\":\\\"1\\\"},{\\\"date\\\":\\\"2021-1-2\\\",\\\"status\\\":\\\"1\\\"},{\\\"date\\\":\\\"2021-1-3\\\",\\\"status\\\":\\\"1\\\"}],\\\"list#num#\\\":3,\\\"name\\\":\\\"元旦\\\",\\\"rest\\\":\\\"\\\"}]\",\n" +
                "\t\t\t\"holiday_array\":[\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"desc\":\"2021年1月1日至3日放假，共3天\",\n" +
                "\t\t\t\t\t\"festival\":\"2021-1-1\",\n" +
                "\t\t\t\t\t\"list\":[\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"date\":\"2021-1-1\",\n" +
                "\t\t\t\t\t\t\t\"status\":\"1\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"date\":\"2021-1-2\",\n" +
                "\t\t\t\t\t\t\t\"status\":\"1\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"date\":\"2021-1-3\",\n" +
                "\t\t\t\t\t\t\t\"status\":\"1\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"list#num#\":3,\n" +
                "\t\t\t\t\t\"name\":\"元旦\",\n" +
                "\t\t\t\t\t\"rest\":\"\",\n" +
                "\t\t\t\t\t\"list_num\":3\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"error_code\":0\n" +
                "}";
    }

}
