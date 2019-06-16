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
    HouseMainInfoVo selectHouseMainInfoById(int id);

    /**
     * 保存房屋信息
     * @param houseMainInfoVo
     */
    void saveHouseMainInfo(HouseMainInfoVo houseMainInfoVo);

    /**
     * 查询房屋总数
     * @return
     */
    Integer queryHousesCount();

    /**
     * 分页查询房屋信息
     * @param houseMainInfoVo
     * @return
     */
    List<HouseMainInfoVo> queryHousesPaged(HouseMainInfoVo houseMainInfoVo);

    /**
     * 更新房屋信息
     * @param houseMainInfoVo
     */
    void updateHouseMainInfo(HouseMainInfoVo houseMainInfoVo);

    /**
     * 批量删除房屋信息
     * @param ids
     */
    void deleteHouseInfoByIds(List<Integer> ids);

}
