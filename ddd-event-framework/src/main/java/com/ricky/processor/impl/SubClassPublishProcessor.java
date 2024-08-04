package com.ricky.processor.impl;

import com.ricky.processor.PublishProcessor;
import com.ricky.handler.EventHandler;
import com.ricky.model.Event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className SubClassPublishProcessor
 * @desc
 */
public class SubClassPublishProcessor implements PublishProcessor {

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Event> boolean invoke(E event, EventHandler<? extends Event> handler) {
        Class<? extends Event> eventClass = event.getClass();
        Type[] interfaceTypes = handler.getClass().getGenericInterfaces();
        for (Type interfaceType : interfaceTypes) {
            if (interfaceType instanceof ParameterizedType pt && pt.getRawType().equals(EventHandler.class)) {
                Type eventTypeArg = pt.getActualTypeArguments()[0];
                if (eventTypeArg instanceof Class<?> clazz && clazz.isAssignableFrom(eventClass)) {
                    EventHandler<E> safeHandler = (EventHandler<E>) handler;
                    safeHandler.handle(event);
                    return true;
                }
            }
        }
        return false;
    }

}
