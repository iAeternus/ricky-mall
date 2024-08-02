package com.ricky.persistence.converter.decorator;

import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className ForeignKeyDecorator
 * @desc
 */
@Service
public class ForeignKeyDecorator {

    public Long map(Serializable id) {
        return Long.valueOf(id.toString());
    }

}
