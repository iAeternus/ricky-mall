package com.ricky.model;

import com.ricky.handler.EventHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className SubscribedMethod
 * @desc 表示被@Subscribe注解修饰的方法
 */
public class SubscribedMethod<E extends Event> implements EventHandler<E> {

    /**
     * 方法
     */
    private final Method method;

    /**
     * 处理器实体，设置为Object是为了能接受所有类型的处理器
     * 而不仅仅是EventHandler的实现类
     */
    private final Object handler;

    public SubscribedMethod(Method method, Object handler) {
        this.method = method;
        this.handler = handler;
        method.setAccessible(true);
    }

    public Object getHandler() {
        return handler;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "SubscribedMethod{" +
                "handlerInstance=" + handler +
                ", method=" + method +
                '}';
    }

    @Override
    public void handle(Event event) {
        try {
            method.invoke(handler, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
