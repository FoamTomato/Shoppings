package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.Product;
import com.sybinal.shop.model.ProductExample;
import com.sybinal.shop.model.ProductRelation;

public interface ProductMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	long countByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int deleteByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int insert(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int insertSelective(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	List<Product> selectByExampleWithBLOBs(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	List<Product> selectByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	Product selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") Product record, @Param("example") ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int updateByPrimaryKeySelective(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table product
	 * 
	 * @mbg.generated Mon Oct 24 21:14:56 CST 2016
	 */
	int updateByPrimaryKey(Product record);

	List<ProductRelation> selectProductRelationByExample(ProductExample productExample);

	List<ProductRelation> selectProductAndImgByExample(ProductExample productExample);

	int selectCountByProductId(@Param("productId") int productId);
	
	List<Product> selectProductByCondition(Product record); 
	
	int selectCountByCondition(Product record);

	int updateQuantityByExample(@Param("record") Product record, @Param("example") ProductExample example);
	
	int updateQuantityCalculate(@Param("id") Integer id);
}