package com.sybinal.shop.service.catalog;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sybinal.shop.common.PageInformation;
import com.sybinal.shop.mapper.FproductMapper;
import com.sybinal.shop.mapper.ProductCategoryMapper;
import com.sybinal.shop.model.Fproduct;
import com.sybinal.shop.model.Product;
import com.sybinal.shop.model.ProductCategory;
import com.sybinal.shop.model.ProductOptionInfo;
import com.sybinal.shop.model.ProductRelation;
import com.sybinal.shop.model.Sku;
import com.sybinal.shop.utils.PagingTool;
import com.sybinal.shop.utils.UserUtils;

@Service
public class FProductServiceImpl implements FProductService {
	@Autowired
	FproductMapper fproductMapper;
	
	@Autowired
	PagingTool pagingTool;
	@Override
	public Map<String, Object> getProductByCategory(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProductRelation(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductRelation getProductDetailsById(Map<String, Object> reqMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProduct(Map<String, Object> reqMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProductInfoByCondition(PageInformation pageInfo, Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fproduct getProductByid(int productId) {
		// TODO Auto-generated method stub
		return fproductMapper.selectByPrimaryKey(productId);
	}

	@Override
	public Integer saveProductInfo(ProductRelation product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductRelation getProductInfoById(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sku> getProductOptionInfoByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOptionInfo> getOptionByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fproduct selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return fproductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Fproduct> selectProductListAtAll(Fproduct fproduct, Integer price1, Integer price2,String updateTime1,String updateTime2) {
		// TODO Auto-generated method stub
		return fproductMapper.selectProductListAtAll(fproduct,price1,price2,updateTime1,updateTime2);
	}
	@Override
	public List<Fproduct> selectProAll(){
		return fproductMapper.selectProAll();
	 }

	@Override
	public int updateProduct(Fproduct fproduct) {
		// TODO Auto-generated method stub
		return fproductMapper.updateByPrimaryKeySelective(fproduct);
	}
	
	@Override
	public int deleteProduct(int id) {
		// TODO Auto-generated method stub
		return fproductMapper.deleteByPrimaryKey(id);
	}
}
