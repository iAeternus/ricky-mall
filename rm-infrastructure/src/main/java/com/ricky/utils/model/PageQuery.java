package com.ricky.utils.model;

import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/5/27
 * @className PageQuery
 * @desc 分页查询实体
 */
@Data
public class PageQuery {

    private Integer pageNo = 1; // 页码
    private Integer pageSize = 20; // 每页记录数
    private String sortBy; // 排序字段
    private Boolean isAsc = true; // 是否升序

}
