package com.jgybzx.exception;

/**
 * des:
 *
 * @author guojy
 * @date 2021/5/14 17:39
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     *
     * @return java.lang.String
     * @author guojy
     * @date 2021/5/14 17:41
     */
    String getResultCode();

    /**
     * 错误描述
     *
     * @return java.lang.String
     * @author guojy
     * @date 2021/5/14 17:41
     */
    String getResultMsg();
}
