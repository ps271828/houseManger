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

    /**
     * 获取房屋构件项
     * @param id
     * @return
     */
    List<HouseItemVo> queryItemById(Integer id);

    /**
     * 根据id删除原先的构件信息
     * @param id
     */
    void deteteItemInfoByHouseId(List<Integer> id);

    /**
     * 根据房屋id集合查询对应的构件信息
     * @param houseIds
     * @return
     */
    List<HouseItemVo> queryItemByHouseIds(List<Integer> houseIds);
}
