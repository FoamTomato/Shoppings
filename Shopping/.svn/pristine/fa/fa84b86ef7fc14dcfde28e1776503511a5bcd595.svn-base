package com.sybinal.shop.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sybinal.shop.common.ApiResponseEnum;
import com.sybinal.shop.common.ApiResponseObject;
import com.sybinal.shop.model.logistictoid;
import com.sybinal.shop.service.logistics.LogisticsService;

@RestController
@RequestMapping("api/v1")
public class FLogisticApiController  extends AbstractApiController{
	// TODO Auto-generated constructor stub
	@Autowired
	LogisticsService logis;
	
	/*
	 * 接口传输物流方式及订单编号
	 * list数组接收方式
	 */
	@RequestMapping(value = "/logistic/LogisticToId", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ApiResponseObject LogisticToId(@RequestBody List<logistictoid> list) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				logis.LogisticToId(list));
	}
}
