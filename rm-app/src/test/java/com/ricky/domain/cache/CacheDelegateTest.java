package com.ricky.domain.cache;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className CacheDelegateTest
 * @desc
 */
@SpringBootTest
public class CacheDelegateTest {

    @Data
    @AllArgsConstructor
    static class User implements Aggregate<UserId> {
        private UserId id;
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class UserId implements Identifier {
        Long value;
    }

    @Autowired
    private CacheDelegate<User, UserId> cache;

    @Test
    public void testCacheOperates() {
        // Given
        List<User> users = List.of(
                new User(new UserId(1L), "Ricky1", "123"),
                new User(new UserId(2L), "Ricky2", "1234"),
                new User(new UserId(3L), "Ricky3", "12345")
        );

        // When
        for (User user : users) {
            cache.save(user.getId(), user);
        }
        cache.remove(new UserId(3L));

        // Then
        assertThat(cache.find(new UserId(2L)))
                .isEqualTo(new User(new UserId(2L), "Ricky2", "1234"));
        assertThat(cache.find(new UserId(3L))).isNull();
    }

    @Test
    @DisplayName("并发写入与读取")
    public void testConcurrentReadWrite() throws InterruptedException {
        // Given
        List<User> users = List.of(
                new User(new UserId(1L), "Alice", "123"),
                new User(new UserId(2L), "Bob", "456")
        );

        List<Thread> threads = new ArrayList<>();

        // When
        // 写入线程
        for (User user : users) {
            threads.add(new Thread(() -> cache.save(user.getId(), user)));
        }

        // 读取线程
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                assertThat(cache.find(new UserId(1L))).isEqualTo(users.get(0));
                assertThat(cache.find(new UserId(2L))).isEqualTo(users.get(1));
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    @DisplayName("并发写入相同键")
    public void testConcurrentWriteSameKey() throws InterruptedException {
        // Given
        List<Thread> threads = new ArrayList<>();
        int numOfThreads = 10;

        // When
        // 写入线程
        for (int i = 0; i < numOfThreads; i++) {
            final long userId = 1;
            final String userName = "User" + i;
            final String password = "Pass" + i;

            threads.add(new Thread(() -> cache.save(new UserId(userId), new User(new UserId(userId), userName, password))));
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        // Then
        // 验证最终缓存中的值
        User lastUser = null;
        for (int i = 0; i < 10; i++) {
            User user = cache.find(new UserId(1L));
            if (user != null) {
                lastUser = user;
            }
        }

        // 假设最后一个写入的值被保留
        assertThat(lastUser).isNotNull();
        assertThat(lastUser.getUsername()).endsWith(String.valueOf(numOfThreads - 1));
    }

    @Test
    @DisplayName("并发清除缓存")
    public void testConcurrentClear() throws InterruptedException {
        // Given
        List<User> users = List.of(
                new User(new UserId(1L), "Alice", "123"),
                new User(new UserId(2L), "Bob", "456")
        );

        List<Thread> threads = new ArrayList<>();

        // When
        // 填充缓存
        for (User user : users) {
            cache.save(user.getId(), user);
        }

        // 清除线程
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                cache.remove(new UserId(1L));
                cache.remove(new UserId(2L));
            }));
        }

        // 启动并等待所有线程
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        // Then
        // 验证缓存已被清空
        assertThat(cache.find(new UserId(1L))).isNull();
        assertThat(cache.find(new UserId(2L))).isNull();
    }

}
