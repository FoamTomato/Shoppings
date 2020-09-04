<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>商城后台</title>
<jsp:include page="../commonCss.jsp" />
<link rel='dns-prefetch' href='https://images-na.ssl-images-amazon.com'>
<script src="${pageContext.request.contextPath}/resources/admin/jquery.min.js"></script><%-- 
<script src="${pageContext.request.contextPath}/resources/admin/toastr-master/build/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/jss/sweetalert2@9.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/admin/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/admin/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/admin/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/jquery.easyui.min.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script>
<!-- 引入组件库 -->
<%-- <script src="${pageContext.request.contextPath}/resources/jss/image-viewer-vue.js"></script>
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/vue-lazyload.js"></script> --%>
<script	src="${pageContext.request.contextPath}/resources/admin/scripts/productLIst_js.js"></script>
<%-- <!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vue-virtual-scroller.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/vue-virtual-scroller.min.js"></script> --%>
<style type="text/css">
.time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
  .gutters{
  	margin:10px 5px
  }
</style>
</head>
<body class="blank" style="margin: 0px; padding: 0px;overflow: hidden">
	<jsp:include page="../main/top.jsp" />
	<jsp:include page="../main/left.jsp" />
	<jsp:include page="../productCategory/modalCate.jsp" />
<!-- 主界面 -->
<div id="apps">

