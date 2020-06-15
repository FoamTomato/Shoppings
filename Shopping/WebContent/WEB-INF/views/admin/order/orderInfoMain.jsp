<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page session="true"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>订单列表</title>
	<jsp:include page="../VueElementCss.jsp"/> 
	<jsp:include page="../VueElement.jsp"/> 
	<%-- <script
	src="${pageContext.request.contextPath}/resources/admin/js/cvi_busy_lib1.js"></script> --%>
	<style type="text/css">
      .text {
	    font-size: 14px;
	  }
	  .item {
	    margin-bottom: 18px;
	  }
	
	  .clearfix:before,
	  .clearfix:after {
	    display: table;
	    content: "";
	  }
	  .clearfix:after {
	    clear: both
	  }
	
	  .box-card {
	    margin:5px;
	    float:left
	  }
	  .el-row {
	    margin-bottom: 20px;
	    &:last-child {
	      margin-bottom: 0;
	    }
	  }
	  .el-col {
	    border-radius: 4px;
	  }
	</style>
</head>
<body class="blank" style="overflow:hidden">
<jsp:include page="../main/top.jsp"/> 
<jsp:include page="../main/left.jsp"/>
<jsp:include page="HJ_post_track.jsp"/>  
<!-- 加载条 -->
<%-- 
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/admin/jquery-barcode.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/jquery-barcode.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jss/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jss/logisticsChange.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script>
<script src="${pageContext.request.contextPath}/resources/jss/sweetalert2@9.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/resources/jss/jquery-migrate-1.4.1.js"></script> --%>
<%-- 
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tanathos-jquery.jqprint-13a4f0e/jquery.jqprint-0.3.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/aes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/sha1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/evpkdf.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/hmac.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/cipher-core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/enc-base64.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/crypto-js-develop/src/lib-typedarrays.js"></script>--%>
<!-- 引入饼图 -->
<%-- <script src="${pageContext.request.contextPath}/resources/jss/echarts.min.js"></script> --%>

<%-- <jsp:include page="prints.jsp"/>  --%>
<div id="apps">
<jsp:include page="prints.jsp" />
<jsp:include page="batch_add.jsp"/>
<jsp:include page="summary_modal.jsp"/> 
<jsp:include page="yida_modal.jsp"/> 
<div id="wrapper"
	v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)">
    <el-container>
    <el-aside :style="{'width':left}">
  		<el-header  :style="{'height':'160px'}">
  			<div class="animate-panel" style="padding:20px 0px ">
		    <div class="hpanel">
		        <div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="hpanel">
								<div>
										<div class="form-group col-lg-2">
											<label>查询内容</label>
											<el-input v-model="orderNum" id="orderNum" style="width:100%" size="mini" oninput="myFunction()" placeholder="订单/亚马逊编号,跟踪号/集拼号,id"></el-input>
										</div>
										<div class="form-group col-lg-2">
											<label>订单状态</label>
											<el-select v-model="StatusOrder" placeholder="请选择" style="width:100%" size="mini">
											    <el-option
											      v-for="item in ztoptions"
											      :key="item.value"
											      :label="item.name"
											      :value="item.value">
											    </el-option>
											  </el-select>
										</div>
										<div class="form-group col-lg-2">
											<label>物流方式</label>
											<el-select v-model="hj_shippingMethods" style="width:100%" placeholder="请选择"  size="mini">
											    <el-option
											      v-for="item in logoutlist"
											      :key="item.id"
											      :label="item.logisticsName"
											      :value="item.shortName">
											    </el-option>
											  </el-select>
										</div>
										<div class="form-group col-lg-6">
										    <label>选择日期</label>
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
										    </el-date-picker>
										</div>
										
										</div>
										<div class="text-right m-t-xs">
											   <div style="float:left">
												<nobr style="margin-top:10px">展示 <input min=0 type="number" v-model="pagsNums" style="width:50px"></nobr>
												<el-checkbox v-model="allChe" style="margin:-1px">运单</el-checkbox>
												<el-radio v-model="jps" label="2" style="margin:-1px">全部</el-radio>
  												<el-radio v-model="jps" label="1" style="margin:-1px">已集拼</el-radio>
  												<el-radio v-model="jps" label="0" style="margin:-1px">未集拼</el-radio>
												<el-checkbox v-model="pps" style="margin:-1px">未匹配</el-checkbox>
												</div>
											   <input type="checkbox" name="stockCheckboxFid" style="display:none">
											   <div style="float:right">
											   <el-button type="success" size="small" @click="selectall()" icon="el-icon-search" style="margin-left:10px">查询</el-button>
											   
											   <el-button type="warning" size="small"  @click="reset" icon="el-icon-refresh">重置</el-button>
											   
											   <el-button type="primary" size="small" @click="operator = true" icon="el-icon-edit-outline">操作</el-button>
											   
											   <c:if test="${fn:contains(jurisdiction.jurisdiction,'order_del')}">
											   	<el-button type="info" size="small" @click="delalls" icon="el-icon-close">删除</el-button>
											   </c:if>
											   </div>
												<!-- <a onclick="test()">====</a> -->
										</div>		
										<div style="width:100%;float:left">选中:{{checkedCities.length}} 总数:{{total}}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
  		</el-header>
	    <el-main>
			<div class="animate-panel" style="padding:20px 0px ">	
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-body">
							<div style="margin-left:20px;width:100%">
								<div style="float:left;padding-top: 6px">
									<el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
									<nobr style="color:red">红</nobr>/未发件，<nobr style="color:blue">蓝</nobr>/已发件，<nobr style="color:green">绿</nobr>/已录入erp，<nobr style="color:#c925d478">紫</nobr>/问题件 
								</div>
								<!-- 分页 -->
								<div style="text-align: center;margin-bottom:5px;width:80%">
										<el-pagination
										  background
										  :page-size="pagsNums"
		      							  @current-change="handleCurrentChange"
										  layout="prev, pager, next"
										  :total="total">
										</el-pagination>
								</div>
							</div>
							<div id="F-main-body"  style="height:577px;overflow-y:auto">
							<el-checkbox-group  v-model="checkedCities" class="F-main-body" @change="handleCheckedCitiesChange">
								<el-card class="box-card" :body-style="{background:colors(item.standby6),color:'white'}" shadow="hover" v-for="item in lists.list" :key="item.id">
								  <div class="text item">
								  	<el-checkbox  :label="item.id"  style="color:#FFFFCC;margin-right:15px" :key="item.id">
								    	订单
									</el-checkbox>{{item.fIds}}
								    <el-button style="float: right; padding: 3px 0;color:white" type="text" @click="addTab(editableTabsValue,item.fIds,item.id)">查看</el-button>
								  </div>
								  <div class="text item">
								    {{ellipsis(item.fStore,15)}}
								  </div>
								  <div class="text item">
								    物流：{{ellipsis(changeLogis(item.standby12),10)}}
								  </div>
								  <div class="text item">
								   集拼号：{{ellipsis(item.standby14,13)}}
								  </div>
								  <div class="text item">
								    跟踪号：{{ellipsis(item.standby11,13)}}
								  </div> 
								</el-card>
							</el-checkbox-group>
							</div>
						</div>
					</div>
				</div>
				</div>
			</div>
		</el-main>
		</el-aside>
	<!-- 右侧内容 -->
	<el-container :style="{'width':right}">
	    <el-main >
			<!-- <div class="small-header transition animated fadeIn"> -->
				
					
				<!-- </div> -->
				
			 <div class=" animate-panel"style="margin-left:-25px;">
				<div class="row">
					<div class="col-lg-12">
						<div class="hpanel">
							<div class="panel-body" style="height:837px">
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
								    v-for="(item, index) in editableTabs"
								    :key="item.name"
								    :label="item.title"
								    :name="item.name"
								  >
								    <el-row :gutter="20" style="padding:20px;border-bottom:1px dashed #ccc">
									  <el-col :span="12">
									  	id:{{item.content.id}}
									  </el-col>
									  <el-col :span="12">
									  	中文简称:{{item.content.fChinaShort}}
									  </el-col>
									</el-row>
									
									
								    <el-row :gutter="20" :style="{padding:'0px 20px','word-break':'break-all'}"  style="border-bottom:1px dashed #ccc">
									  <el-col :span="12" :style="{color:colors(item.content.standby6)}" style="border-right:1px dashed #ccc">
									  	<el-row :gutter="20">
									  		<el-col :span="12">
									  			送货人:Standard
									  		</el-col>
									  		<el-col :span="12">
									  			<a @click="edit(item.content,index)" style="color:black">编辑</a> |
									  			<a @click="copy(item.content)" style="color:black">复制</a>
									  		</el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20">
									  		<el-col :span="24">
									  			收货人:{{item.content.fFirstName+item.content.fLastName}}
									  		</el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20">
									  		<el-col :span="24">
									  			地址:{{item.content.fAddress1+" "+item.content.fAddress2+" "+item.content.fAddress3}}
									  		</el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20">
									  		<el-col :span="12">
									  			邮编:{{item.content.fPostCode}}
									  		</el-col>
									  		<el-col :span="12">
									  			国家:{{item.content.fCountry}}
									  		</el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20">
									  		<el-col :span="24">
									  			电话:{{item.content.fTelephone}}
									  		</el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20">
									  		<el-col :span="24">
									  			邮箱:{{item.content.fEmail}}
									  		</el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20">
									  		<el-col :span="24">
									  			备注:{{item.content.fRemark}}
									  		</el-col>
									  	</el-row>
									  </el-col>
									  <el-col :span="12">
									  	<el-row :gutter="20">
									  	 <el-col :span="24">
									  		亚马逊编号:{{item.content.fClientOrderCode}}
									  	 </el-col>
									  	</el-row>
									  	 
									  	<el-row :gutter="20">
									  	 <el-col :span="24">
									  		店铺:{{item.content.fStore}}
									  	 </el-col>
									  	</el-row>
									  	 <span style="font-size:10px;color:red">(注意：是当地国家的币种)</span><br/>
									  	
									  	<el-row :gutter="20"> 
									  	 <el-col :span="8">
									  		订单:{{item.content.fTotalPrice}}
									  	 </el-col>
									  	 <el-col :span="8">
									  		手续:{{item.content.standby1}}
									  	 </el-col>
									  	 <el-col :span="8">
									  		结余:{{item.content.fTotalPrice-item.content.standby1}}
									  	 </el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20"> 
									  	 <el-col :span="24">
									  		利润:{{item.content.standby2}}-{{item.content.standby3}}=<nobr style="color:green">{{item.content.standby4}}</nobr>
									  	 </el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20"> 
									  	 <el-col :span="8" style="line-height:50px">
									  		百分比:{{(100*item.content.standby4 / (item.content.standby2)).toFixed(2)}}%
									  	 </el-col>
									  	 <el-col :span="5">
									  	 	<el-progress :width="50" :height="50" size="small" type="circle" v-if="item.content.standby4!='0.00'" :percentage="parseFloat((100*item.content.standby4 / (item.content.standby2)).toFixed(2))"  :stroke-width="1"></el-progress>
									  	 </el-col>
									  	 <el-col :span="11">
									  	 	<el-button
											    size="small"
											    style="margin-top:10px"
											    @click="orderSave(item.content)"
											  >
											    保存
											  </el-button>
									  	 	<el-button
											    size="small"
											    style="margin-top:10px"
											    @click="caDia=true"
											  >
											    操作
											  </el-button>
									  	 </el-col>
									  	</el-row>
									  	
									  	<el-row :gutter="20"> 
									  	 <el-col :span="12">
									  		订单状态:
											<el-select  v-model="item.content.standby6" style="width:50%" size="mini"  placeholder="请选择">
											    <el-option
											      v-for="item in orderStatus"
											      :key="item.value"
											      :label="item.label"
											      :value="item.value">
											    </el-option>
											  </el-select>
									  	 </el-col>
									  	 <el-col :span="12">
									  	 	集拼状态:
											<el-select  v-model="item.content.standby13" style="width:50%"  size="mini" placeholder="请选择">
											    <el-option
											      v-for="item in jpStatus"
											      :key="item.value"
											      :label="item.label"
											      :value="item.value">
											    </el-option>
											  </el-select>
										 </el-col>
									  	</el-row>
									  </el-col>
									</el-row>
									<div style="overflow:auto;height:280px;padding:20px">
										<el-row :gutter="20"  v-for="(items,indexs) in item.content.list.hj" :key="indexs+'hj'">
											  <el-col :span="12">
											  <input type="radio" :name="'hjor'+item.name" v-if="items.hjStandy2=='1'" value="0" checked  @click="radioslo('0',items.id,'',item.content.fIds,items.hjShippingmethod,index)" > 
											  <input type="radio" :name="'hjor'+item.name" v-if="items.hjStandy2!='1'" value="0"   @click="radioslo('0',items.id,'',item.content.fIds,items.hjShippingmethod,index)" > 
											  	运单号:<nobr style="color:red;font-weight:900">{{items.hjShipperhawbcode}}</nobr></el-col>
											  <el-col :span="12" style="line-height:22px;font-weight: 900;">
											  <a @click="selectOrder_HJ(items.id,'0')">查看</a>|
											  <a @click="geteveryone(items.hjStandy5,items.hjShippingmethod,'0',item.title)">打印</a>|
											  <a @click="delete_HJ(items.id,items.hjShipperhawbcode,'0',indexs,index)">删除</a></el-col>
											  <el-col :span="12">物流:<nobr style="color:black">{{hjShippingmethods(items.hjShippingmethod)}}</nobr></el-col>
											  <el-col :span="12">跟踪:<nobr style="color:black">{{items.hjStandy7}}</nobr></el-col>
											  <el-col :span="12">参考号:<nobr style="color:black">{{items.hjReferenceno}}</nobr></el-col>
											  <el-col :span="12">创建日期:<nobr style="color:black">{{getDate(items.hjStandy1)}}</nobr></el-col>
										</el-row>
										<el-row :gutter="20" v-for="(itemy,indexs) in item.content.list.yd" :key="indexs+'yd'">
											  <el-col :span="12">
											  <input type="radio" :name="'hjor'+item.name" v-if="itemy.ydStandy2=='1'" value="2" checked  @click="radioslo('2',itemy.id,itemy.ydShippingMethodNo,item.content.fIds,itemy.ydShippingMethod,index)" > 
											  <input type="radio" :name="'hjor'+item.name" v-if="itemy.ydStandy2!='1'" value="2"  @click="radioslo('2',itemy.id,itemy.ydShippingMethodNo,item.content.fIds,itemy.ydShippingMethod,index)" > 
											     运单号:<nobr style="color:red;font-weight:900">{{itemy.ydStandy3}}</nobr></el-col>
											  <el-col :span="12" style="line-height:22px;font-weight: 900;">
											  <a @click="selectOrder_HJ(itemy.id,'2')">查看</a>|
											  <a @click="geteveryone(itemy.ydReferenceNo,'','1',item.title)">打印</a>|
											  <a @click="delete_HJ(itemy.id,itemy.ydReferenceNo,'1',indexs,index)">删除</a></el-col>
											  <el-col :span="12">物流:<nobr style="color:black">{{hjShippingmethods(itemy.ydShippingMethod)}}</nobr></el-col>
											  <el-col :span="12">跟踪:<nobr style="color:black">{{itemy.ydShippingMethodNo}}</nobr></el-col>
											  <el-col :span="12">参考号:<nobr style="color:black">{{itemy.ydReferenceNo}}</nobr></el-col>
											  <el-col :span="12">创建日期:<nobr style="color:black">{{itemy.ydStandy5}}</nobr></el-col>
										</el-row>
										<el-row :gutter="20" v-for="(itemy,indexs) in item.content.list.cz" :key="indexs+'cz'">
											  <el-col :span="12">
											  <input type="radio" :name="'hjor'+item.name" v-if="itemy.fsr3=='1'" value="3" checked  @click="radioslo('3',itemy.id,itemy.clientReference,item.content.fIds,itemy.service,index)" > 
											  <input type="radio" :name="'hjor'+item.name" v-if="itemy.fsr3!='1'" value="3"  @click="radioslo('3',itemy.id,itemy.clientReference,item.content.fIds,itemy.service,index)" > 
											     运单号:<nobr style="color:red;font-weight:900">{{itemy.fsr2}}</nobr></el-col>
											  <el-col :span="12" style="line-height:22px;font-weight: 900;">
											  <a @click="selectOrder_HJ(itemy.id,'3')">查看</a>|
											  <a @click="geteveryone(2,'','3',item.title)">打印</a>|
											  <a @click="delete_HJ(itemy.id,itemy.fsr2,'3',indexs,index)">删除</a></el-col>
											  <el-col :span="12">物流:<nobr style="color:black">{{hjShippingmethods(itemy.service)}}</nobr></el-col>
											  <el-col :span="12">跟踪:<nobr style="color:black">{{itemy.fsr4}}</nobr></el-col>
											  <el-col :span="12">参考号:<nobr style="color:black">{{itemy.clientReference}}</nobr></el-col>
											  <el-col :span="12">创建日期:<nobr style="color:black">{{getDate(itemy.fsr1)}}</nobr></el-col>
										</el-row>
									</div>
								  </el-tab-pane>
								</el-tabs>
							</div>
						</div>
					</div>
				</div>
			</div> 
			</el-main>
	</el-container>
