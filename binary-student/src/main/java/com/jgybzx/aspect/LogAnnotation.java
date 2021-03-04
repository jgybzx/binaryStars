package com.jgybzx.aspect;

import java.lang.annotation.*;

/**
 * @author jgybzx
 * @date 2020/11/20 10:16
 * @description 用于自定义切面注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAnnotation {
}
