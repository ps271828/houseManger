package com.houses.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author:panshuang
 * @Data:2019/6/9 13:01
 * @Description:
 */
public class HouseMainInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 门牌号
     */
    private String houseNum;

    /**
     * 检测日期
     */
    private Long checkDate;

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    /**
     * 户主姓名
     */
    private String masterName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public Long getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Long checkDate) {
        this.checkDate = checkDate;
    }
}
