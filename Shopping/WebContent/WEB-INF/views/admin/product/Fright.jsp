<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Access-Control-Allow-Origin" content="*" />
<title>商城后台</title>
<jsp:include page="../commonCss.jsp" />
<script src="${pageContext.request.contextPath}/resources/admin/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/toastr-master/build/toastr.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/vendor/sweetalert/lib/sweet-alert.css" />
<script src="${pageContext.request.contextPath}/resources/jss/sweetalert2@9.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/admin/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/admin/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/admin/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/admin/scripts/productLIst_js.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/vendor/sweetalert/lib/sweet-alert.min.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script>
</head>
<body class="blank" style="margin: 0px; padding: 0px; background: white">
	<jsp:include page="../productCategory/modalCate.jsp" />
<div id="apps">

	<div class="form-group col-xs-2">
		<label>商品编号</label> <input 
			name="externalId" class="form-control" type="hidden" v-model="externalId"
			placeholder="商品编号">
		</p>{{externalId}}
	</div>
	<div class="form-group col-xs-10">
		<label>商品名称</label> <input  name="name" v-model="name"
			class="form-control" type="hidden" placeholder="商品名称">
		</p>{{name}}
	</div>
	<div class="form-group col-xs-12">
		<label>产品分类</label> <input  v-model="productStatus"
			name="productStatus" class="form-control f_kind_select"  onfocus="this.blur();">
			
	</div>
	<div class="form-group col-xs-12" style="display: block;width: 750px;">
		<div id="picss" v-for="(item,i) in pic"  :key="i">
			<img class="mianImgs" width="50px" style="margin: 2.5px;float:left" v-if="item.indexOf('www.ec-sudo.com') == -1"  height="50px" :src="'${pageContext.request.contextPath}/resources/upload/'+item">
			<img class="mianImgs" width="50px" style="margin: 2.5px;float:left" v-if="item.indexOf('www.ec-sudo.com') != -1"  height="50px" :src="item">
		</div>
		<div id="mainUpdateimg" @click="selMain"
			style="float: left; width: 50px; height: 50px; border: 1px solid #9BCD9B; text-align: center; line-height: 50px; margin: 2.5px">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</div>

	</div>
	
	<div class="form-group col-xs-2">
		<label>特殊状态</label> <select  v-model="special"
			name="special" class="form-control">
			<option value="0" selected="selected">全部</option>
			<option value="1">重点</option>
			<option value="2">原创</option>
			<option value="3">海外</option>
			<option value="4">抓取</option>
			<option value="5">导入</option>
		</select>
	</div>
	<div class="form-group col-xs-2">
		<label>分销状态</label> <select v-model="distribution"
			name="distribution" class="form-control">
			<option value="0" selected="selected">全部</option>
			<option value="1">分销</option>
			<option value="2">不分销</option>
			<option value="3">没运费</option>
			<option value="4">无挂号</option>
			<option value="5">未分类</option>
			<option value="6">零库存</option>
		</select>
	</div>
	<div class="form-group col-xs-2">
		<label>日期</label> <select v-model="dates"
			name="date" class="form-control">
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
	<div class="form-group col-xs-2">
		<label>审核状态</label> <select v-model="audit"
			name="audit" class="form-control">
			<option value="0" selected="selected">全部</option>
			<option value="1">通过</option>
			<option value="2">待审核</option>
			<option value="3">过滤</option>
			<option value="4">侵权</option>
			<option value="5">禁止上传</option>
			<option value="6">屏蔽</option>
		</select>
	</div>
	<div class="form-group col-xs-2">
		<label>产品状态</label> <select v-model="products"
			name="products" class="form-control">
			<option value="0" selected="selected">全部</option>
			<option value="1">已上架</option>
			<option value="2">已下架</option>
			<option value="3">失效</option>
		</select>
	</div>
	<div class="form-group col-xs-2">
		<label>分级及图案</label> <select v-model="grading"
			name="grading" class="form-control">
			<option value="0" selected="selected">全部</option>
			<option value="1">未分级</option>
			<option value="2">图案设计</option>
			<option value="3">有图案设计</option>
			<option value="4">国内品牌</option>
			<option value="5">高风险</option>
		</select>
	</div>
	<div class="form-group col-xs-12" style="margin-top: 0px;">
		<label style="color: red; font-size: 12px; font-weight: 400;">设置过滤，侵权和屏蔽，亚马逊上传明细将自动标注"待删除"</label>
	</div>
	<div class="form-group col-xs-3" style="margin-top: 30px;">
		<label>业务员</label>
		<input name="salesman" v-model="salesman"
		 class="form-control" type="hidden"
			placeholder="英文简称" >
		</p>{{salesman}}
	</div>
	<div class="form-group col-xs-3" style="margin-top: 30px;">
		<label>原产地区</label> <input name="provenance"
			v-model="provenance" class="form-control" type="text"
			placeholder="原产地区">
	</div>
	<div class="form-group col-xs-3" style="margin-top: 30px;">
		<label>英文简称</label> <input name="englishShort" v-model="englishShort"
			 class="form-control" type="text"
			placeholder="英文简称">
	</div>
	<div class="form-group col-xs-3" style="margin-top: 30px;">
		<label>中文简称</label> <input id="chinaShort" v-model="chinaShort"
			class="form-control" type="text" placeholder="中文简称"
			>
	</div>
	<div class="form-group col-xs-3" style="">
		<label>包装毛重</label> <input id="productGrossWeight" v-model="productGrossWeight"
			class="form-control" type="text" placeholder="包装毛重"
		>
	</div>
	<div class="form-group col-xs-6" style="">
		<label>包装尺寸</label>
		<div class="" style="">
			<input name="lengths"
				style="width: 30%; display: -webkit-inline-box"
				v-model="lengths" class="form-control" type="text"
				placeholder="长"> x <input
				name="widths" style="width: 30%; display: -webkit-inline-box"
				v-model="widths" class="form-control" type="text"
				placeholder="宽"> x <input
				name="heights" style="width: 30%; display: -webkit-inline-box"
				v-model="heights" class="form-control" type="text"
				placeholder="高">
		</div>
	</div>


	<div class="form-group col-xs-3" style="margin-top: 0px;">
		<label>产品币种</label> <input name="currency"
			v-model="currency" class="form-control"
			type="text" placeholder="产品币种">
	</div>
	<div class="form-group col-xs-5" style="">
		<label>分销单价</label> <input name="store"
			v-model="store" class="form-control" type="text"
			placeholder="分销单价">
	</div>

	<div class="form-group col-xs-2"
		style="margin-top: 30px; font-size: 15px">
		<input type="checkbox" style="margin-right: 10px;" v-model="storeChecked"
			>分销
	</div>

	<div class="form-group col-xs-5" style="">
		<label>成本单价</label> <input name="shopPrice" class="form-control"
			type="text" placeholder="成本单价" v-model="shopPrice">

	</div>

	<div class="form-group col-xs-3" style="">
		<label>固定运费</label> <br>
		<nobr style="display: -webkit-inline-box;    line-height: 30px;">
			<input name="discount" style="width: 100%; margin-right: 20px;"
				class="form-control" type="text" placeholder="分销单价"
				v-model="discount">
			建议用国家运费
		</nobr>
	</div>
	<div class="form-group col-xs-6"
		style="margin-top: 0px; margin-left: 110px">
		<label>适用人群</label>
		<input name="general" style="width: 100%; margin-right: 20px;"
			class="form-control" type="text"
			placeholder="适用人群" v-model="general">
	</div>
	<div class="form-group col-xs-12" style="">
		<label>产品来源网址<a style="color: red; margin-left: 20px"
			:href="sourceUrl" target="view_blank">点击查看</a></label>
		</p>
		<textarea class="cdtext" cols="95" rows="1"
			v-model="sourceUrl">{{sourceUrl}}</textarea>
	</div>

	<div class="form-group col-xs-6" style="margin-top: 0px;">
		<label>产品材料</label>
		
		<input 
			style="width: 100%; margin-right: 20px;" class="form-control"
			type="text" placeholder="材料" v-model="materials">
	</div>
	<div class="form-group col-xs-6" style="margin-top: 0px;">
		<label>内部SKU</label> <input name="sku"
			class="form-control" type="hidden"
			placeholder="分销单价" v-model="sku">
		</p>{{sku}}
	</div>
	<div class="form-group col-xs-12" style="margin-top: 0px;">
		<label>产品变体<nobr style="color:red;font-weight:500">(请用斜杠（/）隔开)</nobr></label>
		</p>
		<div class="col-xs-12">
			颜色&nbsp;
			</p>
			<textarea class="cdtext" cols="95" id="stockColor" v-model="stockColor"
				placeholder="颜色 多个用/隔开"></textarea>
		</div>
		<div class="col-xs-12">
			</p>
			尺寸&nbsp;
			</p>
			<textarea class="cdtext" cols="95" id="stockSize" v-model="stockSize"
				placeholder="尺寸 多个用/隔开"></textarea>

		</div>

		
		<!-- 遮罩层 -->
		<div class="col-xs-12">
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content modals-top">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">修改图片</h4>
							<!-- <button type="button" class="btn btn-default" @click="imgfirst">
								前移
							</button>
							<button type="button" class="btn btn-default" @click="imglast">
								后移
							</button>
							<button type="button" class="btn btn-default" @click="imgtop">
								置顶
							</button> -->
						</div>
						<div class="modal-body"
							style="display: inline-block; width: 580px;">
							<div class="form-group" id="mainViewImg">
								<div class="col-xs-2"  v-for="(item,i) in imglist" :key="i">
									<input type="checkbox" :checked="item.checke" class="imgcheck" @click="checkimgs('imgcheck','imgchecks')"/>
									<img style="width:60px;height:60px" v-if="item.img.indexOf('www.ec-sudo.com') == -1" :src="'/Shopping/resources/upload/'+item.img"/>
									<img style="width:60px;height:60px" v-if="item.img.indexOf('www.ec-sudo.com') != -1" :src="item.img"/>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="form-group" style="float: left">
								<div class="file-container"
									style="float: left; display: inline-block; position: relative; overflow: hidden; vertical-align: middle">
									<button class="btn btn-success fileinput-button" type="button">浏览</button>
									<input type="file" id="jobData"
										onchange="loadFile(this.files[0])"
										style="position: absolute; top: 0; left: 0; font-size: 34px; opacity: 0">
								</div>
								<div id="filename" 
									style="float: left; vertical-align: middle; margin-left: 5px; text-overflow: ellipsis; width: 100px; white-space: nowrap; line-height: 34px; font-size: 14px; text-align: left; overflow: hidden;">未选择文件</div>
								<input type="checkbox" style="display:none" id="imgchecks">
								<button type="button" class="btn btn-default" @click="changepic">
									<span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>图片添加
								</button>
								<button type="button" class="btn btn-default" @click="checkAll('imgcheck','imgchecks')">
									全选
								</button>
								<button type="button" class="btn btn-default" @click="checkBack('imgcheck','imgchecks')">
									反选
								</button>
								<button type="button" class="btn btn-default"  v-if="btn!='pl'" aria-hidden="true" data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span>关闭
								</button>
								<button type="button" class="btn btn-default" v-if="btn=='subgraph'" aria-hidden="true" data-dismiss="modal" @click="savebtn(btncheck)">
									<span class="glyphicon glyphicon-floppy-disk"></span>保存
								</button>
								<button type="button" class="btn btn-default"  v-if="btn=='main'" aria-hidden="true"  @click="deletMain">
									<span class="glyphicon glyphicon-remove"></span>删除
								</button>
								<button type="button" class="btn btn-default"  v-if="btn=='pl'" aria-hidden="true"  @click="addimgs">
									<span class="glyphicon glyphicon-floppy-disk"></span>批量保存
								</button>


								<nobr id="saveIMGs"></nobr>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-xs-12">
				</p>
				<div class="" style="display: -webkit-inline-box">
					<a class="btn btn-primary   ccc" style="margin:2px;"
						@click="addStock()">更新变体</a> <a
						class="btn btn-primary   ccc"
						style="margin:2px;background: #ee4747; border: 1px solid #ee4747"
						@click.prevent="deleteStock()">删除选择变体</a>
						<a
						class="btn btn-primary   ccc"
						style="margin:2px;"
						@click="pl()">批量添加图片</a>
				</div>
			</div>
			
			<table class="table-striped " style="width: 700px">

				<tr
					style="color: black; font-size: 14px; line-height: 50px; background: white;">
					<th style="width: 100px; padding: 10px">
						<div style="float: left">
								<input id="stockCheckboxAll" type="checkbox" style="display:none"> 
						</div>
						<div style="float: left; margin-left: 14px">
							<div id="allInner"
								style="color: red; font-size: 14px; line-height: 30px" @click="checkAll('stockCheckbox','stockCheckboxAll')">全选</div>
							<div id="reverse"
								style="color: red; font-size: 14px; line-height: 0px" @click="checkBack('stockCheckbox')">反选</div>

						</div>
					</th>
					<th>变体</th>
					<th>SKU(选填)</th>
					<th>库存</th>
					<th>加价</th>
					<th>图片</th>
				</tr>
				<tbody id="StockAdd">
								<tr class="del" v-for="(item,i) in stock">
									<td style="width: 20px; padding: 10px">
										<input class="stockCheckbox" type="checkbox" @click="checkimgs('stockCheckbox','stockCheckboxAll')">
									</td>
									<td>
										<div class="colorp" id="colorp" style="word-break: break-all; width: 100px">{{item.fcolor+"-"+item.fsize}}</div>
									</td>
									<td>
										<input class="no" style="width: 70px; height: 20px" type="text" v-model="item.fno"></td>
									<td>
										<input class="num" style="width: 70px; height: 20px" type="number" v-model="item.fnum"></td>
									<td>
										<input class="add" style="width: 70px; height: 20px" type="number" v-model="item.fadd"></td>
									<td style="max-width: 150px; word-break: break-all">
										<div id="fimgs" v-if="item.fimg!=''">
											<img  width="20px" height="20px" style="margin:2.5px" v-if="iteme.indexOf('www.ec-sudo.com') == -1" :src="'${pageContext.request.contextPath}/resources/upload/'+iteme" v-if="iteme!=''"  v-for="iteme in catStock(item.fimg)"/>
											<img  width="20px" height="20px" style="margin:2.5px" v-if="iteme.indexOf('www.ec-sudo.com') != -1" :src="iteme" v-if="iteme!=''"  v-for="iteme in catStock(item.fimg)"/>
										</div> 
										<!-- <a class="btn btn-primary  ccc" style="margin-left: 5px" @click="btn_add(i)">编辑</a> -->
									</td>
								</tr>
							
				</tbody>
			</table>
		</div>
		<!-- 选项卡菜单-->
		<ul id="myTab" class="nav nav-tabs" role="tablist"
			style="margin-bottom: 30px">
			<li class="cs" v-for="(item,i) in languages">
				<a :href="'#'+i" role="tab" data-toggle="tab">{{changeLang(i)}}</a>
			</li>
		</ul>
		<!-- 选项卡面板 -->
		<el-row v-loading="loading2" element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)">
		<div id="myTabContent" class="tab-content" >
			<div class="tab-pane" :id="i" v-for="(item,i) in languages">
				<button type="button" class="btn btn-default" style="margin-left:10px" @click="changes(i,item)">
				翻译
				</button>
				<div class="form-group col-xs-12">
					<label>产品标题({{changeLang(i)}})</label>
					<textarea cols="95" rows="2" v-model="item.title">{{item.title}}</textarea>
					<label>要点说明：回车分隔，不超过五行，每行不超过五百个字符({{changeLang(i)}})</label>
					<textarea cols="95" rows="5" v-model="item.points">{{item.points}}</textarea>
					<label>产品描述：不要输入html标签({{changeLang(i)}})</label>
					<textarea cols="95" rows="8" v-model="item.content">{{item.content}}</textarea>
					<label>关键词：不超过五行 逗号，空格或回车分隔({{changeLang(i)}})</label>
					<textarea cols="95" rows="5" v-model="item.word">{{item.word}}</textarea>
				</div>
			</div>
			
			</div>
			</el-row>
			<a  class="easyui-linkbutton"
				style="background: #62cb31; border: 1px solid #62cb31; color: white; margin: 15px; padding: 5px;"
				@click="updatePanels">保存</a> <!-- updatePanels; --><a
				 class="easyui-linkbutton"
				style="background: #ee4747; border: 1px solid #ee4747; color: white; margin: 15px; padding: 5px;"
				@click="deletePanels">删除</a>
		</div> 
