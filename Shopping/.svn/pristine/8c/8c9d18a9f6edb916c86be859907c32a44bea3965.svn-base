<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="searchCates">
<!-- 模态框（Modal） -->
        <div class="modal fade" style="overflow:hidden" id="myModal_HJ"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <nobr class="Foam-title" id="myModalLabelp">
                        
                        </nobr>
                    </div>
                    <div class="modal-body" style="height:400px;display:block;overflow-y: auto;"> 
                      <div  class="form-group F_modal_ls col-lg-6">国家英文:<input id="hj_consigneeCountryEname" style="width:157px" class="F_modal_input" type="text" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></div>
                      <div  class="form-group F_modal_ls col-lg-6">国家中文:<input id="hj_consigneeCountryCname" style="width:157px" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">英文:<input id="hj_invoiceEnname" class="F_modal_input" type="text" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"></div>
                      <div  class="form-group F_modal_ls col-lg-6">中文:<input id="hj_invoiceCnname" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">城市:<input id="hj_CITY" class="F_modal_input"  type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">省州:<input id="hj_province" class="F_modal_input"  type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">公司:<input id="hj_COMPANY" class="F_modal_input"  type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">单位:<input id="hj_unitCode" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">总价:<input id="hj_invoiceUnitcharge" class="F_modal_input" type="text" disabled></div>
                      <div  class="form-group F_modal_ls col-lg-6">币种:<input id="hj_invoiceCurrencycode" class="F_modal_input" type="text" value="USD"></div>
                      <div  class="form-group F_modal_ls col-lg-6">sku :<input id="hj_sku" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-12">数量:<input id="hj_invoiceQuantity" class="F_modal_input" type="text" value="1"></div>
                      <div  class="form-group F_modal_ls col-lg-6">物流:<select id="hj_shippingMethod" class="F_modal_input" style="width:190px" type="text">
                      	  <option value="0">未匹配</option>
                      	  <c:forEach items="${logoutlist}" var="item">
                      	  	<c:if test="${item.status=='2'}"> 
                     	  	<option value="${item.shortName}">${item.logisticsName}</option>
                     	  	</c:if>
                     	  </c:forEach>
                      </select>
                      </div>
                      <div  class="form-group F_modal_ls col-lg-6">海关协制编号:<input id="hj_hsCode" class="F_modal_input" style="height:28px" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-4">是否包含电池:<input id="hj_isContainsBattery" class="F_modal_input f_radio" type="checkbox"></div>
                      <div  class="form-group F_modal_ls col-lg-6">产品销售价格:<input id="hj_salePrice" class="F_modal_input" type="text" disabled></div>
                      <div  class="form-group F_modal_ls col-lg-4">非液体化妆品:<input id="hj_isAneroidMarkup" class="F_modal_input f_radio" type="checkbox"></div>
                      <div  class="form-group F_modal_ls col-lg-6">销售结算币种:<input id="hj_saleCurrency" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-4">是否纯电池:<input id="hj_isOnlyBattery" class="F_modal_input f_radio" type="checkbox"></div>
                      <div  class="form-group F_modal_ls col-lg-6">产品中文类目:<input id="hj_categoryName" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-4">是否易碎:<input id="hj_isBreakable" class="F_modal_input f_radio" type="checkbox"></div>
                      <div  class="form-group F_modal_ls col-lg-6">产品英文类目:<input id="hj_parentEnName" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-4">是否带电:<input id="hj_isCharged" class="F_modal_input f_radio" type="checkbox"></div>
                      <div  class="form-group F_modal_ls col-lg-6">重量/g :<input id="hj_orderWeight" class="F_modal_input" style="width:222px" type="number" oninput = "value=value.replace(/[^\d]/g,'')"></div>
                      <div  class="form-group F_modal_ls col-lg-4">是否退回:<input id="hj_isReturn" class="F_modal_input f_radio" type="checkbox"></div>
                      <div  class="form-group F_modal_ls col-lg-6">外包装数:<input id="hj_orderPieces" class="F_modal_input" style="width:140px" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">申报种类:<input id="hj_mailCargoType" class="F_modal_input" style="width:152px" type="text"></div>
                      <div  class="form-group F_modal_ls" style="width: 31%;margin-left: 15px;"> 长  :<input id="hj_length" class="F_modal_input" style="width: 70%;" type="text"></div>
                      <div  class="form-group F_modal_ls" style="width: 31%;"> 宽  :<input id="hj_width" class="F_modal_input" style="width: 70%;" type="text"></div>
                      <div  class="form-group F_modal_ls" style="width: 31%;"> 高  :<input id="hj_height" class="F_modal_input" style="width: 70%;" type="text"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <c:if test="${fn:contains(jurisdiction.jurisdiction,'order_list_edit')}">
                        <button type="button" id="modal-change-hj" class="btn btn-primary">
                            	
                        </button>
                        </c:if>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