<div id="wrapper"
v-loading="loading"
element-loading-text="拼命加载中"
element-loading-spinner="el-icon-loading"
element-loading-background="rgba(0, 0, 0, 0.8)">
<el-container style="height:850px;">
  		<el-header style="height:200px;padding:0px">
			<div class="animate-panel" style="padding:10px">
			<div class="hpanel">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="hpanel">
									<div class="form-group col-lg-2">
										<label>其它搜索</label>
										<el-input v-model="fname" id="fname" style="width:100%" size="mini" placeholder="其它搜索"></el-input>
									</div>
									
									<div class="form-group col-lg-2">
										<label>产品编号/SKU</label>
										<el-input v-model="fids" id="fids" style="width:100%" size="mini" placeholder="产品编号/SKU"></el-input>
									</div>
									
									<div class="form-group col-lg-1">
										<label>产品分类</label>
										<el-input v-model="fcateid" id="fcateid" class="f_kind_select2" style="width:100%" size="mini" placeholder="产品编号/SKU"></el-input>
									</div>
									
									<div class="form-group col-lg-1">
										<label>最低价</label>
										<el-input v-model="fprice1" id="fprice1" type="number" min="0" style="width:100%" size="mini" placeholder="最低价"></el-input>
										<!-- <div class="input-group">
											<input id="fprice1" name="updateTimeFrom" type="number" v-model="fprice1" :max="fprice2"
												class="form-control" min="0">
										</div> -->
									</div>
									
									<div class="form-group col-lg-1">
										<label>最高价</label>
										<el-input v-model="fprice2" id="fprice2" type="number" :min="fprice1" style="width:100%" size="mini" placeholder="最高价"></el-input>
										<!-- <div class="input-group">
											<input id="fprice2" name="updateTimeTo" type="number" v-model="fprice2" :min="fprice1"
												class="form-control">
										</div> -->
									</div>
									
									<div class="form-group col-lg-4">
										<label>日期</label>
										<el-date-picker  size="mini" style="width:100%"
									      v-model="value6"
									      value-format="yyyy-MM-dd"
									      type="daterange"
									      align="right"
									      unlink-panels
									      range-separator="至"
									      start-placeholder="开始日期"
									      end-placeholder="结束日期"
									      :picker-options="pickerOptions2">
									    </el-date-picker><!-- 
										<div class="input-group date col-lg-12">
											<input name="updateTimeFrom" type="date" id="fdate1"  v-model="fdate1" :max="fdate2"
												class="form-control">
	
										</div> -->
									</div>
									
									<!-- <div class="form-group col-lg-2">
										<label>截至日期</label>
										<div class="input-group date col-lg-12">
											<input name="updateTimeTo" type="date" id="fdate2"  v-model="fdate2" :min="fdate1"
												class="form-control">
	
										</div>
									</div> -->
									<!-- 权限做出来再加入 -->
									<div class="form-group col-lg-1">
										<label>业务员</label>
										  <el-select v-model="ywys"  style="width:100%" placeholder="请选择"  size="mini">
										    <el-option
										      v-for="item in ywyss"
										      :key="item.id"
										      :label="item.userName"
										      :value="item.userName">
										    </el-option>
										  </el-select>
										 <!-- <select name="productStatus" id="ywys" disabled="disabled"   v-model="ywys"
											class="form-control">
											<option value="0" selected="selected">全部</option>
											<option value="1">超级管理员</option>
											<option value="2">管理员</option>
											<option value="3">普通用户</option>
										</select> -->
									</div>
									<!---------------------------------------------------------状态---------------------------------------------------------------------------  -->
									<div class="form-group col-lg-1">
										<label>审核状态</label>
										  <el-select v-model="fselect" style="width:100%" placeholder="请选择"  size="mini">
										    <el-option
										      v-for="item in fselects"
										      :key="item.id"
										      :label="item.logisticsName"
										      :value="item.shortName">
										    </el-option>
										  </el-select>
										<!-- <select id="fselect" name="productStatus" v-model="fselect"
											class="form-control">
											<option value="0" selected="selected">全部</option>
											<option value="1">通过</option>
											<option value="2">待审核</option>
											<option value="3">过滤</option>
											<option value="4">侵权</option>
											<option value="5">禁止上传</option>
											<option value="6">屏蔽</option>
										</select> -->
									</div>
									<div class="form-group col-lg-1">
										<label>产品状态</label>
										  <el-select v-model="fproduct" style="width:100%" placeholder="请选择"  size="mini">
										    <el-option
										      v-for="item in fproducts"
										      :key="item.id"
										      :label="item.logisticsName"
										      :value="item.shortName">
										    </el-option>
										  </el-select>
										 <!-- <select id="fproduct" name="productStatus" v-model="fproduct"
											class="form-control">
											<option value="0" selected="selected">全部</option>
											<option value="1">已上架</option>
											<option value="2">已下架</option>
											<option value="3">失效</option>
										</select> -->
									</div>
									<div class="form-group col-lg-1">
										<label>分级及图案</label>
										  <el-select v-model="flevel" style="width:100%" placeholder="请选择"  size="mini">
										    <el-option
										      v-for="item in flevels"
										      :key="item.id"
										      :label="item.logisticsName"
										      :value="item.shortName">
										    </el-option>
										  </el-select>
										   <!-- <select id="flevel" name="productStatus"  v-model="flevel"
											class="form-control">
											<option value="0" selected="selected">全部</option>
											<option value="1">未分级</option>
											<option value="2">图案设计</option>
											<option value="3">有图案设计</option>
											<option value="4">国内品牌</option>
											<option value="5">高风险</option>
										</select> -->
									</div>
	
									
									
									
									<div class="form-group col-lg-1">
										<label>特殊状态</label>
										  <el-select v-model="fban" style="width:100%" placeholder="请选择"  size="mini">
										    <el-option
										      v-for="item in fbans"
										      :key="item.id"
										      :label="item.logisticsName"
										      :value="item.shortName">
										    </el-option>
										  </el-select>
										 <!-- <select name="productStatus" id="fban"  v-model="fban"
											class="form-control">
											<option value="0" selected="selected">全部</option>
											<option value="1">重点</option>
											<option value="2">原创</option>
											<option value="3">海外</option>
											<option value="4">抓取</option>
											<option value="5">导入</option>
										</select> -->
									</div>
									<div class="form-group col-lg-1">
										<label>分销状态</label>
										  <el-select v-model="ffx" style="width:100%" placeholder="请选择"  size="mini">
										    <el-option
										      v-for="item in ffxs"
										      :key="item.id"
										      :label="item.logisticsName"
										      :value="item.shortName">
										    </el-option>
										  </el-select>
										<!--  <select name="productStatus" id="ffx"  v-model="ffx"
											class="form-control">
											<option value="0" selected="selected">全部</option>
											<option value="1">分销</option>
											<option value="2">不分销</option>
											<option value="3">没运费</option>
											<option value="4">无挂号</option>
											<option value="5">未分类</option>
											<option value="6">零库存</option>
										</select> -->
									</div>
									<div id="copy_text"></div>
									<div class="form-group col-lg-1">
										<label>日期</label>
										<el-select v-model="fdates" style="width:100%" placeholder="请选择"  size="mini">
										    <el-option
										      v-for="item in fdatess"
										      :key="item.id"
										      :label="item.logisticsName"
										      :value="item.shortName">
										    </el-option>
										  </el-select>
										<!--  <select name="productStatus" id="fdates" v-model="fdates"
											class="form-control">
											<option value="0" selected="selected">全部</option>
											<option value="1">七天内</option>
											<option value="2">一周前</option>
											<option value="3">一月前</option>
											<option value="4">三月前</option>
											<option value="5">半年前</option>
											<option value="6">一年前</option>
											<option value="7">未出单</option>
										</select> -->
									</div>
	
									<!-- 展示页数 -->
									<div class="form-group col-lg-1">
										<label>展示页数</label>
										<el-input  v-model.number="pagenum" id="pagenum" type="number" min="1" style="width:100%" size="mini"  placeholder="展示页数"></el-input>
										<!-- <input type="text" v-model="pagenum" id="pagenum" name="pagenum"
											class="form-control"  placeholder="展示页数" /> -->
									</div>
									<!-- -------------------------------------------功能区------------------------------------------------------------------ -->
									<div class="form-group col-lg-5" style="line-height:70px;margin-bottom:-5px">
										<el-row :gutter="20">
										  <el-col :span="2">
											<el-button @click="productLists()" type="success" size="mini">查询</el-button>
										  </el-col>
										  <el-col :span="2">
											<el-button  @click="reset" size="mini">重置</el-button>
										  </el-col>
										  <el-col :span="2">
											<el-button  @click="delcz" type="info" size="mini">删除</el-button>
										  </el-col>
										  <el-col :span="3">
											<el-button @click="dialogVisible = true" size="mini">图片转换</el-button>
										  </el-col>
										  <el-col :span="3">
										  	<el-upload
											  class="upload-demo"
											  :on-progress="toupload"
											  enctype="multipart/form-data"
											  action="${pageContext.request.contextPath}/admin/upload/import?${_csrf.parameterName}=${_csrf.token}"
											  method="post"
											  :on-error="uploaderror"
											  :show-file-list="false"
											  :on-success="upsuccess">
											  <el-button size="mini" type="primary">导入产品</el-button>
											</el-upload>
										  </el-col>
										  <el-col :span="3">
											<form id="exports_price" method="post" action="${pageContext.request.contextPath}/admin/upload/export?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
												<input id="idList" name="idList" type="text" v-model="checkedCities" style="display:none"/>
												<el-button size="mini" type="primary" @click="exports_price">导出选择</el-button>
											</form>
										  </el-col>
										  <el-col :span="3">
										  	
											<%-- <form id="exports_price2" method="post" action="${pageContext.request.contextPath}/admin/lazada?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
												<el-button size="mini" @click="exports_price2" >导出分类</el-button>
											</form> --%>
										  	<el-button size="mini" @click="shops=true">上传店铺</el-button>
										  </el-col>
										</el-row>
										<%-- <div class="text-right m-t-xs">
											<!-- <button id="allInner" class="btn btn-primary" 
													@click="checkAll"	type="button">
													<strong>全选</strong>
											</button>
											<button id="reverse" class="btn btn-primary" 
														@click="checkBack"	type="button">
														<strong>反选</strong>
											</button> -->
											<button class="btn btn-success" type="button"  @click="dialogVisible = true">
												<strong>图片转换</strong>
											</button>
											<button id="searchd" class="btn btn-success" type="button" @click="productLists(1)">
												<strong>查询</strong>
											</button>
											<button id="reset" class="btn btn-success" type="button" @click="reset">
												<strong>重置</strong>
											</button>
	
											<button id="unfold" class="btn btn-success" type="button" @click="unfold(1)">
												<strong>展开</strong>
											</button>
											
											<el-upload
											  class="upload-demo" style="display: inline;margin-top:2px;margin-left:10px"
											  :on-progress="toupload"
											  enctype="multipart/form-data"
											  action="${pageContext.request.contextPath}/admin/upload/import?${_csrf.parameterName}=${_csrf.token}"
											  method="post"
											  :on-error="uploaderror"
											  :show-file-list="false"
											  :on-success="upsuccess">
											  <el-button size="small" type="primary">导入产品</el-button>
											</el-upload>
											<form id="exports_price" method="post" style="float: right;margin-left:15px" action="${pageContext.request.contextPath}/admin/upload/export?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
												<input id="idList" name="idList" type="text" v-model="checkedCities" style="display:none"/>
												<el-button size="small" type="primary" @click="exports_price">导出选择</el-button>
											</form>
										</div> --%>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</el-header>
		<el-container>
			<el-main style="width:60%;padding:0px 10px;overflow:hidden">
				<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-body" >
							<el-checkbox :indeterminate="isIndeterminate" style="float:left;margin-left: 15px;" v-model="checkAlls" @change="handleCheckAllChange" border size="mini">全选</el-checkbox>
							<nobr style="float:left;padding: 2px 5px;line-height: 25px;font-size: 8px;">总数：{{total}} 选中：{{checkedCities.length}}</nobr>
							<el-checkbox-group  v-model="checkedCities" class="F-main-body" @change="handleCheckedCitiesChange">
							<!-- 分页 -->
							<nobr class="block" style="text-align: center;">
							  <el-pagination
								:page-size="pagenum"
		      					@current-change="handleCurrentChange"
							    layout="prev, pager, next"
							    :total="total">
							  </el-pagination>
							</nobr>
							<div id="panel1" style="overflow-y: auto;height:600px">
						    <el-row v-infinite-scroll="load(list,5)"  class="demo-image__error">
							  <el-col :span="4" style="margin:10px 15px;" v-for="(item, index) in list" :key="item.id" :offset="index > 0 ? 2 : 0">
							    <el-card :body-style="{ padding: '0px' }" shadow="hover">
								    <el-image :preview-src-list="['${pageContext.request.contextPath}/resources/upload/'+item.fpic]" fit="cover" style="width: 100%; height: 155px" :src="'${pageContext.request.contextPath}/resources/upload/'+item.fpic" v-if="item.fpic.indexOf('www.ec-sudo.com') == -1" lazy>
								      <div slot="error" class="image-slot"  style="font-size:30px;text-align:center;line-height:150px;color:#ccc">
								        <i class="el-icon-picture-outline"></i>
								      </div>
								    </el-image>
								    <el-image fit="cover" style="width: 100%; height: 155px" :preview-src-list="[item.fpic]" :src="item.fpic" v-if="item.fpic.indexOf('www.ec-sudo.com') != -1" lazy>
								      <div slot="error" class="image-slot" style="font-size:30px;text-align:center;line-height:150px;color:#ccc">
								        <i class="el-icon-picture-outline"></i>
								      </div>
								    </el-image>
							      <div style="padding: 10px;font-size:8px">
							        <span>单价：{{item.fprice}}￥  差价：{{item.fcost}}￥  </span>
							        <div class="bottom clearfix">
							          <el-checkbox  :label="item.id" :key="item.id">{{ellipsis(item.fchinese,5)}}</el-checkbox>
							          <el-button type="text" class="button" @click="addTab(item.id)" style="line-height: 17px;">详情</el-button>
							        </div>
							      </div>
							    </el-card>
							  </el-col>
							</el-row>
							</div>
							</el-checkbox-group>
						</div>
					</div>
					<template>
					  <el-backtop target="#panel1" style="font-size:12px;margin-right:300px;right:23%">
					  	顶部
					  </el-backtop>
					</template>
				</div>
				</div>
			</el-main>
	  		<el-main style="width:40%;padding:0px 10px 0px 0px;overflow:hidden">
				<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-body" id="panel1" style="overflow-y: auto;height:650px">
							<div style="margin-bottom: 20px;">
							  <el-button
							    size="small"
							    @click="clearTab()"
							  >
							    清空
							  </el-button>
							</div>
							<el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
							  <el-tab-pane
							    v-for="(itemc, indexex) in editableTabs"
							    :key="itemc.name"
							    :label="itemc.title"
							    :name="itemc.name"
							    style="color:#676363;font-size:14px;height:500px;overflow-y:auto;padding:10px"
							  >
							  	<el-row :gutter="20" class="gutters">
								  <el-col :span="12">商品编号：{{itemc.content.pro.id}}</el-col>
								  <el-col :span="12">商品名称：{{itemc.content.pro.fchinese}}</el-col>
								</el-row>
							    
							  	<el-row :gutter="20" class="gutters">
								  <el-col :span="24">产品分类：
								  <el-input v-model="itemc.content.pro.fkind" :id="itemc.content.pro.id" size="mini" style="width:60%" placeholder="请选择分类"  :disabled="true">
								  </el-input>
								  <el-button @click="openCate" size="mini">选择</el-button>
								  </el-col>
								</el-row>
								
							  	<el-row :gutter="20" class="gutters">
								  	<el-col :span="24" style="width:100%;height:200px;overflow:auto">
									    <div id="picss" v-for="(items,i) in itemc.content.pic"  :key="i">
									    	<el-image :preview-src-list="['${pageContext.request.contextPath}/resources/upload/'+items]" fit="cover" style="margin: 3px;float:left;width: 50px; height: 50px" :src="'${pageContext.request.contextPath}/resources/upload/'+items" v-if="items.indexOf('www.ec-sudo.com') == -1" lazy>
										      <div slot="error" class="image-slot"  style="font-size:20px;background:#fbfbf9;text-align:center;line-height:50px;color:#afaaaa">
										        <i class="el-icon-picture-outline"></i>
										      </div>
										    </el-image>
										    <el-image :preview-src-list="[items]" fit="cover" style="float:left;width: 50px; height: 50px;margin: 3px" :src="items" v-if="items.indexOf('www.ec-sudo.com') != -1" lazy>
										      <div slot="error" class="image-slot"  style="background:#fbfbf9;font-size:20px;text-align:center;line-height:50px;color:#afaaaa">
										        <i class="el-icon-picture-outline"></i>
										      </div>
										    </el-image>
										</div>
										<div id="mainUpdateimg" @click="selMain(itemc.content.pro.id,itemc.content.pro.fids,'add',itemc)"
											style="float: left; width: 50px; height: 50px; border: 1px solid #9BCD9B; text-align: center; line-height: 50px; margin: 3px">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										</div>
									</el-col>
								</el-row>
								
								
							  	<el-row :gutter="20" class="gutters" style="font-size:12px">
							  	
								  <el-col :span="6">审核状态
								  	  <el-select  v-model="itemc.content.pro.frs1" style="width:100%" placeholder="请选择"  size="mini">
									    <el-option
									      v-for="itemw in fselects"
									      :key="itemw.id"
									      :label="itemw.logisticsName"
									      :value="itemw.shortName">
									    </el-option>
									  </el-select>
								  </el-col>
								  <el-col :span="6">产品状态
								  	  <el-select v-model="itemc.content.pro.frs2" style="width:100%" placeholder="请选择"  size="mini">
									    <el-option
									      v-for="itemw in fproducts"
									      :key="itemw.id"
									      :label="itemw.logisticsName"
									      :value="itemw.shortName">
									    </el-option>
									  </el-select>
								  </el-col>
								  <el-col :span="6">分级及图案
								  	  <el-select v-model="itemc.content.pro.frs3" style="width:100%" placeholder="请选择"  size="mini">
									    <el-option
									      v-for="itemw in flevels"
									      :key="itemw.id"
									      :label="itemw.logisticsName"
									      :value="itemw.shortName">
									    </el-option>
									  </el-select>
								  </el-col>
								  <el-col :span="6">特殊状态
									  <el-select v-model="itemc.content.pro.frs4" style="width:100%"  placeholder="请选择"  size="mini">
									    <el-option
									      v-for="itemw in fbans"
									      :key="itemw.id"
									      :label="itemw.logisticsName"
									      :value="itemw.shortName">
									    </el-option>
									  </el-select>
								  </el-col>
								</el-row>
								
								<el-row :gutter="20" style="color:red">
								设置过滤，侵权和屏蔽，亚马逊上传明细将自动标注"待删除"
								</el-row>
								
							  	<el-row :gutter="20" class="gutters">
								  <el-col :span="6">业务员：<br/>{{itemc.content.pro.frs7}}</el-col>
								  <el-col :span="6">原产地区：<el-input v-model="itemc.content.pro.forigin" size="mini" placeholder="原产地区"></el-input></el-col>
								  <el-col :span="6">英文简称：<el-input v-model="itemc.content.pro.fenglish" size="mini" placeholder="英文简称"></el-input></el-col>
								  <el-col :span="6">中文简称：<el-input v-model="itemc.content.pro.fchinese" size="mini" placeholder="中文简称"></el-input></el-col>
								</el-row>
								
							  	<el-row :gutter="20" class="gutters">
								  <el-col :span="6">包装毛重：<el-input v-model="itemc.content.pro.fweight" size="mini" placeholder="包装毛重"></el-input></el-col>
								  <el-col :span="12">包装尺寸：<br/>
								  	<el-input v-model="itemc.content.pro.flength" size="mini" placeholder="长" style="width:25%"></el-input> x
								  	<el-input v-model="itemc.content.pro.fwidth" size="mini" placeholder="宽" style="width:25%"></el-input> x
								  	<el-input v-model="itemc.content.pro.fheight" size="mini" placeholder="高" style="width:25%"></el-input>
								  </el-col>
								  <el-col :span="6">产品币种：<el-input v-model="itemc.content.pro.fcur" size="mini" placeholder="产品币种"></el-input></el-col>
								</el-row>
								
							  	<el-row :gutter="20" class="gutters">
								  <el-col :span="6">
								  	<template>
									  <!-- `checked` 为 true 或 false -->
									  <el-checkbox v-model="storeChecked" size="mini" style="margin:0px">是否分销</el-checkbox>
									</template>
									<el-input v-model="itemc.content.pro.fprice" size="mini" placeholder="分销单价" :disabled="storeChecked==false"></el-input></el-col>
								  <el-col :span="6">成本单价：<el-input v-model="itemc.content.pro.fcost" size="mini" placeholder="成本单价"></el-input></el-col>
								  <el-col :span="6">运费:<nobr style="color:red">建议用国家运费</nobr><el-input v-model="itemc.content.pro.ffreight" size="mini" placeholder="固定运费"></el-input></el-col>
								  <el-col :span="6">适用人群：<el-input v-model="itemc.content.pro.fdepartment" size="mini" placeholder="适用人群"></el-input></el-col>
								</el-row>
								
								<el-row :gutter="20" class="gutters">
									<el-col :span="24">产品来源网址：
									<a style="color: red; margin-left: 20px" :href="itemc.content.pro.fsource" target="view_blank">点击查看</a>
									<el-input v-model="itemc.content.pro.fsource" size="mini" placeholder="产品来源网址"></el-input></el-col>
								</el-row>
								
								<el-row :gutter="20" class="gutters">
									<el-col :span="12">产品材料：
									<el-input v-model="itemc.content.pro.fmaterial" size="mini" placeholder="产品材料"></el-input></el-col>
									<el-col :span="12">内部SKU：
									<el-input v-model="itemc.content.pro.fsku" size="mini" placeholder="内部SKU" :disabled="true"></el-input></el-col>
								</el-row>
								
								<el-row :gutter="20" class="gutters">
									<el-col :span="24">产品变体<nobr style="color:red;font-weight:500">(请用斜杠（/）隔开)</nobr>
									</el-col>
								</el-row>
								
								<el-row :gutter="20" class="gutters">
									<el-col :span="12">颜色
										<el-input
										  type="textarea"
										  :autosize="{ minRows: 3, maxRows: 3}"
										  placeholder="请输入颜色"
										  v-model="itemc.content.stockColor">
										</el-input>
									</el-col>
									<el-col :span="12">尺寸
										<el-input
										  type="textarea"
										  :autosize="{ minRows: 3, maxRows: 3}"
										  placeholder="请输入尺寸"
										  v-model="itemc.content.stockSize">
										</el-input>
									</el-col>
								</el-row>
								
								<el-row :gutter="20" class="gutters">
									<el-col :span="4">
										<el-button @click="addStock(itemc)" size="mini">更新变体</el-button>
									</el-col>
									<el-col :span="4">
										<el-button @click.prevent="deleteStock(itemc)" size="mini" type="info">删除变体</el-button>
									</el-col>
									<el-col :span="4">
										<el-button type="primary" @click="selMain(itemc.content.pro.id,itemc.content.pro.fids,'update',itemc)"  size="mini">添加图片</el-button>
									</el-col>
								</el-row>
								<!-- 变体 -->
								<el-row :gutter="20" class="gutters">
									<el-col :span="24">
									<el-checkbox-group  v-model="rightclose" class="F-main-body" @change="handleCheckedCitiesChange3">
										<table class="table-striped " style="width: 100%">
											<tr style="color: black; font-size: 14px; line-height: 50px; background: white;">
												<th style="width: 100px; padding: 10px">
													<div style="float: left; margin-left: 14px">
													<el-checkbox :indeterminate="isIndeterminate3" style="color: red; font-size: 14px; line-height: 30px"  v-model="checkAlls3"   @change="handleCheckAllChange3" >全选</el-checkbox>
													</div>
												</th>
												<th>变体</th>
												<th>SKU(选填)</th>
												<th>库存</th>
												<th>加价</th>
												<th>图片</th>
											</tr>
											<tbody>
												<tr class="del" v-for="(itemq,i) in itemc.content.stock":key="i">
													<td style="width: 20px; padding: 10px">
														<el-checkbox :label="itemq.id"></el-checkbox>
													</td>
													<td>
														<el-input
														  placeholder="请输入内容"
														  size="mini"
														  style="width:100px"
														  :value="itemq.fcolor+'-'+itemq.fsize"
														  :disabled="true">
														</el-input>
													</td>
													<td>
														<el-input style="width:70px" v-model="itemq.fno" placeholder="请输入sku" size="mini"></el-input>
													</td>
													<td>
														<el-input style="width:70px" v-model="itemq.fnum" placeholder="请输入库存" size="mini"></el-input>
													</td>
													<td>
														<el-input style="width:70px" v-model="itemq.fadd" placeholder="请输入加价" size="mini"></el-input>
													</td>
													<td style="max-width: 150px; word-break: break-all">
														<div id="fimgs" v-if="itemq.fimg!=''">
															<el-image :preview-src-list="['${pageContext.request.contextPath}/resources/upload/'+iteme]" v-if="iteme.indexOf('www.ec-sudo.com') == -1" fit="cover" style="margin: 2.5px;float:left;width: 20px; height: 20px" :src="'${pageContext.request.contextPath}/resources/upload/'+iteme"  v-if="itemq!=''" :key="i+'s'"  v-for="(iteme,i) in catStock(itemq.fimg)"  lazy>
														      <div slot="error" class="image-slot"  style="font-size:10px;background:#fbfbf9;text-align:center;line-height:20px;color:#afaaaa">
														        <i class="el-icon-picture-outline"></i>
														      </div>
														    </el-image>
														    <el-image :preview-src-list="[iteme]" v-if="iteme.indexOf('www.ec-sudo.com') != -1" fit="cover" :key="i+'w'" style="margin: 2.5px;float:left;width: 20px; height: 20px" :src="iteme"  v-if="itemq!=''"  v-for="(iteme,i) in catStock(itemq.fimg)" lazy>
														      <div slot="error" class="image-slot"  style="font-size:10px;background:#fbfbf9;text-align:center;line-height:20px;color:#afaaaa">
														        <i class="el-icon-picture-outline"></i>
														      </div>
														    </el-image>
														</div> 
													</td>
												</tr>
											</tbody>
										</table>
										</el-checkbox-group>
									</el-col>
								</el-row>
								<el-row :gutter="20" class="gutters"
												v-loading="loading2"
												element-loading-text="我在翻译中。。。别急"
												element-loading-spinner="el-icon-loading"
												element-loading-background="rgba(0, 0, 0, 0.8)">
									<el-col :span="24">
										<!-- 选项卡菜单-->
										<!-- <ul id="myTab" class="nav nav-tabs" role="tablist"
											style="margin-bottom: 30px">
											<li class="cs" v-for="(item,i) in itemc.content.languages">
												<a :href="'#'+i" role="tab" data-toggle="tab">{{changeLang(i)}}</a>
											</li>
										</ul> -->
										  <el-tabs>
										    <el-tab-pane :label="changeLang(item3.country)"  v-for="(item3,i) in itemc.content.languages" :key="item3.country+'q'">
										    	<button type="button" class="btn btn-default" style="margin-left:10px" @click="changes(item3.country,item3,itemc)">
													翻译
													</button>
													<div class="form-group col-xs-12">
														<label>产品标题({{changeLang(item3.country)}})</label>
														<el-input
														  type="textarea"
														  :autosize="{ minRows: 3, maxRows: 3}"
														  placeholder="请输入内容"
														  v-model="item3.title">
														</el-input>
														<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符({{changeLang(item3.country)}})</label>
														<el-input
														  type="textarea"
														  :autosize="{ minRows: 3, maxRows: 3}"
														  placeholder="请输入内容"
														  v-model="item3.points">
														</el-input>
														<label>产品描述：不要输入html标签({{changeLang(item3.country)}})</label>
														<el-input
														  type="textarea"
														  :autosize="{ minRows: 3, maxRows: 3}"
														  placeholder="请输入内容"
														  v-model="item3.content">
														</el-input>
														<label>关键词：不超过五行 逗号，空格或回车分隔({{changeLang(item3.country)}})</label>
														<el-input
														  type="textarea"
														  :autosize="{ minRows: 3, maxRows: 3}"
														  placeholder="请输入内容"
														  v-model="item3.word">
														</el-input>
													</div>
											</el-tab-pane>
										  </el-tabs>
										<!-- <el-row v-loading="loading2" element-loading-text="拼命加载中"
										    element-loading-spinner="el-icon-loading"
										    element-loading-background="rgba(0, 0, 0, 0.8)">
											<div id="myTabContent" class="tab-content" style="width:100%">
												<div class="tab-pane" :id="i" v-for="(item3,i) in itemc.content.languages">
													
												</div>
											</div>
										</el-row> -->
									</el-col>
								</el-row>
								<el-row :gutter="20" class="gutters">
									<el-col :span="4">
										<el-button @click="updatePanels(itemc)" size="mini">保存</el-button>
									</el-col>
									<el-col :span="4">
										<el-button @click="deletePanels" type="danger" size="mini">删除</el-button>
									</el-col>
								</el-row>
							  </el-tab-pane>
							</el-tabs>
						</div>
					</div>
				</div>
				</div>
			</el-main>
	  	</el-container>
