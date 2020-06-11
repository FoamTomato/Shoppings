<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   

<%@page session="true"%>
<!DOCTYPE html>
<html style="overflow:hidden;">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>商城后台</title>
<jsp:include page="../commonCss.jsp" />
<jsp:include page="../commonJs.jsp" />
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script> 
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script>

</head>
<body class="blank">
<jsp:include page="../main/top.jsp" />
<jsp:include page="../main/left.jsp" />
<div id="wrapper">
		<div class="small-header transition animated fadeIn">
			<div class="content animate-panel">
				<div class="hpanel">
					<div class="panel-body">
						<div class="row">
								<div class="hpanel">
									<div id="error_div" class="text-left"></div>
									<form id="form_userInfo" name="form_userInfo" action='<c:url value="/admin/user/save?${_csrf.parameterName}=${_csrf.token}" />' method="POST">
										<input type="hidden" id="id" name="id" value="${user.id}"/>	
										<div class="form-group col-lg-12">
											<label>职称</label>
											<input type="text" v-model="rolename" id="rolename" name="rolename" class="form-control" maxlength="50" placeholder="职称"	value='${user.userName}' />
										</div>
										<div class="form-group col-lg-12">
											<label >备注</label>
											<input type="text" v-model="remark" id="remark" name="remark" class="form-control" maxlength="50" placeholder="备注"	value='${user.userName}' />
										</div>
										<!-- 权限列表 -->
										<div class="form-group col-lg-12">
											<label>用户权限</label>
											<div style="height:500px;overflow: auto">
												<el-tree
												  :data="props"
												  :props="propp"
												  :load="loadNode"
												  ref="tree"
												  lazy
												  default-expand-all
												  show-checkbox
												  node-key="id"
												  @check-change="handleCheckChange">
												</el-tree>
											</div>
										</div>
										<div class="text-left m-t-xs">
											<button  @click="edits" class="btn btn-success" type="button">
												<strong>${but }</strong>
											</button>
											<button  @click="back" class="btn btn-success" type="button">
												<strong>返回</strong>
											</button>
										</div>
									</form>
								</div>
							</div>
							
					</div>
				</div>

			</div>

		</div>
</div>
 
	<script type="text/javascript">
		var oTable;
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		var contextPath = '${pageContext.request.contextPath}';
		var validate;
		$(function() {
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		});
		Vue.http.options.root="${pageContext.request.contextPath}";
		
		var vm=new Vue({
			el:'#wrapper',
			data:{
				id:"",
				rolename:"",//职称
				remark:"",//备注
				list:[],//数据列表
				props: [], //父节点//子节点
				propp:{
					children:"pid",
					label:"name"
				},
				names:""
			},
			created(){
				this.selectAllList();
			},
			methods:{
				selectAllList(){
					//查找所有的权限
					//ajiox请求方式
					let datas={}
					datas["userId"]=${userId}
					//查找权限
		    	    this.$http.post("admin/role/nodes?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
						this.props=result.body
					})
					//查找用户
					this.$http.post("admin/role/edits?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						let data=result.body
						this.id=data.id
						this.rolename=data.roleName
						this.names=data.roleName
						this.remark=data.standy1
						let results=data.roleRoles.split(",")
						//转换为int数组
						let c=[]
						for(let i=0;i<results.length;i++){
							c.push(parseInt(results[i]))
						}
						this.checkeData(c)
					})
					
				},
				async edits(){
					//修改
					//存储权限列表
					let res=""
					let result=this.list
					//判断是否为空值
					if(this.rolename==""){
						toastr.error("职称不能为空")
						return false
					}else if(result.length==0){
						toastr.error("至少选择一个权限")
						return false
					}
					for(let i=0;i<result.length;i++){
						res+=result[i]+","
					}
					//判断职称是否存在
					//ajiox请求方式
					let data={}

					//修改
					let datas={}
					datas["rolename"]=""
					//判断rolename是否重复
					if(this.names!=this.rolename){
						datas["rolename"]=this.rolename
						let che=0
						await this.$http.post("admin/role/checkRolename?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
							if(result.body!=0){
								che+=1
							}
						})
						if(che>0){
							toastr.error("该职称已存在")
							return false
						}
					}
					//ajiox请求方式
					datas["id"]=this.id
					datas["remark"]=this.remark
					datas["roles"]=res.substr(0,res.length-1)
					this.$http.post("admin/role/update?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						if(result.body!=0){
							toastr.success("修改成功")
						}
					})
				},
				back(){
					//返回
					window.location.href =contextPath+'/admin/role';
				},
				handleCheckChange(data, checked, indeterminate) {
					let res = this.$refs.tree.getCheckedNodes()
				    let arr = []
				    res.forEach((item) => {
				      arr.push(item.id)
				    })
					this.list=arr
			    },
			    loadNode(node, resolve) {
			        //支持的下拉等级
			        if (node.level > 4) return resolve([])
			        //存入子级
			        setTimeout(() => {
			        	var data=[];
			        	if(node.data.childen!=null){
			        		data=node.data.childen
			        	}
			        	resolve(data)
			        }, 300)
			      },
			     checkeData(data){
			    	//默认选中的
				    this.$refs.tree.setCheckedKeys(data)
			     }
			}
		})
	</script>
</body>
</html>