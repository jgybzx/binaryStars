package com.jgybzx.mappers;

import com.jgybzx.controller.holiday.DateList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Jgybzx
 * @date 2021/9/23 17:31
 * des
 */
@Component
public interface DateListMapper {
    void saveList(@Param("list") List<DateList> list);
}
