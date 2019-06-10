package com.houses.controller;

import com.houses.common.dto.ResultDto;
import com.houses.common.model.HouseMainInfo;
import com.houses.common.vo.HouseMainInfoVo;
import com.houses.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:panshuang
 * @Data:2019/6/9 12:57
 * @Description:
 */
@Controller
@RequestMapping(value = "/houses")
public class HouseController {

    @Autowired
    IHouseService iHouseService;

    @RequestMapping(value = "/addHouse")
    public String getHouseInfo(){
        //iHouseService.selectHouseMainInfoById(1);
        return "houseInfo/addHouseInfo.html";
    }

    @RequestMapping(value = "/saveHouseInfo")
    @ResponseBody
    public ResultDto<String> saveHouseInfo(@RequestBody HouseMainInfoVo houseMainInfoVo){
        return null;
    }
}
