package com.ricky.domain.cache;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    public void testCacheOperatesAsync() {
        // Given
        List<List<User>> userLists = List.of(
                List.of(
                        new User(new UserId(1L), "Ricky1", "123"),
                        new User(new UserId(2L), "Ricky2", "1234"),
                        new User(new UserId(3L), "Ricky3", "12345")
                ),
                List.of(
                        new User(new UserId(4L), "Ricky1", "123"),
                        new User(new UserId(5L), "Ricky2", "1234"),
                        new User(new UserId(6L), "Ricky3", "12345")
                ),
                List.of(
                        new User(new UserId(7L), "Ricky1", "123"),
                        new User(new UserId(8L), "Ricky2", "1234"),
                        new User(new UserId(9L), "Ricky3", "12345")
                )
        );

        List<Thread> threads = new ArrayList<>();

        // When
        for (List<User> users : userLists) {
            threads.add(new Thread(() -> {
                for (User user : users) {
                    cache.save(user.getId(), user);
                }
            }));
        }

        for (int i = 0; i < 3; ++i) {
            threads.add(new Thread(() -> {
                cache.remove(new UserId(1L));
                cache.remove(new UserId(7L));
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        // Then
        assertThat(cache.find(new UserId(1L))).isNull();
        assertThat(cache.find(new UserId(7L))).isNull();
        assertThat(cache.find(new UserId(3L))).isEqualTo(new User(new UserId(3L), "Ricky3", "12345"));
        assertThat(cache.find(new UserId(9L))).isEqualTo(new User(new UserId(9L), "Ricky3", "12345"));
    }

}
