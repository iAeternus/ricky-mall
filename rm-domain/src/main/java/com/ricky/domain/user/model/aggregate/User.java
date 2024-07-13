package com.ricky.domain.user.model.aggregate;

import com.ricky.constants.MessageConstant;
import com.ricky.enums.impl.UserRole;
import com.ricky.exception.ForbiddenException;
import com.ricky.marker.Aggregate;
import com.ricky.types.common.ExchangeRate;
import com.ricky.types.common.Money;
import com.ricky.types.user.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className User
 * @desc 用户
 */
@Data
public class User implements Aggregate<UserId> {

    private UserId id;
    private Email email;
    private Password password;
    private Nickname nickname;
    private RealName realName;
    private PhoneNumber phoneNumber;
    private UserRole role;
    private Integral integral = Integral.ZERO; // 积分
    private Level level = Level.ZERO; // 等级
    private Money balance = new Money(0); // 余额 TODO 改成自定义的Money

    /**
     * 设置为企业用户
     */
    public void toEnterpriseUser() {
        this.role = UserRole.ENTERPRISE_USERS;
    }

    /**
     * 设置为商家
     */
    public void toBusiness() {
        this.role = UserRole.BUSINESS;
    }

    /**
     * 设置为物流方
     */
    public void toLogisticsParty() {
        this.role = UserRole.LOGISTICS_PARTY;
    }

    /**
     * 设置为管理员
     */
    public void toAdmin() {
        this.role = UserRole.ADMIN;
    }

    /**
     * 增加积分
     *
     * @param points 积分增加值
     */
    public void addIntegral(Long points) {
        integral = new Integral(integral.getValue() + points);
        level = new Level(integral.getLevel());
    }

    /**
     * 增加余额
     *
     * @param money 增加值
     */
    public void increaseBalance(Money money) {
        BigDecimal rate = BigDecimal.valueOf(0.14); // TODO 这里要调用防腐层获取汇率
        balance = balance.add(money.getCurrency().equals(balance.getCurrency()) ?
                money : ExchangeRate.exchangeTo(rate, money, balance.getCurrency()));
    }

    /**
     * 扣减余额
     *
     * @param money 扣减值
     */
    public void deductionBalance(Money money) {
        if (balance.compareTo(money) < 0) {
            throw new ForbiddenException(MessageConstant.BALANCE_NOT_ENOUGH);
        }
        BigDecimal rate = BigDecimal.valueOf(0.14); // TODO 这里要调用防腐层获取汇率
        balance = balance.subtract(money.getCurrency().equals(balance.getCurrency()) ?
                money : ExchangeRate.exchangeTo(rate, money, balance.getCurrency()));
    }

}
