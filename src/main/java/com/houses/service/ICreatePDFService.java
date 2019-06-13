package com.houses.service;

import com.houses.common.vo.HouseMainInfoVo;

public interface ICreatePDFService {
	public void showHousePdf(String path,String projectName,String doorNo,String date,String name,HouseMainInfoVo houseMainInfoVo);
}
