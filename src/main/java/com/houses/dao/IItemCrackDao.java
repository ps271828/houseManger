package com.houses.dao;

import com.houses.common.vo.ItemCrackVo;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:47
 * @Description:
 */
public interface IItemCrackDao {

    void batchSaveItemCrack(List<ItemCrackVo> itemCrackVoList);
}