</div>
<script type="text/javascript">
function HJaddOrder(data){
	let prices=sj();
	//let st8=$("#standby8").val().substr(0,$("#standby8").val().length-1)+sj();
	
	let das=CountryEANDC($("#FCountry").html());
	$("#myModal_HJ").modal("show");
	$(".Foam-title").html("环金运单   海关申报信息");
	$("#hj_invoiceEnname").val("");
	$("#hj_invoiceCnname").val("");
	$("#hj_unitCode").val("pce");
	$("#hj_invoiceUnitcharge").val(prices);
	$("#hj_invoiceCurrencycode").val("USD");
	$("#hj_sku").val("B07"+generateMixed(7).toUpperCase());
	$("#hj_hsCode").val("1");
	$("#hj_isContainsBattery").removeAttr("checked");
	$("#hj_salePrice").val(prices);
	$("#hj_isAneroidMarkup").removeAttr("checked");
	$("#hj_saleCurrency").val("USD");
	$("#hj_isOnlyBattery").removeAttr("checked");
	$("#hj_categoryName").val("家具装饰");
	$("#hj_isBreakable").removeAttr("checked");
	$("#hj_parentEnName").val("Furniture decoration");
	$("#hj_isCharged").removeAttr("checked");
	$("#hj_invoiceQuantity").val("1");
	$("#hj_remark").val("");
	$("#hj_orderPieces").val("1");
	$("#hj_mailCargoType").val("2");
	$("#hj_consigneeCountryEname").val(das.b);
	$("#hj_consigneeCountryCname").val(das.a);
	$("#hj_orderWeight").val("");
	$("#hj_length").val("");
	$("#hj_width").val("");
	$("#hj_height").val("");
	$("#hj_CITY").val($("#FCity").val());
	$("#hj_province").val($("#FProvince").val());
	//默认物流
	if($("#st12").val()!=null&&$("#st12").val()!=""){
		$("#hj_shippingMethod").val($("#st12").val());
	}else{
		$("#hj_shippingMethod").val(0);
	}
	
	if($("#company").val()!=null && $("#company").val()!=""){
		$("#hj_COMPANY").val($("#company").val());
	}else{
		$("#hj_COMPANY").val("1");
	}
	$("#hj_isReturn").removeAttr("checked");

	$("#X_logistics").css("border-color","");
	$("#X_goods").css("border-color","");
	$("#X_express").css("border-color","");
	$("#modal-change-hj").html("新增");
	$("#modal-change-hj").attr("onclick","addx_HJ('0',123,"+data+")");
}
function sj() {
    //x上限，y下限     
    var x = 11;
    var y = 3;
    var rand = parseInt(Math.random() * (x - y + 1) + y);
	return rand;
}

/*
 * 保存修改,修改订单
 */
