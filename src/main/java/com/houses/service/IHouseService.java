package com.houses.service;

import com.houses.common.model.HouseMainInfo;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:48
 * @Description:
 */
public interface IHouseService {

    List<HouseMainInfo> selectHouseMainInfoById(Integer id);
}
