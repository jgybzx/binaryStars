package com.jgybzx.mappers;

import com.jgybzx.entity.LogSql;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/11/23 22:22
 * @description 记录每次执行的sql语句及其相关信息
 */
@Component
public interface LogSqlMapper {

    /**
     * 通过 model方式保存日志
     *
     * @param logSql
     */
    void saveLogByModel(LogSql logSql);

    /**
     * 通过map方式保存日志
     *
     * @param map
     */
    void saveLogByMap(@Param("map") Map<String, String> map);
}