</div>
</div>

<script>
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
		Vue.http.options.root="${pageContext.request.contextPath}";
		var vc=new Vue({
			el:'#apps',
			data:{
				//商品编号
				externalId:${id},
				//商品名称
				name:"",
				//产品分类
				productStatus:"",
				//特殊状态
				special:"0",
				//分销状态
				distribution:"0",
				//日期
				dates:"0",
				//审核状态
				audit:"0",
				//产品状态
				products:"0",
				//分级及图案
				grading:"0",
				//业务员
				salesman:"",
				//原产地区
				provenance:"",
				//英文简称
				englishShort:"",
				//中文简称
				chinaShort:"",
				//包装毛重
				productGrossWeight:"",
				//长
				lengths:"",
				//宽
				widths:"",
				//高
				heights:"",
				//产品币种
				currency:"",
				//分销单价
				store:"",
				//分销
				storeChecked:false,
				//成本单价
				shopPrice:"",
				//固定运费
				discount:"",
				//适用人群
				general:"",
				//产品来源网址
				sourceUrl:"",
				//产品材料
				materials:"",
				//内部sku
				sku:"",
				//中文
				fzh:"",
				//英语
				fen:"",
				//法语
				ffr:"",
				//俄语
				fru:"",
				//德语
				fde:"",
				//意大利
				fit:"",
				//西班牙
				fes:"",
				//日语
				fja:"",
				//语言list
				languages:{},
				//颜色输入框
				stockColor:"",
				//尺寸输入框
				stockSize:"",
				//存储图片地址
				pic:[],
				//存储变体地址
				stock:[],
				//所有未删图片列表
				imglist:[],
				//获取文件
				filename:null,
				//点击按钮计数
				btncheck:"",
				//判断是否主图 main主图 subgraph子图
				btn:"",
				fids:"",
				loading2: false,//加载圈
			},
			created(){
				this.selectAlllist()
			},
			mounted(){
			},
			beforeUpdate(){
			},
			updated(){
			},
			methods:{
				selectAlllist(){//查询所有
					let datas={};
					datas["id"]=this.externalId
					//ajiox请求方式
					this.$http.post("admin/getAllproducts?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						let pro=result.body.pro
						this.name=pro.fchinese
						this.productStatus=pro.fkind
						this.special=pro.frs4==null?0:pro.frs4
						this.distribution=pro.frs5==null?0:pro.frs5
						this.dates=pro.frs6==null?0:pro.frs6
						this.audit=pro.frs1==null?0:pro.frs1
						this.products=pro.frs2==null?0:pro.frs2
						this.grading=pro.frs3==null?0:pro.frs3
						this.salesman=pro.frs7==null?'管理员':pro.frs7
						this.provenance=pro.forigin
						this.englishShort=pro.fenglish
						this.chinaShort=pro.fchinese
						this.productGrossWeight=pro.fweight
						this.lengths=pro.flength
						this.widths=pro.fwidth
						this.heights=pro.fheight
						this.currency=pro.fcur
						this.store=pro.fprice
						this.shopPrice=pro.fcost
						this.discount=pro.ffreight
						this.general=pro.fdepartment
						this.sourceUrl=pro.fsource
						this.materials=pro.fmaterial
						this.sku=pro.fsku
						this.fzh=this.catlang(pro.fzh)
						this.fen=this.catlang(pro.fen)
						this.fja=this.catlang(pro.fja)
						this.jru=this.catlang(pro.fru)
						this.fes=this.catlang(pro.fes)
						this.fit=this.catlang(pro.fit)
						this.fde=this.catlang(pro.fde)
						this.ffr=this.catlang(pro.ffr)
						this.fids=pro.fids
						this.languages["zh"]=this.fzh
						this.languages["en"]=this.fen
						this.languages["ja"]=this.fja
						this.languages["es"]=this.fes
						this.languages["it"]=this.fit
						this.languages["de"]=this.fde
						this.languages["fr"]=this.ffr
						this.pic=result.body.pic
						this.stock=result.body.stock
						//颜色
						this.stockColor=""
						for(let i=0;i<this.stock.length;i++){
							if(this.stock[i].fcolor!=""){
								if(i==this.stock.length-1){
									this.stockColor+=this.stock[i].fcolor
								}else{
									this.stockColor+=this.stock[i].fcolor+"/"
								}
							}
						}
						//尺寸
						this.stockSize=""
							for(let a=0;a<this.stock.length;a++){
								if(this.stock[a].fsize!=""){
									if(a==this.stock.length-1){
										this.stockSize+=this.stock[a].fsize
									}else{
										this.stockSize+=this.stock[a].fsize+"/"
									}
								}
							}
					})
				},
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
					}
				},
				catlang(datas){
					if(datas == null){
						return false
					}
					let det=datas.split("毰毸")
					let list={}
					list["title"]=det[0]
					list["points"]=det[1]
					list["content"]=det[2]
					list["word"]=det[3]
					return list
				},
				updatePanels(){//保存
					let data={}
					data["id"]=this.externalId
					data["name"]=this.name
					data["fids"]=this.fids
					data["fkind"]=this.productStatus
					data["fcost"]=this.shopPrice
					data["frs1"]=this.audit
					data["frs2"]=this.products
					data["frs3"]=this.grading
					data["frs4"]=this.special
					data["frs5"]=this.distribution
					data["frs6"]=this.dates
					data["forigin"]=this.provenance
					data["fenglish"]=this.englishShort
					data["fchinese"]=this.chinaShort
					data["fweight"]=this.productGrossWeight
					data["flength"]=this.lengths
					data["fwidth"]=this.widths
					data["fheight"]=this.heights
					data["fcur"]=this.currency
					data["fprice"]=this.store
					data["ffreight"]=this.discount
					data["fdepartment"]=this.general
					data["fsource"]=this.sourceUrl
					data["fmaterial"]=this.materials
					data["fen"]=this.pushlang(this.languages["en"])
					data["fzh"]=this.pushlang(this.languages["zh"])
					data["fja"]=this.pushlang(this.languages["ja"])
					data["fes"]=this.pushlang(this.languages["es"])
					data["fit"]=this.pushlang(this.languages["it"])
					data["fde"]=this.pushlang(this.languages["de"])
					data["ffr"]=this.pushlang(this.languages["fr"])
					data["y"]=this.stock
					data["fsku"]=this.sku
					this.$http.post("adminsdf/productUpdate?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data),{emulateJSON:true}).then(result=>{
						let pro=result.body
						if(pro=='1'){
							swal("提示", "保存成功!", "success");
							//this.selectAlllist()
						}else{
							swal("提示", "保存失败!", "error"); 
						}
					})
				},
				pushlang(content){//拼接内容

					let dets=""
					dets+=content.title==undefined?"":content.title
					dets+="毰毸"
					dets+=content.points==undefined?"":content.points
					dets+="毰毸"
					dets+=content.content==undefined?"":content.content
					dets+="毰毸"
					dets+=content.word==undefined?"":content.word
					return dets
				},
				deletePanels(){//删除
					let datas={}
					datas["id"]=this.externalId
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
								parent.location.reload()
							})
						  }else{
							  Swal.fire("取消", "点击了取消", "error");
						  }
						})
				},
				catStock(data){
					//裁剪变体
					if(data!=null){
						return data.split("|")
					}
					return null
				},
				btn_add(data){
					this.btncheck=data
					this.btn='subgraph'
					//清空原来的勾选
					let checkS=$(".imgcheck")
					//迭代
					for(var i = 0;i<checkS.length;i++){
				　　　　checkS[i].checked=false
					}
					$('.modals-top').css("margin-top","600px")
					//图片编辑按钮
					let datas={}
					datas["fSku"]=this.sku
					datas["fImagesUrl"]=this.stock[data].fimg
					this.$http.post("admin/mainViewImgs?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						let imglist=result.body
						this.imglist=[]
						let fimg=this.stock[data].fimg.split(",,")
						for(let i=0;i<imglist.length;i++){
							let datae={}
							if(imglist[i].indexOf('F:/LibraryImage/')!=-1){
								for(let a=1;a<fimg.length;a++){
									if(imglist[i].split('F:/LibraryImage/')[1]==fimg[a]){
										datae["checke"]=true
									}else{
										datae["checke"]=false
									}
								}
								datae["img"]=imglist[i].split('F:/LibraryImage/')[1]
							}else{
								for(let a=1;a<fimg.length;a++){
									if(imglist[i]==fimg[a]){//.split('/resources/upload/')[1]
										datae["checke"]=true
									}else{
										datae["checke"]=false
									}
								}
								datae["img"]=imglist[i]//.split('/resources/upload/')[1]
							}
							this.imglist.push(datae)
						}
						$("#myModal").modal();
					})
				},
				mains(indexs,datas){
					//判断图片是否重复
					
				},
				changepic(){
					//图片添加
					let pip=this.sku
					//最低级判断后缀是否图片，不准确
					if(typeof FileReader != 'undefined'){    
						   var file = document.getElementById("jobData").files[0];    
						     if((file.type).indexOf("image/")==-1){  
						     swal("提示", "请上传图片!", "warning");
						     return false;
						    }  
						}else{  
						   var fileName=document.getElementById("jobData").value;  
						   var suffixIndex=fileName.lastIndexOf(".");  
						   var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
						   if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
						    swal("提示", "请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!", "error");  
						     return false;
						   }  
						}
					
					var reads = new FileReader();
					//获取需要添加的图片
			        f = document.getElementById('jobData').files[0];
			        
					var form = new FormData()
					 // 存入文件对象
					form.append("file",f);
					form.append("fSku",pip);
					this.$http.post("upload?${_csrf.parameterName}=${_csrf.token}",form,{emulateJSON:true}).then(result=>{
					 	var imglist=result.body
						let datae={}
						datae["check"]=false
						console.log(imglist)
						datae["img"]=imglist[imglist.length-1].fImagesUrl
						this.imglist.push(datae)
						this.pic.push(imglist[imglist.length-1].fImagesUrl)
					})
				},
				checkimgs(data1,data2){
					//单选data1是子选项 data2是父选项
					let imgcheck=$("."+data1)
					//全选框
					let imgchecks=$("#"+data2)
					//假设全选是选中的
					let isSelectAll=true
					let reslut=""
					for(var i = 0;i<imgcheck.length;i++){
						if(imgcheck[i].checked==true){
			                reslut+=imgcheck[i].id+","
			        	}else{
			        		isSelectAll=false
			        	}
					}
					imgchecks[0].checked=isSelectAll
					this.idList=reslut.substr(0,reslut.length-1)
				},
				checkAll(data1,data2){
					//全选data1是子选项 data2是父选项
					//获取全选勾选框
					let checke = $("#"+data2);
					//获取所有的勾选框
					let checkS = $("."+data1);
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
				checkBack(data1){
						//反选data1是子选项 
						//获取所有的勾选框
						let checkS = $("."+data1);
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
				savebtn(data){
					//保存图片到相应的框
					if(this.btn=="main"){
						//获取所有的勾选框
						let imgcheck=$(".imgcheck")
						this.pic=[]
						for(var i = 0;i<imgcheck.length;i++){
								//获取图片地址
								this.pic.push(imgcheck.eq(i).next("img")[0].src.split("/upload/")[1])
						}
					}else if(this.btn=="subgraph"){
						//获取所有的勾选框
						let imgcheck=$(".imgcheck")
						let result=""
						for(var i = 0;i<imgcheck.length;i++){
							if(imgcheck[i].checked){
								//获取图片地址
								result+="|"+imgcheck.eq(i).next("img")[0].src.split("/upload/")[1]
							}
						}
						this.stock[data].fimg=result
					}
				},
				addStock(){
					//更新变体
					/* if(this.stockColor=="" && this.stockSize==""){
						swal("提示","请输入变体!","error")
						return false;
					} */
					let color=this.stockColor.split("/")
					let size=this.stockSize.split("/")
					let result=[]
					//添加所有的可能
					for(let a=0;a<color.length;a++){
						for(let b=0;b<size.length;b++){
								result.push({fadd:"",
											fcolor:color[a],
											fid:this.fids,
											file:"",
											fimg:"",
											fno:"",
											fnum:30,
											fsize:size[b],
											id:""})
						}
					}
					//判断值是否存在,如果存在则赋值
					for(let i=0;i<result.length;i++){
						for(let t=0;t<this.stock.length;t++){
							if((result[i].fcolor+result[i].fsize)==(this.stock[t].fcolor+this.stock[t].fsize)){
								result[i]=this.stock[t]
							}
						}
					}
					this.stock=result
				},
				deleteStock(){
					//删除变体
					let imgcheck=$(".stockCheckbox")
					//删除指定
					for(var i = 0;i<imgcheck.length;i++){
						if(imgcheck[i].checked==true){
			                this.stock.splice(i,1)
			        	}
					}
					//清空原来的勾选
					let checkS=$(".stockCheckbox")
					//迭代
					for(var i = 0;i<checkS.length;i++){
				　　　　checkS[i].checked=false
					}
				},
				catMain(data){
					//主图截取
					if(data.indexOf("/resources/upload/")!=-1){
						return data.split("/resources/upload/")[1]
					}
					return data
				},
				selMain(){
					//主图查询
					this.btn='main'
					//清空原来的勾选
					let checkS=$(".imgcheck")
					//迭代
					for(var i = 0;i<checkS.length;i++){
				　　　　checkS[i].checked=false
					}
					$('.modals-top').css("margin-top","0px")
					//图片编辑按钮
					let datas={}
					datas["fSku"]=this.sku
					this.$http.post("admin/selMainImgs?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						let imglist=result.body
						this.imglist=[]
						for(let i=0;i<imglist.length;i++){
							let datae={}
							if(imglist[i].indexOf('F:/LibraryImage/')!=-1){
								datae["img"]=imglist[i].split('F:/LibraryImage/')[1]
							}else if(imglist[i].indexOf('/resources/upload/')!=-1){
								datae["img"]=imglist[i].split('/resources/upload/')[1]
							}else{
								datae["img"]=imglist[i]
							}
							this.imglist.push(datae)
						}
						$("#myModal").modal();
					})
				},
				deletMain(){
					//主图删除
					//1.获取sku，需要删除的路径
					let datas=[]
					//获取所有的勾选框
					let imgcheck=$(".imgcheck")
					for(var i = 0;i<imgcheck.length;i++){
						if(imgcheck[i].checked){
							//获取图片地址
							datas.push({"fSku":this.sku,"fImagesUrl":imgcheck.eq(i).next("img")[0].src.split("/upload/")[1]})
						}
					}
					//2.发送删除请求
					this.$http.post("admin/deletMainImgs?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						let imglist=result.body
						this.imglist=[]
						this.pic=[]
						for(let i=0;i<imglist.length;i++){
							let datae={}
							if(imglist[i].indexOf('F:/LibraryImage/')!=-1){
								datae["img"]=imglist[i].split('F:/LibraryImage/')[1]
								this.pic.push(imglist[i])
							}else if(imglist[i].indexOf('/resources/upload/')!=-1){
								datae["img"]=imglist[i].split('/resources/upload/')[1]
								this.pic.push(imglist[i])
							}else{
								datae["img"]=imglist[i]
								this.pic.push(imglist[i])
							}
							this.imglist.push(datae)
						}
					})
					//清空原来的勾选
					let checkS=$(".imgcheck")
					//迭代
					for(var i = 0;i<checkS.length;i++){
				　　　　checkS[i].checked=false
					}
					$("#myModal").modal('hide')
				},
				imgfirst(){
					//前移
					//1.获取sku，需要删除的路径
					let datas=[]
					//获取所有的勾选框
					let imgcheck=$(".imgcheck")
					for(var i = 0;i<imgcheck.length;i++){
						if(imgcheck[i].checked){
							if(i==0){
								toastr.error("不能向前移动第一位")
								return false
							}else{
								//获取图片地址
								datas=this.imglist[i]
								this.imglist[i]=this.imglist[i-1]
								this.imglist[i-1]=datas
							}
						}
					}
				},
				imglast(){
					//后移
				},
				imgtop(){
					//置顶
				},
				changes(data,content){
					//翻译
					this.loading2=true
					this.changeLangs(data,content)
				},
				changeLangs(data,content){
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
					this.$http.post("https://www.ec-sudo.com:8080/trans",datas).then(result=>{
						let leng=result.body.message
						this.languages.en=this.tile(leng.en)
						this.languages.zh=this.tile(leng.zh)
						this.languages["ja"]=this.tile(leng.ja)
						this.languages["es"]=this.tile(leng.es)
						this.languages["it"]=this.tile(leng.it)
						this.languages["de"]=this.tile(leng.de)
						this.languages["fr"]=this.tile(leng.fr)
						this.loading2=false
					})
				},
				tile(datas){
					if(datas == null){
						return false
					}
					let det=datas.split("|")
					let list={}
					list["title"]=det[0]
					list["points"]=det[1]
					list["content"]=det[2]
					list["word"]=det[3]
					return list
				},
				createIp(){
					var a = Math.round(Math.random() * 250) + 1,
			        b = Math.round(Math.random() * 250) + 1,
				    c = Math.round(Math.random() * 240) + 1,
				    d = Math.round(Math.random() * 240) + 1;
				    return [a, b, c, d].join('.');
				},
				test(){
					//测试
					console.log(this.stock)
					console.log(this.stockColor)
					console.log(this.stockSize)
				},
				addimgs(){
					//批量添加变体图片	
					let imgcheck=$(".stockCheckbox")
					$("#myModal").modal('hide')
					//删除指定
					for(let i = 0;i<imgcheck.length;i++){
						if(imgcheck[i].checked==true){
							//获取所有的勾选框
							console.log(this.stock[i].fimg)
							let imgcheck=$(".imgcheck")
							let result=""
							for(let c = 0;c<imgcheck.length;c++){
								if(imgcheck[c].checked){
									//获取图片地址
									result+="|"+imgcheck.eq(c).next("img")[0].src.split("/upload/")[1]
								}
							}
							this.stock[i].fimg=result
			        	}
					}
				},
				pl(){
						//打开批量添加
						this.btn='pl'
						//清空原来的勾选
						let checkS=$(".imgcheck")
						//迭代
						for(var i = 0;i<checkS.length;i++){
					　　　　checkS[i].checked=false
						}
						$('.modals-top').css("margin-top","0px")
						//图片编辑按钮
						let datas={}
						datas["fSku"]=this.sku
						this.$http.post("admin/selMainImgs?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							let imglist=result.body
							this.imglist=[]
							for(let i=0;i<imglist.length;i++){
								let datae={}
								if(imglist[i].indexOf('F:/LibraryImage/')!=-1){
									datae["img"]=imglist[i].split('F:/LibraryImage/')[1]
								}else if(imglist[i].indexOf('/resources/upload/')!=-1){
									datae["img"]=imglist[i].split('/resources/upload/')[1]
								}else{
									datae["img"]=imglist[i]
								}
								this.imglist.push(datae)
							}
						})
					$('#myModal').modal()
				}
			}
		})
		function loadFile(file){
		    $("#filename").html(file.name);
		}
		var css="";
		for(let po=0;po<css.length;po++){
			/*
				判断是否包含新图片方法返回-1则为ture
			*/
			if(css[po].indexOf("/resources/upload/")!=-1){
				$("#picss").append('<img class="mianImgs" width="50px" style="margin: 2.5px;float:left" height="50px" src="${pageContext.request.contextPath}'+css[po]+'">');
			}else{
				$("#picss").append('<img class="mianImgs" width="50px" style="margin: 2.5px;float:left" height="50px" src="/Shopping/resources/upload/'+css[po]+'">');
			}
		}
	</script>
</body>
</html>