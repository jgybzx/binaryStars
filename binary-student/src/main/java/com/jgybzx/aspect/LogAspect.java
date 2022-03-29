package com.jgybzx.aspect;

import com.jgybzx.mappers.LogSqlMapper;
import com.jgybzx.utils.SqlUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * @author jgybzx
 * @date 2020/11/20 10:00
 * @description 记录mapper每一次执行的sql及其相关信息
 */
@Aspect
@Component
public class LogAspect {


    /**
     * 切点 扫描整个mapper
     *
     * @Pointcut("execution(* com.jgybzx.controller.*.*(..)) && @annotation(com.jgybzx.aspect.LogAnnotation)")
     * @Pointcut("execution(* com.jgybzx.mappers.*.*(..))")
     */
    @Pointcut("execution(* com.jgybzx.mappers.*.*(..)) && !execution(* com.jgybzx.mappers.LogSqlMapper.*(..))")
    public void logRecord() {
        // 定义
    }

    /**
     * 前置通知
     *
     * @param jp
     */
    @Before(value = "logRecord()")
    public void before(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法开始执行");
    }

    /**
     * 后置通知
     *
     * @param jp
     */
    @After(value = "logRecord()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法执行结束...");
    }

    /**
     * 返回通知
     *
     * @param jp
     * @param result
     */
    @AfterReturning(value = "logRecord()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法返回值为：" + result);
    }

    /**
     * 异常通知
     *
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "logRecord()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法异常：" + e.getMessage());
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private LogSqlMapper logSqlMapper;

    @Around(value = "logRecord()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取请求信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Signature signature = pjp.getSignature();
        String logId = String.valueOf(System.currentTimeMillis());
        String methodName = signature.getName();
        String methodUrl = signature.toShortString();
        String requestUrl = request.getRequestURL().toString();
        String sql = SqlUtils.getMybatisSql(pjp, sqlSessionFactory);
        System.out.println("执行的sql语句为###" + sql + "###");
        /*//<editor-fold desc="1、jdbc 保存日志">
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        String insertSql = "insert into logsql(log_id, sql_str, method_name, method_url, request_url, create_date) " +
                "values (" + "\'" + logId + "\'" + ","
                + "\"" + sql + "\"" + ","
                + "\"" + methodName + "\"" + ","
                + "\'" + methodUrl + "\'" + ","
                + "\'" + requestUrl + "\'" + ","
                + "NOW()" + ")";
        statement.executeUpdate(insertSql);
        //</editor-fold>*/

        /*//<editor-fold desc="2、mybatis 通过model方式保存">
        LogSql logSql = new LogSql();
        logSql.setLogId(logId);
        logSql.setMethodName(methodName);
        logSql.setMethodUrl(methodUrl);
        logSql.setSqlStr(sql);
        logSql.setRequestUrl(requestUrl);
        logSqlMapper.saveLogByModel(logSql);
        //</editor-fold>*/

        //<editor-fold desc="3、mybatis 通过 map方式保存">
        Map<String, String> map = new HashMap<>(16);
        map.put("log_id", logId);
        map.put("sql_str", sql);
        map.put("method_name", methodName);
        map.put("method_url", methodUrl);
        map.put("request_url", requestUrl);
        logSqlMapper.saveLogByMap(map);
        //</editor-fold>
        return pjp.proceed();
    }
}
