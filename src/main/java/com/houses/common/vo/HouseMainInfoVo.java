package com.houses.common.vo;

import com.houses.common.model.HouseMainInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 23:24
 * @Description:
 */
public class HouseMainInfoVo extends HouseMainInfo {

    private List<HouseItemVo> houseItemVoList;

    public List<HouseItemVo> getHouseItemVoList() {
        return houseItemVoList;
    }

    public void setHouseItemVoList(List<HouseItemVo> houseItemVoList) {
        this.houseItemVoList = houseItemVoList;
    }
}
