package com.ricky.dto.command;

import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/28
 * @className ListItemCommand
 * @desc
 */
@Data
public class ListItemCommand<T> implements Command {

    private Integer index;
    private T entity;

}
