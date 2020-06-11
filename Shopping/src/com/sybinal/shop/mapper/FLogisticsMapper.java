package com.sybinal.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.FLogistics;
import com.sybinal.shop.model.hjBase;


public interface FLogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FLogistics record);

    int insertSelective(FLogistics record); 

    FLogistics selectByPrimaryKey(FLogistics id);
    /**
     * 查询单个未删除的
    *    所谓的右侧详情列表
    * 项目名称：Shopping   
    * 类描述：   
    * 创建人：PC1   
    * 创建时间：2020年3月12日 下午2:20:02   
    * @version
     */
    FLogistics selectOnes(FLogistics id);

    FLogistics selectByPrimaryKey2(FLogistics id);
    
    List<FLogistics> SelectIdsForm(Integer id);

    int updateByPrimaryKeySelective(FLogistics record);

    int updateByPrimaryKey(FLogistics record);
    /**
     * 查询订单
     * @user: spor-wen10
     * @Title: selectAll  
     * @Description: TODO
     * @date: 2020年2月20日 下午12:27:07
     * @param: @param map
     * @param: @return    参数  
     * @return: List<FLogistics>    返回类型  
     * @throws
     */
	List<FLogistics> selectAll(@Param("list") Map<String, String> map,@Param("content") List<String> strings);

	List<FLogistics> selStatusOrder(@Param("flog")FLogistics fLogistics,@Param("let") List<String> strings,@Param("uks") String uks);
	
    int insertAll(@Param("FLogistics") List<FLogistics> record);

	int updateStatues(@Param("strings")List<String> strings);

	FLogistics selectIDs(@Param("id") String id,@Param("username")  String username);

	int postLogistics(@Param("id")String id,@Param("username")String username);

	int insertLableKey(@Param("ids") String id,@Param("labs") String lab,@Param("num1") String num1,@Param("method") String method,@Param("username") String username);//,@Param("num2") String num2

	int updatajp(@Param("jpResult") String jpResult,@Param("updatajp") String updatajp,@Param("nos") String nos,@Param("username") String username);

	int batdel(@Param("lit") List<String> strings);

	void addWeight(@Param("wei")  String hjInvoiceweight,@Param("iid")  String string,@Param("nos") String nos,@Param("username") String username);

	/**
	 * 批量添加重量
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:34:46   
	* @version
	 */
	int addWeightPlus(@Param("hjList")List<hjBase> hjList, @Param("username")String standby1);
	
	int updataCn(@Param("referenceNo")String referenceNo,@Param("trackNum1") String trackNum1);
 
	void updateStatue2s(@Param("string2s")List<String> string2s,@Param("username")String  id);
 
	int defaultsLO(@Param("valueOf")String valueOf,@Param("username")String username,@Param("username2") String username2);
	/**
	 * 修改集拼
	 * @param states
	 * @return
	 */
	int jps(@Param("states")String states,@Param("ids")String ids);
	/**
	 * 批量查找id
	 * @param strings
	 * @return
	 */
	List<FLogistics> selectListid(@Param("lit")List<String> strings);
	/**
	 * 根据亚马逊编号查找
	 * @param postman
	 * @return
	 */
	List<FLogistics> stream(@Param("postman") String postman);
	/**
	 * 根据邮箱查找
	 * @param replace
	 * @return
	 */
	List<FLogistics> streamEmail(@Param("emails") String replace);
	/**
	 * 更改义达
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月6日 下午5:58:45   
	* @version
	 * @param integer 
	 * @param standy7 
	 */
	void defaulData(@Param("hjStandy1s")String hjStandy1, @Param("usernames")String username, @Param("cntegers")String cnteger,@Param("standy7s") String standy7,@Param("standy8s") String standy8s);
	/**
	 * 查找运单
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月9日 下午5:47:26   
	* @version
	 */
	List<String> selectIDList(@Param("list")List<String> list);
	/**
	 * 批量添加标签
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:33:30   
	* @version
	 */
	int insertLableKeyList(@Param("lists")List<Map<String,Object>> list, @Param("username")String username);
	/**
	 * 批量添加订单
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月20日 下午3:34:17   
	* @version
	 */
	int updataCnList(@Param("Lists")List<Map<String, Object>> map);
	/**
	 * 批量修改状态
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月21日 上午10:58:29   
	* @version
	 */
	int postLogisticsList(@Param("hjBases")List<String> hjBases, String standby1);
 
}