<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>商城后台</title>
<jsp:include page="../commonJs.jsp" />
<jsp:include page="../commonCss.jsp" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/admin/vendor/webuploader/webuploader.css">
<script
	src="${pageContext.request.contextPath}/resources/admin/vendor/jquery-validation/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/admin/vendor/bootstrap-fileinput-master/js/fileinput.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/admin/vendor/bootstrap-fileinput-master/js/fileinput_locale_zh.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/admin/vendor/webuploader/webuploader.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/pc/assets/js/bootstrap-touchspin-master/src/jquery.bootstrap-touchspin.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/admin/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/admin/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/admin/ztree/js/jquery.ztree.excheck.js"></script>
</head>

<body class="blank">
	<jsp:include page="../main/top.jsp" />
	<jsp:include page="../main/left.jsp" />
	<div id="wrapper">
		<div class="small-header transition animated fadeIn">
			<div class="content animate-panel">
				<div class="hpanel">
					<div class="panel-body">
						<div class="hpanel">
							<ul class="nav nav-tabs">
								<li class="active"><a id="liProduct" data-toggle="tab"
									href="#tab-1">商品信息</a></li>
								<li class=""><a id="liOption" data-toggle="tab"
									href="#tab-2">商品Option</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<form:form modelAttribute="product" id="form_productInfo"
										name="form_productInfo" method="POST">
										<input type="hidden" id="parameterName"
											value="${_csrf.parameterName}">
										<input type="hidden" id="token" value="${_csrf.token}">
										<div class="row" style="padding: 20px">
											<div class="row">
												<div class="form-group col-lg-1">
													<label>商品编号</label> <input name="externalId"
														class="form-control" type="text" placeholder="商品编号"
														value="E${fproduct.id}" disabled="disabled">
												</div>
												<div class="form-group col-lg-5">
													<label>商品名称</label> <input id="id" name="id" type="hidden"
														value="${fproduct.id}"> <input name="name"
														class="form-control" type="text" placeholder="商品名称"
														value="${fproduct.name}">
												</div>
												<div class="form-group col-lg-6">
											 <!--<label>父类</label>
											 <select name="pid" class="form-control">
													<c:forEach var="pid" items="${produtId}">
									
													<option value="${pid.id}" selected="selected">${pid.name }${pid.id }</option>
													</c:forEach>  
											</select> 
											 -->
					                      <div class="content_wrap">
											<div class="zTreeDemoBackground">
												<ul class="list" style="list-style: none;padding-inline-start: 0px;">
												
													<li class="title"><a id="menuBtn" style="float:left"  href="#"  class="btn btn-info" onclick="showMenu(); return false;" style="float:left">选择父类</a>
													 <c:if test="${empty  productCategorys.name}">
													 
													 <input id="citySel"  class="form-control" type="text" readonly  value="总目录" style="width:89%;margin-top: 23px"/>
													 	 <input id="citySel1"  type="hidden" name="pid" class="form-control"  readonly  value="0" style="width:90%"/>
												&nbsp;
													</c:if>
													<c:if test="${not empty  productCategorys.name}">
													<input id="citySel"  class="form-control" type="text" readonly  value="${productCategorys.name}" style="width:90%"/>
													 	 <input id="citySel1"  type="hidden" name="pid" class="form-control"  readonly  value="${productCategorys.id}" style="width:90%"/>
												&nbsp;
													 </c:if>
												</li>
												</ul>
											</div>
											
										</div>
										 
										<div id="menuContent" class="menuContent" style="display:none;margin-top:-10px;  ">
											<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
										</div>
										</div>
												
												<!-- 图片 -->
												<div class="form-group col-lg-6">
													<label>上传图片</label>
													<!--dom结构部分-->
													<div id="uploader-demo">
														<div id="fileList" class="uploader-list"></div>
														<div id="filePicker">选择图片</div>
														<div style='padding-top: 10px'>上传图片的最佳尺寸：300像素*300像素，其他尺寸会影响页面效果，格式png，jpeg，jpg，gif。大小不超过1M。最多允许上传4张</div>
														<c:if test="${product.id!=null}">
															<div id="imageFile">
																<div id="DB_WU_FILE_0" style="float: left"
																	class="file-item thumbnail">
																	<input type="radio" name="defaultImg"
																		value="DB_WU_FILE_0" checked />设为默认 <img
																		src="${pageContext.request.contextPath}${product.defaultImg}"
																		style="width: 150px; height: 150px" />
																	<div style="padding-top: 2px">
																		<button class="btn btn-default btn-sm" type="button"
																			onclick="deleteImage(this)">删除</button>
																	</div>
																	<input type="hidden" name="DB_WU_FILE_0"
																		value="${product.defaultImg}" />
																</div>
																<c:forEach items="${product.productImageList}"
																	var="imageInfo" varStatus="status">
																	<div id="DB_WU_FILE_${status.index+1}"
																		style="float: left" class="file-item thumbnail">
																		<input type="radio" name="defaultImg"
																			value="DB_WU_FILE_${status.index+1}" />设为默认 <img
																			src="${pageContext.request.contextPath}${imageInfo.url}"
																			style="width: 150px; height: 150px" />
																		<div style="padding-top: 2px">
																			<button class="btn btn-default btn-sm" type="button"
																				onclick="deleteImage(this)">删除</button>
																		</div>
																		<input type="hidden"
																			name="DB_WU_FILE_${status.index+1}"
																			value="${imageInfo.url}" />
																	</div>
																</c:forEach>
															</div>
														</c:if>
														<c:if test="${product.id==null}">
															<div id="imageFile">
																<img src="" />
															</div>
														</c:if>
														<div id="hiddenImage"></div>
													</div>
												</div>
												
												<div class="row">
													<div class="form-group col-lg-2">
											<label>特殊状态</label>
					                        <select name="productStatus" class="form-control">
													<option value="0" selected="selected">全部</option>
													<option value="1">重点</option>
													<option value="2">原创</option>
													<option value="3">海外</option>
													<option value="4">抓取</option>
													<option value="5">导入</option>
											</select>
										</div>
										<div class="form-group col-lg-2">
											<label>分销状态</label>
					                        <select name="productStatus" class="form-control">
													<option value="0" selected="selected">全部</option>
													<option value="1">分销</option>
													<option value="2">不分销</option>
													<option value="3">没运费</option>
													<option value="4">无挂号</option>
													<option value="5">未分类</option>
													<option value="6">零库存</option>
											</select>
										</div>
										<div class="form-group col-lg-2">
											<label>日期</label>
					                        <select name="productStatus" class="form-control">
													<option value="0" selected="selected">全部</option>
													<option value="1">七天内</option>
													<option value="2">一周前</option>
													<option value="3">一月前</option>
													<option value="4">三月前</option>
													<option value="5">半年前</option>
													<option value="6">一年前</option>
													<option value="7">未出单</option>
											</select>
										</div>
											<div class="form-group col-lg-2">
											<label>审核状态</label>
					                        <select name="productStatus" class="form-control">
													<option value="6" selected="selected">全部</option>
													<option value="0">通过</option>
													<option value="1">待审核</option>
													<option value="2">过滤</option>
													<option value="3">侵权</option>
													<option value="4">禁止上传</option>
													<option value="5">屏蔽</option>
											</select>
										</div>
										<div class="form-group col-lg-2">
											<label>产品状态</label>
					                        <select name="productStatus" class="form-control">
													<option value="2" selected="selected">全部</option>
													<option value="0">已上架</option>
													<option value="1">已下架</option>
													<option value="3">失效</option>
											</select>
										</div>
										<div class="form-group col-lg-2">
											<label>分级及图案</label>
					                        <select name="productStatus" class="form-control">
													<option value="0" selected="selected">全部</option>
													<option value="1">为分级</option>
													<option value="2">图案设计</option>
													<option value="3">有图案设计</option>
													<option value="4">国内品牌</option>
													<option value="5">高风险</option>
											</select>
										</div>		
										<div class="form-group col-lg-6" style="margin-top: 30px;">
														<label style="color:red;font-size: 20px;font-weight: 400;">设置过滤，侵权和屏蔽，亚马逊上传明细将自动标注"待删除"</label> 
													</div>
										<div class="form-group col-lg-1" style="margin-top: 30px;" >
													<label>业务员</label> 
													<select name="productStatus" class="form-control">
															<option value="0" selected="selected">全部</option>
															<option value="1">为分级</option>
															<option value="2">图案设计</option>
															<option value="3">有图案设计</option>
															<option value="4">国内品牌</option>
															<option value="5">高风险</option>
													</select>
										</div>
										<div class="form-group col-lg-1" style="margin-top: 30px;">
													<label>原产地区</label> <input name="externalId"
														class="form-control" type="text" placeholder="商品编号"
														value="${fproduct.id}">
										</div>
										<div class="form-group col-lg-2" style="margin-top: 30px;">
													<label>英文简称</label> <input name="externalId"
														class="form-control" type="text" placeholder="商品编号"
														value="${fproduct.id}">
										</div>
										<div class="form-group col-lg-2" style="margin-top: 30px;">
													<label>中文简称</label> <input name="externalId"
														class="form-control" type="text" placeholder="商品编号"
														value="${fproduct.id}">
										</div>
										</div>
												<div class="row">
												<!-- 选项卡菜单-->
													<ul id="myTab" class="nav nav-tabs" role="tablist" style="width:49%;margin-bottom: 30px">
													    <li class="active"><a href="#bulletin" role="tab" data-toggle="tab">英语</a></li>
													    <li><a href="#rule" role="tab" data-toggle="tab">法语</a></li>
													    <li><a href="#forum" role="tab" data-toggle="tab">德语</a></li>
													    <li><a href="#security" role="tab"  data-toggle="tab">意大利</a></li>
													    <li><a href="#spanlish" role="tab" data-toggle="tab">西班牙</a></li>
													    <li><a href="#japanese" role="tab" data-toggle="tab">日语</a></li>
													    <li><a href="#chinese" role="tab" data-toggle="tab">中文</a></li>
													</ul>
													<!-- 选项卡面板 -->
													<div id="myTabContent" class="tab-content">
													    <div class="tab-pane active" id="bulletin">
															<div class="form-group col-lg-6">
															<label>产品标题(英语)</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
															<label>关键词：不超过五行 逗号，空格或回车分隔(英语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符(英语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>产品描述：不要输入html标签(英语)</label> 
															<textarea   cols="110"   rows="8"   ></textarea>
														</div>
														</div>
													    <div class="tab-pane " id="rule">
															<div class="form-group col-lg-6">
															<label>产品标题(法语)</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
															<label>关键词：不超过五行 逗号，空格或回车分隔(法语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符(法语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>产品描述：不要输入html标签(法语)</label> 
															<textarea   cols="110"   rows="8"   ></textarea>
													    </div>
													    </div>
													    <div class="tab-pane " id="forum">
															<div class="form-group col-lg-6">
															<label>产品标题(德语)</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
															<label>关键词：不超过五行 逗号，空格或回车分隔(德语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符(德语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>产品描述：不要输入html标签(德语)</label> 
															<textarea   cols="110"   rows="8"   ></textarea>
														</div>
													    </div>
													    <div class="tab-pane " id="security">
															<div class="form-group col-lg-6">
															<label>产品标题(意大利)</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
															<label>关键词：不超过五行 逗号，空格或回车分隔(意大利)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符(意大利)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>产品描述：不要输入html标签(意大利)</label> 
															<textarea   cols="110"   rows="8"   ></textarea>
														</div>
													    </div>
													    <div class="tab-pane " id="spanlish">
															<div class="form-group col-lg-6">
															<label>产品标题(西班牙)</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
															<label>关键词：不超过五行 逗号，空格或回车分隔(西班牙)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符(西班牙)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>产品描述：不要输入html标签(西班牙)</label> 
															<textarea   cols="110"   rows="8"   ></textarea>
														</div>
													    </div>
													    <div class="tab-pane " id="japanese">
															<div class="form-group col-lg-6">
															<label>产品标题(日语)</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
															<label>关键词：不超过五行 逗号，空格或回车分隔(日语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符(日语)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>产品描述：不要输入html标签(日语)</label> 
															<textarea   cols="110"   rows="8"   ></textarea>
														</div>
													    </div>
													    <div class="tab-pane " id="chinese">
															<div class="form-group col-lg-6">
															<label>产品标题(中文)</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
															<label>关键词：不超过五行 逗号，空格或回车分隔(中文)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符(中文)</label> 
															<textarea   cols="110"   rows="5"   ></textarea>
															<label>产品描述：不要输入html标签(中文)</label> 
															<textarea   cols="110"   rows="8"   ></textarea>
														</div>
													    </div>


														<div class="form-group col-lg-1" style="margin-top: -65px;">
																	<label>包装毛重</label> <input name="externalId"
																		class="form-control" type="text" placeholder="包装毛重"
																		value="${fproduct.id}">
														</div>
														<div class="form-group col-lg-5" style="margin-top: -65px;">
																	<label>包装尺寸</label>
																	<div class="" style="">
																	<input name="externalId" style="width:30%;display:-webkit-inline-box"
																		class="form-control" type="text" placeholder="长"
																		value="${fproduct.id}">
																	x	
																	<input name="externalId" style="width:30%;display:-webkit-inline-box"
																		class="form-control" type="text" placeholder="宽"
																		value="${fproduct.id}">
																		x
																	<input name="externalId" style="width:30%;display:-webkit-inline-box"
																		class="form-control" type="text" placeholder="高"
																		value="${fproduct.id}">
														</div>
														</div>
														</div>
												<div class="row">
														<div class="form-group col-lg-2" style="margin-top: 0px;" >
																	<label>产品币种</label> 
																	<select name="productStatus" class="form-control">
																			<option value="0" selected="selected">人民币</option>
																			<option value="1">美元</option>
																			<option value="2">欧元</option>
																	</select>
														</div>
														<div class="form-group col-lg-3" style="">
																	<label>分销单价</label> <input name="externalId"
																		class="form-control" type="text" placeholder="分销单价"
																		value="${fproduct.id}">
														</div>
														
														<div class="form-group col-lg-1" style="margin-top:30px;font-size:15px">
																	<input  type="checkbox" style="margin-right:10px;">允许分销
														</div>
														
														<div class="form-group col-lg-2" style="">
																	<label>成本单价</label> 
																	<input name="externalId" 
																		class="form-control" type="text" placeholder="分销单价"
																		value="${fproduct.id}">
																	
														</div>
														
														<div class="form-group col-lg-4" style="">
																	<label>固定运费</label> <br><nobr style="display: -webkit-inline-box;    line-height: 30px;">
																	<input name="externalId" style="width:100%;margin-right: 20px;" 
																		class="form-control" type="text" placeholder="分销单价"
																		value="￥${fproduct.id}">
																		建议用国家运费</nobr>
														</div>
														<div class="form-group col-lg-6" style="">
																	<label>产品来源网址</label> 
															<textarea   cols="110"   rows="2"   ></textarea>
														</div>
														<div class="form-group col-lg-2" style="margin-top: 0px;" >
																	<label>适用人群</label> 
																	<select name="productStatus" class="form-control">
																			<option value="0" selected="selected">全部</option>
																			<option value="1">成人</option>
																			<option value="2">小孩</option>
																	</select>
														</div>
														<div class="form-group col-lg-2" style="margin-top: 0px;" >
																	<label>产品材料</label> 
																	<select name="productStatus" class="form-control">
																			<option value="0" selected="selected">全部</option>
																			<option value="1">玻璃</option>
																			<option value="2">金属</option>
																	</select>
														</div>
														<div class="form-group col-lg-2" style="margin-top: 0px;" >
																	<label>内部SKU</label> 
																	<input name="externalId" 
																		class="form-control" type="text" placeholder="分销单价"
																		value="￥${fproduct.id}">
														</div>
														<div class="form-group col-lg-6" style="margin-top: 0px;" >
																	<label>产品变体</label> 
																	
																	<div class="" style="">
																	<select name="productStatus" class="form-control" style="width:50%;display: -webkit-inline-box;">
																			<option value="0" selected="selected">全部</option>
																			<option value="1">棕色</option>
																			<option value="2">红色</option>
																	</select>
																	<a class="btn btn-primary" style="margin-top: -5px;margin-left: 30px;">添加变体</a>
																	</div>
																	<table class="table-striped " style="width:100%">
																		
																			<tr style="color: black;font-size: 14px;line-height: 50px;background: white;">
																				<th>变体</th>
																				<th>SKU(选填)</th>
																				<th>库存</th>
																				<th>加价</th>
																				<th>图片</th>
																				</tr>
																			<tbody>
																			<tr style="line-height: 60px;">
																			    <td>棕色</td>
																			    <td>E12855</td>
																			    <td>18</td>
																			    <td>23</td>
																			    <td>
																					<img alt="加载中" src="http://img5.imgtn.bdimg.com/it/u=1200889471,2010793696&fm=26&gp=0.jpg" width="50px" height="50px">
																				</td>
																			  </tr>
																			  <tr style="line-height: 60px;">
																			    <td>红色</td>
																			    <td>E12853</td>
																			    <td>15</td>
																			    <td>30</td>
																			    <td>
																					<img alt="加载中" src="http://img3.imgtn.bdimg.com/it/u=3228145009,2253179202&fm=26&gp=0.jpg" width="50px" height="50px">
																				</td>
																				  </tr>
																			  </tbody>
																	</table>
														</div>
													</div>
													
													</div>
													
													<div class="form-group col-lg-12" style="margin-top: 0px;" >
																	
																		<table class="table-striped " style="width:100%">
																		
																			<tr style="color: black;font-size: 14px;line-height: 50px;background: white;">
																				<th>产品操作日志</th>
																				</tr>
																			<tbody>
																			<tr style="line-height: 60px;">
																			    <td>管理员：
																			    	<nobr style="color:#7CCD7C">小新</nobr>
																			    	
																			    	<nobr style="color:#7CCD7C">192.168.171.63 &nbsp; 广东省深圳市  &nbsp; 移动</nobr>
																			    	
																			    	<nobr style="color:#7CCD7C">
																			    	<nobr id="clocks" type="text" style="border: none;margin-left:30px"></nobr>
																			    	<!-- <!-- 日期 
																			    	<script type="text/javascript">
																			    	function showTime(){
																			    		var t=new Date();
																			    		var year=t.getFullYear();
																			    		var month=t.getMonth();
																			    		var day=t.getDate();
																			    		var week=t.getDay();
																			    		var arr=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
																			    		var hour=t.getHours();
																			    		var minute=t.getMinutes();
																			    		var second=t.getSeconds();
																			    		var showTime=year+"/"+month+"/"+day+" "+arr[week]+" "+hour+((minute<10)?":0":":")+minute+((second<10)?":0":":")+second+((hour>12)?".pm":".am");
																			    		
																			    		document.getElementById("clock").innerHTML=showTime;
																			    	}
																			    	setInterval("showTime()",1000);
																			    	</script> -->
																			    	</nobr>
																			    </td>
																			</tr>
																			<tr style="line-height: 60px;">
																			    <td>状态从 通过 上架 未分级 抓取 改成 通过 上架 未分级 抓取通过 改成 过滤 </td>
																			</tr>
																			<tr style="line-height: 60px;">
																			    <td>管理员：
																			    	<nobr style="color:#7CCD7C">小白</nobr>
																			    	
																			    	<nobr style="color:#7CCD7C">192.168.171.68 &nbsp; 广东省深圳市  &nbsp; 移动</nobr>
																			    	
																			    	<nobr style="color:#7CCD7C">
																			    	<nobr id="clock" type="text" style="border: none;margin-left:30px"></nobr>
																			    	<!-- 日期 -->
																			    	<script type="text/javascript">
																			    	function showTime(){
																			    		var t=new Date();
																			    		var year=t.getFullYear();
																			    		var month=t.getMonth();
																			    		var day=t.getDate();
																			    		var week=t.getDay();
																			    		var arr=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
																			    		var hour=t.getHours();
																			    		var minute=t.getMinutes();
																			    		var second=t.getSeconds();
																			    		var showTime=year+"/"+month+"/"+day+" "+arr[week]+" "+hour+((minute<10)?":0":":")+minute+((second<10)?":0":":")+second+((hour>12)?".pm":".am");
																			    		
																			    		document.getElementById("clock").innerHTML=showTime;
																			    		document.getElementById("clocks").innerHTML=showTime;
																			    	}
																			    	setInterval("showTime()",1000);
																			    	</script>
																			    	</nobr>
																			    </td>
																			</tr>
																			<tr style="line-height: 60px;">
																			   <td>状态从 通过 上架 未分级 导入 改成 通过 上架 未分级 导入未分级 改成 没图案设计 </td>
																			</tr>
																			</tbody>
																	</table>
														</div>
												</div>
												
												
												
												<%-- <div class="form-group col-lg-12">
													<input type="hidden" id="explain" name="explain"
														value='${fproduct.explains}'> <label
														class="control-label">商品说明</label>
													<div style="width: auto; height: auto;">
														<script id="container" type="text/plain"></script>
														<!-- 配置文件 -->
														<script
															src="${pageContext.request.contextPath}/resources/admin/js/ueditor/ueditor.config.js"
															type="text/javascript"></script>
														<!--　编辑器源码文件 -->
														<script
															src="${pageContext.request.contextPath}/resources/admin/js/ueditor/ueditor.all.js"
															type="text/javascript"></script>
														<!--　实例化编辑器 -->
														<script type="text/javascript">
													var ue = UE.getEditor('container', {
														autoWidth: true
													});
													ue.ready(function() {
														ue.setContent($("#explain").val());
													});
													</script>
													</div>
												</div> --%>
											</div>
											<div class="row" style="padding: 20px">
												<div class="text-left m-t-xs">
													<button id="saveProductInfo" class="btn btn-success"
														style="margin-right: 5px; margin-top: 5px" type="button">
														<strong>确认提交</strong>
													</button>
													<button id="back" class="btn btn-success"
														style="margin-right: 5px; margin-top: 5px" type="button">
														<strong>返回</strong>
													</button>
												</div>
											</div>
									</form:form>
								</div>
								<SCRIPT type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				onCheck: onCheck
			}
		};
		function zTreeOnClick(event, treeId, treeNode) {       //第二步
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = treeObj.getCheckedNodes(true),
                v = "";
            for (var i = 0; i < nodes.length; i++) {
                v += nodes[i].name + ",";
                console.log("节点id:" + nodes[i].id + "节点名称" + v); //获取选中节点的值
            }
        }
		var zNodes =[
		 ];

		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}

		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			 
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#citySel");

			var cityObj1 = $("#citySel1");
			for (var i = 0; i < nodes.length; i++) {
                cityObj1.attr("value", nodes[i].id);
                console.log("节点id:" + nodes[i].id + "节点名称" + v); //获取选中节点的值
            } 
            cityObj.innerHTML="第"+v+"次 ";
			cityObj.attr("value", v);
		}

		function showMenu() {
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		/* $(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			MoveTest.updateType();
			MoveTest.bindDom();
		}); */
		 $(document).ready(function(){
			$.ajax({
				type : "GET",
				contentType : "application/json;charset=utf-8", 
				url :'${pageContext.request.contextPath}/admin/addCategorys',
				dataType : 'json',
				success : function(data) {
					
					for(var i=0;i<data.length;i++){
						//alert("成功"+data[i].id);
						 //zNodes.push({"id":data[i].id,"name":data[i].name,"pId":data[i].pId});

						zNodes.push(data[i]);
						//console.log(zNodes);
						
					
						
					} $.fn.zTree.init($("#treeDemo"), setting, zNodes);
				
					
				},
				error : function(json) {
					alert("失败"+json);
				}
			});
		}); 
		
	</SCRIPT>
								<div id="tab-2" class="tab-pane">
									<div class="panel-body">
										<div class="row">
											<div class="col-md-3">
												<div class="hpanel">
													<div class="panel-heading">商品选项</div>
													<div class="form-group">
														<c:forEach items="${optionList}" var="item">
															<div class="input-group">
																<div class="checkbox checkbox-success">
																	<input type="checkbox" name="optionId"
																		value="${item.id}"> <label>${item.name}</label>
																</div>
															</div>
														</c:forEach>
													</div>
													<button id="createSkuBtn" class="btn btn-success"
														type="button">生成SKU</button>
													<button id="deleteSkuBtn" class="btn btn-success"
														type="button">清除SKU</button>
												</div>
											</div>
											<div class="col-md-9">
												<div class="hpanel">
													<div id="skuListId" class="panel-heading">SKU列表</div>
													<div class="panel">
														<div id="skuGroup">
															<c:forEach items="${skuVOInfoList}" var="item">
																<div class="panel-body"
																	data-key="${item.optionValueKeyGroup}">
																	<h4>${item.skuJson}</h4>
																	<div class="form-group">
																		<label class="col-sm-2 control-label">价格</label>
																		<div class="col-sm-10">
																			<input type="text" name="price"
																				class="form-control priceTouchSpin"
																				value="${item.price / 100}"> <input
																				type="hidden" name="key"
																				value="${item.optionValueKeyGroup}">
																		</div>
																	</div>
																	<div class="form-group">
																		<label class="col-sm-2 control-label">库存</label>
																		<div class="col-sm-10">
																			<input type="text" name="quantity"
																				class="form-control quantityTouchSpin"
																				value="${item.quantity}">
																		</div>
																	</div>
																	<div class="form-group">
																		<div class="col-sm-8 col-sm-offset-2">
																			<button class="btn btn-default"
																				onclick="delMoreRow('${item.optionValueKeyGroup}')">删除</button>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
														<div class="form-group text-center">
															<div class="col-sm-8 col-sm-offset-2"
																style="padding-top: 20px">
																<button class="btn btn-success" onclick="saveSku(this)">保存SKU</button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	${fproduct }
	<script type="text/javascript">
	$('#leftMenuProduct').addClass('active');
	var oTable;
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	var contextPath = '${pageContext.request.contextPath}';

	var uploader;
	
	$(function() {
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});

		uploader = WebUploader.create({
			// 选完文件后，是否自动上传。
			auto: true,

			// swf文件路径
			swf: contextPath + '/resources/vendor/webuploader/Uploader.swf',
			// 文件接收服务端。
			server: contextPath + '/admin/newUploadfile?${_csrf.parameterName}=${_csrf.token}',

			fileNumLimit: 300,
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick: '#filePicker',
			// 只允许选择图片文件。
			accept: {
				title: 'Images',
				extensions: 'gif,jpg,jpeg,bmp,png',
				mimeTypes: 'image/*'
			}
		});
		uploader.on('uploadAccept', function(object, ret) {
			var hiddenImage = '<input type="hidden" name="' + object.file.id + '" value="' + ret.fileName + '" />'
			$("#hiddenImage").append(hiddenImage);
		});
		// 当有文件添加进来的时候
		uploader.on('fileQueued', function(file) {
			var $li = $(
					'<div id="' + file.id + '" style="float:left" class="file-item thumbnail">' +
					'<input type="radio" name="defaultImg" value="' + file.id + '"/>设为默认' + '<img>' + '<div style="padding-top: 2px"><button class="btn btn-default btn-sm" type="button" onclick="deleteImage(this)">删除</button></div>' +
					'</div>'
				),
				$img = $li.find('img');
			// $list为容器jQuery实例
			$('#imageFile').append($li);

			var ratio = window.devicePixelRatio || 1;
			// 缩略图大小
			var thumbnailWidth = 150 * ratio;
			var thumbnailHeight = 150 * ratio;
			uploader.makeThumb(file, function(error, src) {
				if (error) {
					$img.replaceWith('<span>不能预览</span>');
					return;
				}
				$img.attr('src', src);
			}, thumbnailWidth, thumbnailHeight);
		});
		var validate = $("#form_productInfo").validate({
			onsubmit: false,
			focusInvalid: false,
			errorClass: "error",
			onfocusout: false,
			onkeyup: false,
			onclick: false,
			ignoreTitle: true,
			rules: {
				name: {
					required: true,
					maxlength: 100
				},
				externalId: {
					maxlength: 50
				},
				shopPrice: {
					required: true,
					number: true,
					decimals: 2,
					max: 99999.99,
					min: 0
				},
				price: {
					required: true,
					number: true,
					decimals: 2,
					max: 99999.99,
					min: 0
				},
				quantity: {
					required: true,
					digits: true,
					maxlength: 4
				},
				generalExplain: {
					maxlength: 255
				}

			},
			messages: {
				name: {
					required: "请输入商品名!",
					maxlength: "商品名不能超过100位"
				},
				externalId: {
					maxlength: "商品外部编号不能超过50位",
				},
				shopPrice: {
					required: "请输入店内价格!",
					number: "请输入合法数字！",
					max: "店内价格最大不能超过99999.99元！",
					min: "店内价格不能小于0元！",
					decimals: "小数位数不能大于两位！"
				},
				price: {
					required: "请输入市场价格!",
					number: "请输入合法数字！",
					max: "市场价格最大不能超过99999.99元！",
					min: "市场价格不能小于0元！",
					decimals: "小数位数不能大于两位！"
				},
				quantity: {
					required: "请输入商品库存!",
					digits: "请输入正整数！",
					maxlength: "商品库存最大长度不能超过4位！"
				},
				generalExplain: {
					maxlength: "商品概要说明不能超过255位！"
				}
			}
		});
	});
	/** 返回  */
	$("#back").bind("click", function() {
		window.location.href = contextPath + '/admin/productList';
	});
	
	$("#saveProductInfo").bind("click", function() {
		
		if (checkImage() == false) {
			return;
		}
		$.ajax({
			type: "POST",
			contentType: "application/json;charset=utf-8",
			url: "${pageContext.request.contextPath}/admin/product/save",
			data: formToJson($("#form_productInfo")),
			dataType: "json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）  
			async: true,
			success: function(json) {
				if (json.error == '0') {
					$("#id").val(json.data.id);
					swal({
						title: "保存成功!",
						text: "",
						type: "success"
					});
				} else {
					swal("保存失败！", "请与系统管理员联系！", "error");
				}
			},
			error: function(json) {
				alert(json);
			}
		})
	});
	$('.priceTouchSpin').TouchSpin({
		min: 1,
		max: 99999,
		step: 0.01,
		decimals: 2,
		boostat: 5,
		maxboostedstep: 10
	});
	$('.quantityTouchSpin').TouchSpin({
        min: 1,
        max: 99999,
        step: 1,
        boostat: 5,
		maxboostedstep: 10
	});
	
	$('#deleteSkuBtn').click(function() {
		$('#skuGroup').empty();
	});
	var dataArray = [];
<c:if test="${productOptionInfo != null}">
	var optionChecked = ${productOptionInfo};
	$(optionChecked).each(function() {
		dataArray[dataArray.length] = this.optionId;
		$('input:checkbox[value="' + this.optionId + '"]').attr('checked', 'checked');
	});
</c:if>
	$('#createSkuBtn').click(function() {
		if ($("#id").val() == '') {
			swal({
				title: "提示",
				text: "请登录商品信息！"
			});
		} else {
			if ($("[name=optionId]:checked").length > 2 || $("[name=optionId]:checked").length == 0) {
				swal({
					title: "提示",
					text: "商品选项只能有1或2个！"
				});
			} else if ($('#skuGroup').find('div.panel-body').length > 0) {
				swal({
					title: "提示",
					text: "生成SKU必须先清除SKU！"
				});
			} else {
				dataArray = [];
				$("[name=optionId]:checked").each(function(i, v) {
					dataArray.push(Number($(v).val()));
				});
				$.ajax({
					type: "POST",
					contentType: "application/json;charset=utf-8",
					url: "${pageContext.request.contextPath}/admin/product/option/value",
					data: JSON.stringify(dataArray),
					dataType: "json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）  
					success: function(json) {
						if (json.length > 0) {
							addMoreRow(json);
							$('.priceTouchSpin').TouchSpin({
								min: 1,
								max: 99999,
								step: 0.01,
								decimals: 2,
								boostat: 5,
								maxboostedstep: 10
							});
							$('.quantityTouchSpin').TouchSpin({
						        min: 1,
						        max: 99999,
						        step: 1,
								boostat: 5,
								maxboostedstep: 10
							});
						}
					},
					error: function(json) {
						alert(json);
					}
				});
			}
		}

	});
	function delMoreRow(id) {
		$('div[data-key="' + id + '"]').remove();
	}
	
	function addMoreRow(json) {
		for ( var item in json) {
			$('#skuGroup').append(
				'<div class="panel-body" data-key="' + json[item].key + '">'+
				'	<h4>' + json[item].titleName + '</h4>'+
				'	<div class="form-group">'+
				'		<label class="col-sm-2 control-label">价格</label>'+
				'		<div class="col-sm-10">'+
				'			<input type="text" name="price" class="form-control priceTouchSpin" value="0">'+
				'			<input type="hidden" name="key" value="' + json[item].key + '">'+
				'		</div>'+
				'	</div>'+
				'	<div class="form-group">'+
				'		<label class="col-sm-2 control-label">库存</label>'+
				'		<div class="col-sm-10">'+
				'			<input type="text" name="quantity" class="form-control quantityTouchSpin" value="0">'+
				'		</div>'+
				'	</div>'+
				'	<div class="form-group">'+
				'		<div class="col-sm-8 col-sm-offset-2">'+
				'			<button class="btn btn-default" onclick="delMoreRow(\'' + json[item].key + '\')">删除</button>'+
				'		</div>'+
				'	</div>'+
				'</div>'
			);
		}
	}
	
	function saveSku(dom) {
		$(dom).attr("disabled","disabled");
		var productId = $('#id').val() ;
		if (productId == null || productId == "") {
			swal({
				title: "提示",
				text: "请登录商品信息！"
			});
			$(dom).removeAttr("disabled");
			return;
		}
		productId = Number(productId);
		var saveData = { 'productOptionInfoList' : [], 'skuList' : [], 'id' : productId };
		var skuList = saveData.skuList;
		var quantitySum = 0;
		$('#skuGroup').find('div.panel-body').each(function() {
			var price = Number($(this).find('input[name="price"]').val());
			var quantity = Number($(this).find('input[name="quantity"]').val());
			quantitySum += quantity;
			if (price == 0 && quantity == 0) {
				$(this).remove();
				return;
			}
			var sku = {};
			sku['optionValueKeyGroup'] = $(this).find('input[name="key"]').val();
			sku['price'] = price * 100; 
			sku['quantity'] = quantity; 
			sku['productId'] = productId;
			sku['skuJson'] = $(this).find('h4').html().trim();
			skuList[skuList.length] = sku;
		});
		if (quantitySum > Number($('#form_productInfo').find('input[name="quantity"]').val())) {
			swal({
				title: "提示",
				text: "库存总和不能大于商品库存!"
			});
			$(dom).removeAttr("disabled");
			return;
		}
		if (skuList.length != 0) {
			var optionList = saveData.productOptionInfoList;
			for ( var i in dataArray)
				optionList[optionList.length] = { 'optionId' : dataArray[i], 'productId' : productId };
		} else {
			$('input[name="optionId"]').removeAttr('checked');
		}
		$.ajax({
			type: "POST",
			contentType: "application/json;charset=utf-8",
			url: "${pageContext.request.contextPath}/admin/product/save/sku",
			data: JSON.stringify(saveData),
// 			data: saveData,
			dataType: "json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）  
			success: function(json) {
				if (json.success) {
					swal({
						title: "提示",
						text: json.message
					});
				}
				$(dom).removeAttr("disabled");
			},
			error: function(json) {
				alert('error' + json.code);
			}
		});
		
		
	}
	
	function formToJson($form) {
		var data = {};
		var imageArray = [];
		$($form.serializeArray()).each(function(i, v) {
			if (v.name == "shopPrice" || v.name == "price") {
				data[v.name] = Number(v.value) * Math.pow(10, 2)
			} else {
				data[v.name] = v.value
			}

		});
		var imageName = $("[name=defaultImg]:checked").val();
		data["defaultImg"] = $('[name="' + imageName + '"]').val();
		data["explain"] = ue.getContent();


		//添加图片信息
		$("[name=defaultImg]").each(function() {
			if ($(this).is(":checked") == false) {
				var blankData = {};
				blankData["url"] = $('[name="' + $(this).val() + '"]').val();
				imageArray.push(blankData);
			}
		});
		data["productImageList"] = imageArray;
		return JSON.stringify(data);
	}

	function checkImage() {
		if (typeof($("[name=defaultImg]")) == "undefined" || $("[name=defaultImg]").length == 0) {
			swal({
				title: "提示",
				text: "请上传商品图片！"
			});
			return false;
		} else {
			if ($("[name=defaultImg]").length > 4) {
				swal({
					title: "提示",
					text: "最多只允许上传4张图片！"
				});
				return false;
			} else {
				var checkFlag = false;
				$("[name=defaultImg]").each(function() {
					if ($(this).is(":checked") == true) {
						checkFlag = true;
					}
				});
				if (checkFlag == false) {
					swal({
						title: "提示",
						text: "请设置默认图片！"
					});
					return false;
				}
			}

		}
		return true;

	}

	function deleteImage(item) {
		for (var i = 0; i < uploader.getFiles().length; i++) {
			// 将图片从上传序列移除
			if ($(item).parent().parent().find("input").val() == uploader.getFiles()[i].id) {
				uploader.removeFile(uploader.getFiles()[i]);
			}
		}
		$(item).parent().parent().remove();
	}
	//验证小数点后的位数  
	jQuery.validator.addMethod("decimals", function(value, element, d) {
		var a = value.indexOf(".") + 1;
		if (a == 0) {
			a = value.length;
		}
		var b = value.length;
		var c = b - a;
		return this.optional(element) || c <= d;
	});
	</script>
</body>

</html>
