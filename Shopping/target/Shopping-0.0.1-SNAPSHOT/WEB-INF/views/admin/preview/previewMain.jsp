<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page session="true"%>
<!DOCTYPE html>
<html style="overflow: hidden;padding:0px;margin:0px">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>集拼</title>

<script src="${pageContext.request.contextPath}/resources/admin/jquery.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/admin/jQuery.print.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/admin/toastr-master/build/toastr.min.js"></script>
<jsp:include page="../commonCss.jsp" />
<script src="${pageContext.request.contextPath}/resources/jss/sweetalert2@9.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vue-virtual-scroller.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script>
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/vue-virtual-scroller.min.js"></script>
<!-- 物流翻译 -->
<script src="${pageContext.request.contextPath}/resources/jss/logisticsChange.js"></script>
<!-- 运费 -->
<script src="${pageContext.request.contextPath}/resources/jss/Calculation.js"></script>
</head>
<style scoped>
	.scroller {
	  height: 100%;
	}
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
    width: 480px;
  }
  
</style>
<body class="blank" >
<jsp:include page="../main/top.jsp" />
<jsp:include page="../main/left.jsp" />
<div id="wrapper" v-loading="loading2"
    element-loading-text="拼命上传中............."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)">
		<div class="small-header transition animated fadeIn">
			<div class="content animate-panel" style="margin-bottom: -40px">
				<div class="hpanel">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="hpanel">
								
									<div class="form-group col-lg-2">
											<label>查询内容</label>
											<el-input v-model="selectText" placeholder="跟踪号" type="text"></el-input>
									</div>
									
									<div class="form-group col-lg-2">
											<label>物流方式</label>
											<template>
											  <el-select v-model="value" placeholder="请选择">
											    <el-option
											      v-for="item in options"
											      :key="item.shortName"
											      :label="item.name"
											      :value="item.shortName">
											    </el-option>
											  </el-select>
											</template>
											
												<%-- <select id="hj_shippingMethods" class="form-control" v-model="logistics" type="text">
							                      	  <option value="">全部</option>
							                      	  <c:forEach items="${logoutlist}" var="item">
							                     	  	<option value="${item.shortName}">${item.logisticsName}</option>
							                     	  </c:forEach>
							                      </select> --%>
									</div>
						
									<div class="form-group col-lg-4">
											<label>日期</label>
											<template>
											  <div class="block">
											    <el-date-picker 
											      style="width: 100%;"
											      v-model="value7"
											      type="daterange"
											      align="right"
											      value-format="yyyy-MM-dd"
											      unlink-panels
											      range-separator="至"
											      start-placeholder="开始日期"
											      end-placeholder="结束日期"
											      :picker-options="pickerOptions2">
											    </el-date-picker>
											  </div>
											</template>
									</div>
									<div class="form-group col-lg-2">
										<label>展示数</label>
										<el-input v-model.number="limit"  placeholder="展示数" type="number" min="0"></el-input>
									</div>
									
									<!-- 功能 -->
									<div style="float:right">
										<el-button size="small" type="success" @click="selectAllList">查询</el-button>
										<el-button size="small" type="info"  @click="reset">重置</el-button>
										<c:if test="${fn:contains(jurisdiction.jurisdiction,'previews_del')}">
											<el-button size="small" type="danger" @click="deletes">删除</el-button>
										</c:if>
										
										<c:if test="${fn:contains(jurisdiction.jurisdiction,'previews_post')}">
											<template>
												<el-upload
												  class="upload-demo" style="display: inline;margin-top:2px;margin-left:10px"
												  :on-progress="toupload"
												  enctype="multipart/form-data"
												  action="${pageContext.request.contextPath}/preivew/preivew/import?${_csrf.parameterName}=${_csrf.token}"
												  method="post"
												  :on-change="handleChange"
												  :show-file-list="false"
												  :file-list="fileList3"
												  :on-success="upsuccess">
												  <el-button size="small" type="primary">点击上传</el-button>
												</el-upload>
											</template>
										</c:if>
										<c:if test="${fn:contains(jurisdiction.jurisdiction,'previews_export')}">
											<form id="formtyw2" method="post" style="float: right;margin-left:15px" action="${pageContext.request.contextPath}/preivew/preivew/exports?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
												<input id="idList" name="idList" type="text" v-model="idList" style="display:none"/>
												<el-button size="small" type="primary" @click="exports">点击导出</el-button>
											</form>
										</c:if>
										<c:if test="${fn:contains(jurisdiction.jurisdiction,'previews_export_price')}">
											<form id="exports_price" method="post" style="float: right;margin-left:15px" action="${pageContext.request.contextPath}/preivew/preivew/exportsPrice?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
												<input id="idList" name="idList" type="text" v-model="idList" style="display:none"/>
												<el-button size="small" type="primary" @click="exports_price">导出费用</el-button>
											</form>
										</c:if>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
		<div class="content animate-panel">
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-body" style="height:680px;display:block">
							<el-checkbox :indeterminate="isIndeterminate" style="float: left;line-height: 30px;margin-left: 10px;" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
							<!-- `checked` 为 true 或 false -->
  							<el-checkbox style="float: left;line-height: 30px;margin-left: 10px;" v-model="checked">未扣费</el-checkbox>
							<!-- 分页 -->
							<div style="text-align: center;margin-bottom:5px;height:30px">
									<el-pagination
									  background
									  :page-size="limit"
	      							  @current-change="handleCurrentChange"
									  layout="prev, pager, next"
									  :total="total">
									</el-pagination>
							</div>
							<!-- 列表 -->
							<div style="overflow-y:auto">
							<template>
								<el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
								<recycle-scroller
								    class="scroller"
								    :items="list"
								    :item-size="110"
								    key-field="id"
								    v-slot="{ item }"
								    style="height:600px;width:100%"
								  >
									<el-card class="box-card" style="width:100%" >
										<el-checkbox :label="item.id" class="form-group col-lg-2">{{item.hjShipperHawbcode}}</el-checkbox>
										<nobr  class="text item form-group col-lg-2">
										   参考编号:{{item.customerNumber}}
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										  跟踪号:{{item.trackingSingleSign}}
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										 物流方式:{{changes(item.channelCode)}}
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										 反馈重量:{{item.actualFreight}}g
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										 申报重量:{{item.spare3}}g
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										 反馈金额:{{item.amountReceivable}}
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										 预算金额:{{item.spare2}}
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										 净利润:{{item.spare5 == null?"请填写采购费":parseFloat(((item.standby1-item.spare2-item.spare5)/item.standby1)*100).toFixed(2)+"%"}}
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										 时间:{{times(item.deliverTime)}}
										</nobr>
										<nobr  class="text item form-group col-lg-2">
										国家:{{item.destinationCountry}}
										差值:<b v-if="item.amountReceivable-item.standby2>=0" style="color:green"> {{parseFloat(item.amountReceivable-item.spare2).toFixed(2)}}</b>
											<b v-if="0>item.amountReceivable-item.standby2" style="color:red">{{parseFloat(item.amountReceivable-item.spare2).toFixed(2)}}</b>
										</nobr>
										<nobr  class="text item form-group col-lg-2">
											<el-button size="small" style="padding: 5px;" type="danger" v-if="item.spare5 == null"  @click="dialogVisibles(item.trackingSingleSign)">采购费</el-button>
											<el-button size="small" style="padding: 5px;" type="success" v-if="item.spare5 != null"  @click="dialogVisibles(item.trackingSingleSign)">采购费</el-button>
										</nobr>
									</el-card>
									</recycle-scroller>
								</el-checkbox-group>
								<el-dialog
								  title="提示"
								  :visible.sync="dialogVisible"
								  width="30%"
								  :before-close="handleClose">
								  <el-input
									  placeholder="请输入采购费"
									  v-model="ProcurementFee"
									  type="number"
									  clearable>
									</el-input>
								  <span slot="footer" class="dialog-footer">
								    <el-button @click="procurementscach">取 消</el-button>
								    <el-button type="primary" @click="procurements">确 定</el-button>
								  </span>
								</el-dialog>
							</template>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
