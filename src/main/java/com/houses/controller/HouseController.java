package com.houses.controller;

import com.houses.common.dto.ResultDto;
import com.houses.common.model.HouseMainInfo;
import com.houses.common.vo.HouseMainInfoVo;
import com.houses.service.IHouseService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:57
 * @Description:
 */
@Controller
@RequestMapping(value = "/houses")
public class HouseController {

    private static final String TEMP_PATH = System.getProperty("java.io.tmpdir") + File.separator + "temp" + File.separator;

    private static final String IMAGE_SUFFIX = "image.jpg";

    private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    IHouseService iHouseService;

    @RequestMapping(value = "/addHouse")
    public String getHouseInfo(){
        //iHouseService.selectHouseMainInfoById(1);
        return "houseInfo/addHouseInfo.html";
    }

    @RequestMapping(value = "/saveHouseInfo")
    @ResponseBody
    public ResultDto<String> saveHouseInfo(@RequestBody  HouseMainInfoVo houseMainInfoVo){
        ResultDto<String> resultDto;
        try{
            resultDto = iHouseService.saveHouseInfo(houseMainInfoVo);
        }catch (Exception e){
            logger.error("保存房屋信息异常！");
            e.printStackTrace();
            resultDto = new ResultDto<>(ResultDto.FAIL, "保存房屋信息异常！" , null);
        }
        return resultDto;
    }

    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public ResultDto<String> uploadImage(MultipartFile file){
        ResultDto<String> resultDto = new ResultDto<>();
        String imagePath = TEMP_PATH + System.currentTimeMillis() + IMAGE_SUFFIX;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            imagePath = "";
        }
        resultDto = new ResultDto<>(ResultDto.SUCCESS, null , imagePath);
        return resultDto;
    }
}
