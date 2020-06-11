<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="searchCates">
<!-- 模态框（Modal） -->
        <div class="modal fade" style="overflow:hidden" id="myModal_YT"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <nobr class="Foam-title" id="myModalLabelp">
                        
                        </nobr>
                    </div>
                    <div class="modal-body" style="height:300px;display:block">
                      <div  class="form-group F_modal_ls col-lg-6">物流:<input id="X_logistics" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">英文:<input id="X_english" class="F_modal_input" type="text" onKeyUp="value=value.replace(/[\W]/g,'')"></div>
                      <div  class="form-group F_modal_ls col-lg-6">中文:<input id="X_china" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">面单:<input id="X_express" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">海关:<input id="X_customs" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">追踪:<input id="X_trace" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">发货:<input id="X_goods" class="F_modal_input" style="height:28px" type="date"></div>
                      <div  class="form-group F_modal_ls col-lg-6">申报:<input id="X_declare" class="F_modal_input" type="number"></div>
                      <div  class="form-group F_modal_ls col-lg-6">保险:<input id="X_insurance" class="F_modal_input" type="number"></div>
                      <div  class="form-group F_modal_ls col-lg-6">总重:<input id="X_all_up" class="F_modal_input" type="number"></div>
                      <div  class="form-group F_modal_ls col-lg-6">运费:<input id="X_freight" class="F_modal_input" type="number"></div>
                      <div  class="form-group F_modal_ls col-lg-6">数量:<input id="X_quantity" class="F_modal_input" type="text"></div>
                      <div  class="form-group F_modal_ls col-lg-6">备注:<input id="X_remark" class="F_modal_input" style="width:450px" type="text"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" id="modal-changes" class="btn btn-primary">
                            	
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
</div>
<script type="text/javascript">
function FaddOrder(){
	$("#myModal_YT").modal("show");
	$(".Foam-title").html("添加新运单");
	$("#X_logistics").val("");
	$("#X_english").val("");
	$("#X_china").val("");
	$("#X_express").val("");
	$("#X_customs").val("");
	$("#X_trace").val("");
	$("#X_goods").val("");
	$("#X_declare").val("");
	$("#X_insurance").val("");
	$("#X_all_up").val("");
	$("#X_freight").val("");
	$("#X_remark").val("");
	$("#X_quantity").val("");

	$("#X_logistics").css("border-color","");
	$("#X_goods").css("border-color","");
	$("#X_express").css("border-color","");
	$("#modal-changes").html("新增");
	$("#modal-changes").attr("onclick","addx('0',123)");
}
/*
 * 保存修改,修改订单
 */
function addx(status,value){
	 let datas={};
	datas["xLogistics"]="手工输入";
	datas["xCustom"]=$("#X_logistics").val();
	datas["xEnglish"]=$("#X_english").val();
	datas["xChina"]=$("#X_china").val();
	datas["xExpress"]=$("#X_express").val();
	datas["xCustoms"]=$("#X_customs").val();
	datas["xTrace"]=$("#X_trace").val();
	datas["xDataTime"]=$("#X_goods").val();
	datas["xDeclare"]=$("#X_declare").val();
	datas["xInsurance"]=$("#X_insurance").val();
	datas["xWeight"]=$("#X_all_up").val();
	datas["xFrieght"]=$("#X_freight").val();
	datas["xRemark"]=$("#X_remark").val();
	datas["xQuantity"]=$("#X_quantity").val();
	datas["xFIds"]=$("#Fid").html();
	if($("#X_logistics").val()==null||$("#X_logistics").val()==""){
		$("#X_logistics").css("border-color","red");
		return false;
	}
	if($("#X_express").val()==null||$("#X_express").val()==""){
		$("#X_express").css("border-color","red");
		return false;
	}
	if($("#X_goods").val()==null||$("#X_goods").val()==""){
		$("#X_goods").css("border-color","red");
		return false;
	}
	if(status==1){
		datas["id"]=value;
		//查看运单
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :'${pageContext.request.contextPath}/Logistics/editLogisticsQuantity?${_csrf.parameterName}=${_csrf.token}',
			data:JSON.stringify(datas),
			dataType : 'json',
			success : function(data) {
				toastr.success("修改成功");
				$("#logis").html("");addHtml();selHtml();
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
		url :'${pageContext.request.contextPath}/Logistics/addLogisticsQuantity?${_csrf.parameterName}=${_csrf.token}',
		data:JSON.stringify(datas),
		dataType : 'json',
		success : function(data) {
			$("#logis").html("");addHtml();selHtml();
			toastr.success("添加成功");
			$("#myModal_YT").modal("hide");
		},
		error : function(json) {
			toastr.error("添加失败");
		}
	}); 
	}
}
//查看运单
function selectOrder(value){

 	let datas={};
	datas["id"]=value;
	//查看运单
	$.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :'${pageContext.request.contextPath}/Logistics/selLogisticsQuantity?${_csrf.parameterName}=${_csrf.token}',
		data:JSON.stringify(datas),
		dataType : 'json',
		success : function(data) {
			console.log(timeStamp2String2(data.xDataTime))
			FaddOrder();
			$("#X_logistics").val(data.xCustom);
			$("#X_english").val(data.xEnglish);
			$("#X_china").val(data.xChina);
			$("#X_express").val(data.xExpress);
			$("#X_customs").val(data.xCustoms);
			$("#X_trace").val(data.xTrace);
			$("#X_goods").val(timeStamp2String2(data.xDataTime));
			$("#X_declare").val(data.xDeclare);
			$("#X_insurance").val(data.xInsurance);
			$("#X_all_up").val(data.xWeight);
			$("#X_freight").val(data.xFrieght);
			$("#X_remark").val(data.xRemark);
			$("#X_quantity").val(data.xQuantity);
			$("#modal-changes").html("修改");
			$("#modal-changes").attr("onclick","addx('1',"+data.id+")");
		},
		error : function(json) {
			toastr.error("查找失败");
		}
	});  
}
/*
 * 时间格式化
 */
function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "年" + month + "月" + date+"日";
}
/*
 * 时间格式化2
 */
function timeStamp2String2(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date;
}
 
</script>