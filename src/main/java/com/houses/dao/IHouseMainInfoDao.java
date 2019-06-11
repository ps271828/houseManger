package com.houses.dao;

import com.houses.common.model.HouseMainInfo;
import com.houses.common.vo.HouseMainInfoVo;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 13:19
 * @Description:
 */
public interface IHouseMainInfoDao {
    List<HouseMainInfo> selectHouseMainInfoById(int i);

    /**
     * 保存房屋信息
     * @param houseMainInfoVo
     */
    void saveHouseMainInfo(HouseMainInfoVo houseMainInfoVo);
}
