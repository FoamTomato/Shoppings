package com.sybinal.shop.service.logistics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.common.Calculations;
import com.sybinal.shop.controller.admin.FLogisticsController;
import com.sybinal.shop.mapper.AstrictMapper;
import com.sybinal.shop.mapper.UserMapper;
import com.sybinal.shop.mapper.hjBaseMapper;
import com.sybinal.shop.model.Astrict;
import com.sybinal.shop.model.hjBase;

@Service
public class hjServiceImpl implements hjService {
	@Autowired
	hjBaseMapper hjBasesMapper;
	
	@Autowired
	UserMapper userService;

	@Autowired
	AstrictMapper astrictMapper;
	
	private static Logger logger= Logger.getLogger(hjServiceImpl.class);
	@Override
	public int addHJLogistics(hjBase hjBases) {
		// TODO Auto-generated method stub
		/*try {
			hjBases.setHjStandy14(Calculations.los(hjBases.getHjInvoiceweight(),hjBases.getHjCountrycode(), hjBases.getHjShippingmethod()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return hjBasesMapper.insertSelective(hjBases);
	}

	@Override
	public int insertSelective2(hjBase hjBases) {
		// TODO Auto-generated method stub
		try {
			hjBases.setHjStandy14(Calculations.los(hjBases.getHjInvoiceweight(),hjBases.getHjCountrycode(), hjBases.getHjShippingmethod()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hjBasesMapper.insertSelective2(hjBases);
	}

	@Override
	public  List<hjBase>  selHJLogistics(hjBase hjBases) {
		// TODO Auto-generated method stub
		return hjBasesMapper.selHJLogistics(hjBases);
	}

	@Override
	public List<hjBase> selectHjLogice(String id,String username,String fids) {
		// TODO Auto-generated method stub
		return hjBasesMapper.selectHjLogice(id,username,fids);
	}

	@Override
	public hjBase ckHJLogistics(hjBase hjBases) {
		// TODO Auto-generated method stub
		return hjBasesMapper.selectByPrimaryKey(hjBases.getId(),hjBases.getHjStandy9());
	}

	@Override
	public int editHJLogistics(hjBase hjBases) {
		// TODO Auto-generated method stub
		return hjBasesMapper.updateByPrimaryKeySelective(hjBases);
	}

	@Override
	public int delHJLogistics(hjBase hjBases) {
		// TODO Auto-generated method stub 
		return hjBasesMapper.deleteByPrimaryKey(hjBases.getId(),hjBases.getHjStandy9());
	}

	/**
	 * 批量添加默认
	 */
	@Override
	public int defaultHj0Plus(List<hjBase> hjList) {
		// TODO Auto-generated method stub
		return hjBasesMapper.defaultHj0Plus(hjList,userService.Justiactions(FLogisticsController.username()).getStandby1());
	}
	@Override
	public int defaultHj1(Integer id) {
		// TODO Auto-generated method stub

		String usernames=userService.Justiactions(FLogisticsController.username()).getStandby1();
		return hjBasesMapper.defaultHj1(id,usernames);
	}
	/*
	 * (non-Javadoc)储存标签
	 * @see com.sybinal.shop.service.logistics.hjService#setLableKey(java.lang.String)
	 */
	@Override
	public String setLableKey(String doPost,String vID) {
		// TODO Auto-generated method stub

		String usernames=userService.Justiactions(FLogisticsController.username()).getStandby1();
		return hjBasesMapper.setLableKey(doPost,vID,usernames);
	}

	@Override
	public List<hjBase> selectallOrid(String lableKeyList,String username) {
		// TODO Auto-generated method stub
		String[] split = lableKeyList.split(",");
        List<String> strings = Arrays.asList(split);
		return hjBasesMapper.selectallOrid(strings,username);
	}

	@Override
	public List<hjBase> selHJLogistics2(String spili) {
		// TODO Auto-generated method stub
		return hjBasesMapper.selHJLogistics2(spili);
	}

	@Override
	public hjBase selalls(String getfIds,String username) {
		// TODO Auto-generated method stub
		return hjBasesMapper.selalls(getfIds,username);
	}

	@Override
	public hjBase selalls2(String getfIds,String username) {
		// TODO Auto-generated method stub
		return hjBasesMapper.selalls2(getfIds,username);
	}


	@Override
	public List<hjBase> getNoList(String list,String username) {
		// TODO Auto-generated method stub
		//把所有的值截取
		String[] split = list.split(",");
		//存为list
        List<String> strings = Arrays.asList(split);
        //根据list添加in
		return hjBasesMapper.getNoList(strings,username);
	}

	@Override
	public int updata(String referenceNo, String trackNum1) {
		// TODO Auto-generated method stub

		String usernames=userService.Justiactions(FLogisticsController.username()).getStandby1();
		return hjBasesMapper.updata(referenceNo,trackNum1,usernames);
	}

	@Override
	public void addStandy12(String hjStandy12) {
		// TODO Auto-generated method stub
		hjBasesMapper.addStandy12(hjStandy12);
	}

	@Override
	public int insertSelective2Plus(List<hjBase> ALLbatch) {
		// TODO Auto-generated method stub
		/*List<hjBase> hjList2=new ArrayList<hjBase>();
		hjList.forEach(x->{
			try {
				x.setHjStandy14(Calculations.los(x.getHjInvoiceweight(),x.getHjCountrycode(), x.getHjShippingmethod()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(x.getHjShipperhawbcode()+"获取运费错误");
			}
			logger.info(x);
			hjList2.add(x);
		});
		logger.info("添加");*/
		return hjBasesMapper.insertSelective2Plus(ALLbatch);
	}

	@Override
	public List<Astrict> astrict() {
		// TODO Auto-generated method stub
		return  astrictMapper.selectAll();
	}
	/**
	 * 批量修改
	 */
	@Override
	public int updataList(List<Map<String, Object>> map) {
		// TODO Auto-generated method stub
		return hjBasesMapper.updataList(map,userService.Justiactions(FLogisticsController.username()).getStandby1());
	}

}
