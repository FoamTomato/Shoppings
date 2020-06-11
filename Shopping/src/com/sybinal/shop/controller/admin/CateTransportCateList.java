package com.sybinal.shop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sybinal.shop.model.Cate;
import com.sybinal.shop.model.CateList;
import com.sybinal.shop.service.cate.CateListService;
import com.sybinal.shop.service.cate.CateService;

@Controller
public class CateTransportCateList {
	@Autowired
	CateService cateService;
	
	@Autowired
	CateListService cateListService;
	
	@RequestMapping(value = "/cateTransportCateList", method = RequestMethod.GET)
	@ResponseBody
	public List<Cate> receiveMain(Cate cate){
		CateList results=new CateList();
		//查询出所有的cate
		List<Cate> cate1=cateService.selectAllCate(cate);
		//用于计数然后添加数据
		int i=0;
		for(Cate cate2:cate1) {
			Integer f1=null,f2=null,f3=null,f4=null,f5=null,f6=null;
			//查询出所有catelist
			List<CateList> catelist1=cateListService.selectAllCatelist();
			for(CateList catelist2:catelist1) {
				//判断cate1是否有相等的
				if(cate2.getCate1()==null) {
					i=-1;
					break;
				}else if(cate2.getCate1().equals(catelist2.getCates())) {
					results.setPid(catelist2.getId());
					f1=catelist2.getId();
					//记相同的数
					i++;
					break;
				}
			}
			System.out.println();
			System.out.println("1f1="+f1+"+1f2="+f2+"+1f3="+f3+"+1f4="+f4+"+1f5="+f5+"+1f6="+f6);
			System.out.println(cate2);
			System.out.println();
			//如果等于0则添加，如果大于0则进行cate2的判断
			if(i==0) {
				results.setStatue("c1");
				results.setCates(cate2.getCate1());
				cateListService.setValue(results);
			}else if(i>0){
				int a=0;
				//List<CateList> catelist3=cateListService.selectAllCatelist();
				for(CateList catelist2:catelist1) {
					//判断cate2是否有相等的
					if(cate2.getCate2()==null) {
						a=-1;
						break;
					}else if(cate2.getCate2().equals(catelist2.getCates()) && cate2.getCate1().equals(f1)) {
						//记相同的数
						results.setPid(catelist2.getId());
						System.out.println("ca=="+catelist2.getStatue());
						f2=catelist2.getId();
						a++;
						break;
					}
				}
				System.out.println();
				System.out.println("2f1="+f1+"+2f2="+f2+"+2f3="+f3+"+2f4="+f4+"+2f5="+f5+"+2f6="+f6);
				System.out.println(a);
				System.out.println();
				if(a==0) {
					results.setStatue("c2");
					results.setCates(cate2.getCate2());
					cateListService.setValue(results);
				}else if(a>0){
					int b=0;
					//List<CateList> catelist4=cateListService.selectAllCatelist();
					for(CateList catelist2:catelist1) {
						//判断cate3是否有相等的
						if(cate2.getCate3()==null) {
							b=-1;
							break;
						}else if(cate2.getCate3().equals(catelist2.getCates()) && cate2.getCate2().equals(f2) && cate2.getCate1().equals(f1)) {
							//记相同的数
							results.setPid(catelist2.getId());
							System.out.println("ca2=="+catelist2.getStatue());
							f3=catelist2.getId();
							b++; 
							break;
						}
					}
					System.out.println();
					System.out.println("3f1="+f1+"+3f2="+f2+"+3f3="+f3+"+3f4="+f4+"+3f5="+f5+"+3f6="+f6);
					System.out.println(b);
					System.out.println();
					if(b==0) {
						results.setStatue("c3");
						results.setCates(cate2.getCate3());
						cateListService.setValue(results);
					}else if(b>0){
						int c=0;
						//List<CateList> catelist5=cateListService.selectAllCatelist();

						//CateList dateCate=cateListService.selectPid(f3);
						//if(dateCate.getPid()!=null) {
							
						//}
						for(CateList catelist2:catelist1) {
							
							//判断cate4是否有相等的
							if(cate2.getCate4()==null) {
								c=-1;
								break;
							}else if(cate2.getCate4().equals(catelist2.getCates()) && cate2.getCate3().equals(f3) && cate2.getCate2().equals(f2) && cate2.getCate1().equals(f1)) {
								//记相同的数
								results.setPid(catelist2.getId());
								System.out.println("ca3=="+catelist2.getStatue());

								f4=catelist2.getId();
								c++;
								break;
							}
						}
						System.out.println();
						System.out.println("4f1="+f1+"+4f2="+f2+"+4f3="+f3+"+4f4="+f4+"+4f5="+f5+"+4f6="+f6);
						System.out.println(c);
						System.out.println();
						if(c==0) {
							results.setStatue("c4");
							results.setCates(cate2.getCate4());
							cateListService.setValue(results);
						}else if(c>0){
							int d=0;
							//List<CateList> catelist6=cateListService.selectAllCatelist();
							for(CateList catelist2:catelist1) {
								//判断cate5是否有相等的
								if(cate2.getCate5()==null) {
									d=-1;
									break;
								}else if(cate2.getCate5().equals(catelist2.getCates()) && cate2.getCate4().equals(f4)  && cate2.getCate3().equals(f3) && cate2.getCate2().equals(f2) && cate2.getCate1().equals(f1)) {
									//记相同的数
									results.setPid(catelist2.getId());
									System.out.println("ca4=="+catelist2.getStatue());
									f5=catelist2.getId();
									d++;
									break;
								}
							}
							System.out.println();
							System.out.println("5f1="+f1+"+5f2="+f2+"+5f3="+f3+"+5f4="+f4+"+5f5="+f5+"+5f6="+f6);
							System.out.println(cate2);
							System.out.println();
							if(d==0) {
								results.setStatue("c5");
								results.setCates(cate2.getCate5());
								cateListService.setValue(results);
							}else if(d>0){
								int e=0;
								//List<CateList> catelist7=cateListService.selectAllCatelist();
								for(CateList catelist2:catelist1) {
									//判断cate6是否有相等的
									if(cate2.getCate6()==null) {
										e=-1;
										break;
									}else if(cate2.getCate6().equals(catelist2.getCates()) && cate2.getCate5().equals(f5)  && cate2.getCate4().equals(f4)  && cate2.getCate3().equals(f3) && cate2.getCate2().equals(f2) && cate2.getCate1().equals(f1)) {
										//记相同的数
										results.setPid(catelist2.getId());
										System.out.println("ca5=="+catelist2.getStatue());
										f6=catelist2.getId();
										e++;
										break;
									}
								}
								System.out.println();
								System.out.println("6f1="+f1+"+6f2="+f2+"+6f3="+f3+"+6f4="+f4+"+6f5="+f5+"+6f6="+f6);
								System.out.println(cate2);
								System.out.println();
								if(e==0) {
									results.setStatue("c6");
									results.setCates(cate2.getCate6());
									cateListService.setValue(results);
								}
							}
						}
					}else {
						System.out.println("3重复一个"+cate2.getCate3());
					}
				}else {
					System.out.println("2重复一个"+cate2.getCate2());
				}
			}
		}
		return cate1;
	}
}
