package com.houses.common.model;

import com.houses.common.BaseDao;
import lombok.Data;

/**
 * @Author:panshuang
 * @Data:2019/6/9 23:19
 * @Description:
 */
public class ItemCrack extends BaseDao {

    private Integer id;

    private Integer itemId;

    private Integer crackType;

    private Integer wallDamage;

    private double maxLength;

    private String exampleImage;

    private String crackDirection;

    public Integer getCrackType() {
        return crackType;
    }

    public void setCrackType(Integer crackType) {
        this.crackType = crackType;
    }

    public String getCrackDirection() {
        return crackDirection;
    }

    public void setCrackDirection(String crackDirection) {
        this.crackDirection = crackDirection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCrackTyppe() {
        return crackType;
    }

    public void setCrackTyppe(Integer crackType) {
        this.crackType = crackType;
    }

    public Integer getWallDamage() {
        return wallDamage;
    }

    public void setWallDamage(Integer wallDamage) {
        this.wallDamage = wallDamage;
    }

    public double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    public String getExampleImage() {
        return exampleImage;
    }

    public void setExampleImage(String exampleImage) {
        this.exampleImage = exampleImage;
    }
}