function addx_HJ(status,value,datase){
	 let datas={};
	 if($("#hj_consigneeCountryEname").val() =="" ||
		$("#hj_consigneeCountryCname").val() == "" ||
		$("#hj_invoiceEnname").val() == "" ||
		$("#hj_invoiceCnname").val() == "" ||
		$("#hj_CITY").val() == "" ||
		//$("#hj_province").val() == "" ||
		$("#hj_COMPANY").val() == "" ||
		$("#hj_unitCode").val() == "" ||
		$("#hj_invoiceUnitcharge").val() == "" ||
		$("#hj_invoiceCurrencycode").val() == "" ||
		$("#hj_sku").val() == "" ||
		$("#hj_invoiceQuantity").val() == "" ||
		$("#hj_hsCode").val() == "" ||
		$("#hj_salePrice").val() == "" ||
		$("#hj_saleCurrency").val() == "" ||
		$("#hj_categoryName").val() == "" ||
		$("#hj_parentEnName").val() == "" ||
		$("#hj_orderWeight").val() == "" ||
		$("#hj_length").val() == "" ||
		$("#hj_width").val() == "" ||
		$("#hj_height").val() == ""){
		 toastr.error("有值为空");
		 return false;
		 
	 }
	datas["hjReferenceno"]=$("#hjid").val();
	datas["hjShipperhawbcode"]=$("#Fid").html();
	datas["hjShippingmethod"]=$("#hj_shippingMethod").val();//联系客服 运输物流
	if($("#hj_shippingMethod").val().indexOf("cm")!= -1){
		datas["hjStandy6"]="cm";//根据客服添加配送方式
	}else if($("#hj_shippingMethod").val().indexOf("ghxb")!= -1){
		datas["hjStandy6"]="ghxb";//根据客服添加配送方式
	}else if($("#hj_shippingMethod").val().indexOf("kd")!= -1){
		datas["hjStandy6"]="kd";//根据客服添加配送方式
	}else if($("#hj_shippingMethod").val().indexOf("zx")!= -1){
		datas["hjStandy6"]="zx";//根据客服添加配送方式
	}
	datas["hjCountrycode"]=$("#FCountry").html();
	datas["hjShipzip"]=$("#FPostcode").html();
	if($("#hj_orderWeight").val().substr(0,1)=="0"){
		toastr.error("重量不能以0开头");
		return false;
	}
	datas["hjOrderweight"]=$("#hj_orderWeight").val();//订单重量
	datas["hjOrderpieces"]="1";//外包装件数
	datas["hjTotalprice"]=$("#hj_invoiceUnitcharge").val();
	datas["hjMailcargotype"]="2";
	datas["hjLength"]=$("#hj_length").val();
	datas["hjWidth"]=$("#hj_width").val();
	datas["hjHeight"]=$("#hj_height").val();
	datas["hjHeight"]=$("#hj_height").val();
	if($("#hj_isReturn").prop("checked")==true){
		datas["hjIsreturn"]="1";//退回
	}else{
		datas["hjIsreturn"]="0";
	}
	if($("#hj_isCharged").prop("checked")==true){
		datas["hjIsbattery"]="1";//带电
	}else{
		datas["hjIsbattery"]="0";
	}
	
	//收件人信息
	datas["hjConsigneecompany"]=$("#hj_COMPANY").val();
	datas["hjConsigneeprovince"]=$("#hj_province").val();
	datas["hjConsigneecity"]=$("#hj_CITY").val();
	datas["hjConsigneestreet"]=$("#FAddress").html();
	datas["hjConsigneestreet2"]=$("#FAddress2").html();
	datas["hjConsigneestreet3"]=$("#FAddress3").html();
	datas["hjConsigneestreet3"]=$("#FAddress3").html();
	datas["hjConsigneepostcode"]=$("#FPostcode").html();
	datas["hjConsigneename"]=$("#FName").html();
	datas["hjConsigneetelephone"]=$("#FTelephone").html();
	datas["hjConsigneemobile"]=$("#FTelephone").html();
	datas["hjConsigneeemail"]=$("#FEmail").html();
	datas["hjInvoiceenname"]=$("#hj_invoiceEnname").val();
	datas["hjInvoicecnname"]=$("#hj_invoiceCnname").val();
	datas["hjConsigneecountryename"]=$("#hj_consigneeCountryEname").val();
	datas["hjConsigneecountrycname"]=$("#hj_consigneeCountryCname").val();
	if($("#hj_orderWeight").val().substr(0,1)=="0"){
		toastr.error("重量不能以0开头");
		return false;
	}
	datas["hjInvoiceweight"]=$("#hj_orderWeight").val();
	datas["hjInvoicequantity"]=$("#hj_invoiceQuantity").val();
	datas["hjUnitcode"]=$("#hj_unitCode").val();
	datas["hjInvoiceunitcharge"]=$("#hj_invoiceUnitcharge").val();
	datas["hjInvoicecurrencycode"]=$("#hj_invoiceCurrencycode").val();
	datas["hjHscode"]=$("#hj_hsCode").val();
	datas["hjSku"]=$("#hj_sku").val();
	if($("#hj_isContainsBattery").prop("checked")==true){
		datas["hjIscontainsbattery"]="1";//是否包含电池
	}else{
		datas["hjIscontainsbattery"]="0";
	}
	if($("#hj_isAneroidMarkup").prop("checked")==true){
		datas["hjIsaneroidmarkup"]="1";//是否属于非液体化妆品
	}else{
		datas["hjIsaneroidmarkup"]="0";
	}
	if($("#hj_isOnlyBattery").prop("checked")==true){
		datas["hjIsonlybattery"]="1";//是否为纯电池
	}else{
		datas["hjIsonlybattery"]="0";
	}
	if($("#hj_isBreakable").prop("checked")==true){
		datas["hjIsbreakable"]="1";//是否易碎
	}else{
		datas["hjIsbreakable"]="0";
	}
	if($("#hj_isCharged").prop("checked")==true){
		datas["hjIscharged"]="1";//是否带电
	}else{
		datas["hjIscharged"]="0";
	}
	datas["hjSaleprice"]=$("#hj_salePrice").val();
	datas["hjSalecurrency"]=$("#hj_saleCurrency").val();
	datas["hjCategoryname"]=$("#hj_categoryName").val();
	datas["hjParentenname"]=$("#hj_parentEnName").val();
	datas["hjStandy10"]=datase;
	/* if($("#X_logistics").val()==null||$("#X_logistics").val()==""){
		$("#X_logistics").css("border-color","red");
		return false;
	} */
	console.log(datas)
	if(status==1){
		datas["id"]=value;
		//查看运单
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :'${pageContext.request.contextPath}/Logistics/editHJLogistics?${_csrf.parameterName}=${_csrf.token}',
			data:JSON.stringify(datas),
			dataType : 'json',
			success : function(data) {
				//$("#logis").html("");addHtml();selHtml();
				toastr.success("修改成功");
				$("#myModal_HJ").modal("hide");
				vm.selectlist()
			},
			error : function(json) {
				toastr.error("修改失败");
			}
		});  
	}else{
	//保存修改
	$.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :'${pageContext.request.contextPath}/Logistics/addHJLogistics?${_csrf.parameterName}=${_csrf.token}',
		data:JSON.stringify(datas),
		dataType : 'json',
		success : function(data) {
			//$("#logis").html("");addHtml();selHtml();
			$("#myModal_HJ").modal("hide");
			vm.selectlist()
			toastr.success("添加成功");
		},
		error : function(json) {
			toastr.error("添加失败");
		}
	}); 
	}
}

