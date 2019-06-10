package com.houses.common.vo;

import com.houses.common.model.ItemCrack;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:panshuang
 * @Data:2019/6/9 23:23
 * @Description:
 */
public class ItemCrackVo extends ItemCrack {

    private MultipartFile exampleImageFile;

    public MultipartFile getExampleImageFile() {
        return exampleImageFile;
    }

    public void setExampleImageFile(MultipartFile exampleImageFile) {
        this.exampleImageFile = exampleImageFile;
    }
}
