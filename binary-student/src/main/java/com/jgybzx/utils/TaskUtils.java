package com.jgybzx.utils;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/5/10 15:20
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskUtils {
    @Scheduled(cron = "0 0 0,1,2 * * ?")
    public void doTask() {
        // 每天 00:00 01:00 02:00 自动执行任务
        System.out.println("我是定时任务~");
    }
}