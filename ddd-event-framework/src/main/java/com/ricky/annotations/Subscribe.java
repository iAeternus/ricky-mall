package com.ricky.annotations;

import java.lang.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className Subscribe
 * @desc 订阅注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Subscribe {

    int priority() default 0;

}
