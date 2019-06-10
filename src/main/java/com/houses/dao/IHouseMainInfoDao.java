package com.houses.dao;

import com.houses.common.model.HouseMainInfo;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 13:19
 * @Description:
 */
public interface IHouseMainInfoDao {
    List<HouseMainInfo> selectHouseMainInfoById(int i);
}
