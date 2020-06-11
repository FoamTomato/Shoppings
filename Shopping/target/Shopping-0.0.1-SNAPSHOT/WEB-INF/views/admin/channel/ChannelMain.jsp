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
<title>渠道管理</title>
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
						<div class="panel-body"   style="height:850px;">
							<template>
							  <el-button size="mini" style="margin-left:10px" @click="add">添加</el-button>
							  <el-button type="info" @click="deleteChannel" size="mini" >删除</el-button>
							  <p  style="margin:10px"> 总计:{{tableData.length}}条渠道   选中:{{checkedCities.length}}条</p>
							  <el-table size="mini"
							    :data="tableData"
							    stripe
							    height="750px"
							    style="width: 100%;height:95%;overflow:auto"
							    @selection-change="handleSelectionChange">
							    
							    <el-table-column
							      type="selection"
							      fixed
							      width="55">
							    </el-table-column>
							     <el-table-column
							      label="日期"
							      prop="times"
							      sortable
							      width="220">
							      <template slot-scope="scope" >
							        <i class="el-icon-time"></i>
							        <span style="margin-left: 10px">{{formatDate(scope.row.times)}}</span>
							      </template>
							    </el-table-column>
							    <el-table-column
							      label="名称"
							      width="280">
							      <template slot-scope="scope">
							        {{ scope.row.logisticsName }}
							      </template>
							    </el-table-column>
							    <el-table-column
							      label="限制重量"
							      width="240">
							      <template slot-scope="scope">
							        {{scope.row.standy4!=""?scope.row.standy4+"kg":"无明确限制"}}
							      </template>
							    </el-table-column>
							    <el-table-column
							      label="简码"
							      width="240">
							      <template slot-scope="scope">
							        {{scope.row.standy5}}
							      </template>
							    </el-table-column>
							    
							    <el-table-column
							      label="备注"
							      width="280">
							      <template slot-scope="scope">
							        {{scope.row.standy1}}
							      </template>
							    </el-table-column>
								<el-table-column
							      prop="tag"
							      label="标签"
							      width="100"
							      :filters="[{ text: '未激活', value: '1' }, { text: '环金', value: '2' }, { text: '义达', value: '3' }, { text: '创志', value: '4' }]"
							      :filter-method="filterTag"
							      filter-placement="bottom-end">
							     
							      <template slot-scope="scope">
							        <el-tag :type="changeColor(scope.row.status)" disable-transitions>{{change(scope.row.status)}}</el-tag>
							      </template>
							    </el-table-column>
							    <el-table-column
							      fixed="right"
							      label="操作"
							      width="120">
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
  :title="title+'渠道'"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose">
  <span style="color:red;font-size:12px;margin-bottom:10px">目前只支持环金,义达,创志的渠道</span>
    <el-row :gutter="20" style="margin:10px 0px">
	  <el-col :span="12">名称<el-input v-model="name" size="mini" placeholder="名称"></el-input></el-col>
	  <el-col :span="12">集拼名称<el-input v-model="logName" size="mini" placeholder="集拼名称"></el-input></el-col>
	</el-row>
  
    <el-row :gutter="20" style="margin:10px 0px">
	  <el-col :span="12">限制重量(单位kg)<el-input v-model="weight" size="mini"  placeholder="限制重量"></el-input></el-col>
	  <el-col :span="12">备注<el-input v-model="remark" size="mini" placeholder="备注"></el-input></el-col>
	</el-row>
	
    <el-row :gutter="20" style="margin:10px 0px">
	  <el-col :span="12">短码<el-input v-model="shortNum" size="mini" placeholder="短码"></el-input></el-col>
	  <el-col :span="12">标签<br/>
		  <el-select v-model="lable" style="width:100%" size="mini" placeholder="请选择标签">
		    <el-option
		      v-for="item in options"
		      :key="item.value"
		      :label="item.lable"
		      :value="item.value">
		    </el-option>
		  </el-select>
	  </el-col>
	</el-row>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addChannel">{{title}}</el-button>
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
		        title:"",//标题
		        dialogVisible:false,//弹窗
		        name:"",//名称
		        logName:"",//集拼名称
		        weight:"",//重量
		        remark:"",//备注
		        shortNum:"",//短码
		        lable:"",//标签
		        ids:"",
		        options:[{
		        	lable:"未激活",
		        	value:"1"
		        },{
		        	lable:"环金",
		        	value:"2"
		        },{
		        	lable:"义达",
		        	value:"3"
		        },{
		        	lable:"创志",
		        	value:"4"
		        }],//标签
		      }
		},
		created(){
			this.selectAll()
		},
		methods:{
		  selectAll(){
			  this.$http.post("Logistics/channel/selectChannels?${_csrf.parameterName}=${_csrf.token}",{emulateJSON:true}).then(result=>{
					let data=result.body
					this.tableData=[]
					for(let c in data){
						this.tableData.push(data[c])
					}
					this.$forceUpdate()
			  })
		  },
		  add(){
			//添加  
			 this.dialogVisible=true
			 this.title="添加"
			 this.name=""
			 this.logName=""
			 this.weight=""
			 this.remark=""
			 this.shortNum=""
			 this.lable=""
			 this.ids=""
		  },
		  formatDate(inputTime) {
		    var date = new Date(inputTime);
		    var y = date.getFullYear();
		    var m = date.getMonth() + 1;
		    m = m < 10 ? ('0' + m) : m;
		    var d = date.getDate();
		    d = d < 10 ? ('0' + d) : d;
		    var h = date.getHours();
		    h = h < 10 ? ('0' + h) : h;
		    var minute = date.getMinutes();
		    var second = date.getSeconds();
		    minute = minute < 10 ? ('0' + minute) : minute;
		    second = second < 10 ? ('0' + second) : second;
		    return y + '-' + m + '-' + d;
		  },
		  edits(dataw){
			  //修改
			 this.dialogVisible=true
			 this.title="修改"
			 this.name=dataw.name
			 this.logName=dataw.logisticsName
			 this.weight=dataw.standy4
			 this.remark=dataw.standy1
			 this.shortNum=dataw.standy5
			 this.lable=dataw.status
			 this.ids=dataw.id
		  },
		  addChannel(){
			//添加发送
			  if(this.name==""||
				 this.logName==""||
				 this.weight==""||
				 this.shortNum==""){
				  this.$message.error("有值为空")
				  return false
			  }
			let data={}
			data["name"]=this.name
			data["logisticsName"]=this.logName
			data["status"]=this.lable==""?"1":this.lable
			data["standy1"]=this.remark
			data["standy4"]=this.weight
			data["standy5"]=this.shortNum
			data["shortName"]=this.shortNum
			if(this.title=="添加"){
				this.$http.post("Logistics/channel/adds?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
					if(result.body>0){
						this.$message.success("成功添加"+result.body+"条渠道")
						this.selectAll()
					}else{
						this.$message.error("有"+result.body+"条渠道添加失败")
					}
				})
			}else if(this.title=="修改"){

				data["id"]=this.ids
				this.$http.post("Logistics/channel/update?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
					if(result.body>0){
						this.$message.success("成功修改"+result.body+"条渠道")
						this.selectAll()
					}else{
						this.$message.error("有"+result.body+"条渠道修改失败")
					}
				})
			}
			this.dialogVisible=false
			
		  },
		  deleteChannel(){ 
			  //删除渠道
			  this.$confirm("是否删除这"+this.checkedCities.length+"条渠道？请谨慎操作!!!").then(_ => {
				//删除
				this.$http.post("Logistics/channel/deleteChannel?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(this.checkedCities),{emulateJSON:true}).then(result=>{
					if(result.body>0){
						this.$message.success("成功删除"+result.body+"条渠道")
						this.selectAll()
					}else{
						this.$message.error("有"+result.body+"条渠道删除失败")
					}
				})
			  })
			  this.dialogVisible=false
		  },
		  handleClick(row) {
	        console.log(row);
	      },
	      toggleSelection(rows) {
	          if (rows) {
	            rows.forEach(row => {
	              this.$refs.multipleTable.toggleRowSelection(row);
	            });
	          } else {
	            this.$refs.multipleTable.clearSelection();
	          }
          },
          handleSelectionChange(val) {
          	this.checkedCities = val;
          },
          filterHandler(value, row, column) {
              const property = column['property'];
              return row[property] === value;
          },
          filterTag(value, row) {
              return row.status === value;
          },
          change(val){
        	  if(val=="1"){
        		  return "未激活"
        	  }else if(val=="2"){
        		  return "环金"
        	  }else if(val=="3"){
        		  return "义达"
        	  }else if(val=="4"){
        		  return "创志"
        	  }
          },
          changeColor(val){
        	  if(val=="1"){
        		  return "info"
        	  }else if(val=="2"){
        		  return "danger"
        	  }else if(val=="3"){
        		  return "success"
        	  }else if(val=="4"){
        		  return "warning"
        	  }
        	  return "success"
          },
          handleClose(done) {
              this.$confirm('确认关闭？')
                .then(_ => {
                  done();
                })
                .catch(_ => {});
            }
		}
 	})

</script>
</body>
</html>