</el-container>
<el-dialog
  title="提示"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose">
    <span>
    <el-input
	  type="textarea"
	  :autosize="{ minRows: 1, maxRows: 10}"
	  placeholder="请输入链接多个用|隔开"
	  v-model="input">
	</el-input>
	</span>
	<span>
		<el-upload
		  style="margin-top:20px"
		  class="upload-demo"
		  ref="upload"
		  action="${pageContext.request.contextPath}/admin/newUploadfile?${_csrf.parameterName}=${_csrf.token}"
		  :on-preview="handlePreview"
		  :on-remove="handleRemove"
		  :on-success="handleSuccess"
		  :on-change="listee"
		  :file-list="fileList"
		  :auto-upload="false"
		  multiple>
		  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
		 <!--  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button> -->
		  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
		</el-upload>
	</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false;postUrl()">确 定</el-button>
  </span>
</el-dialog>
<!-- 图片展示 -->
<el-dialog
  title="展示"
  :visible.sync="dialogVisible2"
  width="30%"
  style=""
  :before-close="handleClose">
  <div style="overflow: auto;height:600px">
  <span v-for="item in imglists">
  	<div style="width:20%"><img style="float: left;margin:3px;" :src="'${pageContext.request.contextPath}'+item" width="60px" height="60px"></div>
  	<a style="float: right;height:60px;width:80%" @click="copys('localhost:11080${pageContext.request.contextPath}'+item)">{{"localhost:11080${pageContext.request.contextPath}"+item}}</a>
  </span>
  </div>
