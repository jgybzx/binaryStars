package com.jgybzx.service;

import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/9/2 11:18
 * @description
 */
public interface RemoteService {
    /**
     * 查询全部
     *
     * @return
     */
    String queryAll();

    /**
     * Feign 远程调用
     *
     * @param map
     * @return
     */
    String queryByCondition(Map<String, Object> map);
}