<el-container>
</div>
<!--右侧功能区  -->
<el-dialog
  title="右侧功能区"
  :visible.sync="caDia"
  width="30%"
  :before-close="handleClose">
  	<span>
	  	<el-row :gutter="20">
		  <el-col :span="4">
			<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_hjlogistic')}">
				<el-button  size="mini" @click="selectOrder_HJ('','1')">环金物流</el-button>
			</c:if>
		  </el-col>
		  
		  <el-col :span="4">
			<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_hjlogistic')}">
				<el-button  size="mini" @click="resetYd();caDia=false;optionList='3';dialogVisible = true">义达物流</el-button>
			</c:if>
		  </el-col>
		  
		  <el-col :span="4">
				<el-button  size="mini" @click="czs2()">创志物流</el-button>
		  </el-col>
		  
		  <el-col :span="4">
			<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_post')}">
				<el-button type="primary" size="mini" @click="submitOrderOnes">发送运单</el-button>
			</c:if>
		  </el-col>
		  
		  <el-col :span="4">
			<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_post')}">
				<!-- <el-button type="primary" size="mini" @click="yidaUpdate">修改义达</el-button> -->
			</c:if>
		  </el-col>
		  
		</el-row>
	</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="caDia = false">取 消</el-button>
    <el-button type="primary" @click="caDia = false">确 定</el-button>
  </span>
</el-dialog>
<!-- 环金添加修改弹窗 -->
<el-dialog
  title="环金操作区"
  :visible.sync="hjWindow"
  width="40%"
  :before-close="handleClose">
  
  <el-row :gutter="20">
	  <el-col :span="12">国家英文:<el-input v-model="hjOr.hjConsigneecountryename" size="mini" placeholder="请输入国家英文"></el-input></el-col>
	  <el-col :span="12">国家中文:<el-input v-model="hjOr.hjConsigneecountrycname" size="mini" placeholder="请输入国家中文"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">英文:<el-input v-model="hjOr.hjInvoiceenname" size="mini" placeholder="请输入英文"></el-input></el-col>
	  <el-col :span="12">中文:<el-input v-model="hjOr.hjInvoicecnname" size="mini" placeholder="请输入中文"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">城市:<el-input v-model="hjOr.hjConsigneecity" size="mini" placeholder="请输入城市"></el-input></el-col>
	  <el-col :span="12">省州:<el-input v-model="hjOr.hjConsigneeprovince" size="mini" placeholder="请输入省州"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">公司:<el-input v-model="hjOr.hjConsigneecompany" size="mini" placeholder="请输入公司"></el-input></el-col>
	  <el-col :span="12">单位:<el-input v-model="hjOr.hjUnitcode" size="mini" placeholder="请输入单位"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">总价:<el-input v-model="hjOr.hjInvoiceunitcharge" size="mini" placeholder="请输入总价"></el-input></el-col>
	  <el-col :span="12">币种:<el-input v-model="hjOr.hjInvoicecurrencycode" size="mini" placeholder="请输入币种"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">sku:<el-input v-model="hjOr.hjSku" size="mini" placeholder="请输入sku"></el-input></el-col>
	  <el-col :span="12">数量:<el-input v-model="hjOr.hjInvoicequantity" size="mini" placeholder="请输入数量"></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="24">物流:
	  <el-select v-model="hjOr.hjShippingmethod" style="width:100%" size="mini" placeholder="请选择物流">
	    <el-option
	      v-for="item in logoutlist" v-if="item.status=='2'"
	      :key="item.id"
	      :label="item.logisticsName"
	      :value="item.shortName">
	    </el-option>
	  </el-select>
	  </el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="12">海关协制编号:<el-input v-model="hjOr.hjHscode" size="mini" placeholder="请输入海关协制编号"></el-input></el-col>
	  <el-col :span="12">产品销售价格:<el-input v-model="hjOr.hjSaleprice" size="mini" placeholder="请输入产品销售价格"></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="12">销售结算币种:<el-input v-model="hjOr.hjSalecurrency" size="mini" placeholder="请输入销售结算币种"></el-input></el-col>
	  <el-col :span="12">产品中文类目:<el-input v-model="hjOr.hjCategoryname" size="mini" placeholder="请输入产品中文类目"></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="12">产品英文类目:<el-input v-model="hjOr.hjParentenname" size="mini" placeholder="请输入产品英文类目"></el-input></el-col>
	  <el-col :span="12">重量/g:<el-input v-model="hjOr.hjInvoiceweight" size="mini" placeholder="请输入重量/g"></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="12">外包装数:<el-input v-model="hjOr.hjOrderpieces" size="mini" placeholder="请输入外包装数"></el-input></el-col>
	  <el-col :span="12">申报种类:<el-input v-model="hjOr.hjMailcargotype" size="mini" placeholder="请输入申报种类"></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="8">长:<el-input v-model="hjOr.hjLength" size="mini" placeholder="请输入长"></el-input></el-col>
	  <el-col :span="8">宽:<el-input v-model="hjOr.hjWidth" size="mini" placeholder="请输入宽"></el-input></el-col>
	  <el-col :span="8">高:<el-input v-model="hjOr.hjHeight" size="mini" placeholder="请输入高"></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="8"><el-checkbox v-model="hjOr.hjIscontainsbattery" true-label="1" false-label="0">是否包含电池</el-checkbox></el-col>
	  <el-col :span="8"><el-checkbox v-model="hjOr.hjIsaneroidmarkup" true-label="1" false-label="0">非液体化妆品</el-checkbox></el-col>
	  <el-col :span="8"><el-checkbox v-model="hjOr.hjIsonlybattery" true-label="1" false-label="0">是否纯电池</el-checkbox></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="8"><el-checkbox v-model="hjOr.hjIsbreakable" true-label="1" false-label="0">是否易碎</el-checkbox></el-col>
	  <el-col :span="8"><el-checkbox v-model="hjOr.hjIscharged" true-label="1" false-label="0">是否带电</el-checkbox></el-col>
	  <el-col :span="8"><el-checkbox v-model="hjOr.hjIsreturn" true-label="1" false-label="0">是否退回</el-checkbox></el-input></el-col>
	</el-row>
  </span>
  
  <span slot="footer" class="dialog-footer">
    <el-button @click="hjWindow = false">取 消</el-button>
    <el-button type="primary" v-if="hjType=='1'" @click="hjWindowAdd()">添加</el-button>
    <el-button type="primary" v-if="hjType=='2'" @click="hjWindowEdit(hjOr);">修改</el-button>
  </span>
</el-dialog>
<!-- 右侧编辑 -->
<el-dialog
  title="基本信息编辑"
  :visible.sync="edits"
  width="30%"
  :before-close="handleClose">
  <span>
  	<el-row :gutter="20">
	  <el-col :span="12">姓名:<el-input v-model="F_name" size="mini" placeholder="请输入姓名"></el-input></el-col>
	  <el-col :span="12">公司:<el-input v-model="F_company" size="mini" placeholder="请输入公司"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">地址:<el-input v-model="F_address" size="mini" placeholder="请输入地址"></el-input></el-col>
	  <el-col :span="12">城市:<el-input v-model="F_city" size="mini" placeholder="请输入城市"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">州省:<el-input v-model="F_province" size="mini" placeholder="请输入州省"></el-input></el-col>
	  <el-col :span="12">国家:<el-input v-model="F_country" size="mini" placeholder="请输入国家"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">邮编:<el-input v-model="F_post_code" size="mini" placeholder="请输入邮编"></el-input></el-col>
	  <el-col :span="12">电话:<el-input v-model="F_phone" size="mini" placeholder="请输入电话"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">邮箱:<el-input v-model="F_email" size="mini" placeholder="请输入邮箱"></el-input></el-col>
	  <el-col :span="12">备注:<el-input v-model="F_remark" size="mini" placeholder="请输入备注"></el-input></el-col>
	</el-row>

  </span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="edits = false">取 消</el-button>
    <el-button type="primary" @click="editSave();edits = false">确 定</el-button>
  </span>
</el-dialog>
<!-- 操作功能区 -->
<el-dialog
  title="操作功能区"
  :visible.sync="operator"
  width="40%"
  :before-close="handleClose">
  
  <el-row :gutter="20">
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_postasync')}">
			<el-button size="small" type="primary" @click="submitOrder" icon="el-icon-upload2">环金同步</el-button>
		</c:if>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_post')}">
			<el-button size="small" type="primary" @click="syncOrder()" icon="el-icon-upload2">环金异步</el-button>
		</c:if>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_post')}">
	 		<el-button size="small" type="primary" @click="traceNumber()" icon="el-icon-share">环金跟踪</el-button>
		</c:if>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_print')}">
	 		<el-button size="small" type="primary" @click="print()" icon="el-icon-printer">环金打印</el-button>
		</c:if>
	 </el-col>
	
	 <el-col :span="4">
		<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_add')}">
			<el-button size="small" type="primary" @click="myModalBatchAdd()" icon="el-icon-plus">环金添加</el-button>
		</c:if>
	 </el-col>
	  	
	 <el-col :span="4">
	  	   <c:if test="${fn:contains(jurisdiction.jurisdiction,'order_outof_pdf')}">
		   	<el-button type="warning" size="small" @click="edit_summart()" icon="el-icon-menu">环金集拼</el-button>
		   </c:if>	
	 </el-col>
	</el-row>
	
	<el-row :gutter="20">
	
	  <el-col :span="4">
	  	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_import')}">
			<el-upload
			  class="upload-demo"
			  :on-progress="toupload"
			  enctype="multipart/form-data"
			  action="${pageContext.request.contextPath}/admin/import?${_csrf.parameterName}=${_csrf.token}"
			  method="post"
			  :on-change="handleChange"
			  :show-file-list="false"
			  :file-list="fileList3"
			  :on-success="upsuccess">
			  <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
			</el-upload>
		</c:if>
	 </el-col>
	  <el-col :span="4">
		<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_email')}">
			<form id="formtyw3" method="post" action="${pageContext.request.contextPath}/admin/export3?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
			   <input id="idList3" name="idList3" type="text" style="display:none"/>
			   <el-button size="small" @click="excel_bu3" type="danger" icon="el-icon-download">导出邮政</el-button>
			</form>
		</c:if>
	  </el-col>
	  
	  <el-col :span="4">
		<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_outof')}">
			<form id="formtyw2" method="post" action="${pageContext.request.contextPath}/admin/export2?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
				<input id="idList2" name="idList2" type="text" value="" style="display:none"/>
			    <el-button size="small" @click="excel_bu2" type="danger" icon="el-icon-download">导出集拼</el-button>
			</form>
		</c:if>
	  </el-col>
	  
	  <el-col :span="4">
	  	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_out')}">
			<form id="formtyw" method="post" action="${pageContext.request.contextPath}/admin/export?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
			   <input id="idList" name="idList" type="text" value="" style="display:none"/>
			   <el-button size="small" @click="excel_buttonssss" type="danger" icon="el-icon-download">导出商品</el-button>
			</form>
		</c:if>
	  </el-col>
	  				
	  <el-col :span="4">
	  	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_out')}">
			<form id="formtyw4" method="post" action="${pageContext.request.contextPath}/admin/export/Estimate?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
			   <input id="idList4" name="idList4" type="text" value="" style="display:none"/>
			   <el-button size="small" @click="list4" type="danger" icon="el-icon-download">导出预估</el-button>
			</form>
		</c:if>	
	  </el-col>

	  <!-- <el-col :span="4">
		   	<el-button type="primary" size="small" @click="ydlabel" icon="el-icon-upload2">义达标签</el-button>
	  </el-col> -->
	  
	</el-row>
	
	<el-row :gutter="20">
	 <el-col :span="4">
		<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_add')}">
			<el-button size="small" type="primary" @click="dialogVisible=true" icon="el-icon-plus">义达添加</el-button>
		</c:if>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_postasync')}">
			<el-button size="small" type="primary" @click="submityida" icon="el-icon-upload2">义达发送</el-button>
		</c:if>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_print')}">
	 		<el-button size="small" type="primary" @click="ydPrint()" icon="el-icon-printer">义达打印</el-button>
		</c:if>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_print')}">
	 		<el-button size="small" type="info" @click="yddel()" icon="el-icon-delete">义达删除</el-button>
		</c:if>
	 </el-col>
	</el-row>
	
	<el-row :gutter="20">
	 <el-col :span="4">
			<el-button size="small" type="primary" @click="czs();" icon="el-icon-plus">创志添加</el-button>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_postasync')}">
			<el-button size="small" type="primary" @click="czPost('0')" icon="el-icon-upload2">创志发送</el-button>
		</c:if>
	 </el-col>
	 
	 <el-col :span="4">
	 	<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_postasync')}">
			<el-button size="small" type="primary" @click="geteveryone('1','','3','')" icon="el-icon-printer">创志打印</el-button>
		</c:if>
	 </el-col>
	</el-row>
	
