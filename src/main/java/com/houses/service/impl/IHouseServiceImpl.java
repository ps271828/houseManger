package com.houses.service.impl;

import com.houses.common.dto.PageDto;
import com.houses.common.dto.ResultDto;
import com.houses.common.model.HouseItem;
import com.houses.common.model.HouseMainInfo;
import com.houses.common.vo.HouseItemVo;
import com.houses.common.vo.HouseMainInfoVo;
import com.houses.common.vo.ItemCrackVo;
import com.houses.dao.IHouseItemDao;
import com.houses.dao.IHouseMainInfoDao;
import com.houses.dao.IItemCrackDao;
import com.houses.service.IHouseService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    IItemCrackDao iItemCrackDao;

    private static final String TEMP_PATH = System.getProperty("java.io.tmpdir") + File.separator + "temp" + File.separator;

    @Value("${house.config.upload}")
    private String UPLOAD_PATH;

    @Autowired
    IHouseItemDao iHouseItemDao;

    @Override
    public List<HouseMainInfo> selectHouseMainInfoById(Integer id) {
        // return iHouseMainInfoDao.selectHouseMainInfoById(1);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto<String> saveHouseInfo(HouseMainInfoVo houseMainInfoVo) {
        ResultDto<String> resultDto = new ResultDto<>();

        //保存房屋信息
        iHouseMainInfoDao.saveHouseMainInfo(houseMainInfoVo);

        //拼接id到构件项
        List<HouseItemVo> itemVoList = new ArrayList<>();
        for (HouseItemVo currItem : houseMainInfoVo.getHouseItemVoList()) {
            currItem.setHouseId(houseMainInfoVo.getId());
            File fullImage = new File(currItem.getFullItemExampleImage());
            try {
                FileUtils.copyFile(fullImage, new File(UPLOAD_PATH + File.separator + fullImage.getName()));
                FileUtils.deleteQuietly(fullImage);
                currItem.setFullItemExampleImage(UPLOAD_PATH + File.separator + fullImage.getName());
            } catch (IOException e) {
                e.printStackTrace();
                resultDto.setResultData(ResultDto.FAIL, "保存全景图失败！", null);
                return resultDto;
            }
            itemVoList.add(currItem);
        }

        //保存构件信息
        if (!CollectionUtils.isEmpty(itemVoList)) {
            iHouseItemDao.batchSaveHouseItem(itemVoList);
        }

        //拼装构件id到裂缝想中
        List<ItemCrackVo> itemCrackVoList = new ArrayList<>();
        for (HouseItemVo currItem : itemVoList) {
            for (ItemCrackVo currCrack : currItem.getItemCrackVoList()) {
                currCrack.setItemId(currItem.getId());
                File exampleImage = new File(currCrack.getExampleImage());
                try {
                    FileUtils.copyFile(exampleImage, new File(UPLOAD_PATH + File.separator + exampleImage.getName()));
                    FileUtils.deleteQuietly(exampleImage);
                    currCrack.setExampleImage(UPLOAD_PATH + File.separator + exampleImage.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                    resultDto.setResultData(ResultDto.FAIL, "保存示意图失败！", null);
                    return resultDto;
                }
            }
            itemCrackVoList.addAll(currItem.getItemCrackVoList());
        }

        //保存裂缝项
        if (!CollectionUtils.isEmpty(itemCrackVoList)) {
            iItemCrackDao.batchSaveItemCrack(itemCrackVoList);
        }

        resultDto.setResultData(ResultDto.SUCCESS, null, "保存成功！");
        return resultDto;
    }

    @Override
    public PageDto<List<HouseMainInfoVo>> queryHouses(HouseMainInfoVo houseMainInfoVo) {
        PageDto<List<HouseMainInfoVo>> pageDto = new PageDto<>();

        Integer count = iHouseMainInfoDao.queryHousesCount();

        houseMainInfoVo.setStart((houseMainInfoVo.getPage() - 1) * houseMainInfoVo.getLimit());

        List<HouseMainInfoVo> houseMainInfoVoList = iHouseMainInfoDao.queryHousesPaged(houseMainInfoVo);

        pageDto.setCount(count);
        pageDto.setData(houseMainInfoVoList);
        return pageDto;
    }

    @Override
    public ResultDto<HouseMainInfoVo> getHouseInfoByHouseMainInfoVo(HouseMainInfoVo houseMainInfoVo) {
        ResultDto<HouseMainInfoVo> resultDto = new ResultDto<>();

        //先根据id获取主要信息
        houseMainInfoVo = iHouseMainInfoDao.selectHouseMainInfoById(houseMainInfoVo.getId());

        //再获取构件项信息
        List<HouseItemVo> houseItemVoList = iHouseItemDao.queryItemById(houseMainInfoVo.getId());

        //根据构件项集合获取所有裂缝项
        List<Integer> idList = new ArrayList<>();
        houseItemVoList.forEach(currItem -> idList.add(currItem.getId()));

        if (!CollectionUtils.isEmpty(idList)) {
            List<ItemCrackVo> itemCrackVoList = iItemCrackDao.queryCrackListByIdList(idList);
            //拼接数据
            for (HouseItemVo currItem : houseItemVoList) {
                List<ItemCrackVo> crackVoList = new ArrayList<>();
                for (ItemCrackVo currCrack : itemCrackVoList) {
                    if (currItem.getId() == currCrack.getItemId()) {
                        crackVoList.add(currCrack);
                    }
                }
                currItem.setItemCrackVoList(crackVoList);
            }
        }

        houseMainInfoVo.setHouseItemVoList(houseItemVoList);
        resultDto.setResultData(ResultDto.SUCCESS, null, houseMainInfoVo);
        return resultDto;
    }
}
