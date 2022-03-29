package com.jgybzx.controller.holiday;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.jgybzx.mappers.DateListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Jgybzx
 * @date 2021/9/23 16:44
 * des
 */
@RestController
@RequestMapping("/isHoliday")
public class IsHolidayController {
    public static final String YEAR_MONTH = "year-month";
    public static final String KEY = "key";

    @Autowired
    private DateListMapper mapper;

    @GetMapping
    public String isHoliday() {
        String url = "https://v.juhe.cn/calendar/month";
        LocalDate now = LocalDate.now();
        String year = now.getYear() + "-" + now.getMonth().getValue();
        String key = "73777cdcc5bef04f4cfb47afdabc4a09";
        url = url + "?" + YEAR_MONTH + "=" + year + "&" + KEY + "=" + key;
        String s = HolidayUtil.doGet(url);
        HolidayResult holidayResult = JSONUtil.toBean(s, HolidayResult.class);
        List<Holiday_array> holidayArrayList = holidayResult.getResult().getData().getHoliday_array();
        List<DateList> dateLists = new ArrayList<>();
        for (Holiday_array holidayArray : holidayArrayList) {
            List<DateList> list = holidayArray.getList();
            dateLists.addAll(list);
        }
        mapper.saveList(dateLists);
        return "success";
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        Month month = now.getMonth();
        System.out.println("year = " + year);
        int value = month.getValue();
        System.out.println("value = " + value);
    }
}
