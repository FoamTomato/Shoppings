package com.sybinal.shop.service.cate;

import java.util.List;

import com.sybinal.shop.model.Cate;

public interface CateService {
	//查询所有的分类
	public List<Cate> selectAllCate(Cate cate);
	//查询所有的类似分类
	public List<Cate> Seach_cate(Cate cate);
	/*
	 * 添加分类
	 */
	public int AddCates(Cate cates);
	/*
	 * 删除分类
	 */
	public int DelCates(Cate cates);
	/*
	 * 查询所有需要移动的分类子类
	 */
	public List<Cate>  select_modal_cate(Cate cates);
	/*
	 * 更新移动后的分类
	 */
	public int modal_cate(Cate cateF);
}
