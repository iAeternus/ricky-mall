package com.ricky.handler;

import com.ricky.model.Event;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className EventHandler
 * @desc 事件处理器
 */
@FunctionalInterface
public interface EventHandler<E extends Event> {

    void handle(E event);

}
