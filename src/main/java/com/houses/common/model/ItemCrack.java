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

    private Double maxLength;

    private Double maxWidth;

    public double getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }

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
    @Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		if("1".equals(crackType)) {
//			 墙面 1--空鼓 2--龟裂 */
			sb.append("墙面出现空鼓，");
		}else if("2".equals(crackType)) {
			sb.append("墙面出现龟裂，");
		}
		if(wallDamage != null ) {
			switch(wallDamage) {
			case 1:
				sb.append("裂缝方向为斜方向，");break;
			case 2:
				sb.append("裂缝方向为竖直方向，");break;
			case 3:
				sb.append("裂缝方向为水平方向，");break;
			case 4:
				sb.append("裂缝方向为不规则方向，");break;
			default:
				break;
			}
		}
		if(maxLength != null) {
			sb.append("最大长度为" + maxLength + "毫米。");
		}
		
		if(maxWidth != null) {
			sb.append("最大宽度为" + maxWidth + "毫米。");
		}
		
		sb.append("如图");
		return sb.toString();
	}
}
