package com.sybinal.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.hjBase;

public interface hjBaseMapper {
    int deleteByPrimaryKey(@Param("id") Integer id,@Param("hjStandy9") String s );

    int insert(hjBase record);

    int insertSelective(hjBase record);
    
    int insertSelective2(hjBase record);

    hjBase selectByPrimaryKey(@Param("id") Integer id,@Param("hjStandy9") String s);

    int updateByPrimaryKeySelective(hjBase record);

    int updateByPrimaryKey(hjBase record);

	List<hjBase>  selHJLogistics(hjBase hjBases);

	List<hjBase> selectHjLogice(@Param("id")String id,@Param("hjStandy9")String hjStandy9,@Param("fids") String fids);

	hjBase ckHJLogistics(Integer id);

	int delHJLogistics(hjBase hjBases);

	int defaultHj0(@Param("hjStandy2") String hjStandy2,@Param("hjStandy9") String s);
	
	int defaultHj1(@Param("id") Integer id,@Param("hjStandy9") String s);

	hjBase selectresult(@Param("getfIds") String getfIds,@Param("hjStandy9") String s);

	String setLableKey(@Param("doPost") String doPost,@Param("vID")  String vID,@Param("hjStandy9") String s);

	int insertLableKey(@Param("ids") String id,@Param("labs") String lab,@Param("num1") String num1,@Param("hjStandy9") String s);//,@Param("num2") String num2
 
	List<hjBase> selectallOrid(@Param("lableKeyList") List<String> strings,@Param("hjStandy9") String s);

	List<hjBase> selHJLogistics2(String spili);

	hjBase selalls(@Param("getfIds")String getfIds,@Param("hjStandy9") String s);

	hjBase selalls2(@Param("getfIds")String getfIds,@Param("hjStandy9") String s);
	
	List<hjBase> getNoList(@Param("strings") List<String> strings,@Param("hjStandy9") String s);

	hjBase selectByPrimaryKeyse(@Param("fids") String fids,@Param("hjStandy9") String s);

	int updata(@Param("referenceNo")String referenceNo,@Param("trackNum1") String trackNum1,@Param("hjStandy9") String s);

	void addStandy12(@Param("referenceNo") String hjStandy12);

	List<hjBase> seljps(@Param("jplist")List<String> string2s,@Param("username") String standby9);
	/**
	 * 修改集拼
	 * @param states
	 * @return
	 */
	int jps(@Param("states")String states,@Param("ids")String ids);
	/**
	 * 查询环金
	 * @param trackingSingleSign 跟踪号
	 * @return
	 */
	hjBase selres(@Param("states")String trackingSingleSign);
	/**
	 * 根据系统单号查询
	 * @param parseInt
	 * @return List<hjBase>
	 */
	List<hjBase> logticslist(int parseInt);
	/**
	 * 批量添加环金
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:15:52   
	* @version
	 */
	int insertSelective2Plus(@Param("hjList") List<hjBase> hjList);
	/**
	 * 批量修改默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:23:37   
	* @version
	 */
	int defaultHj0Plus(@Param("hjList")List<hjBase> hjList,@Param("hjStandy9") String standby1);
	/**
	 * 批量根据系统单号查询订单信息
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月19日 下午2:05:58   
	* @version
	 */
	List<hjBase> BaseList(@Param("Baselist")List<String> map,@Param("username") String username);
	/**
	 * 批量添加标签
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月19日 下午6:06:55   
	* @version
	 */
	int insertLableKeyList(@Param("listse")List<Map<String,Object>> list, @Param("username")String username);
	/**
	 * 批量修改
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月20日 下午3:28:23   
	* @version
	 */
	int updataList(@Param("Liste")List<Map<String, Object>> map,@Param("username") String standby1);

}