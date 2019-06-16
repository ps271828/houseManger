package com.houses.controller;

import com.houses.common.dto.PageDto;
import com.houses.common.dto.ResultDto;
import com.houses.common.model.HouseMainInfo;
import com.houses.common.vo.HouseMainInfoVo;
import com.houses.service.ICreatePDFService;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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

    private static final String TEMP_PATH = System.getProperty("java.io.tmpdir");

    private static final String IMAGE_SUFFIX = "image.jpg";

    private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    IHouseService iHouseService;

    @Autowired
    ICreatePDFService iCreatePDFService;

    @RequestMapping(value = "/addHouse")
    public String getHouseInfo() {
        return "houseInfo/addHouseInfo.html";
    }

    @RequestMapping(value = "/index")
    public String toMainPage() {
        return "index.html";
    }

    @RequestMapping(value = "/showHouses")
    public String showHouses() {
        return "houseInfo/showHouses.html";
    }

    @RequestMapping(value = "/editHouseInfo")
    public String showHouses(Integer houseId, ModelMap modelMap) {
        modelMap.addAttribute("houseId", houseId);
        return "houseInfo/editHouseInfo.html";
    }

    @RequestMapping(value = "/queryHouses")
    @ResponseBody
    public PageDto<List<HouseMainInfoVo>> queryHouses(HouseMainInfoVo houseMainInfoVo) {
        PageDto<List<HouseMainInfoVo>> pageDto;
        try {
            pageDto = iHouseService.queryHouses(houseMainInfoVo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            pageDto = new PageDto<>();
            pageDto.setCount(0);
            pageDto.setData(new ArrayList<>());
        }
        return pageDto;
    }

    @RequestMapping(value = "/saveHouseInfo")
    @ResponseBody
    public ResultDto<String> saveHouseInfo(@RequestBody HouseMainInfoVo houseMainInfoVo) {
        ResultDto<String> resultDto;
        try {
            resultDto = iHouseService.saveHouseInfo(houseMainInfoVo);
        } catch (Exception e) {
            logger.error("保存房屋信息异常！");
            e.printStackTrace();
            resultDto = new ResultDto<>(ResultDto.FAIL, "保存房屋信息异常！", null);
        }
        return resultDto;
    }

    @RequestMapping(value = "/updateHouseInfoById")
    @ResponseBody
    public ResultDto<String> updateHouseInfoById(@RequestBody HouseMainInfoVo houseMainInfoVo) {
        ResultDto<String> resultDto;
        try {
            resultDto = iHouseService.updateHouseInfoById(houseMainInfoVo);
        } catch (Exception e) {
            logger.error("更新房屋信息异常！");
            e.printStackTrace();
            resultDto = new ResultDto<>(ResultDto.FAIL, "更新房屋信息异常！", null);
        }
        return resultDto;
    }

    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public ResultDto<String> uploadImage(MultipartFile file) {
        ResultDto<String> resultDto = new ResultDto<>();
        String imagePath = TEMP_PATH + System.currentTimeMillis() + IMAGE_SUFFIX;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            imagePath = "";
        }
        resultDto = new ResultDto<>(ResultDto.SUCCESS, null, imagePath);
        return resultDto;
    }

    /**
     * 获取房屋信息
     * @param houseMainInfoVo
     * @return
     */
    @RequestMapping(value = "/getHouseInfoByHouseMainInfoVo", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<HouseMainInfoVo> getHouseInfoByHouseMainInfoVo(HouseMainInfoVo houseMainInfoVo) {
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

    /**
     * pdf下载
     * @param request
     * @param response
     * @param houseId
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/downLoadPdf")
    public void doGet(HttpServletRequest request, HttpServletResponse response, Integer houseId)
            throws ServletException, IOException {

        HouseMainInfoVo houseMainInfoVo = new HouseMainInfoVo();
        houseMainInfoVo.setId(houseId);
        ResultDto<HouseMainInfoVo> resultDto = iHouseService.getHouseInfoByHouseMainInfoVo(houseMainInfoVo);
        houseMainInfoVo = resultDto.getData();

        String path = TEMP_PATH + File.separator + System.currentTimeMillis() + ".pdf";//获取文件的相对路径
        iCreatePDFService.showHousePdf(path, houseMainInfoVo);
        //response.setHeader告诉浏览器以什么方式打开
        //假如文件名称是中文则要使用 URLEncoder.encode()编码
        //否则直接使用response.setHeader("content-disposition", "attachment;filename=" + filename);即可
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("house.pdf", "UTF-8"));

        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(path); //获取文件的流
            int len = 0;
            byte buf[] = new byte[1024];//缓存作用
            out = response.getOutputStream();//输出流
            while ((len = in.read(buf)) > 0) //切忌这后面不能加 分号 ”;“
            {
                out.write(buf, 0, len);//向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
