package com.sybinal.shop.service.order;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sybinal.shop.common.PageInformation;
import com.sybinal.shop.model.Logistics;
import com.sybinal.shop.model.Order;
import com.sybinal.shop.model.OrderHistory;
import com.sybinal.shop.model.User;

public interface OrderService {
	/**
	 * 保存订单及购物车
	 * @param order 订单对象
	 */
	public void saveOrder(Order order) ;
	/**
	 * 修改订单及购物车
	 * @param order 订单对象
	 */
	public void modOrder(Order order);
	/**
	 * 删除订单或购物车
	 * @param order 订单对象
	 */
	public void delOrder(Order order);
	/**
	 * 删除全部
	 * @param order 订单对象
	 */
	public void delAllOrder(Order order);
	/**
	 * 获取订单
	 * @param order 订单对象
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> getOrder(Order order) throws ParseException;
	/**
	 * 获取详细信息
	 * @param order 订单对象
	 * @return
	 */
	public Order getOrderItem(Order order);
	/**
	 * 获取购物车数量
	 * @param user 用户对象
	 * @return
	 */
	public Integer getOrderCount(User user);
	/**
	 * 支付订单
	 * @param order 订单对象
	 */
	public void savePaymentHistory(Map<String,Object> mapReq);
	/**
	 * 改变购物车状态 (预留)
	 * @param order
	 * @return
	 */
	public String changeOrder(Order order);

	public Map<String, Object> getOrderManage(PageInformation pageInfo,Order order) throws Exception;
	
	
	public Map<String, Object> getOrderHistoryManage(PageInformation pageInfo) throws Exception;
	/**
	 * 根据用户id获取订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Order getOrderByUserId(Integer userId) throws Exception;
	/**
	 * 获取详细信息
	 * @param order
	 * @return
	 */
	public Order getOrderItemData(Order order);
	
	public List<OrderHistory> getOrderHistoryManage(Order order);
	
	public void saveLogistics(Logistics logistics);
	
}