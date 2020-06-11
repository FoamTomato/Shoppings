<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>商城后台</title>
<jsp:include page="../commonCss.jsp" />
<jsp:include page="../commonJs.jsp" />
    <!-- 不想打印 -->
   <style type="text/css">
  
  .yidaw{
  	width:80%;
  	margin-left:5px
  }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jss/logisticsChange.js" ></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/element.css">
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/resources/jss/element.js"></script>

<!-- <script>
window.onload=
function()
{
var linkList=window.parent.document.getElementsByTagName("link");//获取父窗口link标签对象列表
var head=document.getElementsByTagName("head").item(0);
//外联样式
for(var i=0;i<linkList.length;i++)
{
var l=document.createElement("link");
l.rel = 'stylesheet'
l.type = 'text/css';
l.href=linkList[i].href;
head.appendChild(l);
}

var script2=window.parent.document.getElementsByTagName("script");//获取父窗口link标签对象列表

//外联样式
for(var i=0;i<script2.length;i++)
{
var l1=document.createElement("script");
l1.src=script2[i].src;
head.appendChild(l1);
}
}
</script> -->

</head>
<body class="blank" style="margin: 0px; padding: 0px; background: white;font-size:16px">
<jsp:include page="o_modal.jsp"/> 
<jsp:include page="YT_modal.jsp"/> 
<jsp:include page="HJ_modal.jsp"/> 
<div id="yida">
	<div class="form-group col-xs-6" style="width:40%">
		<label>订单号:<nobr id="Fid" style="
		<c:if test="${fLogistics.standby6 == 0 && fLogistics.standby6 != ''}">color:blue</c:if>
		<c:if test="${fLogistics.standby6 == 2}">color:green</c:if>
		<c:if test="${fLogistics.standby6 == 1}">color:red</c:if>
		<c:if test="${fLogistics.standby6 == 3}">color:#c925d478</c:if>
		">${fLogistics.fIds}</nobr>
		<nobr style="margin-left:15px"> id:${fLogistics.id }</nobr>
		<input type="hidden" id="useid" value="${fLogistics.id }"/>
		</label> 
	</div>
	<div class="form-group col-xs-6">
		<label>中文简称:${fLogistics.fChinaShort}</label>
	</div>
	<div class="form-group col-xs-12" style="margin-top: -25px">
	<hr/>
	</div>
	<div class="form-group col-xs-12" style="margin-top: -15px">
		<div class="form-group col-xs-6" style="border-right:1px dashed #ccc;width:40%;">
			<div class="form-group col-xs-12 F_logistics_text1">
				送货：<label style="margin-right:20px">Standard</label><c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_edit')}"><nobr onclick="edit()">编辑</nobr></c:if>|<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_copy')}"><nobr @click="copy()">复制</nobr></c:if>
			</div>
			
			<div class="form-group col-xs-12 F_logistics_text1">
				收货人：<span id="FName" style="word-break: break-all;<c:if test="${fLogistics.standby6 == 0 && fLogistics.standby6 != ''}">color:blue</c:if>
		<c:if test="${fLogistics.standby6 == 2}">color:green</c:if>
		<c:if test="${fLogistics.standby6 == 3}">color:#c925d478</c:if>
		<c:if test="${fLogistics.standby6 == 1}">color:red</c:if>">${fLogistics.fFirstName} ${fLogistics.fLastName }</span>
			</div>
			
			<div class="form-group col-xs-12 F_logistics_text1" >
				地址：<br/><div id="FAddress">${fLogistics.fAddress1}</div> <br/><div id="FAddress2">${fLogistics.fAddress2 }</div> <br/><div id="FAddress3">${fLogistics.fAddress3 }</div>
			</div>
			<input id="hjid" type="hidden" value="${fLogistics.id}">
			<input id="FProvince" type="hidden" value="${fLogistics.fProvince}">
			<input id="FCity" type="hidden" value="${fLogistics.fCity}">
			<input id="standby8" type="hidden" value="${fLogistics.standby8}">
			<div class="form-group col-xs-12 F_logistics_text1">
				邮编：<nobr id="FPostcode">${fLogistics.fPostCode}</nobr>
			</div>
			
			<div class="form-group col-xs-12 F_logistics_text1">
				国家：<nobr id="FCountry">${fLogistics.fCountry}</nobr>
			</div>
			
			<div class="form-group col-xs-12" style="border-bottom:1px dashed #ccc;">
				
			</div>
			
			<div class="form-group col-xs-12 F_logistics_text1">
				电话：<nobr id="FTelephone">${fLogistics.fTelephone}</nobr>
			</div>
			
			<div class="form-group col-xs-12 F_logistics_text1">
				邮箱：<div id="FEmail" style="word-break: break-all;">${fLogistics.fEmail}</div>
			</div>
		</div>
		<div class="form-group col-xs-6">
			
			<div class="form-group col-xs-12">
				${fLogistics.fClientOrderCode}
			</div>
			
			<div class="form-group col-xs-12">
				店铺：${fLogistics.fStore}
			</div>
			
			<div class="form-group col-xs-12">
				<span style="font-size:10px;color:red">(注意：是当地国家的币种)</span><br/>
				订单：${fLogistics.fTotalPrice}<br/>
				手续：<nobr style="color:red">-${fLogistics.standby1}</nobr><br/><hr>
				结余：<nobr style="color:green"><fmt:formatNumber type="number" value="${fLogistics.fTotalPrice-fLogistics.standby1}" pattern="0.00" maxFractionDigits="2"/></nobr>
			</div>
			
			<div class="form-group col-xs-12">
				利润：${fLogistics.standby2} - ${fLogistics.standby3} =<nobr style="color:green">${fLogistics.standby4}</nobr>
			</div>
			<input id="company" type="hidden" value="${fLogistics.standby5}">
			<div class="form-group col-xs-12">
			占百分比：<fmt:formatNumber type="number" value="${100*fLogistics.standby4 / (fLogistics.standby2)}" pattern="0.00" maxFractionDigits="2"/>%
			</div>
			<div class="form-group col-xs-12">
				<%-- <c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_logistic')}"><a onclick="FaddOrder()">内部物流</a> </c:if> --%>
				<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_hjlogistic')}"><a onclick="HJaddOrder('${fLogistics.id }')">环金物流</a></c:if>
				<nobr>
					<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_hjlogistic')}"><a @click="reset();optionList='0';dialogVisible = true">义达物流</a>
						<jsp:include page="yida_modal.jsp"/> 
					</c:if>
					<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_post')}">
						<el-button type="primary" size="mini" @click="submitOrderOnes">发送运单</el-button>
					</c:if>
				</nobr>
			</div>
			<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_status')}">
			<div class="form-group col-xs-12">
												<select name="status" class="form-control" id="StatusOrder" @click="StatusOrder">
													<option value="0" <c:if test="${fLogistics.standby6==0 }">selected="selected"</c:if>>已发件</option>
													<option value="1" <c:if test="${fLogistics.standby6==1 }">selected="selected"</c:if>>未发件</option>
													<option value="2" <c:if test="${fLogistics.standby6==2 }">selected="selected"</c:if>>已录入erp</option>
													<option value="3" <c:if test="${fLogistics.standby6==3 }">selected="selected"</c:if>>问题件</option>
													<!-- <option value="4">订单取消</option> -->
												</select>
			</div>
			</c:if>
			<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_right_status')}">
			<!-- 是否集拼 -->
			<div class="form-group col-xs-12">
			<input type="radio" name="eles" @click="jps(1)" <c:if test="${fLogistics.standby13==1 }">checked</c:if>>已集拼
			<input type="radio" name="eles" @click="jps(0)" <c:if test="${fLogistics.standby13!=1 }">checked</c:if>>未集拼
			</div>
			</c:if>
		<textarea id="copy_text" style="display:none"></textarea>
	</div>
	
	<div class="form-group col-xs-12" style="margin-top: -25px">
	<hr/>
	</div>
	<div class="form-group col-xs-12" style="margin-top: -25px">
	<nobr>备注：</nobr><nobr style="width:90%" id="textareaS">${fLogistics.fRemark}</nobr>
	<!-- 默认物流方式 -->
	<input id="st12" type="hidden" value="${fLogistics.standby12}">
	</div>
	<div class="form-group col-xs-12" style="margin-top: -25px">
	<hr/>
	</div>
	<div id="logis" class="form-group col-xs-12">
		<div  v-for="item in orderlist.hj">
			<el-row :gutter="20" class="F_logistics_item2 F_logistics_body2">
			  <el-col :span="12">
			  <input type="radio" name="hjor" v-if="item.hjStandy2=='1'" value="0" checked  @click="radioslo('0',item.id,'')" > 
			  <input type="radio" name="hjor" v-if="item.hjStandy2!='1'" value="0"   @click="radioslo('0',item.id,'')" > 
			  	运单号:<nobr style="color:red;font-weight:900">{{item.hjShipperhawbcode}}</nobr></el-col>
			  <el-col :span="12" style="line-height:22px;font-weight: 900;">
			  <a @click="selectOrder_HJ(item.id,'0')">查看</a>|
			  <a @click="geteveryone(item.hjStandy5,item.hjShippingmethod,'0')">打印</a>|
			  <a @click="delete_HJ(item.id,item.hjShipperhawbcode,'0')">删除</a></el-col>
			  <el-col :span="12">物流:<nobr style="color:black">{{hjShippingmethods(item.hjShippingmethod)}}</nobr></el-col>
			  <el-col :span="12">跟踪:<nobr style="color:black">{{item.hjStandy7}}</nobr></el-col>
			  <el-col :span="12">参考号:<nobr style="color:black">{{item.hjReferenceno}}</nobr></el-col>
			  <el-col :span="12">创建日期:<nobr style="color:black">{{getDate(item.hjStandy1)}}</nobr></el-col>
			</el-row>
		</div>
		<div  v-for="item in orderlist.yd">
			<el-row :gutter="20" class="F_logistics_item2 F_logistics_body2">
			  <el-col :span="12">
			  <input type="radio" name="hjor" v-if="item.ydStandy2=='1'" value="2" checked  @click="radioslo('2',item.id,item.ydShippingMethodNo)" > 
			  <input type="radio" name="hjor" v-if="item.ydStandy2!='1'" value="2"  @click="radioslo('2',item.id,item.ydShippingMethodNo)" > 
			     运单号:<nobr style="color:red;font-weight:900">{{item.ydStandy3}}</nobr></el-col>
			  <el-col :span="12" style="line-height:22px;font-weight: 900;">
			  <a @click="selectOrder_HJ(item.id,'1')">查看</a>|
			  <a @click="geteveryone(item.ydReferenceNo,'','1')">打印</a>|
			  <a @click="delete_HJ(item.id,item.id,'1')">删除</a></el-col>
			  <el-col :span="12">物流:<nobr style="color:black">{{hjShippingmethods(item.ydShippingMethod)}}</nobr></el-col>
			  <el-col :span="12">跟踪:<nobr style="color:black">{{item.ydShippingMethodNo}}</nobr></el-col>
			  <el-col :span="12">参考号:<nobr style="color:black">{{item.ydReferenceNo}}</nobr></el-col>
			  <el-col :span="12">创建日期:<nobr style="color:black">{{item.ydStandy5}}</nobr></el-col>
			</el-row>
		</div>
	</div>
	</div>
	</div>
	<script type="text/javascript">
	//更改单独商品状态
	 $("#StatusOrder").click(function(){
		let datas={};
		datas["id"]=${fLogistics.fIds};
		datas["standby6"]=$("#StatusOrder option:selected").val();
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :'${pageContext.request.contextPath}/Logistics/printStatus?${_csrf.parameterName}=${_csrf.token}',
			data:JSON.stringify(datas),
			dataType : 'json',
			success : function(data) {
				if($("#StatusOrder option:selected").val()==0){
					$("#Fid").css("color","blue");
					$("#FName").css("color","blue");
				}else if($("#StatusOrder option:selected").val()==1){
					$("#Fid").css("color","red");
					$("#FName").css("color","red");
				}else if($("#StatusOrder option:selected").val()==2){
					$("#Fid").css("color","green");
					$("#FName").css("color","green");
				}else if($("#StatusOrder option:selected").val()==3){
					$("#Fid").css("color","#c925d478");
					$("#FName").css("color","#c925d478");
				}
				toastr.success("状态已改变");
				parent.pages("1");
			},
			error : function(json) {
				toastr.error("状态改变失败");
			}
		});  
	}) 
	//改变打印状态
   function printStatus(value){
		let datas={};

		datas["id"]=value;
		datas["standby6"]=0;
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :'${pageContext.request.contextPath}/Logistics/printStatus?${_csrf.parameterName}=${_csrf.token}',
			data:JSON.stringify(datas),
			dataType : 'json',
			success : function(data) {
				
			},
			error : function(json) {
				toastr.error("打印失败");
			}
		});  
	} 
	//修改集拼状态
	//删除运单
	function	deleteX(value,values){
		let resl="是否删除  '"+values+"'请谨慎操作!!!";
		swal({
			  title: "您正在进行删除操作",
			  text: resl,
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "确认",
			  cancelButtonText: "取消",
			  closeOnConfirm: true,//设置为true就可以取消弹窗了
			  closeOnCancel: false
			},
			function(isConfirm){
			  if (isConfirm) {
				  let datas={};
					datas["id"]=value;
					datas["xFIds"]=values;
					//保存修改
					$.ajax({
						type : "post",
						contentType : "application/json;charset=utf-8", 
						url :'${pageContext.request.contextPath}/Logistics/delLogisticsQuantity?${_csrf.parameterName}=${_csrf.token}',
						data:JSON.stringify(datas),
						dataType : 'json',
						success : function(data) {
							toastr.success("删除成功");
							$("#logis").html("");addHtml();selHtml();
						},
						error : function(json) {
							toastr.error("删除失败");
						}
					});  
			  } else {
				    swal("已取消", "请继续您的操作 :)", "error");
			  }
			});
	 	
	} 
	
	/* 复制 */
	
	
	 function getJsonss(data){
		let data1={};
		let data2={};
		let data3={};
		let b="";
		 for(var key in data){
			var value = data[key]; //注意是 [  ]
			//console.log(value+key); //依次弹出小明、小花
				if(value!=null&&value!=""){
					if(key == "itemArr"){
						for(var key1 in value[0]){
							var value1 = value[0][key1];
							if(value1!=null&&value1!=""){
								
								if(key1=="shipperHawbcode"){
									b+=value1;
								}
								data3[key1]=value1;
							}
						}
						data1["itemArr"]=[data3];
					}else if(key == "consignee"){
						for(var key1 in value){
							var value1 = value[key1];
							if(value1!=null&&value1!=""){
								if(key1=="shipperHawbcode"){
									b+=value1;
								}
								data2[key1]=value1;
							}
						}
						data1["consignee"]=data2;
					}else{

						data1[key]=value;
						if(key=="shipperHawbcode"){
							b+=value;
						}
					} 
				}
			} 
			return {a:JSON.stringify(data1),b:b};
	}  
	
	
	Vue.http.options.root="${pageContext.request.contextPath}"
	var vm=new Vue({
		el:'#yida',
		data:{
			dialogVisible: false,
			consigneeCountryEname:"",//国家英文
			consigneeCountryCname:"",//国家中文
			invoiceEnname:"",//英文
			invoiceCnname:"",//中文
			city:"",//城市
			province:"",//省州
			company:"",//公司
			unitCode:"PCE",//单位
			invoiceUnitcharge:"",//总价
			invoiceCurrencycode:"",//币种
			sku:"",//sku
			invoiceQuantity:"1",//数量
			shippingMethod:"",//物流
			hsCode:"",//海关协制编号
			salePrice:"",//产品销售价格
			saleCurrency:"",//销售结算币种
			categoryName:"",//产品中文类目
			parentEnName:"",//产品英文类目
			orderWeight:"",//重量/g
			orderPieces:"1",//外包装数
			mailCargoType:"4",//申报种类
			length:"",//长
			width:"",//宽
			height:"",//高
			isContainsBattery:false,//是否包含电池
			isAneroidMarkup:false,//非液体化妆品
			isBreakable:false,//是否易碎
			isOnlyBattery:false,//是否纯电池
			isCharged:false,//是否带电
			isReturn:false,//是否退回
			order_info:"",//订单备注
			cargotype:"W",//货物类型
			logoutlist:[],//物流
			jurisdiction:"",//权限
			orderlist:{},//订单列表
			optionList:"",//选项
			ydid:"",//义达id
		},
		created(){
			this.sku="B07"+generateMixed(7).toUpperCase()
			this.city=$("#FCity").val()
			this.province=$("#FProvince").val()
			this.invoiceUnitcharge=sj()
			this.salePrice=this.invoiceUnitcharge
			this.$http.post("${pageContext.request.contextPath}/order/select/list?${_csrf.parameterName}=${_csrf.token}").then(result=>{
			    this.logoutlist=result.body.logoutlist
			    this.jurisdiction=result.body.jurisdiction
			})
			this.selectlist()
		},
		methods:{
	      handleClose(done) {
	          this.$confirm('确认关闭？')
	            .then(_ => {
	              done();
	            })
	            .catch(_ => {});
	      },
	      copy() {
	  	    var inputText = document.getElementById('copy_text');
	  		var result = "";
	  		result += "姓名："+$("#FName").html()+"\r\n";
	  		result += "地址："+$("#FAddress").text()+"\r\n";
	  		result += "城市："+$("#FAddress2").text()+"\r\n";
	  		result += "州省："+$("#FAddress3").text()+"\r\n";
	  		result += "国家："+$("#FCountry").html()+"\r\n";
	  		result += "邮编："+$("#FPostcode").html()+"\r\n";
	  		result += "电话："+$("#FTelephone").html()+"\r\n";
	  		$('#copy_text').text(result);
	  	    this.copyTextToClipboard(inputText);
	  	},
	  	copyTextToClipboard(input) {
		    input.select();  //PC端复制
		    //input.setSelectionRange(0, input.value.length); //ios移动端复制
		    document.execCommand('copy', true);

		    try {
		       document.execCommand('copy');
		       toastr.success("复制成功");
		    } catch (err) {
		       toastr.error("复制失败");
		   }
		},jps(data){
			let datas={}
			datas["states"]=data
			datas["ids"]=${fLogistics.fIds}
			$.ajax({
				type : "post",
				contentType : "application/json;charset=utf-8", 
				url :'${pageContext.request.contextPath}/Logistics/jps?${_csrf.parameterName}=${_csrf.token}',
				data:JSON.stringify(datas),
				dataType : 'json',
				success : function(data) {
					toastr.success("修改集拼成功");
					$("#logis").html("");addHtml();selHtml();
				},
				error : function(json) {
					toastr.error("修改集拼失败");
				}
			});  
		},
	      submit(){
	    	  if(this.orderWeight==""||
	    		 this.invoiceEnname==""||
	    		 this.invoiceCnname==""||
	    		 this.shippingMethod==""){
	    		  this.$message.error("有值为空")
	    		  return false
	    	  }
	    	  this.dialogVisible = false
	    	  //提交订单
	    	  let datas={}
	    	  datas["ydShippingMethod"]=this.shippingMethod
	    	  datas["ydStandy3"]="${fLogistics.fIds}"
	    	  datas["ydOrderWeight"]=(this.orderWeight/1000) //注意kg
	    	  datas["ydOrderPieces"]=this.orderPieces
	    	  datas["ydCargotype"]=this.cargotype  //货物类型
											    	 //W：包裹
											    	 //D：文件
											    	 //B：袋子
	    	  datas["ydMailCargoType"]=this.mailCargoType //申报种类
	    	  datas["ydReturnSign"]=this.isReturn==true?"Y":"N"
	    	  datas["ydOrderInfo"]=this.order_info
	    	  
	    	  //发件人信息
	    	  //let shipper={}
	    	  //datas["shipper"]=shipper
	    	  
	    	  //收件人信息
	    	 // let consignee={}
	    	  //datas["consignee"]=consignee
	    	  datas["ydConsigneeName"]="${fLogistics.fFirstName}"+"${fLogistics.fLastName }"
	    	  datas["ydConsigneeCountrycode"]="${fLogistics.fCountry}"
    		  datas["ydConsigneeProvince"]=this.province
    		  datas["ydConsigneeCity"]=this.city
    		  datas["ydConsigneeStreet"]="${fLogistics.fAddress1}"
   			  datas["ydConsigneePostcode"]="${fLogistics.fPostCode}"
			  datas["ydConsigneeTelephone"]="${fLogistics.fTelephone}"
			  datas["ydConsigneeMobile"]="${fLogistics.fTelephone}"
  			  datas["ydConsigneeEmail"]="${fLogistics.fEmail}"
  	  	      datas["ydReferenceNo"]="${fLogistics.fIds}"
  				
	    	  //consignee["consignee_tariff"]=//收件人税号
	    	  
	    	  //let len=[]
	    	  //let invoice={}
	    	  //len.push(invoice)
	    	  //datas["invoice"]=len
	    	  datas["ydInvoiceEnname"]=this.invoiceEnname
	    	  datas["ydInvoiceCnname"]=this.invoiceCnname
	    	  datas["ydInvoiceQuantity"]=this.invoiceQuantity
	    	  datas["ydUnitCode"]=this.unitCode
	    	  if(this.shippingMethod=="FRDB"){
	    		  datas["ydInvoiceUnitcharge"]="18"
	    	  }else{
	    		  datas["ydInvoiceUnitcharge"]=this.invoiceUnitcharge
	    	  }
	    	  datas["ydSku"]=this.sku
	    	  
	    	//添加义达商品
				this.$http.post("${pageContext.request.contextPath}/Logistics/addyida?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
					let results=result.body
					if(results=="0"){
						this.$message.error("添加失败")
					}else if(results=="1"){
						//添加运单
						this.$message.success("添加成功")
						this.selectlist()
					}
				})
	      },
	      StatusOrder(){
	  		let datas={};
	  		datas["id"]=${fLogistics.fIds};
	  		datas["standby6"]=$("#StatusOrder option:selected").val();
	  		$.ajax({
	  			type : "post",
	  			contentType : "application/json;charset=utf-8", 
	  			url :'${pageContext.request.contextPath}/Logistics/printStatus?${_csrf.parameterName}=${_csrf.token}',
	  			data:JSON.stringify(datas),
	  			dataType : 'json',
	  			success : function(data) {
	  				if($("#StatusOrder option:selected").val()==0){
	  					$("#Fid").css("color","blue");
	  					$("#FName").css("color","blue");
	  				}else if($("#StatusOrder option:selected").val()==1){
	  					$("#Fid").css("color","red");
	  					$("#FName").css("color","red");
	  				}else if($("#StatusOrder option:selected").val()==2){
	  					$("#Fid").css("color","green");
	  					$("#FName").css("color","green");
	  				}else if($("#StatusOrder option:selected").val()==3){
	  					$("#Fid").css("color","#c925d478");
	  					$("#FName").css("color","#c925d478");
	  				}
	  				toastr.success("状态已改变");
	  				parent.pages("1");
	  			},
	  			error : function(json) {
	  				toastr.error("状态改变失败");
	  			}
	  		});  
	  	},
	      reset(){
	    	    this.dialogVisible=false
	    	    this.consigneeCountryEname=""//国家英文
	    	    this.consigneeCountryCname=""//国家中文
	    	    this.invoiceEnname=""//英文
	    	    this.invoiceCnname=""//中文
	    	    this.city=""//城市
	    	    this.province=""//省州
	    	    this.company=""//公司
	    	    this.unitCode="PCE"//单位
	    	    this.invoiceUnitcharge=""//总价
	    	    this.invoiceCurrencycode=""//币种
	    	    this.sku="B07"+generateMixed(7).toUpperCase()//sku
	    	    this.invoiceQuantity="1"//数量
	    	    this.shippingMethod=""//物流
	    	    this.hsCode=""//海关协制编号
				this.salePrice=""//产品销售价格
				this.saleCurrency=""//销售结算币种
				this.categoryName=""//产品中文类目
				this.parentEnName=""//产品英文类目
				this.orderWeight=""//重量/g
				this.orderPieces="1"//外包装数
				this.mailCargoType="4"//申报种类
				this.length="10"//长
				this.width="10"//宽
				this.height="10"//高
				this.isContainsBattery=false//是否包含电池
				this.isAneroidMarkup=false//非液体化妆品
				this.isBreakable=false//是否易碎
				this.isOnlyBattery=false//是否纯电池
				this.isCharged=false//是否带电
				this.isReturn=false//是否退回
				this.order_info=""//订单备注
				this.cargotype="W"//货物类型
				this.sku="B07"+generateMixed(7).toUpperCase()
				this.city=$("#FCity").val()
				this.province=$("#FProvince").val()
				this.invoiceUnitcharge=sj()
				this.salePrice=this.invoiceUnitcharge
				this.ydid=""
	      },
	      submitOrderOnes(){
	    	  //发送运单
	    	  if($("input[name='hjor']:checked").val()=="0"){
	    			let datas=[];
	    			datas.push(${fLogistics.id});//$("input[name='hjor']:checked").val();
	    			$.ajax({
	    					type : "post",
	    					contentType : "application/json;charset=utf-8", 
	    					url :"${pageContext.request.contextPath}/Logistics/getPostId?${_csrf.parameterName}=${_csrf.token}",
	    					dataType:"json",
	    					data:JSON.stringify(datas),
	    					success:function(data){
	    						let sun=0;
	    						let num="";
	    						let num1="";
	    						for(var key in data){
	    							var value = data[key]; //注意是 [  ]
	    							let result_hj=getJsonss(value).a;
	    							let result_hj2=getJsonss(value).b;
	    							$.ajax({
	    								type : "post",
	    								contentType : "application/json;charset=utf-8", 
	    								url :"${pageContext.request.contextPath}/Logistics/getPostYu?${_csrf.parameterName}=${_csrf.token}",//http://vakind.f3322.org:10008/api/logistics/v1/track/create/order
	    								dataType:"json",
	    								async:false,
	    								data:result_hj,//JSON.stringify(datasy)
	    								success:function(data2){
	    									if(data2.toString().match("重量超过")||data2.toString().match("敏感词")){
	    	      								toastr.error(data[key].shipperHawbcode+data2);
	    	      								return false;
	    	      							}
	    									if(data2.status==0){
	    										$("#resultHJ").val($("#resultHJ").val()+"发送失败   "+data[key].shipperHawbcode+"  消息：:"+data2.errormsg+"\n"+"*****************"+"\n");
	    										toastr.error(data2.errormsg)
	    										 swal("提示", data2.errormsg, "error"); 
	    										return false;
	    									}
	    									 if(data2.result.status=="0"){
	    										 $("#resultHJ").val($("#resultHJ").val()+"发送失败   "+data[key].shipperHawbcode+"  消息：:"+data2.result.errorMessage+"\n"+"*****************"+"\n");
	    										 toastr.error(data2.result.errorMessage);
	    										 swal("提示", data2.result.errorMessage, "error");  
	    										 return false;
	    									 }else if(data2.result.status=="1"){
	    									let labls={};
	    									
	    									labls["methosd"]=data[key].shippingMethod;
	    									labls["id2"]=data[key].shipperHawbcode;
	    									labls["id"]=result_hj2;
	    									labls["lab"]=data2.result.lableKey;
	    									labls["num1"]=data2.result.trackNum1;
	    									labls["num2"]=data2.result.trackNum2;
	    									toastr.success("发送成功");
	    									$.ajax({
	    										type : "post",
	    										contentType : "application/json;charset=utf-8", 
	    										url :"${pageContext.request.contextPath}/Logistics/setLableid?${_csrf.parameterName}=${_csrf.token}",//http://vakind.f3322.org:10008/api/logistics/v1/track/create/order
	    										dataType:"json",
	    										async:false,
	    										data:JSON.stringify(labls),//JSON.stringify(datasy)
	    										success:function(data){
	    											//$("#logis").html("");
	    											//addHtml();
													vm.selectlist()
	    										},
	    										error:function(json){
	    											toastr.error("添加失败");
	    										}
	    									});
	    									 }
	    								},
	    								error:function(json){

	    									console.log(json)
	    									toastr.error(json.responseText);
	    								}
	    							});
	    						}
	    						
	    					},
	    					error:function(json){
	    						swal("提示", "id查找失败!", "error");  
	    					}
	    				}); 
	    			}else if($("input[name='hjor']:checked").val()=="2"){
	    				let datas={}
	    				 
	    				datas["id"]=${fLogistics.fIds}
	    				//义达
	    				this.$http.post("${pageContext.request.contextPath}/Logistics/postYd?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
							let results=result.body
							if(results.success=="0"){
								this.$message.error(results.cnmessage)
							}else if(results.success=="1"){
								//添加运单
								this.$message.success(results.cnmessage)
								let datas1={}
								datas1["ydStandy6"]=results.data.order_id
								datas1["ydStandy3"]=$("#Fid").html()
								datas1["ydReferenceNo"]=results.data.refrence_no
								datas1["ydShippingMethodNo"]=results.data.shipping_method_no
								this.$http.post("${pageContext.request.contextPath}/Logistics/updateYd?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas1),{emulateJSON:true}).then(result1=>{
									this.selectlist()
									parent.vm.selectall()
								})
							}
						})
	    				
	    			}
	    	  this.selectlist()
	      },
	      selectlist(){
	    	  //查询订单列表
	    	  let datas={}
	    	  datas["id"]=${fLogistics.id}
	    	  datas["fid"]=${fLogistics.fIds}
	    	  this.$http.post("${pageContext.request.contextPath}/Logistics/orderlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
	    		  this.orderlist=result.body
	    	  })
	      },getDate (item) {
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
			hjShippingmethods(val){
	        	  //转换物流
	        	  let chans=this.logoutlist
	        	  for(let i=0;i<chans.length;i++){
	        		  if(chans[i].shortName==val||chans[i].standy5==val){
	        			  return chans[i].logisticsName
	        		  }
	        	  }
			},
			//value:0.不是默认 1.默认
			radioslo(value,va2,va3){
			 	let datas={};
				datas["hjStandy3"]=value;
				datas["hjStandy2"]=va2
				datas["hjStandy4"]=va3
				datas["hjShipperhawbcode"]=$("#Fid").html();
					//hj
				$.ajax({
						type : "post",
						contentType : "application/json;charset=utf-8", 
						url :'${pageContext.request.contextPath}/Logistics/defaultHj?${_csrf.parameterName}=${_csrf.token}',
						data:JSON.stringify(datas),
						dataType : 'json',
						success : function(data) {
							toastr.success("默认修改成功");
							parent.vm.selectall();
							//parent.pages("1");
						},
						error : function(json) {
							toastr.error("默认修改失败");
						}
					});  
			},
			//查看运单
			selectOrder_HJ(value,val2){
				if(val2=="0"){
				 	let datas={};
					datas["id"]=value;
					//查看运单
					$.ajax({
						type : "post",
						contentType : "application/json;charset=utf-8", 
						url :'${pageContext.request.contextPath}/Logistics/ckHJLogistics?${_csrf.parameterName}=${_csrf.token}',
						data:JSON.stringify(datas),
						dataType : 'json',
						success : function(data) {
							HJaddOrder($("#useid").val());
							$("#hj_consigneeCountryEname").val(data.hjConsigneecountryename);
							$("#hj_consigneeCountryCname").val(data.hjConsigneecountrycname);
							$("#hj_invoiceEnname").val(data.hjInvoiceenname);
							$("#hj_invoiceCnname").val(data.hjInvoicecnname);
							$("#hj_unitCode").val(data.hjUnitcode);
							$("#hj_invoiceUnitcharge").val(data.hjInvoiceunitcharge);
							$("#hj_invoiceCurrencycode").val(data.hjInvoicecurrencycode);
							$("#hj_sku").val(data.hjSku);
							$("#hj_invoiceQuantity").val(data.hjInvoicequantity);
							$("#hj_hsCode").val(data.hjHscode);
							$("#hj_province").val(data.hjConsigneeprovince);
							//默认物流
							if(data.hjShippingmethod!=""){
								$("#hj_shippingMethod").val(data.hjShippingmethod);
							}else{
								$("#hj_shippingMethod").val(0);
							}
							
							if(data.hjIscontainsbattery=="1"){
								$("#hj_isContainsBattery").click();
							}
							$("#hj_salePrice").val(data.hjSaleprice);
							if(data.hjIsaneroidmarkup=="1"){
								$("#hj_isAneroidMarkup").click();
							}
							$("#hj_saleCurrency").val(data.hjSalecurrency);
							if(data.hjIsonlybattery=="1"){
								$("#hj_isOnlyBattery").click();
							}
							$("#hj_categoryName").val(data.hjCategoryname);
							if(data.hjIsbreakable=="1"){
								$("#hj_isBreakable").click();
							}
							$("#hj_parentEnName").val(data.hjParentenname);
							if(data.hjIscharged=="1"){
								$("#hj_isCharged").click();
							}
							$("#hj_orderWeight").val(data.hjOrderweight);
							if(data.hjIsreturn=="1"){
								$("#hj_isReturn").click();
							}
							$("#hj_orderPieces").val(data.hjOrderpieces);
							$("#hj_mailCargoType").val(data.hjMailcargotype);
							$("#hj_CITY").val(data.hjConsigneecity);
							$("#hj_COMPANY").val(data.hjConsigneecompany);
							$("#hj_length").val(data.hjLength);
							$("#hj_width").val(data.hjWidth);
							$("#hj_height").val(data.hjHeight);
							$("#modal-change-hj").html("修改");
							$("#modal-change-hj").attr("onclick","addx_HJ('1',"+data.id+","+$("#useid").val()+")");
					},
					error : function(json) {
						toastr.error("查找失败");
					}
				});  
			}else if(val2=="1"){
				 
			  //查询订单列表
	    	  let datas={}
	    	  datas["id"]=value
	    	  this.$http.post("${pageContext.request.contextPath}/Logistics/yidaSelectOne?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
	    		  let list=result.body
	    		  this.ydid=value
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
	    		  this.isReturn=list.ydReturnSign=="Y"?true:false
	    		  this.optionList="1"
	    		  this.dialogVisible=true
	    	  })
			}
	    },
	    geteveryone(value,metho,val1){
	    	//打印
	    	if(val1=="0"){
			if(value==""||value==null){
				toastr.error("未获取到改运单的打印单");
				return false;
			}
			let day={};
			day["lableKey"]=value;
			$.ajax({
				type : "post",
				contentType : "application/json;charset=utf-8", 
				url :"${pageContext.request.contextPath}/Logistics/setLableKey?${_csrf.parameterName}=${_csrf.token}",
				dataType:"json",
				async:false,
				data:JSON.stringify(day),
				success:function(data){
					let days={};
					days["bytes"]=data.result.lableData;
					days["metho"]=metho;
					$.ajax({
						type : "post",
						contentType : "application/json;charset=utf-8", 
						url :"${pageContext.request.contextPath}/Logistics/conserveFile?${_csrf.parameterName}=${_csrf.token}",
						dataType:"json",
						async:false,
						data:JSON.stringify(days),
						success:function(data){
							window.open("${pageContext.request.contextPath}/"+data,"_blank");
							let zs={};
							zs["hjStandy8"]=$("#Fid").html();
							$.ajax({
								type : "post",
								contentType : "application/json;charset=utf-8", 
								url :"${pageContext.request.contextPath}/Logistics/hj_logistics_post?${_csrf.parameterName}=${_csrf.token}",
								dataType:"json",
								async:false,
								data:JSON.stringify(zs),
								success:function(data){
									console.log("已发件")
								},
								error:function(json){
									toastr.error("未发件");
								}
							}); 
							parent.vm.selectall()
						},
						error:function(json){
							toastr.error("未获取");
						}
					}); 
					//toastr.success("已添加"+data.result.lableData+"面单链接");
				},
				error:function(json){
					toastr.error("未获取到面单链接");
				}
			}); 
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
	    		listorder.push(numbers)
				datas["configInfo"]=configInfo
				datas["listorder"]=listorder
				configInfo["additional_info"]=additionalinfo
				this.$http.post("${pageContext.request.contextPath}/Logistics/lable?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
					if(result.body.success=="0"){
						this.$message.error(result.body.cnmessage)
					}else if(result.body.success=="1"){
						//添加运单
						console.log(result.body)
						let listOrder=result.body.data
						for(let li=0;li<listOrder.length;li++){
							window.open(listOrder[li].lable_file)
						}
					}
	    	  	})
	    	}
		},
		//删除运单
		delete_HJ(value,values,val1){
			if(val1=="0"){
			let resl="是否删除  '"+values+"'请谨慎操作!!!";
			swal({
				  title: "您正在进行删除操作",
				  text: resl,
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "确认",
				  cancelButtonText: "取消",
				  closeOnConfirm: true,//设置为true就可以取消弹窗了
				  closeOnCancel: false
				},
				function(isConfirm){
				  if (isConfirm) {
					  let datas={};
						datas["id"]=value;
						datas["hjShipperhawbcode"]=values;
						//保存修改
						$.ajax({
							type : "post",
							contentType : "application/json;charset=utf-8", 
							url :'${pageContext.request.contextPath}/Logistics/delHJLogistics?${_csrf.parameterName}=${_csrf.token}',
							data:JSON.stringify(datas),
							dataType : 'json',
							success : function(data) {
								toastr.success("删除成功");
								vm.selectlist()
							},
							error : function(json) {
								toastr.error("删除失败");
							}
						});  
				  } else {
					    swal("已取消", "请继续您的操作 :)", "error");
				  }
				});
			}else if(val1=="1"){
				 
				let data1={}
				data1["id"]=value
				this.$http.post("${pageContext.request.contextPath}/Logistics/ydDelOrder?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(data1),{emulateJSON:true}).then(result=>{
					let results=result.body
					if(results=="0"){
						this.$message.error("删除失败")
					}else if(results=="1"){
						//添加运单
						this.$message.success("删除成功")
						this.selectlist()
					}
				})
			}
		},
		ydUpdateOrder(){
			//修改义达订单
			if(this.orderWeight==""||
		    		 this.invoiceEnname==""||
		    		 this.invoiceCnname==""||
		    		 this.shippingMethod==""){
		    		  this.$message.error("有值为空")
		    		  return false
		    	  }
		    	  this.dialogVisible = false
		    	  //提交订单
		    	  let datas={}
		    	  datas["id"]=this.ydid
		    	  datas["ydShippingMethod"]=this.shippingMethod
		    	  datas["ydStandy3"]="${fLogistics.fIds}"
		    	  datas["ydOrderWeight"]=(this.orderWeight/1000) //注意kg
		    	  datas["ydOrderPieces"]=this.orderPieces
		    	  datas["ydCargotype"]=this.cargotype  //货物类型
												    	 //W：包裹
												    	 //D：文件
												    	 //B：袋子
		    	  datas["ydMailCargoType"]=this.mailCargoType //申报种类
		    	  datas["ydReturnSign"]=this.isReturn==true?"Y":"N"
		    	  datas["ydOrderInfo"]=this.order_info
		    	  
		    	  //发件人信息
		    	  //let shipper={}
		    	  //datas["shipper"]=shipper
		    	  
		    	  //收件人信息
		    	 // let consignee={}
		    	  //datas["consignee"]=consignee
		    	  datas["ydConsigneeName"]="${fLogistics.fFirstName}"+"${fLogistics.fLastName }"
		    	  datas["ydConsigneeCountrycode"]="${fLogistics.fCountry}"
	    		  datas["ydConsigneeProvince"]=this.province
	    		  datas["ydConsigneeCity"]=this.city
	    		  datas["ydConsigneeStreet"]="${fLogistics.fAddress1}"
	   			  datas["ydConsigneePostcode"]="${fLogistics.fPostCode}"
				  datas["ydConsigneeTelephone"]="${fLogistics.fTelephone}"
				  datas["ydConsigneeMobile"]="${fLogistics.fTelephone}"
	  			  datas["ydConsigneeEmail"]="${fLogistics.fEmail}"
		    	  //consignee["consignee_tariff"]=//收件人税号
		    	  
		    	  //let len=[]
		    	  //let invoice={}
		    	  //len.push(invoice)
		    	  //datas["invoice"]=len
		    	  datas["ydInvoiceEnname"]=this.invoiceEnname
		    	  datas["ydInvoiceCnname"]=this.invoiceCnname
		    	  datas["ydInvoiceQuantity"]=this.invoiceQuantity
		    	  datas["ydUnitCode"]=this.unitCode
		    	  datas["ydInvoiceUnitcharge"]=this.invoiceUnitcharge
		    	  datas["ydSku"]=this.sku
		    	  
		    	//添加义达商品
					this.$http.post("${pageContext.request.contextPath}/Logistics/ydUpdateOrder?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						let results=result.body
						if(results=="0"){
							this.$message.error("修改失败")
						}else if(results=="1"){
							//添加运单
							this.$message.success("修改成功")
							this.selectlist()
						}
					})
		}
	}
	})
</script>
</body>
</html>