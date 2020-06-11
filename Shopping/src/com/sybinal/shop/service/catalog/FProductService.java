package com.sybinal.shop.service.catalog;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sybinal.shop.common.PageInformation;
import com.sybinal.shop.model.Fproduct;
import com.sybinal.shop.model.Product;
import com.sybinal.shop.model.ProductOptionInfo;
import com.sybinal.shop.model.ProductRelation;
import com.sybinal.shop.model.Sku;

public interface FProductService {

	Map<String, Object> getProductByCategory(Map<String, Object> condition);

	Map<String, Object> getProductRelation(Map<String, Object> condition);

	ProductRelation getProductDetailsById(Map<String, Object> reqMap);

	List<Product> getProduct(Map<String, Object> reqMap);
	
	/**
	 * 查询商品列表
	 * @param user
	 * @return
	 */
	public Map<String, Object> getProductInfoByCondition(PageInformation pageInfo,Product product);
	
	/**
	 * 获取商品信息
	 * @param productId
	 * @return
	 */
	public Fproduct getProductByid(int productId);
	
	/**
	 * 添加商品
	 * @param user
	 * @return
	 */
	public Integer saveProductInfo(ProductRelation product);
		
	/**
	 * 根据商品ID获取商品信息
	 * @param productId
	 * @return
	 */
	public ProductRelation getProductInfoById(Integer productId);
	
	/**
	 * 根据商品ID获取商品Option页面应用
	 * @param productId
	 * @return
	 */
	List<Sku> getProductOptionInfoByProductId(Integer productId);
	
	/**
	 * 根据商品ID获取商品Option
	 * @param productId
	 * @return
	 */
	List<ProductOptionInfo> getOptionByProductId(Integer productId);


    Fproduct selectByPrimaryKey(Integer id);
    
    List<Fproduct> selectProductListAtAll(Fproduct fproduct, Integer price1, Integer price2,String updateTime1,String updat);

	List<Fproduct> selectProAll();

	int updateProduct(Fproduct fproduct);

	int deleteProduct(int id);

}
