package com.ricky.handler;


import com.ricky.annotations.Subscribe;
import com.ricky.bus.EventBus;
import com.ricky.bus.impl.ConcurrentMapEventBus;
import com.ricky.model.Event;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className EventHandlerTest
 * @desc
 */
class EventHandlerTest {

    static class OrderPlacedEvent implements Event {
        private final String orderId;
        private final double totalAmount;

        public OrderPlacedEvent(String orderId, double totalAmount) {
            this.orderId = orderId;
            this.totalAmount = totalAmount;
        }

        // Getters
        public String getOrderId() {
            return orderId;
        }

        public double getTotalAmount() {
            return totalAmount;
        }
    }

    /**
     * 第一种写法：实现接口
     */
    static class OrderServiceEventHandler implements EventHandler<OrderPlacedEvent> {
        @Override
        public void handle(OrderPlacedEvent event) {
            System.out.println("Order placed with ID: " + event.getOrderId() + " and total amount: $" + event.getTotalAmount());
        }
    }

    /**
     * 第二种写法：添加注解
     */
    static class TestEventHandler {

        @Subscribe
        public void handler1(OrderPlacedEvent event) {
            System.out.println("handler1");
        }

        @Subscribe
        public void handler2(OrderPlacedEvent event) {
            System.out.println("handler2");
        }

    }

    @Test
    public void test() {
        // Given
        EventBus eventBus = ConcurrentMapEventBus.getInstance();

        OrderPlacedEvent event = new OrderPlacedEvent("0001", 199.99);

        // When
        eventBus.subscribe(OrderPlacedEvent.class, new OrderServiceEventHandler());

        Thread publishThread = new Thread(() -> {
            System.out.println("发布事件");
            eventBus.publish(event);
        });

        publishThread.start();
    }

    @Test
    public void testHighConcurrency() throws InterruptedException {
        // Given
        EventBus eventBus = ConcurrentMapEventBus.getInstance();

        int numOfRequest = 100000;
        int numOfThread = 100;
        int singleThread = numOfRequest / numOfThread;

        List<OrderPlacedEvent> events = new ArrayList<>();
        for(int i = 0; i < numOfRequest; ++i) {
            events.add(new OrderPlacedEvent(String.valueOf(i), 199.99));
        }

        List<Thread> threads = new ArrayList<>();

        // When
        eventBus.subscribe(OrderPlacedEvent.class, new OrderServiceEventHandler());

        int startIndex = 0;
        for (int i = 0; i < numOfThread; ++i) {
            int finalStartIndex = startIndex;
            threads.add(new Thread(() -> {
                for(int j = 0; j < singleThread; ++j) {
                    eventBus.publish(events.get(j + finalStartIndex));
                }
            }));
            startIndex += singleThread;
        }

        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }

        System.out.println("All threads end");
    }

    @Test
    public void testEventHandler() throws InterruptedException {
        // Given
        EventBus eventBus = ConcurrentMapEventBus.getInstance();

        OrderPlacedEvent event = new OrderPlacedEvent("0001", 199.99);

        // When
        eventBus.subscribeAll(new TestEventHandler(), TestEventHandler.class);

        Thread thread = new Thread(() -> {
            System.out.println("发布事件");
            eventBus.publish(event);
        });

        thread.start();
        thread.join();
    }

}