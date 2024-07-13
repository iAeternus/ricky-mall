package com.ricky.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.ricky.utils.model.PageQuery;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/5/27
 * @className MpPageUtils
 * @desc mybatis-plus分页工具类
 */
public class MpPageUtils {

    private MpPageUtils() {
    }

    /**
     * 根据查询完毕的Page对象构建分页结果
     *
     * @param page      mp 分页对象
     * @param convertor 对象转换的逻辑
     * @return 返回分页结果DTO
     */
    public static <PO, VO> PageDTO<VO> toPageDTO(Page<PO> page, Function<PO, VO> convertor) {
        PageDTO<VO> pageDTO = new PageDTO<>();
        // 获取总条数
        pageDTO.setTotal(page.getTotal());
        // 获取总页数
        pageDTO.setPages(page.getPages());
        // 获取当前页数据
        List<PO> records = page.getRecords();
        if (CollUtil.isEmpty(records)) {
            pageDTO.setRecords(Collections.emptyList());
            return pageDTO;
        }
        pageDTO.setRecords(records.stream().map(convertor).collect(Collectors.toList()));
        return pageDTO;
    }

    /**
     * 使用字段拷贝构建分页结果
     * 注意PO与VO的字段名必须是一样的
     *
     * @param page  mp 分页对象
     * @param clazz 目标VO的字节码
     * @return 分页查询结果
     */
    public static <PO, VO> PageDTO<VO> toPageDTO(Page<PO> page, Class<VO> clazz) {
        return MpPageUtils.toPageDTO(page, po -> BeanUtil.copyProperties(po, clazz));
    }

    /**
     * 构建分页和排序条件
     *
     * @param query 查询条件，可以是PageQuery的派生类
     * @param items 排序条件
     * @return 返回封装好的mp的Page对象
     */
    public static <T> Page<T> toMpPage(PageQuery query, OrderItem... items) {
        // 构建分页条件
        Page<T> page = Page.of(query.getPageNo(), query.getPageSize());
        // 排序条件
        if (StrUtil.isNotBlank(query.getSortBy())) {
            // 不为空
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(query.getSortBy());
            orderItem.setAsc(query.getIsAsc());
            page.addOrder(orderItem);
        } else if (items != null) {
            // 为空，默认排序
            page.addOrder(items);
        }
        return page;
    }

    /**
     * 构建分页和排序条件-单排序条件
     */
    public static <T> Page<T> toMpPage(PageQuery query, String defaultSortBy, Boolean defaultAsc) {
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn(defaultSortBy);
        orderItem.setAsc(defaultAsc);
        return toMpPage(query, orderItem);
    }

    /**
     * 构建分页和排序条件-默认按创建时间降序排列
     */
    public static <T> Page<T> toMpPageDefaultSortByCreateTime(PageQuery query) {
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
        return toMpPage(query, orderItem);
    }

    /**
     * 构建分页和排序条件-默认按修改时间降序排列
     */
    public static <T> Page<T> toMpPageDefaultSortByUpdateTime(PageQuery query) {
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("update_time");
        orderItem.setAsc(false);
        return toMpPage(query, orderItem);
    }

}
