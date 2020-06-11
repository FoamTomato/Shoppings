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
<title>国家管理</title>
<jsp:include page="../commonCss.jsp" />
<script src="${pageContext.request.contextPath}/resources/admin/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script>
<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
</head>
<body class="blank" style="margin: 0px; padding: 0px;overflow: hidden">
	<jsp:include page="../main/top.jsp" />
	<jsp:include page="../main/left.jsp" />
<!-- 主界面 -->
<div id="apps">

<div id="wrapper"
v-loading="loading"
element-loading-text="拼命加载中"
element-loading-spinner="el-icon-loading"
element-loading-background="rgba(0, 0, 0, 0.8)">
<el-container>
	    <el-main>
			<div class="animate-panel">	
			<div class="row">
				<div class="col-lg-12">
					<div class="hpanel">
						<div class="panel-body"   style="height:850px;padding:0 50px">
							<template>
								<el-row :gutter="12" style="margin:30px 0;" class="demo-autocomplete">
									  <el-col :span="24" style="padding:0">
									    <el-card shadow="hover">
									      <el-row class="demo-autocomplete">
										    <el-autocomplete
										      class="inline-input"
										      v-model="state2"
										      size="mini"
										      :fetch-suggestions="querySearch"
										      placeholder="请输入二字码/中文简写/英文简写"
										      style="width:300px"
										      :trigger-on-focus="false"
										      @select="handleSelect"
										    ></el-autocomplete>
										  <el-button size="mini" style="margin-left:10px" @click="search">查找</el-button>
										  <el-button size="mini" style="margin-left:10px" @click="dialogVisibles">添加</el-button>
										  <el-button type="info" size="mini" @click="deletes">删除</el-button>
							  			  <p style="margin-top:10px;margin-bottom:-10px"> 总计:{{tableData.length}}个国家   选中:{{checkedCities.length}}个国家</p>
									    </el-card>
									  </el-col>
								</el-row>
															  
							  <el-table size="mini"
							    :data="tableData"
							    stripe
							    border
							    height="650px"
							    style="width: 100%;height:95%;overflow:auto"
							    @selection-change="handleSelectionChange">
							    
							    <el-table-column
							      type="selection"
							      fixed
							      width="65">
							    </el-table-column>
							    <el-table-column
							      label="二字码"
							      width="300">
							      <template slot-scope="scope">
							      	{{ scope.row.shortCode }}
							      </template>
							    </el-table-column>
							    <el-table-column
							      label="中文简称"
							      width="300">
							      <template slot-scope="scope">
							        {{scope.row.cName}}
							      </template>
							    </el-table-column>
							    <el-table-column
							      label="英文简称"
							      width="300">
							      <template slot-scope="scope">
							        {{scope.row.eName}}
							      </template>
							    </el-table-column>
							    
							    <el-table-column
							      label="备注"
							      width="450">
							      <template slot-scope="scope">
							        {{scope.row.remark}}
							      </template>
							    </el-table-column>
							    <el-table-column
							      label="操作"
							      width="180">
							      <template slot-scope="scope">
							        <el-button type="text" size="small" @click="edits(scope.row)">编辑</el-button>
							      </template>
							    </el-table-column>
							  </el-table>
							</template>
						</div>
					</div>
				</div>
				</div>
			</div>
		</el-main>
  		
</el-container>
<!-- 详情 -->
<el-dialog
  :title="title+'国家'"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose">
    <el-row :gutter="20" style="margin:10px 0px">
	  <el-col :span="12">中文简称<el-input v-model="cname" size="mini" placeholder="中文简称"></el-input></el-col>
	  <el-col :span="12">英文简称<el-input v-model="ename" size="mini" placeholder="英文简称"></el-input></el-col>
	</el-row>
  
    <el-row :gutter="20" style="margin:10px 0px">
	  <el-col :span="12">二字码<el-input v-model="ecode" size="mini"  placeholder="二字码"></el-input></el-col>
	  <el-col :span="12">备注<el-input v-model="remark" size="mini" placeholder="备注"></el-input></el-col>
	</el-row>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addCountry">{{title}}</el-button>
  </span>
