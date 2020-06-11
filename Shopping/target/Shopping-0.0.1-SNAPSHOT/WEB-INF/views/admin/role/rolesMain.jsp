<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page session="true"%>
<!DOCTYPE html>
<html style="overflow:hidden">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>商城后台</title>
<jsp:include page="../commonCss.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css" />
<script src="${pageContext.request.contextPath}/resources/admin/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/toastr-master/build/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/jss/sweetalert2@9.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script> 
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script>

</head>
<body class="blank" >
<jsp:include page="../main/top.jsp"/> 
<jsp:include page="../main/left.jsp"/>
<div id="wrapper">
	<div class="small-header transition animated fadeIn">
		<div class="content animate-panel" style="margin-bottom:-40px">
		    <div class="hpanel">
		        <div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="hpanel">
								<div id="error_div" class="text-left"></div>
									<form id="form_userInfo" name="form_userInfo" action="userInfo_save.do" method="POST">
										<div class="form-group col-lg-3">
											<label>职称</label>
											<el-input v-model="selectText" placeholder="职称"></el-input>
										</div>
										<div class="form-group col-lg-3">
											<label>包含权限</label><br>
											<el-cascader style="width: 100%;"
											v-model="role"
											:options="options" 
											:props="propp" 
											:show-all-levels="false"
											:change-on-select="true">
											</el-cascader>
										</div>
										<div class="form-group col-lg-3">
											<label>展示数</label>
											<el-input v-model="limit" placeholder="展示数" type="number"></el-input>
										</div>
										<div class="form-group col-lg-3">
											<div class="text-right m-t-xs" style="margin-top:20px">
													<el-row>
													  <el-button type="primary" @click="selectAllList" icon="el-icon-search">查询</el-button>
													  <el-button @click="reset" type="success">重置</el-button>
													  <c:if test="${fn:contains(jurisdiction.jurisdiction,'roles_add')}">
													  <el-button @click="add" type="success">添加</el-button>
													  </c:if>
													</el-row>
											</div>
										</div>			
									</form>
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
					<div class="panel-body" style="height:700px">
					<!-- 展示列表 -->
					<ul style="list-style-type: none;">
						<li v-for="item in list" style="" class="user_list ">
							职称：<b>{{item.roleName}}</b><br>
							备注：{{item.standy1}}
							<br>
							<c:if test="${fn:contains(jurisdiction.jurisdiction,'roles_del')}">
							<template>
							  <button id="tojp" class="btn btn-danger" style="margin-right: 5px;margin-top: 5px"
									@click="open4(item.id)" type="button">
								<strong>删除</strong>
							</button>
							</template>
							</c:if>
							<c:if test="${fn:contains(jurisdiction.jurisdiction,'roles_edit')}">
							<button  @click="editUser(item.id)" class="btn btn-success"  style="margin-right: 5px;margin-top: 5px"
									type="button">
								<strong>修改</strong>
							</button>
							</c:if>
						</li>
					</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<form id="forwordform" action='<c:url value="/admin/role/edit?${_csrf.parameterName}=${_csrf.token}" />' method='POST'>
	<input type="hidden" name="userId" id="userId"/>
	<input type="hidden" name="roleStr" id="roleStr"/>
</form>
<script type="text/javascript">
var oTable;
var header = $("meta[name='_csrf_header']").attr("content");  
var token = $("meta[name='_csrf']").attr("content"); 
var contextPath = '${pageContext.request.contextPath}';
$(function() {
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
Vue.http.options.root="${pageContext.request.contextPath}";
var vm=new Vue({
	el:'#wrapper',
	data:{
		selectText:"",//查找的文本内容
		role:"",//用户角色
		page:"1",//第几页
		limit:"50",//每次展示多少个
		type:"",//用户状态
		endpage:"",//尾页
		totalPage:[],//所有页数显示数据
		list:[],//数据列表
		options:[],//权限列表
		propp:{
			children:"childen",
			label:"name",
			value:"id"
		}
	},
	created(){
		this.selectAllList();

		//查找所有的权限
		//ajiox请求方式
   	    this.$http.post("admin/role/nodes?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
			this.options=result.body
		})
	},
	methods:{
		selectAllList(){
			//查询所有的职称
			let datas={}
			datas["selectText"]=this.selectText
			datas["role"]=this.role[this.role.length-1]
			datas["page"]=this.page
			datas["limit"]=this.limit
			//ajiox请求方式
			this.$http.post("admin/role/selAll?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
				this.list=result.body.list
			})
		},
		add(){
			//添加职称
			var url = contextPath+"/admin/role/add?${_csrf.parameterName}=${_csrf.token}";
		 	$("#forwordform").attr("action",url);
			$("#forwordform").submit(); 
		},
		editUser(userId){
			//修改职称
			$("#userId").val(userId);
			$("#forwordform").submit();
		},
		async del(id){
			//删除职称
			let datas={}
			datas["id"]=id
			//ajiox请求方式
			await this.$http.post("admin/role/del?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
				if(result.body>0){
				     this.$message({
				           type: 'success',
				           message: '已删除'
				       });
					this.selectAllList();
				}
			})
		},
		reset(){
			//重置
			this.selectText=""//查找的文本内容
			this.role=""//用户角色
			this.limit="50"//每次展示多少个
		},
		open4(data) {
	        this.$msgbox({
	            title: '消息',
	            message: '删除后无法恢复，请谨慎……',
	            showCancelButton: true,
	            confirmButtonText: '确定',
	            cancelButtonText: '取消'
	          }).then(action => {
	        	  this.del(data)
	          });
	     }
	}
})

</script>
</body>
</html>