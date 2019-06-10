package com.houses.common.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author:panshuang
 * @Data:2019/6/9 23:09
 * @Description:
 */
public class ResultDto <T> implements Serializable {

    /**
     * 成功结果码
     */
    public static final String SUCCESS = "000000";

    /**
     * 失败结果码
     */
    public static final String FAIL = "000000";

    /**
     * 状态码
     */
    @Setter
    @Getter
    private String code;

    /**
     * 提示信息
     */
    @Setter
    @Getter
    private String info;

    /**
     * 返回的数据集
     */
    @Setter
    @Getter
    private T data;
}
