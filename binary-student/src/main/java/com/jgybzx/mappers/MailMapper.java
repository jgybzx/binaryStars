package com.jgybzx.mappers;

import com.jgybzx.entity.LogSql;
import com.jgybzx.entity.Mail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/11/23 22:22
 * @description 记录每次执行的sql语句及其相关信息
 */
@Component
public interface MailMapper {
    Mail query();
}
