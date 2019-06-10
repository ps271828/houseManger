package com.houses.common.model;

import lombok.Data;

/**
 * @Author:panshuang
 * @Data:2019/6/9 23:19
 * @Description:
 */
public class ItemCrack {

    private Integer id;

    private Integer itemId;

    private Integer crackTyppe;

    private Integer wallDamage;

    private double maxLength;

    private String exampleImage;

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
        return crackTyppe;
    }

    public void setCrackTyppe(Integer crackTyppe) {
        this.crackTyppe = crackTyppe;
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