</el-dialog>
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
		        tableData: [],
				checkedCities:[],//选中
		        loading:false,
		        dialogVisible:false,//弹窗
		        restaurants: [],
		        state2: '',
		        cname:"",
		        ename:"",
		        ecode:"",
		        remark:"",
		        title:"",
		        id:""
		      }
		},
		created(){
			  this.$http.post("Logistics/country/selectCountry?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
					let data=result.body
					this.tableData=[]
					for(let c in data){
						this.tableData.push(data[c])
					}
					this.restaurants=this.tableData
					this.$forceUpdate()
			  })
		},
		methods:{
		  selectAll(){
			  this.$http.post("Logistics/country/selectCountry?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
					let data=result.body
					this.tableData=[]
					for(let c in data){
						this.tableData.push(data[c])
					}
					this.$forceUpdate()
			  })
		  },
		  querySearch(queryString, cb) {
	        let ae={}
	        let de=[]
	        var restaurants = this.restaurants;
	        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
	        for(let c=0;c<results.length;c++){
		        ae["value"]=results[0].cName
		        de.push(ae)
	        }
	        // 调用 callback 返回建议列表的数据
	        cb(de);
	      },
	      createFilter(queryString) {
	        return (restaurant) => {
	          return (restaurant.cName.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
	        }
	      },
          handleSelect(item) {
            console.log(item);
          },
          handleSelectionChange(val) {
            this.checkedCities = val;
          },
          search(){
        	  //查找
        	  this.$http.post("Logistics/country/selectCountryText?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.state2),{emulateJSON:true}).then(result=>{
					let data=result.body
					this.tableData=[]
					for(let c in data){
						this.tableData.push(data[c])
					}
					this.$forceUpdate()
			  })
          },
          dialogVisibles(){
        	  this.dialogVisible=true
        	  this.title="添加"
          },
          handleClose(done) {
              this.$confirm('确认关闭？')
                .then(_ => {
                  done();
                })
                .catch(_ => {});
          },
		  edits(dataw){
			  //修改
			  console.log(dataw)
			 this.dialogVisible=true
			 this.title="修改"
			 this.cname=dataw.cName
			 this.ename=dataw.eName
			 this.ecode=dataw.shortCode
			 this.remark=dataw.remark
			 this.id=dataw.id
		  },
          addCountry(){
        	//添加发送
			  if(this.cname==""||
				 this.ename==""||
				 this.ecode==""){
				  this.$message.error("有值为空")
				  return false
			  }
			let data={}
			data["cName"]=this.cname
			data["eName"]=this.ename
			data["remark"]=this.remark
			data["shortCode"]=this.ecode
			if(this.title=="添加"){
				this.$http.post("Logistics/country/adds?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
					if(result.body>0){
						this.$message.success("成功添加"+result.body+"个国家")
						this.selectAll()
					}else{
						this.$message.error("有"+result.body+"个国家添加失败")
					}
				})
			}else if(this.title=="修改"){

				data["id"]=this.id
				this.$http.post("Logistics/country/update?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
					if(result.body>0){
						this.$message.success("成功修改"+result.body+"个国家")
						this.selectAll()
					}else{
						this.$message.error("有"+result.body+"个国家修改失败")
					}
				})
			}
			this.dialogVisible=false
			
          },
		  deletes(){ 
			  //删除国家
			  this.$confirm("是否删除这"+this.checkedCities.length+"个国家？请谨慎操作!!!").then(_ => {
				//删除
				this.$http.post("Logistics/country/deletes?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
					if(result.body>0){
						this.$message.success("成功删除"+result.body+"个国家")
						this.selectAll()
					}else{
						this.$message.error("有"+result.body+"个国家删除失败")
					}
				})
			  })
			  this.dialogVisible=false
		  }
        }
 	})

</script>
</body>
</html>