</el-dialog>
<!-- 主图详情 -->
<el-dialog
  :title="mainText"
  :visible.sync="mainview"
  width="60%"
  style=""
  :before-close="handleClose">
  <div style="overflow: auto;height:570px">
	  <el-checkbox-group  v-model="mainclose" class="F-main-body" @change="handleCheckedCitiesChange">
		  <span v-for="item in Mainimglist">
			    <el-card  style="width:100px;height:120px;float:left;margin:5px":body-style="{ padding: '0px' }">
			      <el-image :preview-src-list="['${pageContext.request.contextPath}/resources/upload/'+item.fOnlineUrl]" fit="cover" style="width:100px;height:100px" :src="'${pageContext.request.contextPath}/resources/upload/'+item.fOnlineUrl" lazy>
				     <div slot="error" class="image-slot"  style="font-size:30px;text-align:center;line-height:150px;color:#ccc">
				       <i class="el-icon-picture-outline"></i>
				     </div>
				  </el-image>
			      <el-checkbox  :label="item.id" :key="item.id"></el-checkbox>
			    </el-card>
		  </span>
	  </el-checkbox-group>
  </div>
  <div style="height:30px">
  	<el-checkbox :indeterminate="isIndeterminate" style="float:left;margin-left: 10px;" v-model="checkAlls2" @change="handleCheckAllChange2" border size="mini">全选</el-checkbox>
	<el-upload  v-if="btn=='add'"
	  class="upload-demo" style="float:left"
	  action="${pageContext.request.contextPath}/upload?${_csrf.parameterName}=${_csrf.token}"
	  :on-success="uploadSuccess"
	  :show-file-list="false"
	  :data="dates">
	  <el-button size="mini" icon="el-icon-plus" style="margin-left:10px">图片添加</el-button>
	</el-upload>
	<el-button  style="float:left;margin-left: 10px;" @click="deletMain" v-if="btn=='add'" type="danger" icon="el-icon-delete" size="mini">删除</el-button>
	<el-button  style="float:left;margin-left: 10px;" @click="mainview=false" size="mini">关闭</el-button>
	<el-button  style="float:left;margin-left: 10px;" @click="pl(stock)" v-if="btn=='update'" size="mini">修改</el-button>
  </div>