</el-dialog>

<!-- 创志添加修改弹窗 -->
<el-dialog
  title="创志操作区"
  :visible.sync="czView"
  width="40%"
  :before-close="handleClose">
  
  	<el-row :gutter="20">
	  <el-col :span="24">物流方式:
	  <el-select v-model="czOr.service" style="width:100%" size="mini" placeholder="请选择物流">
	    <el-option
	      v-for="item in logoutlist" v-if="item.status=='4'"
	      :key="item.id"
	      :label="item.logisticsName"
	      :value="item.shortName">
	    </el-option>
	  </el-select>
	  </el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="12">箱子总数:<el-input v-model="czOr.parcelCount" size="mini" placeholder="箱子总数"></el-input></el-col>
	  <el-col :span="12">箱子号:<el-input v-model="czOr.number" size="mini" placeholder="箱子号"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">英文:<el-input v-model="czOr.nameEn" size="mini" placeholder="请输入英文"></el-input></el-col>
	  <el-col :span="12">中文:<el-input v-model="czOr.nameZh" size="mini" placeholder="请输入中文"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">申报单价:<el-input v-model="czOr.unitValue" size="mini" placeholder="请输入价格" :disabled="true"></el-input></el-col>
	  <el-col :span="12">申报数量:<el-input v-model="czOr.qty" size="mini" placeholder="请输入申报数量"></el-input></el-col>
	</el-row>

  	<el-row :gutter="20">
	  <el-col :span="12">重量:<el-input v-model="czOr.weight" size="mini" placeholder="请输入重量"></el-input></el-col>
	  <el-col :span="12">备注:<el-input v-model="czOr.remark" size="mini" placeholder="请输入单位"></el-input></el-col>
	</el-row>
	
  	<el-row :gutter="20">
	  <el-col :span="8">长:<el-input v-model="czOr.length" size="mini" placeholder="请输入长"></el-input></el-col>
	  <el-col :span="8">宽:<el-input v-model="czOr.width" size="mini" placeholder="请输入宽"></el-input></el-col>
	  <el-col :span="8">高:<el-input v-model="czOr.height" size="mini" placeholder="请输入高"></el-input></el-col>
	</el-row>
	
	
  	<el-row :gutter="20">
	  <el-col :span="8"><el-checkbox v-model="czOr.isBattery" true-label="1" false-label="0">是否带电</el-checkbox></el-col>
	</el-row>
  </span>
  
  <span slot="footer" class="dialog-footer">
    <el-button @click="czView = false">取 消</el-button>
    <el-button type="primary" v-if="czType=='1'" @click="czViewAdd('1')">批量添加</el-button>
    <el-button type="primary" v-if="czType=='2'" @click="czViewAdd('2')">修改</el-button>
    <el-button type="primary" v-if="czType=='3'" @click="czViewAdd('3')">添加</el-button>
  </span>
</el-dialog>
</div>

<script type="text/javascript">
$('#leftMenuOrder').addClass('active');
var oTable;
var header = $("meta[name='_csrf_header']").attr("content");  
var token = $("meta[name='_csrf']").attr("content"); 
var contextPath = '${pageContext.request.contextPath}';
$(function() {
	//$('.date').datepicker();
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	//userInfoListData()
});

function PostTrack(){
	$("#myModal_post_track").modal("show");
	$("body").css('padding-right','0px');
	
	
	$(".Foam-title").html("结果");
	$("#modal-change").html("下载结果");
	$("#modal-change").attr("onclick","dowEdit()");
}
function dowEdit(){
	var date = new Date();
	var dd = date.getFullYear();
	var mm =date.getMonth()+1;
	var aa =date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	var p = dd +"年"+ mm + "月"+aa + "日" + h + "点" + m + "分下载发送文件";
	exportRaw(p,$("#postHj").val());
}