//查询普通运单
function selHtml(){
	let datas={};
	datas["xFIds"]=$("#Fid").html();
	//保存修改
	$.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :'${pageContext.request.contextPath}/Logistics/selLogi?${_csrf.parameterName}=${_csrf.token}',
		data:JSON.stringify(datas),
		dataType : 'json',
		success : function(data) {
			for(let a=0;a<data.length;a++){
				let c,b,d="";
				if(data[a].xFrieght!=""){
					c=data[a].xFrieght;
				}
				if(data[a].xStandby1==1){
					d="checked";
				}
				$("#logis").append('<div class="F_setting col-lg-12">'
				+'<div class="F_logistics_body2">'
				+'	<div class="d-flex flex-column">' 
				+'		<div class="F_logistics_item1">'  
				+'   		<div class="d-flex flex-row">' 
				+'   				<div class="F_logistics_item2" style="width:100%">'
				+'   					<input type="radio" '+d+'   value="'+data[a].id+'" name="Hjradio" onclick="radioslo(1)">运单号：<nobr class="F_logistics_item4" style="font-weight:900;margin-right:25px">'+data[a].xExpress+'</nobr>'
				+'   					<nobr style="float:right;margin-right:150px">'
				+'   						<a onclick="selectOrder('+data[a].id+')">查看</a> |'
				+'   						<a onclick="geteveryone()">打印</a> |'
				+'   						<a onclick="deleteX('+data[a].id+','+data[a].xFIds+')">删除</a>'
				+'   					</nobr>'
				+'   				</div>   	'
				+'   				<div class="F_logistics_item2">'
				+'   					<nobr>物流</nobr>'
				+'   					<nobr style="margin-left:10px">发货:'+timeStamp2String(data[a].xDataTime)+'</nobr>'
				/* +'   						<nobr>运费：￥'+c+'</nobr>' */
				+'   				</div>'
				+'   	   	</div>  '
				+' 	  	</div>   '
				+'</div>'
				+'</div>'
				+'</div>')
				}
			$("#myModal_YT").modal("hide");
		},
		error : function(json) {
			toastr.error("查询失败");
		}
	});  
}
var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];

