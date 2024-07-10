package com.ricky.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ricky.constants.AutoFillConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/5/27
 * @className AutoFillHandler
 * @desc 公共字段自动填充处理器
 */
@Slf4j
@Component
public class AutoFillHandler implements MetaObjectHandler {

    /**
     * 插入操作自动填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始进行 插入操作 公共字段填充");
        LocalDateTime now = LocalDateTime.now();
        metaObject.setValue(AutoFillConstant.CREATE_TIME, now);
        metaObject.setValue(AutoFillConstant.UPDATE_TIME, now);
    }

    /**
     * 更新操作自动填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始进行 更新操作 公共字段填充");
        LocalDateTime now = LocalDateTime.now();
        metaObject.setValue(AutoFillConstant.UPDATE_TIME, now);
    }

}

