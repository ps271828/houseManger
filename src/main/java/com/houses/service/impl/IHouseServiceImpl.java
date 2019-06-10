package com.houses.service.impl;

import com.houses.common.model.HouseMainInfo;
import com.houses.dao.IHouseMainInfoDao;
import com.houses.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:49
 * @Description:
 */
@Service
public class IHouseServiceImpl implements IHouseService {

    @Autowired
    IHouseMainInfoDao iHouseMainInfoDao;

    @Override
    public List<HouseMainInfo> selectHouseMainInfoById(Integer id) {
        return iHouseMainInfoDao.selectHouseMainInfoById(1);
    }
}
