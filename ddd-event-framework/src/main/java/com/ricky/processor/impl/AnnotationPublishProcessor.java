package com.ricky.processor.impl;

import com.ricky.annotations.Subscribe;
import com.ricky.processor.PublishProcessor;
import com.ricky.handler.EventHandler;
import com.ricky.model.Event;
import com.ricky.model.SubscribedMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className AnnotationPublishProcessor
 * @desc
 */
public class AnnotationPublishProcessor implements PublishProcessor {

    private static final Logger LOGGER = Logger.getLogger(AnnotationPublishProcessor.class.getName());

    @Override
    public <E extends Event> boolean invoke(E event, EventHandler<? extends Event> handler) {
        Class<? extends Event> eventClass = event.getClass();
        if (handler instanceof SubscribedMethod<? extends Event> subscribedMethod) {
            Method method = subscribedMethod.getMethod();
            Object methodHandler = subscribedMethod.getHandler();
            if (isSuitableMethod(method, eventClass)) {
                try {
                    method.invoke(methodHandler, event);
                    return true;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.warning("Failed to invoke handler method " + method.getName() + " in " + methodHandler.getClass().getName() + ". Cause: " + e.getCause());
                }
            }
        }
        return false;
    }

    private boolean isSuitableMethod(Method method, Class<? extends Event> eventClass) {
        return method.isAnnotationPresent(Subscribe.class) &&
                method.getParameterCount() == 1 &&
                eventClass.isAssignableFrom(method.getParameterTypes()[0]);
    }
}
