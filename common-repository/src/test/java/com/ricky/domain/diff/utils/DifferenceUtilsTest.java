package com.ricky.domain.diff.utils;

import com.ricky.domain.diff.entity.AggregateDifference;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.utils.DifferenceUtils;
import lombok.*;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/27
 * @className DifferenceUtilsTest
 * @desc
 */
public class DifferenceUtilsTest {

    @Value
    static class UserId implements Identifier {
        Long value;
    }

    @Value
    static class Username {
        String value;
    }

    @Value
    static class Password {
        String value;
    }

    @Value
    static class ItemId implements Identifier {
        String value;
    }

    @Data
    @AllArgsConstructor
    static class Item implements Entity<ItemId> {
        private ItemId id;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class User implements Aggregate<UserId> {
        private UserId id;
        private Username username;
        private Password password;
        private Integer balance;
        private Integer age;
        private List<Item> items;
    }

    @Data
    @AllArgsConstructor
    static class UserPO {
        private Long id;
        private String username;
        private String password;
        private Integer balance;
        private Integer age;
    }

    @Data
    @AllArgsConstructor
    static class ModifyUserCommand {
        private Long id;
        private String username;
        private Integer age;
    }

    static class UserAssembler {
        public User toUser(ModifyUserCommand command) {
            User user = new User();
            user.setId(new UserId(command.getId()));
            user.setUsername(new Username(command.getUsername()));
            user.setAge(command.getAge());
            return user;
        }
    }

    static class UserDataConverter {
        public User toEntity(UserPO po) {
            User user = new User();
            user.setId(new UserId(po.getId()));
            user.setUsername(new Username(po.getUsername()));
            user.setPassword(new Password(po.getPassword()));
            user.setAge(po.getAge());
            user.setBalance(po.getBalance());
            return user;
        }
    }

    /////////////////// Start Test ///////////////////

    private final UserAssembler userAssembler = new UserAssembler();
    private final UserDataConverter userDataConverter = new UserDataConverter();

    @Test
    public void different() {
        // Given
        // 前端传过来的对象
        ModifyUserCommand command = new ModifyUserCommand(1L, "ricky", 18);
        // 经过数据转换为领域对象
        User userFromFront = userAssembler.toUser(command);

        // 模拟从数据库中查询出的结果
        UserPO userPO = new UserPO(1L, "aaa", "123", 10000, 17);
        // 经过数据转换成转换为领域对象
        User userFromDB = userDataConverter.toEntity(userPO);

        // When
        AggregateDifference<User, UserId> different = DifferenceUtils.different(userFromFront, userFromDB);

        // Then
        System.out.println(different);
    }

    @Test
    public void compareCollectionType() {
        // Given
        User user1 = User.builder()
                .id(new UserId(1L))
                .username(new Username("ricky"))
                .password(new Password("123"))
                .balance(1000)
                .age(20)
                .items(List.of(
                        new Item(new ItemId("i-1"), "aaa"),
                        new Item(new ItemId("i-2"), "bbb"),
                        new Item(new ItemId("i-3"), "ccc"),
                        new Item(new ItemId("i-4"), "ddd")
                ))
                .build();

        User user2 = User.builder()
                .id(new UserId(1L))
                .username(new Username("ricky666"))
                .password(new Password("123"))
                .balance(1000)
                .age(20)
                .items(List.of(
                        // remove
                        new Item(new ItemId("i-2"), "bbb1"), // update
                        new Item(new ItemId("i-3"), "ccc"),
                        new Item(new ItemId("i-4"), "ddd"),
                        new Item(null, "eee") // insert
                ))
                .build();

        // When
        AggregateDifference<User, UserId> different = DifferenceUtils.different(user1, user2);

        // Then
        System.out.println(different);
    }

    @Test
    public void testStream() {
        // Given
        List<Integer> l1 = List.of(1, 2, 3, 4, 5);
        List<Integer> l2 = List.of(4, 5, 6, 7, 8);

        // When
        List<Integer> list = Stream.concat(l1.stream(), l2.stream()).distinct().toList();

        // Then
        list.forEach(System.out::println);
    }

}