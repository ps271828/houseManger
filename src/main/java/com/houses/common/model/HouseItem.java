package com.houses.common.model;

import com.houses.common.BaseDao;
import lombok.Data;

/**
 * @Author:panshuang
 * @Data:2019/6/9 23:16
 * @Description:
 */
public class HouseItem extends BaseDao {

    private Integer id;

    private Integer houseId;

    private String itemSerial;

    private Integer itemDirection;

    private String itemLocation;

    private String fullItemExampleImage;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getItemSerial() {
        return itemSerial;
    }

    public void setItemSerial(String itemSerial) {
        this.itemSerial = itemSerial;
    }

    public Integer getItemDirection() {
        return itemDirection;
    }

    public void setItemDirection(Integer itemDirection) {
        this.itemDirection = itemDirection;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String getFullItemExampleImage() {
        return fullItemExampleImage;
    }

    public void setFullItemExampleImage(String fullItemExampleImage) {
        this.fullItemExampleImage = fullItemExampleImage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
