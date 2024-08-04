package com.ricky.bus;

import com.ricky.annotations.Subscribe;
import com.ricky.handler.EventHandler;
import com.ricky.model.Event;
import com.ricky.model.SubscribedMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className EventBus
 * @desc 事件总线接口
 */
public interface EventBus {

    /**
     * 发布事件
     * @param event 事件
     * @param <E> 扩展自领域事件
     */
    <E extends Event> void publish(E event);

    /**
     * 订阅特定类型的事件
     * @param eventType 领域事件派生类的字节码
     * @param handler 事件处理器
     * @param <E> 扩展自领域事件
     */
    <E extends Event> void subscribe(Class<E> eventType, EventHandler<E> handler);

    /**
     * 取消订阅特定类型的事件和处理器
     * @param eventType 领域事件派生类的字节码
     * @param handler 事件处理器
     * @param <E> 扩展自领域事件
     * @return 若成功取消订阅返回true，否则返回false
     */
    <E extends Event> boolean unsubscribe(Class<E> eventType, EventHandler<E> handler);

    /**
     * 订阅所有被@Subscribe修饰的事件处理方法
     * @param handler 任意的事件处理器，无需实现EventHandler
     * @param eventHandlerType 该事件处理器的字节码
     */
    @SuppressWarnings("unchecked")
    default void subscribeAll(Object handler, Class<?> eventHandlerType) {
        Method[] declaredMethods = eventHandlerType.getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("Event handler method must have exactly one argument.");
                }

                Class<Event> eventType = (Class<Event>) parameterTypes[0];
                SubscribedMethod<Event> subscribedMethod = new SubscribedMethod<>(method, handler);
                subscribe(eventType, subscribedMethod);
            }
        }
    }

}
