package com.ricky.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/5/27
 * @className PageResponse
 * @desc 分页查询结果
 */
@Data
public class PageResponse<T> {

    private Long total; // 总条数
    private Long pages; // 总页数
    private List<T> records; // 当前页数据集合

}
