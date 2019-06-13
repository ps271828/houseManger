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
    /**构件序号*/
    private String itemSerial;
    /**构件方向   1--东  2--南  3--西  4--北*/
    private Integer itemDirection;
    /**构件位置   1--墙面  2--天棚  3--地面 */
    private String itemLocation;
    /**全景图*/
    private String fullItemExampleImage;
    /**备注*/
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
    
    @Override
	public String toString() {
    	StringBuffer sb = new StringBuffer();
    	/*
//    	 *   private String itemSerial;
//    /**构件方向   1--东  2--南  3--西  4--北*/
//    private Integer itemDirection;
//    /**构件位置   1--墙面  2--天棚  3--地面 */
//    private String itemLocation;
//    	 */
    	sb.append(itemSerial).append(":");
    	switch (itemDirection) {
    	case 0:
    		sb.append("构件方向为东方向，");break;
    	case 1:
    		sb.append("构件方向为南方向，");break;
    	case 2:
    		sb.append("构件方向为西方向，");break;
    	case 3:
    		sb.append("构件方向为北方向，");break;
    	}
    	
    	Integer itemLocationVal = Integer.valueOf(itemLocation);
    	switch (itemLocationVal) {
    	case 0:
    		sb.append("构件位置为墙面，");break;
    	case 1:
    		sb.append("构件位置为天棚，");break;
    	case 2:
    		sb.append("构件位置为地面，");break;
    	}
    	sb.append("具体见影像资料。");
		return sb.toString();
	}
}
