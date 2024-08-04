package com.ricky.bus.impl;

import com.ricky.bus.EventBus;
import com.ricky.handler.EventHandler;
import com.ricky.model.Event;
import com.ricky.processor.PublishProcessorChain;
import com.ricky.processor.impl.AnnotationPublishProcessor;
import com.ricky.processor.impl.SubClassPublishProcessor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className ConcurrentMapEventBus
 * @desc
 */
public class ConcurrentMapEventBus implements EventBus {

    /**
     * 事件处理器Map
     * 键-事件字节码
     * 值-处理该事件的处理器列表
     */
    private final Map<Class<? extends Event>, List<EventHandler<? extends Event>>> handlers = new ConcurrentHashMap<>();

    /**
     * 事件发布处理器责任链
     * 支持多种事件订阅方式
     */
    private final PublishProcessorChain publishProcessorChain = new PublishProcessorChain();

    private ConcurrentMapEventBus() {
        // 添加添加发布处理器
        publishProcessorChain.addProcessor(new SubClassPublishProcessor());
        publishProcessorChain.addProcessor(new AnnotationPublishProcessor());
    }

    /**
     * 单例
     */
    public static ConcurrentMapEventBus getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public <E extends Event> void publish(E event) {
        List<EventHandler<? extends Event>> eventHandlers = handlers.getOrDefault(event.getClass(), Collections.emptyList());
        for (EventHandler<? extends Event> handler : eventHandlers) {
            publishProcessorChain.publish(event, handler);
        }
    }

    @Override
    public <E extends Event> void subscribe(Class<E> eventType, EventHandler<E> handler) {
        handlers.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(handler);
    }

    @Override
    public <E extends Event> boolean unsubscribe(Class<E> eventType, EventHandler<E> handler) {
        List<EventHandler<? extends Event>> handlersList = handlers.get(eventType);
        if (handlersList != null) {
            boolean removed = handlersList.removeIf(h -> h.getClass().equals(handler.getClass()) && h.equals(handler));
            if (removed && handlersList.isEmpty()) {
                handlers.remove(eventType);
            }
            return removed;
        }
        return false;
    }

    private static final class Holder {
        private static final ConcurrentMapEventBus INSTANCE = new ConcurrentMapEventBus();
    }

}
