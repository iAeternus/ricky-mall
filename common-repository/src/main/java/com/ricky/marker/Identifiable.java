package com.ricky.marker;

/**
 * @author Ricky
 * @version 2.0
 * @date 2024/7/15
 * @className Identifiable
 * @desc 标识符id需要实现的方法
 */
public interface Identifiable<ID extends Identifier> {

    String ID = "id";

    ID getId();

    void setId(ID id);

}