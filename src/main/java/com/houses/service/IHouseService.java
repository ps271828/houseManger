package com.houses.service;

import com.houses.common.dto.PageDto;
import com.houses.common.dto.ResultDto;
import com.houses.common.model.HouseMainInfo;
import com.houses.common.vo.HouseMainInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:48
 * @Description:
 */
public interface IHouseService {

    List<HouseMainInfo> selectHouseMainInfoById(Integer id);

    /**
     * 保存房屋信息
     * @param houseMainInfoVo
     * @return
     */
    ResultDto<String> saveHouseInfo(HouseMainInfoVo houseMainInfoVo);

    /**
     * 分页查询房屋信息
     * @param houseMainInfoVo
     * @return
     */
    PageDto<List<HouseMainInfoVo>> queryHouses(HouseMainInfoVo houseMainInfoVo);
}
