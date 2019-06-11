package com.houses.common.dto;

import java.io.Serializable;

/**
 * @Author:panshuang
 * @Data:2019/6/11 23:55
 * @Description:
 */
public class PageDto <T> implements Serializable {

    private Integer count;

    private T data;

    private Integer code = 0;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
