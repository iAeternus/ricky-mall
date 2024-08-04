package com.ricky.processor;

import com.ricky.handler.EventHandler;
import com.ricky.model.Event;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className PublishProcessor
 * @desc 发布事件处理器，为了支持EventHandler的不同写法
 */
public interface PublishProcessor {

    /**
     * 执行事件发布
     * @param event 具体事件
     * @param handler 具体的事件处理器
     * @return 发布成功返回true，否则返回false
     * @param <E> 扩展自领域事件
     */
    <E extends Event> boolean invoke(E event, EventHandler<? extends Event> handler);

}
