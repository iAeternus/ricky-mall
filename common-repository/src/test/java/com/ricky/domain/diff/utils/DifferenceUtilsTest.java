package com.ricky.domain.diff.utils;

import com.ricky.domain.diff.entity.AggregateDifference;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.junit.Test;

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

    @Data
    static class User implements Aggregate<UserId> {
        private UserId id;
        private Username username;
        private Password password;
        private Integer balance;
        private Integer age;
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

}