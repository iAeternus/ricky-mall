package com.ricky.processor;

import com.ricky.handler.EventHandler;
import com.ricky.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className PublishProcessorChain
 * @desc 事件发布处理器责任链，支持多种事件订阅写法
 * 写法一：实现EventHandler接口
 * 写法二：添加@Subscribe注解
 */
public class PublishProcessorChain {

    private final List<PublishProcessor> processors = new ArrayList<>();

    public void addProcessor(PublishProcessor processor) {
        processors.add(processor);
    }

    public <E extends Event> void publish(E event, EventHandler<? extends Event> handler) {
        for (PublishProcessor processor : processors) {
            if(processor.invoke(event, handler)) {
                // 若成功处理则不再继续
                break;
            }
        }
    }

}
