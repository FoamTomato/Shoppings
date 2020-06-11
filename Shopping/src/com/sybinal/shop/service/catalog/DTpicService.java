package com.sybinal.shop.service.catalog;

import java.util.List;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.model.DTpic;

public interface DTpicService {

	AjaxResult setinsert(DTpic dtfzing);

	List<DTpic> selectByPicIds(String id);

}
