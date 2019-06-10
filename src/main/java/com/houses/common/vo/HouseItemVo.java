package com.houses.common.vo;

import com.houses.common.model.HouseItem;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author:panshuang
 * @Data:2019/6/9 23:21
 * @Description:
 */
public class HouseItemVo extends HouseItem {

    private MultipartFile fullExampleImge;

    private List<ItemCrackVo> itemCrackVoList;

    public List<ItemCrackVo> getItemCrackVoList() {
        return itemCrackVoList;
    }

    public void setItemCrackVoList(List<ItemCrackVo> itemCrackVoList) {
        this.itemCrackVoList = itemCrackVoList;
    }

    public MultipartFile getFullExampleImge() {

        return fullExampleImge;
    }

    public void setFullExampleImge(MultipartFile fullExampleImge) {
        this.fullExampleImge = fullExampleImge;
    }
}