</el-dialog>
<el-dialog
  title="上传店铺"
  :visible.sync="shops"
  width="30%"
  :before-close="handleClose">
  <el-button  @click="uploadShop('lazada')">lazada</el-button>
  <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="shops = false">确 定</el-button>
  </span>
</el-dialog>
<!-- <el-dialog
  title="选择分类"
  :visible.sync="catesOrder"
  width="60%"
  :before-close="handleClose">
  <el-tabs tab-position="left" style="height: 800px;">
    <el-tab-pane :label="f1" v-for="f1 in cateList1">
        <el-tabs tab-position="left" style="height: 800px;">
            <el-tab-pane :label="f2.cate2"  v-for="f2 in cateList" v-if="f1==f2.cate1">{{f2.cate2}}</el-tab-pane>
        </el-tabs>
    </el-tab-pane>
  </el-tabs>
  <span slot="footer" class="dialog-footer">
    <el-button @click="catesOrder = false">取 消</el-button>
    <el-button type="primary" @click="catesOrder = false">确 定</el-button>
  </span>
</el-dialog> -->
</div>			
</div>
<script type="text/javascript">
/*
 * vue框架编写查询
 */
 //全局路径
 Vue.http.options.root="${pageContext.request.contextPath}";
 var vm=new Vue({
		el:'#apps',
		data() {
			return {
			//搜索名称
			fname:"",
			//产品编号
			fids:"",
			//产品分类
			fcateid:"",
			//最低价
			fprice1:"",
			//最高价
			fprice2:"",
			//更新日期
			fdate1:"",
			//截至日期
			fdate2:"",
			//业务员
			ywys:"",
			dates:{},
			//审核状态
			fselect:"",
			//产品状态
			fproduct:"",
			//分级及图案
			flevel:"",
			//特殊状态
			fban:"",
			//分销状态
			ffx:"",
			//日期
			fdates:"",
			//展示数
			pagenum:200,
			//展示页
			pages:1,
			//返回list对象
			list:[],
			//所有被选择的产品
			idList:"",
			endpage:"",//尾页
			dialogVisible: false,//图片弹窗
			catesOrder:false,//分类弹窗
			cateList:[],//分类列表
			cateList1:[],//分类列表
			input:"",//链接地址
			fileList: [],//图片列表
			listch:[],
			imglists:[],//存储的图片列表
			Mainimglist:[],//主图
			mainclose:[],//主图选中
			rightclose:[],//主图选中
			mainText:"",//主图介绍
			dialogVisible2:false,//图片展示
			checkAlsl: false,//全选
	        checkedCities: [],//选中的
	        //cities: cityOptions,//所有选项
	        isIndeterminate: true,
	        isIndeterminate3:true,
	        shops:false,//上传店铺弹窗
	        itemcc:{},
	        loading:false,//加载圈
	        contents:{},
	        btn:"",
	        pickerOptions2: {
	              shortcuts: [{
	                text: '最近一周',
	                onClick(picker) {
	                  const end = new Date();
	                  const start = new Date();
	                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
	                  picker.$emit('pick', [start, end]);
	                }
	              }, {
	                text: '最近一个月',
	                onClick(picker) {
	                  const end = new Date();
	                  const start = new Date();
	                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
	                  picker.$emit('pick', [start, end]);
	                }
	              }, {
	                text: '最近三个月',
	                onClick(picker) {
	                  const end = new Date();
	                  const start = new Date();
	                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
	                  picker.$emit('pick', [start, end]);
	                }
	              }]
	            },//快捷时间
	            value6:[],//时间列表
	            ywyss:[],//业务员
	            fselects:[
	            	{
	    	         logisticsName:"全部",
	    	         shortName:"0",
	    	         id:"0"
	    	        },
	            	{
	    	         logisticsName:"通过",
	    	         shortName:"1",
	    	         id:"1"
	    	        },
	            	{
	    	         logisticsName:"待审核",
	    	         shortName:"2",
	    	         id:"2"
	    	        },
	            	{
	    	         logisticsName:"过滤",
	    	         shortName:"3",
	    	         id:"3"
	    	        },
	            	{
	    	         logisticsName:"侵权",
	    	         shortName:"4",
	    	         id:"4"
	    	        },
	            	{
	    	         logisticsName:"禁止上传",
	    	         shortName:"5",
	    	         id:"5"
	    	        },
	            	{
	    	         logisticsName:"屏蔽",
	    	         shortName:"6",
	    	         id:"6"
	    	        }
	            ],//审核状态
	            fproducts:[
	            	{
	    	         logisticsName:"全部",
	    	         shortName:"0",
	    	         id:"0"
	    	        },
	            	{
	    	         logisticsName:"已上架",
	    	         shortName:"1",
	    	         id:"1"
	    	        },
	            	{
	    	         logisticsName:"已下架",
	    	         shortName:"2",
	    	         id:"2"
	    	        },
	            	{
	    	         logisticsName:"失效",
	    	         shortName:"3",
	    	         id:"3"
	    	        }
	            ],//产品状态
	            flevels:[
	            	{
	    	         logisticsName:"全部",
	    	         shortName:"0",
	    	         id:"0"
	    	        },
	            	{
	    	         logisticsName:"未分级",
	    	         shortName:"1",
	    	         id:"1"
	    	        },
	            	{
	    	         logisticsName:"没图案设计",
	    	         shortName:"2",
	    	         id:"2"
	    	        },
	            	{
	    	         logisticsName:"有图案设计",
	    	         shortName:"3",
	    	         id:"3"
	    	        },
	            	{
	    	         logisticsName:"国内品牌",
	    	         shortName:"4",
	    	         id:"4"
	    	        },
	            	{
	    	         logisticsName:"高风险",
	    	         shortName:"5",
	    	         id:"5"
	    	        }
	            ],//分级及图案
	            fbans:[
	            	{
	    	         logisticsName:"全部",
	    	         shortName:"0",
	    	         id:"0"
	    	        },
	            	{
	    	         logisticsName:"重点",
	    	         shortName:"1",
	    	         id:"1"
	    	        },
	            	{
	    	         logisticsName:"原创",
	    	         shortName:"2",
	    	         id:"2"
	    	        },
	            	{
	    	         logisticsName:"海外",
	    	         shortName:"3",
	    	         id:"3"
	    	        },
	            	{
	    	         logisticsName:"抓取",
	    	         shortName:"4",
	    	         id:"4"
	    	        },
	            	{
	    	         logisticsName:"导入",
	    	         shortName:"5",
	    	         id:"5"
	    	        }
	            ],//特殊状态
	            ffxs:[
	            	{
	    	         logisticsName:"全部",
	    	         shortName:"0",
	    	         id:"0"
	    	        },
	            	{
	    	         logisticsName:"分销",
	    	         shortName:"1",
	    	         id:"1"
	    	        },
	            	{
	    	         logisticsName:"不分销",
	    	         shortName:"2",
	    	         id:"2"
	    	        },
	            	{
	    	         logisticsName:"没运费",
	    	         shortName:"3",
	    	         id:"3"
	    	        },
	            	{
	    	         logisticsName:"无挂号",
	    	         shortName:"4",
	    	         id:"4"
	    	        },
	            	{
	    	         logisticsName:"未分类",
	    	         shortName:"5",
	    	         id:"5"
	    	        },
	            	{
	    	         logisticsName:"零库存",
	    	         shortName:"6",
	    	         id:"6"
	    	        }
	            ],//分销状态
	            fdatess:[
	            	{
	    	         logisticsName:"全部",
	    	         shortName:"0",
	    	         id:"0"
	    	        },
	            	{
	    	         logisticsName:"七天内",
	    	         shortName:"1",
	    	         id:"1"
	    	        },
	            	{
	    	         logisticsName:"一周前",
	    	         shortName:"2",
	    	         id:"2"
	    	        },
	            	{
	    	         logisticsName:"一月前",
	    	         shortName:"3",
	    	         id:"3"
	    	        },
	            	{
	    	         logisticsName:"三月前",
	    	         shortName:"4",
	    	         id:"4"
	    	        },
	            	{
	    	         logisticsName:"半年前",
	    	         shortName:"5",
	    	         id:"5"
	    	        },
	            	{
	    	         logisticsName:"一年前",
	    	         shortName:"6",
	    	         id:"6"
	    	        },
	            	{
	    	         logisticsName:"未出单",
	    	         shortName:"7",
	    	         id:"7"
	    	        }
	            ],//日期
	            editableTabsValue: '2',
	            editableTabs: [],
	            tabIndex: 2,
	            pic:[],//主图
	            storeChecked:false,//是否分销
	          	//颜色输入框
				stockColor:"",
				//尺寸输入框
				stockSize:"",
				//存储变体地址
				stock:[],
				//语言list
				languages:{},
				loading2: false,//加载圈
				total:0,//总数
				checkedCities:[],//选中
		        checkAlls: false,//全选
		        checkAlls2:false,//全选2
		        checkAlls3:false,//全选3
		        mainview:false,//主图展示
				}
			},
			created(){
				this.productLists()
				/* let datas={}
				//ajiox请求方式
				this.$http.post("admin/Foam_Cate_list?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
		 			//存储对比值
		 			let obj={}
		 			//存储结果
		 			let results={}
		 			let results2=[]
		 			
					for(let p of result.body){
		 				if(!obj[p.cate1]){
		 					//存储不重复值，利用对象的属性不会重复
		 					results[p.cate1]
		 					results2.push(results[p.cate1])
		 					obj[p.cate1]=1
		 				}else{
		 					if(!obj[p.cate2]){
		 						results2.push(p)
			 					obj[p.cate2]=1
		 					}else{
		 						
		 					}
		 				}
		 				
		 			}
					this.cateList1=results
					this.cateList=results2
				}) */
				//ajiox请求方式
				this.$http.post("admin/selectAllUser?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
					this.ywyss=result.body
				})
			},
			updated(){
				//this.fcateid=$("#fcateid").val()
				//console.log($("#fcateid").val())
			},
			methods:{
				productLists(){
					//创建方法
					let datas={}
					datas["page"]=this.pages
					datas["limit"]=this.pagenum
					datas["fen"]=this.fname
					datas["fsku"]=this.fids
					datas["price1"]=this.fprice1
					datas["price2"]=this.fprice2
					datas["fkind"]=this.fkind
					datas["frs1"]=this.fselect
					datas["frs2"]=this.fproduct
					datas["frs3"]=this.flevel
					datas["frs4"]=this.fban
					datas["frs5"]=this.ffx
					datas["frs6"]=this.fdates
					this.fcateid=$("#fcateid").val()
					datas["fcateid"]=this.fcateid
					datas["updateTime1"]=this.value6[0]
					datas["updateTime2"]=this.value6[1]
					datas["frs7"]=this.ywys
					//ajiox请求方式
					this.$http.post("admin/productListAtAll?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						this.list=result.body.list
						this.endpage=result.body.pages
						this.total=result.body.total
						this.checkedCities=[]
					})
				},
		        handleCheckAllChange(val) {
			    	//全选
			    	var lists=[]
			   		for(let i=0;i<this.lists.list.length;i++){
			   		   lists.push(this.lists.list[i].id)
			   	    }
		          	this.checkedCities = val ? lists : [];
		          	this.isIndeterminate = false;
	            },
		        handleCheckAllChange2(val) {
			    	//全选
			    	var lists=[]
			   		for(let i=0;i<this.Mainimglist.length;i++){
			   		   lists.push(this.Mainimglist[i].id)
			   	    }
		          	this.mainclose = val ? lists : [];
		          	this.isIndeterminate = false;
		        },
		        handleCheckAllChange3(val) {
			    	//全选
			    	var lists=[]
			    	for(let e=0;e<this.editableTabs.length;e++){
			    		if(this.editableTabs[e].title===this.editableTabsValue){
					   		for(let i=0;i<this.editableTabs[e].content.stock.length;i++){
					   		   lists.push(this.editableTabs[e].content.stock[i].id)
					   	    }
			    		}
			    	}
		          	this.rightclose = val ? lists : [];
		          	this.isIndeterminate3 = false;
		        },
				selAll(){
					//单选
					var checkS = $(".Checks")
					//获取全选勾选框
					let checke = $(".CheckALL")
					//假设全选是选中的
					let isSelectAll=true
					let reslut=""
					for(var i = 0;i<checkS.length;i++){
						if(checkS[i].checked==true){
			                reslut+=checkS[i].id+","
			        	}else{
			        		isSelectAll=false
			        	}
					}
					checke[0].checked=isSelectAll
					this.idList=reslut.substr(0,reslut.length-1)
				},
				checkAll(){
					//全选
					//获取全选勾选框
					let checke = $(".CheckALL");
					//获取所有的勾选框
					let checkS = $(".Checks");
					let reslut=""
					//进行判断
					if(checke[0].checked){
						checke[0].checked=false
						//迭代
						for(var i = 0;i<checkS.length;i++){
					　　　　checkS[i].checked=false
						}
					}else{
					　　checke[0].checked=true
						//迭代
						for(var i = 0;i<checkS.length;i++){
					　　　　checkS[i].checked=true
		                	reslut+=checkS[i].id+","
						}
					}

					this.idList=reslut.substr(0,reslut.length-1)
				},
				checkBack(){
						//反选
						//获取所有的勾选框
						let checkS = $(".Checks");
						let reslut=""
						//迭代
						for(var i = 0;i<checkS.length;i++){
					　　　　if(checkS[i].checked){
					　　　　　　checkS[i].checked=false
					　　　　}else{
					　　　　　　checkS[i].checked=true
		                		reslut+=checkS[i].id+","
					　　　　}
						}
						this.idList=reslut.substr(0,reslut.length-1)
				},
				reset(){//重置
					this.fname=""
					this.fids=""
					this.fcateid=""
					this.fprice1=""
					this.fprice2=""
					this.value6=[]
					this.ywys=""
					this.fselect=""
					this.fproduct=""
					this.flevel=""
					this.fban=""
					this.ffx=""
					this.fdates=""
					this.pagenum=200
				},
				ellipsis(value,leng){
		        	  //长度限制
		                if (!value) return '';
		                if (value.length > leng) {
		                    return value.slice(0,leng) + '...'
		                }
		                return value
		          },
				unfold(num){
					if(num==0){
						$("#rightright").css("display","none")
						$("#selectselect").css("display","block")
						$("#wrapper").css("width","")
						$("#panel1").css("height","650px")
					}else if(num==1){
						$("#rightright").css("display","block")
						$("#selectselect").css("display","none")
						$("#wrapper").css("width","49%")
						$("#panel1").css("height","820px")
					}
				},
				addPanel(data){//商品详情
					this.unfold(1)
					//添加详情页选项卡
				 	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
					var pp = $('#tts').tabs('getSelected');
					
					//记录是否重复
					var cf=0;
					
					//获取选项卡的总长度
					var tabss=$('#tts').tabs('tabs');
					var t=tabss.length;
					//获取到每个标签的标题
					var idqs = document.getElementsByClassName("tabs-title");
					if(idqs.length != 0){
						for(var c=0;c<idqs.length;c++){
						//控制台进行对比	
							if(idqs[c].innerText==data){
								z=idqs[c].innerText;
								cf++;
							}
					}}
					 if (cf==0) {
						 $('#tts').tabs('add',{
								title: data,
								content:'<iframe scrolling="yes" id="mainiframe" name="mainiframe" width="105.5%" height="100%" frameborder="0px" style="margin:-20px;padding-top:20px;overflow:hidden" src="/Shopping/admin/productFright?id='+data+'"></iframe>',
								closable: true
							});
							
						} else {
				        	$('#tts').tabs('select', z);//选中并刷新
				        } 
				},
				splits(texts,cat){
					return texts.split(cat)
				},
		        handleCurrentChange(val) {
				  //分页改变
				 this.pages=val
				 this.productLists()
		        },
				bing(texts,add){
					return texts+"/"+add
				},
				removePanels(){//清空其他
					// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
					var pp = $('#tts').tabs('getSelected')
					var tab=pp.panel('options').tab//相应的标签页tab对象
					//获取选项卡的总长度
					var tabss=$('#tts').tabs('tabs')
					var t=tabss.length
					if (pp){
					//关闭所有的框
					var index=$("#tts").tabs('getTabIndex',pp)
					var y=0
					for(var cd=0;cd<t;cd++){
						if(cd<(index)){
							$('#tts').tabs('close', 0)
							y++
						}else if(cd>(index)){
							$('#tts').tabs('close', 1)
							y++
						}
						}
					}
				},
				closePanelsY(){//关闭
					this.unfold(0)
				},
				closePanels(){//关闭并清空
					// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
					var pp = $('#tts').tabs('getSelected');
					//获取选项卡的总长度
					var tabss=$('#tts').tabs('tabs');
					var t=tabss.length;
					if (pp){
					//关闭所有的框
					var y=0;
					for(var cd=0;cd<t;cd++){
						if(cd<t){
							$('#tts').tabs('close', 0);
							y++;
						}else if(cd>t){
							$('#tts').tabs('close', 1);
							y++;
						}
					}
				}
					this.unfold(0)
				},
	            clearTab(){
	            	//清空所有标签
	            	this.editableTabs=[]
	            },
				indexs(){
					var left=1
					var right=this.endpage
					var ar=[]
					if(this.endpage>=5){
						if(this.pages>3 && this.pages < this.endpage -2){
							left = this.pages -2
							right =this.pages +2
						}else{
							if(this.pages<=3){
								left = 1
								right = 5
							}else{
								left=this.endpage-4
								right=this.endpage
							}
						}
					}
					while(left<=right){
						ar.push(left)
						left++
					}
					return ar
				},
				handleClose(done) {
					//关闭弹窗
			        this.$confirm('确认关闭？')
			          .then(_ => {
			        	this.imglists=[]
			        	this.fileList=[]
			        	this.input=""
			            done();
			          })
			          .catch(_ => {});
		      },
		      submitUpload() {
		    	  //图片提交
		          this.$refs.upload.submit();
		        },
		        handleRemove(file, fileList) {
		        	//图片删除
		          console.log(file, fileList);
		        },
		        handlePreview(file) {
		          console.log(file);
		        },
		        handleSuccess(response, file, fileList){
		        	//文件上传成功
		        	this.imglists.push(response.fileName)
		        },
		      postUrl(){
		    	  ///admin/downloadPictures 提交链接
		    	  let list=this.input.split("|")
		    	  let rest=[]
		    	  for(let i=0;i<list.length;i++){
		    		  rest.push(list[i])
		    	  }
				  if(rest!=""){
					  //请求连接
					//ajiox请求方式
					this.$http.post("admin/downloadimg?${_csrf.parameterName}=${_csrf.token}",rest).then(result=>{
						for(let t=0;t<result.body.length;t++){
							this.imglists.push(result.body[t])
						}
					})
				  }
				  if(this.listch!=""){
					  //请求下载
					this.submitUpload()
				  }
				  if(rest=="" && this.listch==""){
					this.$message('请添加图片')
					return false
				  }
				  this.dialogVisible2=true
		      },
		      listee(file, fileList){
		    	  this.listch=fileList
		      },
		      copys(data){
		    	  //复制
		    	  //js
			      const input = document.createElement('input')
			      document.body.appendChild(input)
			      input.setAttribute('value',data)
			      input.select()
			      if (document.execCommand('copy')) {
			        document.execCommand('copy')
				    this.$message.success('复制成功')
			      }
			      document.body.removeChild(input)
		      },
		      async shows(id){
					//查询订单列表
	  	    	  	let datae={}
	  	    	  	datae["id"]=id
	  	    	    await this.$http.post("admin/getAllproducts?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
	  	    	    	let pro=result1.body.pro
	  	    	    	this.contents={}
						let dat={}
						//this.fids=pro.fids
						let languages=[]
	  	    	    	languages.push(this.catlang(pro.fzh,'zh'))
	  	    	    	languages.push(this.catlang(pro.fen,'en'))
	  	    	    	languages.push(this.catlang(pro.fja,'ja'))
	  	    	    	//languages.push(this.catlang(pro.fru,'ru'))
						languages.push(this.catlang(pro.fes,'es'))
						languages.push(this.catlang(pro.fit,'it'))
						languages.push(this.catlang(pro.fde,'de'))
						languages.push(this.catlang(pro.ffr,'fr'))
	  	    	    	dat=result1.body
	  	    	    	//dat["list"]=result1.body
	  	    	    	dat["languages"]=languages
						//颜色
						dat["stockColor"]=""
						for(let i=0;i<result1.body.stock.length;i++){
							if(result1.body.stock[i].fcolor!=""){
								if(i==result1.body.stock.length-1){
									dat["stockColor"]+=result1.body.stock[i].fcolor
								}else{
									dat["stockColor"]+=result1.body.stock[i].fcolor+"/"
								}
							}
						}

						//尺寸
						dat["stockSize"]=""
						for(let a=0;a<result1.body.stock.length;a++){
							if(result1.body.stock[a].fsize!=""){
								if(a==result1.body.stock.length-1){
									dat["stockSize"]+=result1.body.stock[a].fsize
								}else{
									dat["stockSize"]+=result1.body.stock[a].fsize+"/"
								}
							}
						}
						console.log(dat)
						this.pic=result1.body.pic
	  	    	    	this.contents=dat
	  	    	 	})
		      },
		      upsuccess(response, file, fileList){
		    	  //产品添加成功
		    	  this.productLists()
		    	  this.$message.success("上传成功"+response+"个产品")
		    	  
		      },
		      toupload(event, file, fileList){
		    	  //文件开始上传
		    	  this.$message.success(file.name+"开始上传")
		      },
		      uploaderror(err, file, fileList){
		    	  //文件上传失败
		    	  this.$message.error(file.name+"上传失败")
		      },
		      handleCheckAllChange(val) {
		    	  //全选
		   		   var lists=[]
		   		   for(let i=0;i<this.list.length;i++){
		   			   lists.push(this.list[i].id)
		   		   }
		          this.checkedCities = val ? lists : [];
		          this.isIndeterminate = false;
	          },
	          handleCheckedCitiesChange(value) {
	            let checkedCount = value.length;
	            this.checkAlls = checkedCount === this.list.length;
	            this.isIndeterminate = checkedCount > 0 && checkedCount < this.list.length;
	          },
	          handleCheckedCitiesChange3(value) {
		            let checkedCount = value.length;
		            this.checkAlls = checkedCount === this.list.length;
		            this.isIndeterminate3 = checkedCount > 0 && checkedCount < this.list.length;
		          },
	          exports_price(){
		        //导出erp数据
	         	 if(this.checkedCities==""){
	         		 toastr.error("请选择需要导出的数据");
						 return false;
	         	 }else{
	         		 $("#exports_price").submit()
	         	 }
	          },
	          exports_price2(){
	        	  $("#exports_price2").submit()
	          },
	          load (val,val2) {
	              val += val2
	          },
	          async addTab(val) {
	                //i用来记数，如果为0表示是新的标签，为1就是已经有的标签
	            	let i=0
	                this.editableTabs.forEach(item=>{
	                	if(item.name==val){
	                		this.editableTabsValue = val+""
	                		i++
	                		return false
	                	}
	                }) 
	                if(i==0){
		                await this.shows(val)
		                this.editableTabs.push({
		                  title: val+"",
		                  name: val+"",
		                  content: this.contents
		                });
		                this.editableTabsValue = val+""
	                }
	            },
	            removeTab(targetName) {
	              let tabs = this.editableTabs;
	              let activeName = this.editableTabsValue;
	              if (activeName === targetName) {
	                tabs.forEach((tab, index) => {
	                  if (tab.name === targetName) {
	                    let nextTab = tabs[index + 1] || tabs[index - 1];
	                    if (nextTab) {
	                      activeName = nextTab.name;
	                    }
	                  }
	                });
	              }
	              
	              this.editableTabsValue = activeName;
	              this.editableTabs = tabs.filter(tab => tab.name !== targetName);
	            },
				updatePanels(val){//保存
					this.loading=true				
					this.$http.post("adminsdf/productUpdate?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(val.content),{emulateJSON:true}).then(result=>{
						let pro=result.body
						this.loading=false			
						if(pro>=0){
							this.$message.success("保存成功")
						}else{
							this.$message.error("保存失败")
						}
					})
				},
				deletePanels(){//删除
					let datas={}
					datas["id"]=this.editableTabsValue
					Swal.fire({
						  title: '你确定吗?',
						  text: "删除后无法恢复!",
						  icon: 'warning',
						  showCancelButton: true,            
						    confirmButtonColor: '#3085d6',// 确定按钮的 颜色
						    confirmButtonText: '确定',// 确定按钮的 文字
						    showCancelButton: true, // 是否显示取消按钮
						    cancelButtonColor: '#d33', // 取消按钮的 颜色
						    cancelButtonText: "取消", // 取消按钮的 文字
						}).then((result) => {
						  if (result.value) {
							//ajiox请求方式
							 this.$http.post("admin/productDelete?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
								 Swal.fire(
									      '删除!',
									      '您已经移除了它.',
									      'success'
									    )
								this.productLists()
								this.removeTab(this.editableTabsValue)
							})
						  }else{
							  Swal.fire("取消", "点击了取消", "error");
						  }
						})
				},
				catlang(datas,val){//截取
					let list={}
					if(datas!=null){
						let det=datas.split("|")
						list["title"]=det[0]
						list["points"]=det[2]
						list["content"]=det[3]
						list["word"]=det[1]
					}else{
						list["title"]=""
						list["points"]=""
						list["content"]=""
						list["word"]=""
					}
					list["country"]=val
					return list
				},//语言翻译
				changeLang(data){
					switch(data){
					case "en":
					    return '英语'
					case "fr":
						return '法语'
					case "de":
						return '德语'
					case "ja":
						return '日语'
					case "es":
						return '西班牙语'
					case "it":
						return '意大利语'
					case "zh":
						return '中文'
					case "ru":
						return '俄罗斯'
					}
				},
				changes(data,content,va){
					//翻译
					this.loading2=true
					this.changeLangs(data,content,va)
				},
				changeLangs(data,content,va){
					let result=""
					//判断是否为空
					if(content==""){
						toastr.error("需要翻译的内容不能为空")
						return false
					}
					let datas={}
					//key值word   
					let dets=""
					dets+=content.title==undefined?"":content.title
					dets+="|"
					dets+=content.points==undefined?"":content.points
					dets+="|"
					dets+=content.content==undefined?"":content.content
					dets+="|"
					dets+=content.word==undefined?"":content.word
					datas["word"]=dets
					//ajiox请求方式
					this.$http.post("http://192.168.1.176:5000/trans",datas).then(result=>{
						let leng=result.body.message
						console.log(result.body)
						let languages=[]
						languages.push(this.tile(leng.zh,'zh'))
						languages.push(this.tile(leng.en,'en'))
						languages.push(this.tile(leng.ja,'ja'))
						languages.push(this.tile(leng.es,'es'))
						languages.push(this.tile(leng.it,'it'))
						languages.push(this.tile(leng.de,'de'))
						languages.push(this.tile(leng.fr,'fr'))
						va.content.languages=languages
						this.$forceUpdate()
						this.loading2=false
					})
				},
				tile(datas,val){
					let list={}
					if(datas!=null){
						let det=datas.split("|")
						list["title"]=det[0]
						list["points"]=det[1]
						list["content"]=det[2]
						list["word"]=det[3]
					}else{
						list["title"]=""
						list["points"]=""
						list["content"]=""
						list["word"]=""
					}
					list["country"]=val
					return list
				},
				catStock(data){
					//裁剪变体
					if(data!=null){
						return data.split("|")
					}
					return null
				},
				selMain(val,val2,val3,val4){
					if(val3=="update"){
						if(this.rightclose.length==0){
							this.$message.error("请选择变体")
							return false
						}
					}
					//主图查询
					this.btn=val3
					//图片编辑按钮
					let datas={}
					datas["id"]=val
					this.dates={}
					this.dates["fSku"]=val2
					this.dates["ides"]=val
					this.Fids=val
					this.$http.post("admin/selMainImgs?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{

						if(val3=="add"){
							this.Mainimglist=result.body
							this.mainText="主图查看"
							this.stock=val4
							//this.shows(val)
						}else if(val3=="update"){
							this.Mainimglist=result.body
							this.mainText="图片批量添加"
							this.stock=val4
						}
						this.mainview=true
					})
				
				},
				uploadSuccess(response, file, fileList){//上传成功后
					this.$message.success("添加成功")
					console.log(response[0])
					this.$forceUpdate()
					this.selMain(this.Fids,response[0].fSku,"add")
				},
				deletMain(){
					//主图删除
					//2.发送删除请求
					this.$http.post("admin/deletMainImgs?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.mainclose),{emulateJSON:true}).then(result=>{
						let imglist=result.body
						if(imglist>0){
							this.selMain(this.Fids,this.dates.fSku,"add")
							//查询订单列表
			  	    	  	let datae={}
			  	    	  	datae["id"]=this.editableTabsValue
			  	    	    this.$http.post("admin/getAllproducts?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
			  	    	    	this.contents["list"]=result1.body
								this.$forceUpdate()
								this.$message.success("删除成功")
			  	    	 	})
							//this.shows(this.dates.ides)
						}else{
							this.$message.error("删除失败")
						}
					})
				},
				addStock(itemc){
					//更新变体
					
					let color=itemc.content.stockColor.split("/")
					let size=itemc.content.stockSize.split("/")
					let result=[]
					//添加所有的可能
					let or=0
					for(let a=0;a<color.length;a++){
						for(let b=0;b<size.length;b++){
								or++
								result.push({fadd:"",
											fcolor:color[a],
											fid:this.fids,
											file:"",
											fimg:"",
											fno:"",
											fnum:30,
											fsize:size[b],
											id:or})
						}
					}
					//判断值是否存在,如果存在则赋值
					for(let i=0;i<result.length;i++){
						for(let t=0;t<itemc.content.stock.length;t++){
							if((result[i].fcolor+result[i].fsize)==(itemc.content.stock[t].fcolor+itemc.content.stock[t].fsize)){
								result[i]=itemc.content.stock[t]
							}
						}
					}
					itemc.content.stock=result
				},
				deleteStock(itemc){
					//删除指定
					for(let i = 0;i<itemc.content.stock.length;i++){
						for(let c=0;c<this.rightclose.length;c++){
							if(itemc.content.stock[i].id==this.rightclose[c]){
				                itemc.content.stock.splice(i,1)
				        	}
						}
					}
				},
				pl(itemc){
					let result=""
					for(let c = 0;c<this.mainclose.length;c++){
						for(let i=0;i<this.Mainimglist.length;i++){
							if(this.Mainimglist[i].id==this.mainclose[c]){//获取图片地址
								result+="|"+this.Mainimglist[i].fOnlineUrl
				        	}
						}
					}
					result=result.substr(1,result.length)
					for(let a=0;a<this.rightclose.length;a++){
						for(let i=0;i<itemc.content.stock.length;i++){
							if(itemc.content.stock[i].id==this.rightclose[a]){
								itemc.content.stock[i].fimg=result
				        	}
						}
					}
					this.mainview=false
				},
				openCate(){
					f_kind_selects("3",vm.editableTabsValue)
				},
				delcz(){
					if(this.checkedCities.length==0){
						this.$message.error("请选择产品")
						return false
					}else{
						this.$confirm("是否删除这"+this.checkedCities.length+"条？请谨慎操作!!!").then(_ => {
							//删除
							this.$http.post("admin/delet/delcz?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
								this.$message.success("成功删除"+result.body+"条数据")
								this.productLists()
							})
						})
					}
				},
				async uploadShop(row){
					//上传店铺
					if(this.checkedCities.length==0){
						this.$message.error("请选择产品")
						return false
					}
					this.loading=true
					if(row=="lazada"){
						//lazada
						// this.$http.post("lazada/uploadShop?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
						//	console.log(result.body.msg)
						//	})
						 window.location.href="${pageContext.request.contextPath}/lazada/postProduct/"+this.checkedCities
						this.loading=false
					}
				}
			}
 })

</script>
</body>
</html>