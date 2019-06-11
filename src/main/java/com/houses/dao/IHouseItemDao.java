package com.houses.dao;

import com.houses.common.vo.HouseItemVo;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:46
 * @Description:
 */
public interface IHouseItemDao {

    void batchSaveHouseItem(List<HouseItemVo> houseItemVoList);
}
