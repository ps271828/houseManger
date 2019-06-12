package com.houses.dao;

import com.houses.common.vo.ItemCrackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:47
 * @Description:
 */
public interface IItemCrackDao {

    void batchSaveItemCrack(List<ItemCrackVo> itemCrackVoList);

    /**
     * 根据构件项id集合查找对应的裂缝项
     * @param idList
     * @return
     */
    List<ItemCrackVo> queryCrackListByIdList(List<Integer> idList);
}
