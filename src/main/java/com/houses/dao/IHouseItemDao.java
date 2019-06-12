package com.houses.dao;

import com.houses.common.vo.HouseItemVo;
import org.apache.ibatis.annotations.Param;

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
}
