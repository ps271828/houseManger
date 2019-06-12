package com.houses.controller;

import com.houses.common.dto.PageDto;
import com.houses.common.dto.ResultDto;
import com.houses.common.model.HouseMainInfo;
import com.houses.common.vo.HouseMainInfoVo;
import com.houses.service.IHouseService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        FileUtils.deleteQuietly(new File(TEMP_PATH));
        return "houseInfo/addHouseInfo.html";
    }

    @RequestMapping(value = "/index")
    public String toMainPage(){
        return "index.html";
    }

    @RequestMapping(value = "/showHouses")
    public String showHouses(){
        return "houseInfo/showHouses.html";
    }

    @RequestMapping(value = "/editHouseInfo")
    public String showHouses(Integer houseId, ModelMap modelMap){
        modelMap.addAttribute("houseId", houseId);
        return "houseInfo/editHouseInfo.html";
    }

    @RequestMapping(value = "/queryHouses")
    @ResponseBody
    public PageDto<List<HouseMainInfoVo>> queryHouses(HouseMainInfoVo houseMainInfoVo){
        PageDto<List<HouseMainInfoVo>> pageDto;
        try {
            pageDto = iHouseService.queryHouses(houseMainInfoVo);
        }catch (Exception e){
            logger.error(e.getMessage());
            pageDto = new PageDto<>();
            pageDto.setCount(0);
            pageDto.setData(new ArrayList<>());
        }
        return pageDto;
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

    @RequestMapping(value = "/getHouseInfoByHouseMainInfoVo")
    @ResponseBody
    public ResultDto<HouseMainInfoVo> getHouseInfoByHouseMainInfoVo(HouseMainInfoVo houseMainInfoVo){
        ResultDto<HouseMainInfoVo> resultDto = new ResultDto<>();
        try {
            resultDto = iHouseService.getHouseInfoByHouseMainInfoVo(houseMainInfoVo);
        } catch (Exception e) {
            logger.error("获取房屋信息异常！");
            e.printStackTrace();
            resultDto.setResultData(ResultDto.FAIL, "获取房屋信息异常！", null);
        }
        return resultDto;
    }
}