function generateMixed(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;

}
function addHtml(){
	let datas={};
	datas["hjShipperhawbcode"]=$("#Fid").html();
	datas["hjStandy10"]=$("#useid").val();
	$.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :'${pageContext.request.contextPath}/Logistics/seleHJALL?${_csrf.parameterName}=${_csrf.token}',
		data:JSON.stringify(datas),
		dataType : 'json',
		success : function(data) {
		
				for(let a=0;a<data.length;a++){
					let c,b,d="";
					if(data[a].hjInvoicecnname!=""){
						c=data[a].hjInvoicecnname;
					}
					/* if(data[a].hjShippingmethod!=""&&data[a].hjShippingmethod!=null){
						b=data[a].hjShippingmethod;
					} */
					if(data[a].hjStandy2==1){
						d="checked";
					}
					let stand5="'"+data[a].hjStandy5+"','"+data[a].hjShippingmethod+"'";
					$("#logis").append('<div class="F_setting col-lg-12">'
							+'<div class="F_logistics_body2">'
							+'	<div class="d-flex flex-column">' 
							+'		<div class="F_logistics_item1">'  
							+'   		<div class="d-flex flex-row">' 
							+'   				<div class="F_logistics_item2" style="width:100%">'
							+'   					<input type="radio" '+d+'  name="Hjradio" value="'+data[a].id+'" onclick="radioslo(0)">运单号：<nobr class="F_logistics_item4" style="font-weight:900;margin-right:25px">'+data[a].hjShipperhawbcode+'</nobr>'
							+'   					<nobr style="float:right;margin-right:150px">'
							+'   						<a onclick="selectOrder_HJ('+data[a].id+')">查看</a> |'
							+'   						<a onclick="geteveryone('+stand5+')">打印</a> |'//javascript:doPrint()
							+'   						<a onclick="delete_HJ('+data[a].id+','+data[a].hjShipperhawbcode+')">删除</a>'
							+'   					</nobr>'
							+'   				</div>   	'
							+'   				<div class="F_logistics_item2">'
							+'   					<nobr>物流：'+zhwl(data[a].hjShippingmethod)+'</nobr><nobr>跟踪：'+data[a].hjStandy7+'</nobr>'
							+'   					<nobr style="margin-left:10px">发货:'+timeStamp2String(data[a].hjStandy1)+'</nobr>'
							/* +'   						<nobr>商品名：'+c+'</nobr>' */
							+'   				</div>'
							+'   	   	</div>  '
							+' 	  	</div>   '
							+'</div>'
							+'</div>'
							+'</div>')
					}
				$("#myModal_HJ").modal("hide");
		},
		error : function(json) {
			toastr.error("查询失败");
		}
	});  
}




</script>