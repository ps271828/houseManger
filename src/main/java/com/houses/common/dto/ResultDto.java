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
    private String code;

    /**
     * 提示信息
     */
    private String info;

    public ResultDto() {
    }

    /**
     * 返回的数据集
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultDto(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public void setResultData(String code, String info, T data){
        this.setCode(code);
        if (ResultDto.SUCCESS.equals(code)) {
            this.setData(data);
        }else {
            this.setInfo(info);
        }
    }
}
