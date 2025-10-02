package com.nwpu.shopping.infrastructure.common.paginate;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author: feoyang
 * @date: 2024/11/8 12:51
 * @description: TODO
 */
@Data
public class PageResult<T> {
    private static final PageResult<?> EMPTY_RESULT = new PageResult<>(0L, -1, -1, Collections.emptyList());
    /**
     * 一共有多少条符合要求
     */
    private Long total = 0L;
    /**
     * 上一页的页码（如果当前是第一页，则prev返回-1，表示没有上一页）
     */
    private int prev = -1;
    /**
     * 下一页的页码（如果当前是最后一页，则next返回-1，表示没有下一页）
     */
    private int next = -1;
    /**
     * 数据列表
     */
    private List<T> list = Collections.emptyList();

    private PageResult(Long total, int prev, int next, List<T> list) {
        this.total = total;
        this.prev = prev <= 0 ? -1 : prev;
        this.next = next <= 0 ? -1 : next;
        this.list = list;
    }

    public void setPrev(int prev) {
        this.prev = prev <= 0 ? -1 : prev;
    }

    public void setNext(int next) {
        this.next = next <= 0 ? -1 : next;
    }


    @Deprecated
    public PageResult() {
    }

    @SuppressWarnings("unchecked")
    public static <T> PageResult<T> emptyResult() {
        return (PageResult<T>) EMPTY_RESULT;
    }

    public static <T, S> PageResult<T> of(Page<S> page, List<T> data) {
        
        return new PageResult<>(
                page.getTotal(),
                page.getPageNum() - 1,
                page.getPageNum() < (1.0 * page.getTotal() / page.getPageSize()) ? page.getPageNum() + 1 : -1,
                data
        );
    }

    public static <T> PageResult<T> of(Long total, int current, int pageSize, List<T> list) {
        return new PageResult<>(
                total,
                current - 1,
                current < (1.0 * total / pageSize) ? current + 1 : -1,
                list
        );
    }

    public static <T, S> PageResult<S> of(PageResult<T> page, List<S> data) {
        return new PageResult<>(
                page.getTotal(),
                page.getPrev(),
                page.getNext(),
                data
        );
    }
}

