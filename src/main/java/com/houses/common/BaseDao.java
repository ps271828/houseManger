package com.houses.common;

import java.io.Serializable;

/**
 * @Author:panshuang
 * @Data:2019/6/11 23:53
 * @Description:
 */
public class BaseDao implements Serializable {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 大小
     */
    private Integer limit;

    /**
     * 开始页
     */
    private Integer start;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