/*
 * 查询输入框去值
 */
 function myFunction(){
	var x=$("#orderNum").val();
	let da=x.replace("01 ",",");
	let y="";
	for(var i=0;i<da.length;i++){
		y+=da[i];
	}
	$("#orderNum").val(y);
}

	
	
	Vue.http.options.root="${pageContext.request.contextPath}";
	var vm=new Vue({
			el:'#apps',
			data() {
				return {
				fileList3: [],
				jurisdiction:{},//权限
				logoutlist:[],//物流列表
				orderNum:"",//查询内容
				StatusOrder:"",//订单状态
				hj_shippingMethods:"",//物流方式
				startDatas:"",//开始日期
				endDatas:"",//结束日期
				pagsNums:200,//展示
				allChe:false,//运单
				pps:false,//未匹配
				jps:"2",//是否集拼
				lists:[],//商品列表
				page:"1",//第几页
				totalPage:[],//所有页数显示数据
		        total:0,//总长
		        checkAll: false,//全选
		        checkedCities: [],//选中
		        isIndeterminate: true,
		        sadmin:"出货集拼员P001",//操作员
		        S_pack:"",//渠道袋序
		        S_num:"",//袋内件数(个
		        S_weights:"",//净重(kg
		        S_weight2:"",//毛重(kg)
		        S_time:"",//操作时间
		        S_logistic:[],//物流渠道
		        sun:0,
		        suns:0,
		        resultHJ:"",//文本内容
		        bat_invoiceEnname:"",//英文
		        bat_invoiceCnname:"",//中文
		        bat_COMPANY:"1",//公司
		        bat_unitCode:"PCE",//单位
		        bat_invoiceUnitcharge:"",//总价
		        bat_invoiceCurrencycode:"USD",//币种
		        bat_invoiceQuantity:"1",//数量
		        bat_hsCode:"1",//协编
		        bat_saleCurrency:"USD",//销售结算币种
		        bat_categoryName:"家具装饰",//产品中文类目
		        bat_orderWeight:"",//重量/g
		        bat_orderPieces:"1",//外包装数
		        bat_mailCargoType:"2",//申报种类
		        bat_parentEnName:"Furniture decoration",//产品英文类目
		        bat_length:"",//长
		        bat_width:"",//宽
		        bat_height:"",//高
		        bat_shippingMethod:"",//发货
		        bat_isContainsBattery:false,//是否包含电池
		        bat_isAneroidMarkup:false,//非液体化妆品
		        bat_isOnlyBattery:false,//是否纯电池
		        bat_isBreakable:false,//是否易碎
		        bat_isCharged:false,//是否带电
		        bat_isReturn:false,//是否退回
		        bat_CITY:"",//城市
		        bat_province:"",//地址
		        bat_Login:"",
		        wids:"",//列表宽度
		        bat_invoicePrivan:"",//州省
		        bat_invoiceCity:"",//城市
		        operator:false,//操作
		        value6:[],//日期
		        ztoptions: [{
		            value: '0',
		            name: '已发件'
		          }, {
		            value: '1',
		            name: '未发件'
		          }, {
		            value: '2',
		            name: '已录入erp'
		          }, {
		            value: '3',
		            name: '问题件'
		          }],
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
		            },
		            dialogVisible:false,//批量添加义达 
		            invoiceEnname:"",//英文
		            invoiceCnname:"",//中文
		            city:"",//城市
		            province:"",//省州
		            unitCode:"PCE",//单位
		            invoiceUnitcharge:"随机",//总价
		            sku:"随机",//总价
		            invoiceQuantity:"1",//数量
		            shippingMethod:"",//物流
		            orderWeight:"",//重量/g
		            orderPieces:"1",//外包装数
		            mailCargoType:"4",//申报种类
		            cargotype:"W",//货物类型
		            order_info:"",//订单备注
		            isReturn:false,//是否退回
		            optionList:"2",//批量添加
		            loading:false,//加载圈
		            left:"100%",//左侧
		            right:"0%",//右侧
		            editableTabsValue: '',
		            editableTabs: [],
		            tabIndex: 0,
		            contents:{
		            	dd:"",
		            	jp:""
		            },//内容
		            jpStatus:[{
	            		value:"0",
		            	label:"未集拼"
		            },{
		            	value:"1",
		            	label:"已集拼"
		            }],//集拼状态
		            orderStatus:[{
		            	value:"0",
		            	label:"已发件"
		            },{
	            		value:"1",
		            	label:"未发件"
		            },{
            			value:"2",
		            	label:"已录入erp"
		            },{
           				value:"3",
   		            	label:"问题件"
   		            }],//订单状态
   		            jp:"",
   		            dd:"",
   		         	orderlist:{},//订单列表
   		         	edits:false,//编辑界面
   		            F_name:"",//姓名
   		         	F_company:"",//公司
   		         	F_address:"",//地址
	   		        F_city:"",//城市
		   		    F_province:"",//州省
			   		F_country:"",//国家
			   		F_post_code:"",//邮编
			   		F_phone:"",//电话
			   		F_email:"",//邮箱
			   		F_remark:"",//备注
			   		editId:"",//被编辑的id
			   		listOrderNum:"",//列表id
			   		hjWindow:false,//环金弹窗
			   		hjOr:{
			   			id: "",//环金id
			   			hjReferenceno: "",
			   			hjShipperhawbcode: "",
			   			hjShippingmethod: "",//物流
			   			hjCountrycode: "",
			   			hjShipzip: "",
			   			hjOrderweight: "",//重量
			   			hjOrderpieces: "",//外包装数
			   			hjTotalprice: "",
			   			hjMailcargotype: "",//申报种类
			   			hjLength: "",//长
			   			hjWidth: "",//宽
			   			hjHeight: "",//高
			   			hjIsreturn: "",//是否退回
			   			hjIsbattery: "",
			   			hjConsignee: "",
			   			hjConsigneecompany: "",//公司
			   			hjConsigneeprovince: "",//州省
			   			hjConsigneecity: "",//城市
			   			hjConsigneestreet: "",
			   			hjConsigneestreet2: "",
			   			hjConsigneestreet3: "",
			   			hjConsigneepostcode: "",
			   			hjConsigneename: "",
			   			hjConsigneetelephone: "",
			   			hjConsigneemobile: "",
			   			hjConsigneeemail: "",
			   			hjConsigneecountryename: "",//国家英文
			   			hjConsigneecountrycname: "",//国家中文
			   			hjItemarr: "",
			   			hjInvoiceenname: "",//英文
			   			hjInvoicecnname: "",//中文
			   			hjInvoiceweight: "",
			   			hjInvoicequantity: "",//数量
			   			hjUnitcode: "",//单位
			   			hjInvoiceunitcharge: "",//总价
			   			hjInvoicecurrencycode: "",//币种
			   			hjHscode: "",//海光协制编号
			   			hjInvoicenote: "",
			   			hjSku: "",//sku
			   			hjIscontainsbattery: "",//是否包含电池
			   			hjIsaneroidmarkup: "",//非液体化妆品
			   			hjIsonlybattery: "",//是否纯电池
			   			hjIsbreakable: "",//是否易碎
			   			hjIscharged: "",//是否带电
			   			hjSaleprice: "",//产品销售价格
			   			hjSalecurrency: "",//销售结算币种
			   			hjCategoryname: "",//产品中文类目
			   			hjParentenname: "",//产品英文类目
			   			hjStandy1: "",
			   			hjStandy2: "",
			   			hjStandy3: "",
			   			hjStandy4: "",
			   			hjStandy5: "",
			   			hjStandy6: "",
			   			hjStandy7: "",
			   			hjStandy8: "",
			   			hjStandy9: "",
			   			hjStandy10: "",
			   			hjStandy11: "",
			   			hjStandy12: "",
			   			hjStandy13: "",
			   			hjStandy14: ""
			   		},
			   		hjType:"",//1保存  2修改
			   		visible:false,//删除
			   		caDia:false,//操作区
			   		//viewId:"",//被编辑的id
			   		ydOr:{
			   			id: "",
			   			ydReferenceNo: "",
			   			ydShippingMethod: "",
			   			ydShippingMethodNo: "",
			   			ydOrderWeight: "",
			   			ydOrderPieces: "1",
			   			ydCargotype: "W",
			   			ydMailCargoType: "4",
			   			ydReturnSign: "N",
			   			ydBuyerId: "",
			   			ydOrderInfo: "",
			   			ydPlatformId: "",
			   			ydCustomHawbcode: "",
			   			ydShipperName: "",
			   			ydShipperCompany: "",
			   			ydShipperCountrycode: "",
			   			ydShipperCity: "",
			   			ydShipperStreet: "",
			   			ydShipperPostcode: "",
			   			ydShipperAreacode: "",
			   			ydShipperTelephone: "",
			   			ydShipperMobile: "",
			   			ydShipperEmail: "",
			   			ydShipperFax: "",
			   			ydConsigneeName: "",
			   			ydConsigneeCompany: "",
			   			ydConsigneeCountrycode: "",
			   			ydConsigneeProvince: "",
			   			ydConsigneeCity: "",
			   			ydConsigneeStreet: "",
			   			ydConsigneePostcode: "",
			   			ydConsigneeDoorplate: "",
			   			ydConsigneeAreacode: "",
			   			ydConsigneeTelephone: "",
			   			ydConsigneeMobile: "",
			   			ydConsigneeEmail: "",
			   			ydConsigneeFax: "",
			   			ydConsigneeCertificatetype: "",
			   			ydConsigneeCertificatecode: "",
			   			ydConsigneeCredentialsPeriod: "",
			   			ydConsigneeTariff: "",
			   			ydSku: "",
			   			ydInvoiceEnname: "",
			   			ydInvoiceCnname: "",
			   			ydInvoiceQuantity: "1",
			   			ydUnitCode: "PCE",
			   			ydInvoiceUnitcharge: "",
			   			ydHsCode: "",
			   			ydInvoiceNote: "",
			   			ydInvoiceUrl: "",
			   			ydInvoiceInfo: "",
			   			ydStandy1: "",
			   			ydStandy2: "",
			   			ydStandy3: "",
			   			ydStandy4: "",
			   			ydStandy5: "",
			   			ydStandy6: "",
			   			ydStandy7: "",
			   			ydStandy8: "",
			   			ydStandy9: "",
			   			ydStandy10: "",
			   			ydStandy11: "",
			   			ydStandy12: ""
			   		},
			   		//cAp:"",//city和province
			   		countryCode:"",//二字码
			   		czView:false,//创志窗口
			   		czOr:{
			   			//id: "",
			   			service: "",
			   			storeId: "",
			   			clientReference: "",
			   			parcelCount: "1",
			   			exportScc: "",
			   			importScc: "",
			   			attrs: "",
			   			vatNumber: "",
			   			toName: "",
			   			toCompany: "",
			   			toTel: "",
			   			toMobile: "",
			   			toAddress2: "",
			   			toAddress3: "",
			   			toCity: "",
			   			toState: "",
			   			toStateCode: "",
			   			toCountry: "",
			   			toPostcode: "",
			   			toEmail: "",
			   			fromName: "",
			   			formCompany: "",
			   			formTel: "",
			   			formMobile: "",
			   			formAddress2: "",
			   			formAddress3: "",
			   			formCity: "",
			   			formState: "",
			   			formStateCode: "",
			   			formCountry: "",
			   			formPostcode: "",
			   			formEmail: "",
			   			number: "1",
			   			clientWeight: "",
			   			clientLength: "",
			   			clientWidth: "",
			   			clientHeight: "",
			   			sku: "",
			   			nameZh: "",
			   			nameEn: "",
			   			unitValue: "随机生成3-11",
			   			qty: "1",
			   			material: "",
			   			usages: "",
			   			brand: "",
			   			model: "",
			   			salePrice: "",
			   			asin: "",
			   			fnsku: "",
			   			weight: "",
			   			size: "",
			   			hscode: "",
			   			dutyRate: "",
			   			photos: "",
			   			isBattery: "",
			   			fsr2: "",
			   			fsr3: "",
			   			fsr4: "",
			   			fsr5: "",
			   			toAddress1: "",
			   			formAddress1: "",
			   			saleUrl: "",
			   			photoUrl: "",
			   			remark: "",
			   			length:"",
			   			height:"",
			   			width:""
			   		},
			   		czType:"",//创志状态
				}
			},
			created(){
				this.selectall()
				
			    //查询cz
				/* this.$http.post("Logistics/cz/selectId?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
				    this.czOr=result.body
				}) */
				//查询二字码
				this.$http.post("Logistics/countryCode?${_csrf.parameterName}=${_csrf.token}").then(result=>{
				    this.countryCode=result.body
				})
				//查询权限和物流
				this.$http.post("order/select/list?${_csrf.parameterName}=${_csrf.token}").then(result=>{
				    this.logoutlist=result.body.logoutlist
				    this.jurisdiction=result.body.jurisdiction
				    this.wids="16%"
				})
			},
			methods:{
				  selectall(){
					    let datas={}
						datas["orderNum"]=this.orderNum
						datas["StatusOrder"]=this.StatusOrder
						datas["startDatas"]=this.value6[0]
						datas["endDatas"]=this.value6[1]
						datas["pagsNums"]=this.pagsNums
						if(this.pps==false){
							datas["hj_shippingMethods"]=this.hj_shippingMethods
						}else if(this.pps==true){
							datas["hj_shippingMethods"]='10';
						}
						datas["jps"]=this.jps
						datas["page"]=this.page
						datas["limit"]=this.pagsNums
						if(this.allChe==true){
							datas["allChe"]="1"
						}
						this.loading=true
						//查询物流
						this.$http.post("Logistics/checkTheOrder?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							this.lists=result.body
							this.total=result.body.total
							this.checkedCities=[]
							this.loading=false
						})
				  },
			      handleChange(file, fileList) {
		        	  this.operator=false
			    	  //上传文件名
			    	  this.loading=true
		          },
		          toupload(event, file, fileList){
		        	  //上传中
		        	  this.$message.success(file.name+"已上传")
		          },
		          upsuccess(response, file, fileList){
		        	  this.operator=false
		        	  //上传成功返回
		        	  this.$notify({
		                  title: '成功',
		                  message: '成功上传了'+response+"条数据",
		                  type: 'success'
		                })
		              this.loading=false
		              this.selectall()
		          },
		          ellipsis(value,leng){
		        	  //长度限制
		                if (!value) return '';
		                if (value.length > leng) {
		                    return value.slice(0,leng) + '...'
		                }
		                return value
		          },
		          changeLogis(val){
		        	  //转换物流
		        	  let chans=this.logoutlist
		        	  for(let i=0;i<chans.length;i++){
		        		  if(chans[i].shortName==val||chans[i].standy5==val){
		        			  return chans[i].logisticsName
		        		  }
		        	  }
		        	  //return CountryEANDCs(val)
		          },
			      handleCurrentChange(val) {
					  //分页改变
					 this.page=val
					 this.selectall()
			      },
			      closeAndClear(){
			    		// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
			    		var pp = $('#tt').tabs('getSelected');
			    		//获取选项卡的总长度
			    		var tabss=$('#tt').tabs('tabs');
			    		var t=tabss.length;
			    		if (pp){
			    		//关闭所有的框
			    		var y=0;
			    		for(var cd=0;cd<t;cd++){
			    			if(cd<t){
			    				$('#tt').tabs('close', 0);
			    				y++;
			    			}else if(cd>t){
			    				$('#tt').tabs('close', 1);
			    				y++;
			    			}
			    		}

			    	 	//列表变三个
			    	 	this.wids="16%"
			    	 	$(".box-card").css("width",this.wids)
			    		$("#rightright").css("display","none")
			    		$(".leftleft").css("width","90%")
			    		}
			      },
			      async shows(ids,id){
			    		//获取右侧订单详情
			    		let datas={}
			    		datas["id"]=id
			    		await this.$http.post("Logsistics/shows/ids?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
			    			this.contents=result.body
						}) 
						//查询订单列表
		  	    	  	let datae={}
		  	    	  	datae["id"]=id
		  	    	  	datae["fid"]=ids
		  	    	    await this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
		  	    			this.contents["list"]=result1.body
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
		          handleCheckedCitiesChange(value) {
		        	  //单选
		          	let checkedCount = value.length;
		         	this.checkAll = checkedCount === this.lists.list.length;
		          	this.isIndeterminate = checkedCount > 0 && checkedCount < this.lists.list.length;
		          },
		          delalls(){
		        	    //批量删除
		        		//创建传输值
						if(this.checkedCities.length=="0"){
							 this.$message.error("请选择需要删除的产品");
							 return false;
						 }
						this.$confirm("删除这"+this.checkedCities.length+"条后无法恢复!")
				          .then(_ => {
								this.loading=true
								//axios请求方式
							 	this.$http.post("Logsistics/BatchDel?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
								    this.$notify({
					                  title: '成功',
					                  message: '成功删除了'+result.body+"条数据",
					                  type: 'success'
					                });
								    this.loading=false
									this.selectall()
								}) 
				          })
				          .catch(_ => {})
		          },
		          reset(){
		        	  //重置按钮
		        	  
		        	  //Object.assign(this.$data, this.$options.data())
		        	  this.loading=true
				      this.orderNum=""
			    	  this.StatusOrder=""
		    		  this.hj_shippingMethods=""
	    			  this.startDatas=""
    				  this.endDatas=""
    				  this.allChe=false
    				  this.pagsNums=200//展示
    				  this.allChe=false//运单
    				  this.pps=false//未匹配
    				  this.jps="2"//是否集拼
    			      this.loading=false

		          },
		          resetYd(){
		        	  Object.assign(this.$data.ydOr, this.$options.data().ydOr)
		          },
		          async submitOrder(){
		        	 this.operator=false
		        	 //同步发送
		        	 if(this.checkedCities.length=="0"){
		        		 this.$message.error("请选择需要同步发送的产品");
						 return false;
		        	 }
		        	this.loading=true
        		    this.sun=0
		        	this.resultHJ=""
		        	//发送订单
					var lists=[]
					await this.$http.post("Logistics/getPostId?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
						let data=result.body
						//获取到的值为空
						if(JSON.stringify(data) === '[]'){
							this.loading=false
			        		this.$message.error("未找到环金的运单");
							return false
						}
						let data2=""
   						this.$http.post("Logistics/getPostYu?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result1=>{
   							let data1=result1.body
   							console.log(data1)
   							for(let da in data1){
   								if(data1[da].data!=""){
   	   								let data3=JSON.parse(data1[da].data)
	   								if(data3.status=="1"){
	   									if(data3.result.trackNum1!=null){
											let labls={};
											labls["methosd"]=data1[da].orderId.shippingMethod
											labls["id2"]=data1[da].orderId.shipperHawbcode
											labls["id"]=data1[da].orderId.referenceNo
											labls["lab"]=data3.result.lableKey
											labls["num1"]=data3.result.trackNum1
											labls["num2"]=data3.result.trackNum2
											if(JSON.stringify(labls) === '{}'){
												
											}else{
												lists.push(labls)
											}
										}else{
											this.sun+=1
		   									this.resultHJ+=data1[da].orderId.shipperHawbcode+"   "+data3.result.errorMessage+"\n"+"*****************"+"\n"
										}
	   								}else{
	   									this.sun+=1
	   									this.resultHJ+=data1[da].orderId.shipperHawbcode+"   "+data3.errormsg+"\n"+"*****************"+"\n"
	   								}
   								}else{
   									this.sun+=1
   									this.resultHJ+=data1[da].orderId.shipperHawbcode+"   "+data1[da].message+"\n"+"*****************"+"\n"
   								}
   							}
   							if(JSON.stringify(lists)!="[]"){
   	   							this.$http.post("Logistics/setLableid?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(lists),{emulateJSON:true}).then(result2=>{
	   	   							$("#conHJ").html("已发送"+this.checkedCities.length+"条，其中"+lists.length+"条发送成功，"+this.sun+"条发送失败，"+(this.checkedCities.length-lists.length-this.sun)+"条不是环金的默认")
	   	   							$("#postHj").val(this.resultHJ)
	   	   							PostTrack()
		   							this.selectall()
					        		this.$forceUpdate()
	   	   					  this.loading=false
   	   							})
   	   						}else{
	   	   						$("#conHJ").html("已发送"+this.checkedCities.length+"条，其中"+lists.length+"条发送成功，"+this.sun+"条发送失败，"+(this.checkedCities.length-lists.length-this.sun)+"条不是环金的默认")
	   							$("#postHj").val(this.resultHJ)
	   							PostTrack()
	   							this.selectall()
				        		this.$forceUpdate()
	   						  this.loading=false
   	   						}
   						 	//查询订单列表
   						 	if(this.ydOr.ydStandy3!=""){
			  	    	  	let datae={}
			  	    	  	datae["id"]=this.editableTabsValue
			  	    	  	datae["fid"]=this.ydOr.ydStandy3
			  	    	    this.$http.post("Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
				  	    	 	for(let ise=0;ise<this.editableTabs.length;ise++){
				  	    	 		if(this.editableTabs[ise].name==this.editableTabsValue){
				  	    	 			this.editableTabs[ise].content.list=result1.body
							    		this.$forceUpdate()
				  	    	 		}
				  	    	 	}
			  	    	 	})
   						 	}
   						})
		          })
		          },
		          async syncOrder(){
		        	  this.operator=false
		        	  //异步发送
		        	  if(this.checkedCities.length=="0"){
		        		 this.$message.error("请选择需要异步发送的产品");
						 return false;
		        	  }
		        	  this.loading=true
        		      this.sun=0
					  this.suns=0
		        	  this.resultHJ=""
		        	  //异步发送产品
		        	  await this.$http.post("Logistics/getPostId?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
		        		    let data=result.body
		        		    //获取到的值为空
							if(JSON.stringify(data) === '[]'){
								this.loading=false
				        		this.$message.error("未找到环金的运单");
								return false
							}
		        		    let data2=""
	        		    	this.$http.post("Logistics/asyncPostYu?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result1=>{
	        		    		let data1=result1.body
	        		    		for(let da in data1){
	   								if(data1[da].data!=""){
	   	   								let data3=JSON.parse(data1[da].data)
		   								if(data3.status=="1"){
		   								    this.suns+=1
		   									this.resultHJ+=data1[da].orderId.shipperHawbcode+"   "+data3.errormsg+"\n"+"*****************"+"\n"
		   								}else{
		   									this.sun+=1
		   									this.resultHJ+=data1[da].orderId.shipperHawbcode+"   "+data3.errormsg+"\n"+"*****************"+"\n"
		   								}
	   								}else{
	   									this.sun+=1
	   									this.resultHJ+=data1[da].orderId.shipperHawbcode+"   "+data1[da].message+"\n"+"*****************"+"\n"
	   								}
	   							}
	        		    		$("#conHJ").html("已发送"+this.checkedCities.length+"条，其中"+this.suns+"条发送成功，"+this.sun+"条发送失败，"+(this.checkedCities.length-this.suns-this.sun)+"条不是环金的默认")
	   							$("#postHj").val(this.resultHJ)
	   							PostTrack()
				        		this.$forceUpdate()
	   	 	   					this.loading=false
	        		    	})
		        	  })

		          },
		          async traceNumber(){
		        	  this.operator=false
		        	  //获取跟踪号
		        	  if(this.checkedCities.length=="0"){
						 this.loading=false
		        		 this.$message.error("请选择需要获取跟踪号的产品");
						 return false;
		        	  }
		        	  this.loading=true
		        	  await this.$http.post("Logistics/getPostId?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
		        		let data=result.body
		        		//获取到的值为空
						if(JSON.stringify(data) === '[]'){
							this.loading=false
			        		this.$message.error("未找到环金的运单");
							return false
						}
		        		let refe=[]
	        		    this.sun=0
      					this.suns=0
      					this.resultHJ=""
						for(let key in result.body){
							refe.push(data[key].referenceNo)
						}
						let resultD={}
						resultD["referenceNoList"]=refe
						this.$http.post("Logistics/traceNumber?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(resultD),{emulateJSON:true}).then(result1=>{
							//获取到所有的编号集合
							let c1=result1.body.result
							console.log(result1)
							//迭代传后台
							this.$http.post("Logistics/setTrack?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(c1),{emulateJSON:true}).then(result1=>{
								let data1s=result1.body
								if(data1s.hj!="0"&&data1s.lo!="0"){
					        		this.$message.success("成功获取跟踪号")
					        		this.$forceUpdate()
									this.loading=false
								}else{
					        		this.$forceUpdate()
					        		this.$message.error("获取跟踪号失败")
								}
							})
						})
						this.loading=false
		          	})
					},
					async print(){
						this.operator=false
						//打印
						if(this.checkedCities.length=="0"){
			        		 this.$message.error("请选择需要打印的产品");
							 return false;
			        	}
						this.loading=true
						await this.$http.post("Logistics/getpdfall?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true,_timeout:10000}).then(result=>{
							this.loading=false
							window.open("${pageContext.request.contextPath}/"+result.body,"_blank");
						}).catch(error =>{
							this.loading=false
							this.$message.error("未找到需要打印的环金产品");
						})
					},
					edit_summart(){
						this.operator=false
						//集拼
						if(this.checkedCities.length=="0"){
			        		 this.$message.error("请选择需要集拼的产品");
							 return false;
			        	}
						let idlists=this.checkedCities;
						$("#myModal_summary").modal("show");
						$("body").css('padding-right','0px');
						$(".Foam-title").html("集拼单号打印信息");
				        this.sadmin="出货集拼员P001",//操作员
				        this.S_pack=""//渠道袋序
				        this.S_num=this.checkedCities.length//袋内件数(个
				        this.S_weights=""//净重(kg
				        this.S_weight2=""//毛重(kg)
				        this.S_logistic=""
				        this.getTime()//操作时间
					},
					getTime(){
						//获取当前时间
					      var _this = this;
					      let yy = new Date().getFullYear();
					      let mm = new Date().getMonth()+1;
					      let dd = new Date().getDate();
					      let hh = new Date().getHours();
					      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
					      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
					      _this.S_time = yy+'-'+mm+'-'+dd+' '+hh+':'+mf+':'+ss;
					 },
					 async modalCsum(){
						 	//集拼提交
							//传送所有单号
							if(this.sadmin==""||this.S_weights==""||this.S_weight2==""||this.S_logistic==""||this.S_time==""||this.S_pack==""){
								this.$message.error("有值为空")
								this.loading=false
								return false
							}
							let datas={}
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							datas["referenceNoList"]=reno.substr(0,reno.length-1)
							datas["S_channel"]=this.S_logistic
							datas["S_weights"]=this.S_weights
							datas["S_weight2"]=this.S_weight2
							datas["S_admin"]=this.sadmin
							datas["S_time"]=this.S_time
							datas["S_pack"]=this.S_pack
							datas["S_num"]=this.checkedCities.length
							this.loading=true
							await this.$http.post("Logistics/dropinfo?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
								let y=result.body
								this.loading=false
								if(y.length>1){
									this.$message.error(result.body)
									return false
								}
								if(result.body.dropNo!=""){
									$("#barcodes").barcode(result.body.dropNo, "code128",{barWidth:2,barHeight:88,showHRI:true,fontSize:24}); 
									$("#myModal_Print").modal("show"); 
									$("#myModal_summary").modal("hide");
								}else{
									this.$message.error("未找到集拼单号")
								}
								this.loading=false
							})
					 },
					 print2(){
						 //打印
						 $("#pring_t").jqprint()
					 },
					 async ydPrint(){
						//义达打印
						this.operator=false
							//打印
						if(this.checkedCities.length=="0"){
							this.loading=false
			        		 this.$message.error("请选择需要打印的产品");
							 return false;
			        	}
						this.loading=true
						await this.$http.post("Logistics/ydPrint?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true,_timeout:10000}).then(result=>{
							if(result.body.success=="0"){
								this.loading=false
								this.$message.error(result.body.cnmessage)
							}else if(result.body.success=="1"){
								this.loading=false
								//添加运单
								console.log(result.body)
								let listOrder=result.body.data
								for(let li=0;li<listOrder.length;li++){
									//hj_logistics_post
									this.$http.post("Logistics/hj_logistics_post_list?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true,_timeout:10000}).then(results=>{
										window.open(listOrder[li].lable_file)
										this.selectall()
									})
								}
							}
						}).catch(error =>{
							this.$message.error("未找到需要打印的环金产品");
						})
					 },
					 myModalBatchAdd(){
						 	this.operator=false
						 	//批量添加
						 	if(this.checkedCities.length=="0"){
								this.loading=false
				        		 this.$message.error("请选择需要添加运单的产品")
								 return false
				        	}
						 	//显示弹窗
							$("#myModal_batch_add").modal("show")
							//清空内容
							this.addReset()
					 },
					 Iprice(){
						//随机价格
						//x上限，y下限     
					    var x = 11
					    var y = 3
					    var rand = parseInt(Math.random() * (x - y + 1) + y)
						return rand
					 },
					 addReset(){
						 //批量添加刷新
						 	this.loading=true
						 	let prices=this.Iprice()
							this.bat_invoiceEnname=""
							this.bat_invoiceCnname=""
							this.bat_unitCode="PCE"
							this.bat_invoiceUnitcharge=prices
							this.bat_invoiceCurrencycode="USD"
							this.bat_hsCode="1"
							this.bat_isContainsBattery=false
							this.bat_salePrice=prices
							this.bat_isAneroidMarkup=false
							this.bat_saleCurrency="USD"
							this.bat_isOnlyBattery=false
							this.bat_categoryName="家具装饰"
							this.bat_isBreakable=false
							this.bat_parentEnName="Furniture decoration"
							this.bat_isCharged=false
							this.bat_invoiceQuantity="1"
							this.bat_remark=""
							this.bat_orderPieces="1"
							this.bat_mailCargoType="2"
							this.bat_consigneeCountryEname=""
							this.bat_consigneeCountryCname=""
							this.bat_orderWeight=""
							this.bat_length=""
							this.bat_width=""
							this.bat_height=""
							this.bat_CITY=""
							this.bat_province=""
							this.bat_COMPANY="1"
							this.bat_isReturn=false
							this.bat_shippingMethod=""
							this.bat_invoicePrivan=""
							this.bat_invoiceCity=""
								this.loading=false
					 },
					 add_batch(){
						    //添加
						 	let datas={};
							if(this.bat_orderWeight.substr(0,1)=="0"){
								this.$message.error("重量不能以0开头")
								return false
							}
							for(let i=0;i<this.bat_shippingMethod.length;i++){
								if(this.bat_shippingMethod==this.logoutlist.shortName||this.bat_shippingMethod==this.logoutlist.standy5){
									if(this.bat_orderWeight>(this.logoutlist.standy4*1000)){
										this.$message.error("重量大于"+this.logoutlist.standy4+"千克");
										return false;
									}
								}
							}
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							datas["List"]=reno
							datas["bat_invoiceEnname"]=this.bat_invoiceEnname
							datas["bat_invoiceCnname"]=this.bat_invoiceCnname
							datas["bat_invoiceCity"]=this.bat_invoiceCity
							//datas["bat_province"]=this.bat_province
							datas["bat_COMPANY"]=this.bat_COMPANY
							datas["bat_unitCode"]=this.bat_unitCode;
							datas["bat_invoiceUnitcharge"]=this.bat_invoiceUnitcharge;
							datas["bat_invoiceCurrencycode"]=this.bat_invoiceCurrencycode;
							datas["bat_invoiceQuantity"]=this.bat_invoiceQuantity;
							datas["bat_hsCode"]=this.bat_hsCode;
							datas["bat_salePrice"]=this.bat_salePrice;
							datas["bat_saleCurrency"]=this.bat_saleCurrency;
							datas["bat_categoryName"]=this.bat_categoryName;
							//datas["bat_Login"]=this.bat_Login;
							datas["bat_orderWeight"]=this.bat_orderWeight;
							datas["bat_orderPieces"]=this.bat_orderPieces;
							datas["bat_mailCargoType"]=this.bat_mailCargoType;
							datas["bat_parentEnName"]=this.bat_parentEnName;
							datas["bat_length"]=this.bat_length;
							datas["bat_width"]=this.bat_width;
							datas["bat_height"]=this.bat_height;
							datas["bat_invoicePrivan"]=this.bat_invoicePrivan;
							datas["bat_shippingMethod"]=this.bat_shippingMethod;
							if(this.bat_isContainsBattery==true){
								datas["bat_isContainsBattery"]="1";//是否包含电池
							}else{
								datas["bat_isContainsBattery"]="0";
							}
							if(this.bat_isAneroidMarkup==true){
								datas["bat_isAneroidMarkup"]="1";//是否属于非液体化妆品
							}else{
								datas["bat_isAneroidMarkup"]="0";
							}
							if(this.bat_isOnlyBattery==true){
								datas["bat_isOnlyBattery"]="1";//是否为纯电池
							}else{
								datas["bat_isOnlyBattery"]="0";
							}
							if(this.bat_isBreakable==true){
								datas["bat_isBreakable"]="1";//是否易碎
							}else{
								datas["bat_isBreakable"]="0";
							}
							if(this.bat_isCharged==true){
								datas["bat_isCharged"]="1";//是否带电
							}else{
								datas["bat_isCharged"]="0";
							}
							if(this.bat_isReturn==true){
								datas["bat_isReturn"]="1";//是否带电
							}else{
								datas["bat_isReturn"]="0";
							}
							if(this.bat_invoiceEnname=="" ||
							   this.bat_invoiceCnname=="" ||
							   //this.bat_CITY=="" ||
							   //this.bat_province=="" ||
							   this.bat_COMPANY=="" ||
							   this.bat_unitCode=="" ||
							   this.bat_invoiceCurrencycode=="" ||
							   this.bat_invoiceUnitcharge=="" ||
							   this.bat_invoiceQuantity=="" ||
							   this.bat_hsCode=="" ||
							   this.bat_salePrice=="" ||
							   this.bat_saleCurrency=="" ||
							   this.bat_categoryName=="" ||
							   //this.bat_Login=="" ||
							   this.bat_orderWeight=="" ||
							   this.bat_orderPieces=="" ||
							   this.bat_mailCargoType=="" ||
							   this.bat_parentEnName=="" ||
							   this.bat_width=="" ||
							   this.bat_length=="" ||
							   this.bat_height=="" ||
							   this.bat_shippingMethod==""){
								this.$message.error("有值为空");
								return false;
							}
							console.log(datas)
							this.loading=true
							//批量添加运单
							this.$http.post("Logistics/ALLbatch?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
								$("#myModal_batch_add").modal("hide")
								this.$message.success("添加成功")
								this.loading=false
								//界面刷新
								this.selectall()
							})
					 },
					 getDate (item) {
				    	  //时间转换
							var time = new Date(item) 
							var year = time.getFullYear()
							var month = time.getMonth() + 1
							var date = time.getDate()
							var hours=time.getHours()
							var mins=time.getMinutes()
							var sec=time.getSeconds()
							return year + '-' + month + '-' + date+" "+hours + ':' + mins + ":" + sec
						},
					 excel_bu3(){
						 this.operator=false
						 this.loading=true
						 //导出邮政
						 if(this.checkedCities.length>0){
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							$("#idList3").val(reno);
							this.loading=false
							$("#formtyw3").submit();
						}else{
							this.loading=false
							this.$message.error("请选择邮政需要导出的文件");
						}
					},
					hjShippingmethods(val){
			        	  //转换物流
			        	  let chans=this.logoutlist
			        	  for(let i=0;i<chans.length;i++){
			        		  if(chans[i].shortName==val||chans[i].standy5==val){
			        			  return chans[i].logisticsName
			        		  }
			        	  }
					},
					excel_bu2(){
						this.operator=false
						this.loading=true
						//导出集拼
						if(this.checkedCities.length>0){
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							this.loading=false
							$("#idList2").val(reno);
							$("#formtyw2").submit();
						}else{
							this.loading=false
							this.$message.error("请选择需要导出的集拼产品");
						}
					},
					excel_buttonssss(){
						this.operator=false
						this.loading=true
						//导出产品
						if(this.checkedCities.length>0){
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							$("#idList").val(reno);
							this.loading=false
							$("#formtyw").submit();
						}else{
							this.loading=false
							this.$message.error("请选择需要导出的产品");
						}
					},
					list4(){
						this.operator=false
						this.loading=true
						//导出预估
						if(this.checkedCities.length>0){
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							this.loading=false
							$("#idList4").val(reno);
							$("#formtyw4").submit();
						}else{
							this.loading=false
							this.$message.error("请选择需要预估的产品");
						}
					},
					colors(val){
						//颜色判断
						switch(val){
						case"0":
							return "#0099CC"
						case"1":
							return "#FF6666"
						case"2":
							return "#99CC66"
						case"3":
							return "#c925d478"
						}
					},
					handleClose(done) {
						//关闭操作功能区						
				        this.$confirm('确认关闭？')
				          .then(_ => {
				            done();
				          })
				          .catch(_ => {});
				    },
				    ydlabel(){
				    	//获取义达标签
				    	this.operator=false
						//导出产品
						this.loading=true
						if(this.checkedCities.length>0){
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							$("#idList").val(reno);
							this.loading=false
							$("#formtyw").submit();
						}else{
							this.loading=false
							this.$message('请选择需要获取的产品');
						}
				    },
				    async submit(){
				    	//批量添加义达
				    	this.operator=false
				    	if(this.checkedCities.length>0){
							let datas={}
							datas["invoiceEnname"]=this.ydOr.ydInvoiceEnname
							datas["invoiceCnname"]=this.ydOr.ydInvoiceCnname
							datas["city"]=this.ydOr.ydConsigneeCity
							datas["province"]=this.ydOr.ydConsigneeProvince
							datas["unitCode"]=this.ydOr.ydUnitCode
							datas["invoiceQuantity"]=this.ydOr.ydInvoiceQuantity
							datas["shippingMethod"]=this.ydOr.ydShippingMethod
							datas["orderWeight"]=(this.ydOr.ydOrderWeight/1000)
							datas["orderPieces"]=this.ydOr.ydOrderPieces
							datas["mailCargoType"]=this.ydOr.ydMailCargoType
							datas["cargotype"]=this.ydOr.ydCargotype
							datas["orderInfo"]=this.ydOr.ydOrderInfo
							datas["isReturn"]=this.ydOr.ydReturnSign
							let reno=""
							for(let gi=0;gi<this.checkedCities.length;gi++){
								reno+=this.checkedCities[gi]+","
							}
							datas["checkedCities"]=reno
							this.loading=true
							this.dialogVisible=false
							await this.$http.post("Logistics/addyidas?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
								this.loading=false
								 //查询订单列表
				  	    	  	let datae={}
				  	    	  	datae["id"]=this.editableTabsValue
				  	    	  	datae["fid"]=this.ydOr.ydStandy3
				  	    	    this.$http.post("Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
					  	    	 	for(let ise=0;ise<this.editableTabs.length;ise++){
					  	    	 		if(this.editableTabs[ise].name==this.editableTabsValue){
					  	    	 			this.editableTabs[ise].content.list=result1.body
								    		this.$forceUpdate()
					  	    	 		}
					  	    	 	}
				  	    	 	})
								this.$message.success('成功添加'+result.body+"个运单");

							})
						}else{
							this.loading=false
							this.$message('请选择需要获取的产品');
						}
				    },
				    async submit2(){
				    	//批量添加义达
				    	  if(this.ydOr.ydOrderWeight==""||
				    		 this.ydOr.ydInvoiceEnname==""||
				    		 this.ydOr.ydInvoiceCnname==""||
				    		 this.ydOr.ydShippingMethod==""){
				    		  this.$message.error("有值为空")
				    		  return false
				    	  }
				    	this.caDia=false
				    	this.operator=false
				    	if(this.editableTabsValue!=""){
							let datas={}
							datas["invoiceEnname"]=this.ydOr.ydInvoiceEnname
							datas["invoiceCnname"]=this.ydOr.ydInvoiceCnname
							datas["city"]=this.ydOr.ydConsigneeCity
							datas["province"]=this.ydOr.ydConsigneeProvince
							datas["unitCode"]=this.ydOr.ydUnitCode
							datas["invoiceQuantity"]=this.ydOr.ydInvoiceQuantity
							datas["shippingMethod"]=this.ydOr.ydShippingMethod
							datas["orderWeight"]=(this.ydOr.ydOrderWeight/1000)
							datas["orderPieces"]=this.ydOr.ydOrderPieces
							datas["mailCargoType"]=this.ydOr.ydMailCargoType
							datas["cargotype"]=this.ydOr.ydCargotype
							datas["orderInfo"]=this.ydOr.ydOrderInfo
							datas["isReturn"]=this.ydOr.ydReturnSign
							datas["checkedCities"]=this.editableTabsValue
							this.loading=true
							this.dialogVisible=false
							await this.$http.post("${pageContext.request.contextPath}/Logistics/addyidas?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
								this.loading=false
								//查询订单列表
				  	    	  	let datae={}
				  	    	  	datae["id"]=this.editableTabsValue
				  	    	  	datae["fid"]=this.contents.fIds
				  	    	    this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
					  	    	 	for(let ise=0;ise<this.editableTabs.length;ise++){
					  	    	 		if(this.editableTabs[ise].name==this.editableTabsValue){
					  	    	 			this.editableTabs[ise].content.list=result1.body
								    		this.$forceUpdate()
					  	    	 		}
					  	    	 	}
				  	    	 	})
								this.$message.success('成功添加'+result.body+"个运单");
							})
						}else{
							this.loading=false
							this.$message('请选择需要获取的产品');
						}
				    },
				    ydUpdateOrder(){
						//修改义达订单
							if(this.ydOr.orderWeight==""||
					    		 this.ydOr.invoiceEnname==""||
					    		 this.ydOr.invoiceCnname==""||
					    		 this.ydOr.shippingMethod==""){
					    		  this.$message.error("有值为空")
					    		  return false
					    	  }
							  this.caDia=false
					    	  this.dialogVisible = false
					    	  //提交订单
					    	  let datas={}
					    	  datas["id"]=this.ydOr.id
					    	  datas["ydShippingMethod"]=this.ydOr.ydShippingMethod
					    	  datas["ydStandy3"]=this.ydOr.ydStandy3
					    	  datas["ydOrderWeight"]=(this.ydOr.ydOrderWeight/1000) //注意kg
					    	  datas["ydOrderPieces"]=this.ydOr.ydOrderPieces
					    	  datas["ydCargotype"]=this.ydOr.ydCargotype  //货物类型
															    	 //W：包裹
															    	 //D：文件
															    	 //B：袋子
					    	  datas["ydMailCargoType"]=this.ydOr.ydMailCargoType //申报种类
					    	  datas["ydReturnSign"]=this.ydOr.ydReturnSign
					    	  datas["ydOrderInfo"]=this.ydOr.ydOrderInfo
					    	  
					    	  //发件人信息
					    	  //let shipper={}
					    	  //datas["shipper"]=shipper
					    	  
					    	  //收件人信息
					    	 // let consignee={}
					    	  //datas["consignee"]=consignee
					    	  datas["ydConsigneeName"]=this.ydOr.ydConsigneeName
					    	  datas["ydConsigneeCountrycode"]=this.ydOr.ydConsigneeCountrycode
				    		  datas["ydConsigneeProvince"]=this.ydOr.ydConsigneeProvince
				    		  datas["ydConsigneeCity"]=this.ydOr.ydConsigneeCity
				    		  datas["ydConsigneeStreet"]=this.ydOr.ydConsigneeStreet
				   			  datas["ydConsigneePostcode"]=this.ydOr.ydConsigneePostcode
							  datas["ydConsigneeTelephone"]=this.ydOr.ydConsigneeTelephone
							  datas["ydConsigneeMobile"]=this.ydOr.ydConsigneeTelephone
				  			  datas["ydConsigneeEmail"]=this.ydOr.ydConsigneeEmail
					    	  //consignee["consignee_tariff"]=//收件人税号
					    	  
					    	  //let len=[]
					    	  //let invoice={}
					    	  //len.push(invoice)
					    	  //datas["invoice"]=len
					    	  datas["ydInvoiceEnname"]=this.ydOr.ydInvoiceEnname
					    	  datas["ydInvoiceCnname"]=this.ydOr.ydInvoiceCnname
					    	  datas["ydInvoiceQuantity"]=this.ydOr.ydInvoiceQuantity
					    	  datas["ydUnitCode"]=this.ydOr.ydUnitCode
					    	  datas["ydInvoiceUnitcharge"]=this.ydOr.ydInvoiceUnitcharge
					    	  datas["ydSku"]=this.ydOr.ydSku
					    	  
					    	//添加义达商品
								this.$http.post("${pageContext.request.contextPath}/Logistics/ydUpdateOrder?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
									let results=result.body
									if(results=="0"){
										this.$message.error("修改失败")
									}else if(results=="1"){
										//添加运单
										this.$message.success("修改成功")
									}
								})
					},
				    submityida(){
				    	//批量发送义达
		        	  	this.operator=false
		        	  	this.loading=true
	    				//义达
	    				this.$http.post("Logistics/postYds?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
							let results=result.body
							if(results.length == 0){
								this.loading=false
								this.$message.error('未找到义达的运单');
							}
							this.sun=0
	      					this.suns=0
	      					this.resultHJ=""
							for(let i=0;i<results.length;i++){
								this.sun+=1;
								if(JSON.parse(results[i].data).success=="0"){
									this.resultHJ+="发送失败   "+results[i].fid+"  消息：:"+JSON.parse(results[i].data).cnmessage+"\n"+"*****************"+"\n"
									$("#postHj").val(this.resultHJ);
									 PostTrack();
								}else if(JSON.parse(results[i].data).success=="1"){
									//添加运单
									this.$message.success(JSON.parse(results[i].data).cnmessage)
									let datas1={}
									console.log(results[i])
									datas1["ydStandy7"]=results[i].method
									datas1["ydStandy6"]=JSON.parse(results[i].data).data.order_id+""
									datas1["ydStandy3"]=results[i].fid+""
									datas1["ydReferenceNo"]=JSON.parse(results[i].data).data.refrence_no
									datas1["ydShippingMethodNo"]=JSON.parse(results[i].data).data.shipping_method_no
									console.log(datas1)
									this.$http.post("Logistics/updateYd?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas1),{emulateJSON:true}).then(result1=>{
										
										
										this.resultHJ+="发送成功   "+results[i].fid+"\n"+"*****************"+"\n"
										this.suns+=1;
										$("#conHJ").html("已发送"+this.sun+"条，其中"+this.suns+"条发送成功，"+(this.sun-this.suns)+"条发送失败");
										$("#postHj").val(this.resultHJ);
										PostTrack();
										this.selectall()
									})
								}
							}
							/* if(results.success=="0"){
								this.$message.error(results.cnmessage)
							}else if(results.success=="1"){
								//添加运单
								this.$message.success(results.cnmessage)
								let datas1={}
								datas1["ydStandy6"]=results.data.order_id
								datas1["ydStandy3"]=$("#Fid").html()
								datas1["ydReferenceNo"]=results.data.refrence_no
								datas1["ydShippingMethodNo"]=results.data.shipping_method_no
								this.$http.post("Logistics/updateYd?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas1),{emulateJSON:true}).then(result1=>{
									this.selectlist()
									parent.vm.selectall()
								})
							} */
							this.loading=false
						})
				    },
				    formatDate(row) {
		                let date = new Date(parseInt(row.subscribeTime) * 1000);
		                let Y = date.getFullYear() + '-';
		                let M = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) + '-' : date.getMonth() + 1 + '-';
		                let D = date.getDate() < 10 ? '0' + date.getDate() + ' ' : date.getDate() + ' ';
		                let h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
		                let m = date.getMinutes()  < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes() + ':';
		                let s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
		                return Y + M + D + h + m + s;
		            },
		            async addTab(targetName,fid,ids) {

		            	this.left="52%"
		            	this.right="100%"
		            	this.wids="32%"
		            	//添加标签页
		            	//i用来记数，如果为0表示是新的标签，为1就是已经有的标签
		            	let i=0
		                this.editableTabs.forEach(item=>{
		                	if(item.name==ids){
		                		this.editableTabsValue = ids+""
		                		i++
		                		return false
		                	}
		                }) 
		                if(i==0){
			                let newTabName = ++this.tabIndex + '';
			                await this.shows(fid,ids)
			                this.editableTabs.push({
			                  title: fid,
			                  name: ids+"",
			                  content: this.contents
			                });
			                this.editableTabsValue = ids+"";
		                }
		            },
		            removeTab(targetName) {
		            	//删除一个标签
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
		            clearTab(){
		            	//清空所有标签
		            	this.editableTabs=[]
		            	this.left="100%"
		            	this.right="0%"
		            	this.wids="16%"
		            },
		            copy(val) {
		            	//复制
		    	  		let result = ""
		    	  		result += " 姓名："+val.fFirstName+val.fLastName+"\r\n";
		    	  		result += " 地址："+val.fAddress1+val.fAddress2+val.fAddress3+"\r\n";
		    	  		result += " 城市："+val.fCity+"\r\n";
		    	  		result += " 州省："+val.fProvince+"\r\n";
		    	  		result += " 国家："+val.fCountry+"\r\n";
		    	  		result += " 邮编："+val.fPostCode+"\r\n";
		    	  		result += " 电话："+val.fTelephone+"\r\n";
		    	  		
		    	  		let input = document.createElement("input");     // 直接构建input
				        input.value = result;   // 设置内容
				        document.body.appendChild(input);        // 添加临时实例
				        input.select();      // 选择实例内容
				        try {
				        document.execCommand("Copy");     // 执行复制
				        document.body.removeChild(input);  // 删除临时实例
				        this.$message.success("复制成功")
				        } catch (err) {
				        this.$messageerror("复制失败");
					    }
		    	  	},
		    	  	edit(val,val2){
		    	  		//编辑
		    	  		this.listOrderNum=val2
		    	  		this.editId=val.id
		    	  		this.F_name=val.fFirstName+val.fLastName
		    	  		this.F_company=val.standby5
		    	  		this.F_address=val.fAddress1+"|"+val.fAddress2+"|"+val.fAddress3
		    	  		this.F_city=val.fCity
		    	  		this.F_province=val.fProvince
		    	  		this.F_country=val.fCountry
		    	  		this.F_post_code=val.fPostCode
		    	  		this.F_phone=val.fTelephone
		    	  		this.F_email=val.fEmail
		    	  		this.F_remark=val.fRemark
		    	  		this.edits=true
		    	  	},
		    	  	editSave(){
		    	  		//编辑保存
		    	  		let datas={}
		    	  		datas["fFirstName"]=this.F_name.split(" ")[0];
		    	  		if(this.F_name.split(" ")[1]!=""&&this.F_name.split(" ")[1]!=null){
		    	  			datas["fLastName"]=this.F_name.split(" ")[1]
		    	  		}else{
		    	  			datas["fLastName"]=""
		    	  		}
		    	  		if(this.F_address.split("|")[1]!=""&&this.F_address.split("|")[1]!=null){
		    	  			datas["fAddress2"]=this.F_address.split("|")[1]
		    	  		}else{
		    	  			datas["fAddress2"]=""
		    	  		}
		    	  		if(this.F_address.split("|")[2]!=""&&this.F_address.split("|")[2]!=null){
		    	  			datas["fAddress3"]=this.F_address.split("|")[2]
		    	  		}else{
		    	  			datas["fAddress3"]=""
		    	  		}
		    	  		datas["fAddress1"]=this.F_address.split("|")[0]
		    	  		datas["fCity"]=this.F_city
		    	  		datas["fProvince"]=this.F_province
		    	  		datas["fCountry"]=this.F_country
		    	  		datas["fPostCode"]=this.F_post_code
		    	  		datas["fTelephone"]=this.F_phone
		    	  		datas["fEmail"]=this.F_email
		    	  		datas["fRemark"]=this.F_remark
		    	  		datas["standby5"]=this.F_company
		    	  		datas["id"]=this.editId
		    	  		this.$http.post("Logistics/saveEdit?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
			    	  		this.editableTabs[this.listOrderNum].content.fFirstName=datas["fFirstName"]
			    	  		this.editableTabs[this.listOrderNum].content.fLastName=datas["fLastName"]
			    	  		this.editableTabs[this.listOrderNum].content.fAddress1=datas["fAddress1"]
			    	  		this.editableTabs[this.listOrderNum].content.fAddress2=datas["fAddress2"]
			    	  		this.editableTabs[this.listOrderNum].content.fAddress3=datas["fAddress3"]
			    	  		this.editableTabs[this.listOrderNum].content.fCity=datas["fCity"]
			    	  		this.editableTabs[this.listOrderNum].content.fProvince=datas["fProvince"]
			    	  		this.editableTabs[this.listOrderNum].content.fCountry=datas["fCountry"]
			    	  		this.editableTabs[this.listOrderNum].content.fPostCode=datas["fPostCode"]
			    	  		this.editableTabs[this.listOrderNum].content.fTelephone=datas["fTelephone"]
			    	  		this.editableTabs[this.listOrderNum].content.fEmail=datas["fEmail"]
			    	  		this.editableTabs[this.listOrderNum].content.fRemark=datas["fRemark"]
			    	  		this.editableTabs[this.listOrderNum].content.standby5=datas["standby5"]
			    	  		
			    	  		this.$message.success("修改成功")
							this.clearEdit()
							this.selectall()
						})
		    	  	},
		    	  	clearEdit(){
		    	  		//清除编辑的内容//编辑
		    	  		this.editId=""
		    	  		this.F_name=""
		    	  		this.F_company=""
		    	  		this.F_address=""
		    	  		this.F_city=""
		    	  		this.F_province=""
		    	  		this.F_country=""
		    	  		this.F_post_code=""
		    	  		this.F_phone=""
		    	  		this.F_email=""
		    	  		this.F_remark=""
		    	  		this.listOrderNum=""
		    	  		this.edits=false
		    	  	},
		    	  //value:0.不是默认 1.默认
					radioslo(value,va2,va3,va4,va5,va6){
					 	let datas={};
						datas["hjStandy3"]=value
						datas["hjStandy2"]=va2
						datas["hjStandy4"]=va3
						datas["hjShipperhawbcode"]=va4
						datas["hjShippingmethod"]=va5
						this.$http.post("Logistics/defaultHj?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							this.selectall()
							this.$message.success("默认修改成功")
						})
					},
		    	  	orderSave(val){
		    	  		//状态保存
		    	  		let datas={}
		    	  		datas["id"]=val.id
		    	  		datas["states"]=val.standby13
		    	  		datas["standby6"]=val.standby6
		    	  		//保存状态
						this.$http.post("${pageContext.request.contextPath}/Logistics/orderSave?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							let results=result.body
							if(results=="0"){
								this.$message.error("保存失败")
							}else{
								//添加运单
								this.$message.success("保存成功")
							}
						})
		    	  	},
		    	  	sj() {
		    	  	    //x上限，y下限     
		    	  	    var x = 11;
		    	  	    var y = 3;
		    	  	    var rand = parseInt(Math.random() * (x - y + 1) + y);
		    	  		return rand;
		    	  	},
		    	   	//查看运单
					async selectOrder_HJ(value,val2){
					if(val2=="0"){
						let datas={}
						datas["id"]=value
						//查看运单
						await this.$http.post("${pageContext.request.contextPath}/Logistics/ckHJLogistics?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							let results=result.body
							this.hjOr=results
							this.hjWindow=true
							this.hjType="2"//查看修改
						})
					}else if(val2=="1"){
						let datas={}
						//拿到订单详情列表。
						datas["id"]=this.editableTabsValue
						await this.$http.post("Logistics/getLogisticParticulars?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							let results=result.body
							let core=CountryEANDC(results.fCountry,this.countryCode)
							this.hjOr.hjConsigneecountrycname=core.a
							this.hjOr.hjConsigneecountryename=core.b
							this.hjOr.hjConsigneecity=results.fCity
							this.hjOr.hjConsigneeprovince=results.fProvince
							this.hjOr.hjConsigneecompany=results.standby5==null?"1":results.standby5
							this.hjOr.hjUnitcode="PCE"
							//随机价格
							let prices=this.sj()
							this.hjOr.hjInvoiceunitcharge=prices
							this.hjOr.hjInvoicecurrencycode="USD"
							this.hjOr.hjSku="B07"+this.generateMixed(7).toUpperCase()
							this.hjOr.hjInvoicequantity="1"
							this.hjOr.hjHscode="1"
							this.hjOr.hjSaleprice=prices
							this.hjOr.hjSalecurrency="USD"
							this.hjOr.hjCategoryname="家具装饰"
							this.hjOr.hjParentenname="Furniture decoration"
							this.hjOr.hjOrderpieces="1"
							this.hjOr.hjMailcargotype="2"
							//多选按钮
							this.hjOr.hjIsreturn="0"
							this.hjOr.hjIscontainsbattery="0"//是否包含电池
							this.hjOr.hjIsaneroidmarkup="0"//非液体化妆品
							this.hjOr.hjIsonlybattery="0"//是否纯电池
							this.hjOr.hjIsbreakable="0"//是否易碎
							this.hjOr.hjIscharged="0"//是否带电
							this.hjOr.hjCountrycode=results.fCountry
							this.hjOr.hjShipperhawbcode=results.fIds
							this.hjOr.hjShipzip=results.fPostCode
							this.hjOr.hjConsigneestreet=results.fAddress1
							this.hjOr.hjConsigneestreet2=results.fAddress2
							this.hjOr.hjConsigneestreet3=results.fAddress3
							this.hjOr.hjConsigneepostcode=results.fPostCode
							this.hjOr.hjConsigneename=results.fFirstName+results.fLastName
							this.hjOr.hjConsigneetelephone=results.fTelephone
							this.hjOr.hjConsigneemobile=results.fTelephone
							this.hjOr.hjConsigneeemail=results.fEmail
							this.hjOr.hjTotalprice=prices
							this.hjOr.hjConsignee="1"
							this.hjOr.hjStandby10=this.editableTabsValue
							console.log(results)
							//判断分类
							this.caDia=false
							this.hjWindow=true
							this.hjType="1"//添加
						})
					}else if(val2=="2"){
						//查看义达
						//查询订单列表
				    	  let datas={}
				    	  datas["id"]=value
				    	  await this.$http.post("${pageContext.request.contextPath}/Logistics/yidaSelectOne?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
				    		  let list=result.body
				    		  this.ydOr=list
				    		  this.ydOr.ydOrderWeight=(list.ydOrderWeight*1000)
				    		 /*  this.ydid=value
				    		  this.invoiceEnname=list.ydInvoiceEnname
				    		  this.invoiceCnname=list.ydInvoiceCnname
				    		  this.city=list.ydConsigneeCity
				    		  this.province=list.ydConsigneeProvince
				    		  this.unitCode=list.ydUnitCode
				    		  this.invoiceUnitcharge=list.ydInvoiceUnitcharge
				    		  this.sku=list.ydSku
				    		  this.invoiceQuantity=list.ydInvoiceQuantity
				    		  this.shippingMethod=list.ydShippingMethod
				    		  this.orderWeight=list.ydOrderWeight*1000
				    		  this.orderPieces=list.ydOrderPieces
				    		  this.mailCargoType=list.ydMailCargoType
				    		  this.cargotype=list.ydCargotype
				    		  this.order_info=list.ydOrderInfo
				    		  this.isReturn=list.ydReturnSign=="Y"?true:false */
				    		  this.optionList="1"
				    		  this.dialogVisible=true
				    	  })
					}else if(val2=="3"){
						//查看创志
						//查询订单列表
				    	  let datas={}
				    	  datas["id"]=value
				    	  await this.$http.post("Logistics/cz/selectId?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
				    		  let list=result.body
				    		  this.czOr=list
				    		  this.czOr.length=list.size.split("*")[0]
				    		  this.czOr.width=list.size.split("*")[1]
				    		  this.czOr.height=list.size.split("*")[2]
				    		  this.czOr.weight=(list.weight*1000)
				    		  this.czType="2"
				    		  this.czView=true
				    	  })
					}
			    },
			    generateMixed(n) {
			    	 let chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
		    	     let res = "";
		    	     for(let i = 0; i < n ; i ++) {
		    	         let id = Math.ceil(Math.random()*35);
		    	         res += chars[id];
		    	     }
		    	     return res;

		    	},
			    clearHj(){
			    	this.hjOr={}
					this.hjWindow=false
					this.hjType=""//查看修改
			    },
			    async hjWindowAdd(){
			    	//添加环金
			    	if(this.hjOr.hjConsigneecountryename=="" ||
			    			this.hjOr.hjConsigneecountrycname== "" ||
			    			this.hjOr.hjInvoiceenname== "" ||
			    			this.hjOr.hjInvoicecnname== "" ||
			    			this.hjOr.hjUnitcode== "" ||
			    			this.hjOr.hjInvoiceunitcharge== "" ||
			    			this.hjOr.hjInvoicecurrencycode== "" ||
			    			this.hjOr.hjSaleprice== "" ||
			    			this.hjOr.hjCategoryname== "" ||
			    			this.hjOr.hjParentenname== "" ||
			    			this.hjOr.hjInvoiceweight== "" ||
			    			this.hjOr.hjLength== "" ||
			    			this.hjOr.hjWidth== "" ||
			    			this.hjOr.hjHeight== ""){
			    			 this.$message.error("有值为空");
			    			 return false;
			    		 }
			    	if(this.hjOr.hjInvoiceweight.substr(0,1)=="0"){
			    		this.$message.error("重量不能以0开头");
			    		return false;
			    	}
			    	//需要添加的运单id  赋值为商品id
					/* this.hjIsreturn=this.hjIsreturn==false?"0":"1"
					this.hjIscontainsbattery=this.hjIscontainsbattery==false?"0":"1"//是否包含电池
					this.hjIsaneroidmarkup=this.hjIsaneroidmarkup==false?"0":"1"//非液体化妆品
					this.hjIsonlybattery=this.hjIsonlybattery==false?"0":"1"//是否纯电池
					this.hjIsbreakable=this.hjIsbreakable==false?"0":"1"//是否易碎
					this.hjIscharged=this.hjIscharged==false?"0":"1"//是否带电 */
			    	this.hjOr.id=""
			    	await this.$http.post("${pageContext.request.contextPath}/Logistics/addHJLogistics?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.hjOr),{emulateJSON:true}).then(result=>{
			    		//查询订单列表
		  	    	  	let datae={}
		  	    	  	datae["id"]=this.editableTabsValue
		  	    	  	datae["fid"]=this.hjOr.hjShipperhawbcode
		  	    	    this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
			  	    	 	for(let ise=0;ise<this.editableTabs.length;ise++){
			  	    	 		if(this.editableTabs[ise].name==this.editableTabsValue){
			  	    	 			this.editableTabs[ise].content.list=result1.body
						    		this.$forceUpdate()
			  	    	 		}
			  	    	 	}
		  	    	 	})
			    		this.clearHj()
			    		this.$message.success("添加成功")
			    	})
			    },
			    hjWindowEdit(){
			    	//修改环金
			    	if(this.hjOr.hjConsigneecountryename=="" ||
			    			this.hjOr.hjConsigneecountrycname== "" ||
			    			this.hjOr.hjInvoiceenname== "" ||
			    			this.hjOr.hjInvoicecnname== "" ||
			    			this.hjOr.hjUnitcode== "" ||
			    			this.hjOr.hjInvoiceunitcharge== "" ||
			    			this.hjOr.hjInvoicecurrencycode== "" ||
			    			this.hjOr.hjSaleprice== "" ||
			    			this.hjOr.hjCategoryname== "" ||
			    			this.hjOr.hjParentenname== "" ||
			    			this.hjOr.hjInvoiceweight== "" ||
			    			this.hjOr.hjLength== "" ||
			    			this.hjOr.hjWidth== "" ||
			    			this.hjOr.hjHeight== ""){
			    			 this.$message.error("有值为空");
			    			 return false;
			    		 }
			    	if(this.hjOr.hjInvoiceweight.substr(0,1)=="0"){
			    		this.$message.error("重量不能以0开头");
			    		return false;
			    	}
			    	this.$http.post("${pageContext.request.contextPath}/Logistics/editHJLogistics?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.hjOr),{emulateJSON:true}).then(result=>{
			    		this.$message.success("修改成功");
			    		this.clearHj()
			    	})
			    }
			    ,
			    async geteveryone(value,metho,val1,val23){
			    	//打印
			    	if(val1=="0"){
					if(value==""||value==null){
						this.$message.error("未获取到环金运单的打印单");
						return false;
					}
					let day={};
					day["lableKey"]=value
					this.$http.post("Logistics/setLableKey?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(day),{emulateJSON:true}).then(result=>{
						let data=result.body
						if(data.result.status=="1"){
							let days={}
							days["bytes"]=data.result.lableData
							days["metho"]=metho
							days["fileName"]=data.result.lableKey
							this.$http.post("Logistics/conserveFile?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(days),{emulateJSON:true}).then(result1=>{
								console.log(result1)
								window.open("${pageContext.request.contextPath}/"+result1.body,"_blank")
								let zs={}
								zs["hjStandy8"]=val23
								this.$http.post("Logistics/hj_logistics_post?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(zs),{emulateJSON:true}).then(result2=>{
									this.$message.success("已发件")
									this.selectall()
								})
							})
						}else{
							this.$message.error(data.result.errorMessage)
						}
					})
			    	}else if(val1=="1"){
			    		 
			    		let datas={}
			    		let configInfo={}
			    		configInfo["lable_file_type"]="2"
				    	configInfo["lable_paper_type"]="1"
					    configInfo["lable_content_type"]="1"
					    let additionalinfo={}
						additionalinfo["lable_print_invoiceinfo"]="Y"
						additionalinfo["lable_print_buyerid"]="N"
						additionalinfo["lable_print_datetime"]="Y"
						additionalinfo["customsdeclaration_print_actualweight"]="N"
						let listorder=[]
			    		
			    		let numbers={"reference_no":value}
			    		listorder.push(numbers) 
						datas["configInfo"]=configInfo
						datas["listorder"]=listorder
						configInfo["additional_info"]=additionalinfo
						await this.$http.post("${pageContext.request.contextPath}/Logistics/lable?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							if(result.body.success=="0"){
								this.$message.error(result.body.cnmessage)
							}else if(result.body.success=="1"){
								//添加运单
								let zs={};
								zs["hjStandy8"]=this.contents.fIds
								$.ajax({
									type : "post",
									contentType : "application/json;charset=utf-8", 
									url :"${pageContext.request.contextPath}/Logistics/hj_logistics_post?${_csrf.parameterName}=${_csrf.token}",
									dataType:"json",
									async:false,
									data:JSON.stringify(zs),
									success:function(data){
										console.log("状态改变")
									},
									error:function(json){
										this.$message.error("未发件");
									}
								}); 
								let listOrder=result.body.data
								for(let li=0;li<listOrder.length;li++){
									window.open(listOrder[li].lable_file)
								}
							}
			    	  	})
			    	}else if(val1=="3"){
			    		let data=[]
			    		if(value=="1"){
				    		data=this.checkedCities
			    		}else if(value=="2"){
			    			data.push(this.editableTabsValue)
			    		}else{
			    			this.$message.error("打印异常")
			    			return
			    		}
			    		//创志打印
			    		this.$message.success("后台批量拼接中，很慢!请耐心等待")
			    		this.operator=false
			    		this.$http.post("Logistics/cz/label?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
			    			console.log(result.body)
			    			window.open("${pageContext.request.contextPath}/resources/upload/"+result.body)
			    			
			    		})
			    	}
				},
			  //删除运单
				delete_HJ(value,values,val1,val2,val3){
					if(val1=="0"){
						this.$confirm("是否删除  '"+value+"'请谨慎操作!!!")
				          .then(_ => {
				        	let datas={}
							datas["id"]=value
							datas["hjShipperhawbcode"]=values
							this.$http.post("Logistics/delHJLogistics?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
								this.editableTabs[val3].content.list.hj.splice(val2, 1)
								this.$forceUpdate()
								this.$message.success("删除成功")
							})
				          })
				          .catch(_ => {})
					}else if(val1=="1"){
						this.$confirm("是否删除  '"+value+"'请谨慎操作!!!").then(_ => {
						let data1={}
						data1["id"]=value
						data1["ydReferenceNo"]=values
						this.$http.post("${pageContext.request.contextPath}/Logistics/ydDelOrder?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data1),{emulateJSON:true}).then(result=>{
							let results=result.body
							console.log(results)
							if(results.success=="0"){
								this.$message.error("删除失败")
							}else if(results.success=="1"){
								//添加运单
								this.editableTabs[val3].content.list.yd.splice(val2, 1)
								this.$forceUpdate()
								this.$message.success(results.cnmessage)
							}
						})
						})
					}else if(val1=="3"){
						this.$confirm("是否删除  '"+value+"'请谨慎操作!!!").then(_ => {
							let data1={}
							data1["id"]=value
							this.loading=true
							this.$http.post("Logistics/cz/del?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data1),{emulateJSON:true}).then(result=>{
								let results=result.body
								if(JSON.parse(results.data).status>0){
									//添加运单
										this.loading=false
										let datae={}
						  	    	  	datae["id"]=value
						  	    	  	datae["fid"]=values
						  	    	    this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
							  	    	 	for(let ise=0;ise<this.editableTabs.length;ise++){
							  	    	 		if(this.editableTabs[ise].name==this.editableTabsValue){
							  	    	 			this.editableTabs[ise].content.list=result1.body
										    		this.$forceUpdate()
							  	    	 		}
							  	    	 	}
						  	    	 	})
									this.$message.success(JSON.parse(results.data).info)
								}else{
									this.$message.error(JSON.parse(results.data).info)
								}
							})
							})
						}
				},
				async submitOrderOnes(){
					
			    	  //发送运单
			    	if($("input[name='hjor"+this.editableTabsValue+"']:checked").val()=="0"){
			    		  this.checkedCities=[this.editableTabsValue]
			    		  this.caDia=false
			    		  this.submitOrder()
			    		  this.$forceUpdate()
	    			}else if($("input[name='hjor"+this.editableTabsValue+"']:checked").val()=="2"){
	    				let datas={}

			        	 //同步发送
			        	this.loading=true
		    			this.caDia=false
	    				datas["id"]=this.contents.fIds
	    				//义达
	    				await this.$http.post("${pageContext.request.contextPath}/Logistics/postYd?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							let results=result.body
							console.log(results)
							for(let i=0;i<results.length;i++){
								if(JSON.parse(results[i].data).success=="0"){
      								this.loading=false
									this.$message.error(JSON.parse(results[i].data).cnmessage)
								}else if(JSON.parse(results[i].data).success=="1"){
									//添加运单
									this.$message.success(JSON.parse(results[i].data).cnmessage)
									console.log(JSON.parse(results[i].data))
									let datas1={}
									datas1["ydStandy7"]=results[i].method
									datas1["ydStandy6"]=JSON.parse(results[i].data).data.order_id
									datas1["ydStandy3"]=this.contents.fIds+""
									datas1["ydReferenceNo"]=JSON.parse(results[i].data).data.refrence_no
									datas1["ydShippingMethodNo"]=JSON.parse(results[i].data).data.shipping_method_no
									this.$http.post("${pageContext.request.contextPath}/Logistics/updateYd?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas1),{emulateJSON:true}).then(result2=>{
										let datae={}
	      								this.loading=false
						  	    	  	datae["id"]=this.editableTabsValue
						  	    	  	datae["fid"]=this.contents.fIds
						  	    	    this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
							  	    	 	for(let ise=0;ise<this.editableTabs.length;ise++){
							  	    	 		if(this.editableTabs[ise].name==this.editableTabsValue){
							  	    	 			this.editableTabs[ise].content.list=result1.body
							  	    	 		}
							  	    	 	}
						  	    	 	})
						  	    	 	this.$forceUpdate()
										this.selectall()
									})
								}
							}
						})
	    				
	    			}else if($("input[name='hjor"+this.editableTabsValue+"']:checked").val()=="3"){
	    				this.caDia=false
	    				this.czPost("1")
	    			}
			    	
			      },
			      yidaUpdate(){
			    	  this.$http.post("Logistics/yidaUpdate?${_csrf.parameterName}=${_csrf.token}").then(result1=>{
						})
			      },
			      yddel(){
			    	  if(this.checkedCities.length=="0"){
						 this.$message.error("请选择需要删除的产品");
						 return false;
					 }this.resultHJ=""
			    	  this.$http.post("Logsistics/ydDel?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
					  	for(let i=0;i<result.body.length;i++){
					  		this.resultHJ+=result.body[i]+"\n"+"*****************"+"\n"
					  	}
					  	this.operator=false
					  	$("#postHj").val(this.resultHJ);
						PostTrack();
			    	  })
			      },
			      czs(){//创志重置
			    	  if(this.checkedCities.length==0){
			    		  this.$message.error("请选择需要添加的创志订单")
			    		  return false
			    	  }
			    	  this.czView=true
			    	  this.czType="1"
			    	  Object.assign(this.$data.czOr, this.$options.data().czOr)
			      },
			      czs2(){
			    	  this.czView=true
			    	  this.czType="3"
			    	  Object.assign(this.$data.czOr, this.$options.data().czOr)
			      },
			      async czViewAdd(val){
			    	  //创志添加
			    	  let data={}
			    	  if(this.czOr.length==""||
			    			  this.czOr.service==""||
			    			  this.czOr.parcelCount==""||
			    			  this.czOr.number==""||
			    			  this.czOr.nameEn==""||
			    			  this.czOr.nameZh==""||
			    			  this.czOr.qty==""||
			    			  this.czOr.weight==""||
			    			  this.czOr.width==""||
			    			  this.czOr.height==""){
			    		  this.$message.error("有值为空")
			    		  return false
			    	  }
			    	  this.czOr["size"]=this.czOr.length+"*"+this.czOr.width+"*"+this.czOr.height
			    	  this.czOr.fsr1=this.timeFormate(new Date())
			    	  data["cz"]=this.czOr
			    	  data["type"]=val
			    	  if(val=="1"){
			    		  //批量添加
				    	  data["ids"]=this.checkedCities
			    	  }else if(val=="2"||val=="3"){
			    		  //创志修改||创志单独添加
			    		  let dat=[]
			    		  dat.push(this.editableTabsValue)
				    	  data["ids"]=dat
			    	  }
	    	  		  this.operator=false
	    	  		  this.czView=false
	    	  		  this.loading=true
	    	  		  this.caDia=false
	    	  		  let idcz=""
			    	  //添加创志
			    	  await this.$http.post("Logistics/cz/addOrder?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
			    	  		if(result.body>0){
			    	  			for(let i in this.editableTabs){
			    	  				if(this.editableTabs[i].name==this.editableTabsValue){
			    	  					idcz=this.editableTabs[i].title
			    		                //await this.shows(idcz,this.editableTabsValue)
			    		                //查询订单列表
			    		  	    	  	let datae={}
			    		  	    	  	datae["id"]=this.editableTabsValue
			    		  	    	  	datae["fid"]=idcz
			    		  	    	    this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
			    			  	    	 	for(let ise=0;ise<this.editableTabs.length;ise++){
			    			  	    	 		if(this.editableTabs[ise].name==this.editableTabsValue){
			    			  	    	 			this.editableTabs[ise].content.list=result1.body
			    						    		this.$forceUpdate()
			    			  	    	 		}
			    			  	    	 	}
			    		  	    	 	})
			    	  				}
			    	  			}
				    	  		this.loading=false
				    	  		if(val=="1"||val=="3"){
			    	  				this.$message.success("成功添加"+result.body+"条")
				    	  		}else if(val=="2"){
				    	  			this.$message.success("成功修改"+result.body+"条")
				    	  		}
			    	  		}else{
				    	  		this.loading=false
			    	  			this.$message.error("添加失败")
			    	  		}
			    	  })
			      },
			      timeFormate(timeStamp) {
			          let year = new Date(timeStamp).getFullYear();
			          let month =new Date(timeStamp).getMonth() + 1 < 10? "0" + (new Date(timeStamp).getMonth() + 1): new Date(timeStamp).getMonth() + 1;
			          let date =new Date(timeStamp).getDate() < 10? "0" + new Date(timeStamp).getDate(): new Date(timeStamp).getDate();
			          let hh =new Date(timeStamp).getHours() < 10? "0" + new Date(timeStamp).getHours(): new Date(timeStamp).getHours();
			          let mm =new Date(timeStamp).getMinutes() < 10? "0" + new Date(timeStamp).getMinutes(): new Date(timeStamp).getMinutes();
			          let ss =new Date(timeStamp).getSeconds() < 10? "0" + new Date(timeStamp).getSeconds(): new Date(timeStamp).getSeconds();
			          this.nowTime = year + "-" + month + "-" + date +"-"+" "+hh+":"+mm+':'+ss ;
			        },
			      czPost(bal){
			    	  //创志批量发送
		        	  	this.operator=false
		        	  	this.loading=true
		        	  	this.czView=false
		        	  	let das=[]
		        	  	if(bal=="0"){
		        	  		das=this.checkedCities
		        	  	}else if(bal=="1"){
		        	  		das.push(this.editableTabsValue)
		        	  	}else{
		        	  		this.loading==false
		        	  		this.$message.error("发送异常")
		        	  		return
		        	  	}
	    				//义达
	    				this.$http.post("Logistics/cz/post?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(das),{emulateJSON:true}).then(result=>{
							let results=result.body
							if(results.length == 0){
								this.loading=false
								this.$message.error('未找到创志的运单');
							}
							this.sun=0
	      					this.suns=0
	      					this.resultHJ=""
							for(let i=0;i<results.length;i++){
								this.sun+=1;
								if(JSON.parse(results[i].data).status=="0"){
									this.resultHJ+="发送失败   "+results[i].fid+"  消息：:"+JSON.parse(results[i].data).info+"\n"+"*****************"+"\n"
									$("#postHj").val(this.resultHJ);
									 PostTrack();
								}else if(JSON.parse(results[i].data).status=="1"){
									//添加运单
									let datas1={}
									datas1["service"]=results[i].map.shipment.service
									console.log(JSON.parse(results[i].data))
									datas1["fsr4"]=JSON.parse(results[i].data).data.shipment.shipment_id
									datas1["fsr2"]=results[i].fid
									this.$http.post("Logistics/cz/tarack?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas1),{emulateJSON:true}).then(result1=>{
										this.resultHJ+="发送成功   "+results[i].fid+"\n"+"*****************"+"\n"
										this.suns+=1;
										$("#conHJ").html("已发送"+this.sun+"条，其中"+this.suns+"条发送成功，"+(this.sun-this.suns)+"条发送失败");
										$("#postHj").val(this.resultHJ);
										PostTrack();
										this.selectall()
									})
									//查询订单列表
					  	    	  	let datae={}
					  	    	  	datae["id"]=this.editableTabsValue
					  	    	  	datae["fid"]=results[i].fid
					  	    	    this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datae),{emulateJSON:true}).then(result1=>{
					  	    			this.contents["list"]=result1.body
					  	    	 	})
								}
							}
							this.loading=false
						})
			      }
				},
				updated(){
		    	 $(".box-card").css("width",this.wids)
				}
				
	})
</script>

</body>
</html>