</div>

	<script type="text/javascript">
		$('#leftMenuOrder').addClass('active');
		var oTable;
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		var contextPath = '${pageContext.request.contextPath}';
		$(function() {
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		});
		Vue.component('recycle-scroller', VueVirtualScroller.RecycleScroller)
		Vue.http.options.root="${pageContext.request.contextPath}";
		var vm=new Vue({
			el:'#wrapper',
			data:{
				selectText:"",//查找的文本内容
				page:"1",//第几页
				limit:50,//每次展示多少个
				type:"",
				list:[],//展示列表
				endpage:"",//尾页
				idList:"",
				totalPage:[],//所有页数显示数据
				checked: false,//未扣费
				dialogVisible: false,//添加采购费
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
		        value7: '',//日期数组形式，1.开始日期，2.结束日期
		        options: [],
	          value: '',//选择框结果
	          fileList3: [],
	          checkAll: false,//全选按钮
	          checkedCities: [],//值
	          isIndeterminate: true,//
	          total:0,//总长
	          ProcurementFee:"",//采购费
	          ProcurementFeeSpare:"",//采购费备用
	          loading2: false,//加载中
			  logoutlist:[],
			  jurisdiction:'',
			},
			created(){
				this.selectAllList();
				//ajiox请求方式
				let datas={}
	    	    this.$http.post("preview/logoutlist?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
					this.options=result.body
				})
				this.$http.post("order/select/list?${_csrf.parameterName}=${_csrf.token}").then(result=>{
				    this.logoutlist=result.body.logoutlist
				    this.jurisdiction=result.body.jurisdiction
				})
			},
			mounted(){
			},
			updated(){
	           	 let a=""
	           	 let c=this.checkedCities
	           	 for(let i=0;i<c.length;i++){
	           		 a+=c[i]+","
	           	 }
	           	 this.idList=a.substr(0,a.length-1)
			},
			methods:{
				  selectAllList(){
					//创建一个datas存储值
					//axios请求方式
						let datas={}
						datas["selectText"]=this.selectText
						datas["value"]=this.value
						datas["date1"]=this.value7[0]
						datas["date2"]=this.value7[1]
						datas["page"]=this.page
						datas["limit"]=this.limit
						if(this.checked==true){
							datas["cek"]="1"
						}
						this.$http.post("preview/selectAll?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							this.list=result.body.list
							this.total=result.body.total
							this.checkedCities=[]
						})
				  },
			      handleCurrentChange(val) {
					  //分页改变
					 this.page=val
					 this.selectAllList();
			      },
			      handleChange(file, fileList) {
			    	  //上传文件名
		          },
		          toupload(event, file, fileList){
		        	  //上传中
		        	  toastr.success(file.name+"准备上传")
		        	  this.loading2=true;
		          },
		          upsuccess(response, file, fileList){
		        	  //上传成功返回
		        	  this.$notify({
		                  title: '成功',
		                  message: '成功上传了'+response+"条数据",
		                  type: 'success'
		                });
		        	  this.loading2=false;
		        	  this.selectAllList();
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
	            	  //单选
	                let checkedCount = value.length;
	                this.checkAll = checkedCount === this.list.length;
	                this.isIndeterminate = checkedCount > 0 && checkedCount < this.list.length;
	              },
	              reset(){
	            	  //重置
	            	  this.selectText=""//查找的文本内容
	            	  this.limit=50//每次展示多少个
	            	  this.value=''//选择框结果
	            	  this.value7=""//日期
	              },
	              deletes(){
	            	    //删除
	            		//创建传输值
						if(this.checkedCities.length=="0"){
							 toastr.error("请选择需要删除的数据");
							 return false;
						 }
						Swal.fire({
							  title: '你确定吗?',
							  text: "删除这"+this.checkedCities.length+"条后无法恢复!",
							  icon: 'warning',
							  showCancelButton: true,            
							    confirmButtonColor: '#3085d6',// 确定按钮的 颜色
							    confirmButtonText: '确定',// 确定按钮的 文字
							    showCancelButton: true, // 是否显示取消按钮
							    cancelButtonColor: '#d33', // 取消按钮的 颜色
							    cancelButtonText: "取消", // 取消按钮的 文字
							}).then((result) => {
							  if (result.value) {
								//axios请求方式
							 	this.$http.post("preview/delets?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
								    this.$notify({
					                  title: '成功',
					                  message: '成功删除了'+result.body+"条数据",
					                  type: 'success'
					                });
									this.selectAllList()
								}) 
							  }else{
								  Swal.fire("取消", "点击了取消", "error");
							  }
							})
						
	              },
	              exports(){
	            	  //导出数据
	            	 if(this.idList==""){
	            		 toastr.error("请选择需要导出的数据");
						 return false;
	            	 }else{
	            		 $("#formtyw2").submit()
	            	 }
	              },
	              exports_price(){
	            	  //导出erp数据
	            	 if(this.idList==""){
	            		 toastr.error("请选择需要导出的数据");
						 return false;
	            	 }else{
	            		 $("#exports_price").submit()
	            	 }
	              },
	              changes(val){
	            	  //转换物流
		        	 /*  let chans=this.logoutlist
		        	  for(let i=0;i<chans.length;i++){
		        		  if(chans[i].shortName==val||chans[i].standy5==val){
		        			  return chans[i].name
		        		  }
		        	  } */
	            	//转换物流
		        	  let chans=this.logoutlist
		        	  for(let i=0;i<chans.length;i++){
		        		  if(chans[i].shortName==val||chans[i].standy5==val){
		        			  return chans[i].logisticsName
		        		  }
		        	  }
	              },
	              times(data){
	            	  //时间转换
	            	return timechange(data)
	              },
	              calculation(data1,data2,data3){
	            	//w：重量g，c：国家二字码，s：运输方式简称
	            	 return calculation(data1,data2,data3)
	              },
	              dialogVisibles(data){
	            	  //对话框
	            	  this.ProcurementFeeSpare=data
	            	  this.dialogVisible=true
	              },
	              handleClose(done) {
	            	  //对话框关闭
	                  this.$confirm('确认关闭？')
	                    .then(_ => {
	                      done();
	                    })
	                    .catch(_ => {});
	              },
	              procurements(){ //提交采购费
	            	  let datas={}
	            	  datas["ProcurementFee"]=this.ProcurementFee//输入框采购费
	            	  datas["Track"]=this.ProcurementFeeSpare//跟踪号
	            	  if(this.ProcurementFee==""){
			              this.$message('请输入采购费用');
	            		  return false;
	            	  }
	            	  //axios请求方式
				 	 this.$http.post("preview/procurements?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
					    this.$notify({
		                  title: '成功',
		                  message: '成功添加了'+result.body+"的采购费",
		                  type: 'success'
		                })
						this.selectAllList()
					})
	            	  this.ProcurementFee=""
	            	  this.dialogVisible=false
	            	 
	              },
	              procurementscach(){
	            	  //取消提交采购费
	            	  this.dialogVisible=false
	              }
			}
			
		})
	</script>
	
</body>
</html>