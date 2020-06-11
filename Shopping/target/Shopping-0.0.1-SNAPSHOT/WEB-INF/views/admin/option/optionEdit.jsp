<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   

<%@page session="true"%>
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
</head>
<body class="blank">
<jsp:include page="../main/top.jsp" />
<jsp:include page="../main/left.jsp" />
<jsp:include page="../commonJs.jsp" />
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-validation/jquery.validate.js"></script>
<div id="wrapper">
	<div class="small-header transition animated fadeIn">
		<div class="content animate-panel">
			<div class="hpanel">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8">
							<div class="hpanel">
								<div id="error_div" class="text-left"></div>
								<form id="form_option" name="form_option" action='' method="POST">
									<input type="hidden" id="id" name="id" value="${option.id}"/>	
									<div class="form-group col-lg-12">
										<label class="col-sm-3 control-label">属性名称</label>
										<div class="col-sm-9">
											<input type="text" id="name" name="name" class="form-control" maxlength="50" placeholder="属性名称" value='${option.name}' />
											 <input type="text" style="display:none;">  
										</div>
									</div>
									<!--<c:forEach items="${option.name}" var="item" varStatus="status">
				
									</c:forEach>-->
									<div id="opionValueDiv">									
										<c:forEach items="${option.optionValue}" var="item" varStatus="status">
										    <div class="form-group col-lg-12"><label class="col-sm-3 control-label">属性样式</label>
										    <div class="col-sm-6"><input type="text" name="optionValue" class="form-control" maxlength="50" placeholder="option Value" value="${item.name}"/>
										    </div>
										    <input type="hidden"  value="${item.id}">
										    <button class="btn btn-success" style="margin-right: 5px;" type="button" onclick="deleteOptionValue(this)"><strong>删除</strong></button></div>
										</c:forEach>
									</div>
									
<!-- 									<div class="form-group col-lg-12"> -->
<!-- 										<label class="col-sm-3 control-label">option类型</label> -->
<%-- 										<input type="hidden" id="typeTemp" name="typeTemp" value='${option.type}' /> --%>
<!-- 										<div class="col-sm-9"> -->
<!-- 											<input type="radio" id="type1" name="type" value="0" /> <i></i>select -->
<!-- 											<input type="radio" id="type2" name="type" value="1" /> <i></i> radio -->
<!-- 										</div> -->
<!-- 									</div> -->
									<div class="text-right m-t-xs">
										<button id="addOptionValueButton" class="btn btn-success" style="margin-right: 5px;" type="button">
											<strong>添加样式</strong>
										</button>
										<button id="save" class="btn btn-success" style="margin-right: 5px;" type="button">
											<strong>保存</strong>
										</button>
										<button id="back" class="btn btn-success" type="button">
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
</div>
<script type="text/javascript">
$('#leftMenuProduct').addClass('active');
	var oTable;
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	var contextPath = '${pageContext.request.contextPath}';
	var validate;
	$(function() {
// 		if($("#typeTemp").val() == 0){
// 			$("#type1").attr("checked","checked");
// 		}else if($("#typeTemp").val() == 1){
// 			$("#type2").attr("checked","checked");
// 		}
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		validate = $("#form_option").validate({
			onsubmit:true,
			focusInvalid:false,
			errorClass:"error",
			onfocusout:false,
			onkeyup:false,
			onclick:false,
			ignoreTitle:true,
	        rules:{
	        	name:{
                	required:true,
                	maxlength : 20,
                	remote:{
                      	   url:contextPath+"/admin/optionName?${_csrf.parameterName}=${_csrf.token}",
                           type:"post",
                           dataType:"json",
                           async: false,
                           data:{
                          	 name:function (){return $("#name").val()},
                      	    id:function (){return $("#id").val()} 
                        }
                    }
                    
                },
                optionValue:{
                	required:true,
        			maxlength : 20
        		}
		
// 		,
//                 type:{
//                   	required:true
//                 }
            },
            messages:{
            	name:{
                    required:"属性名称必填",
                    maxlength : "最大位数不能超过20位",
                    remote : "属性名重复"
                },
            optionValue:{
            	required:"属性样式必填",
    			maxlength : "最大位数不能超过20位"
    		}
//             ,
//                 type:{
//                     required:"option类型必选",
//                 }                                   
            }
        });    
	});
	
	$("#addOptionValueButton").click(function() {
		var optionValueInput = '<div class="form-group col-lg-12"><label class="col-sm-3 control-label">属性样式</label>';
			optionValueInput+='<div class="col-sm-6"><input type="text" name="optionValue" class="form-control" maxlength="50" placeholder="属性样式"/></div>';
			optionValueInput+='<input type="hidden"><button class="btn btn-success" style="margin-right: 5px;" type="button" onclick="deleteOptionValue(this)"><strong>删除</strong></button></div>';
		$("#opionValueDiv").append(optionValueInput);
	})
	function deleteOptionValue(item){
		
 		if($(item).prev().val()!=""&&$(item).prev().val()!=undefined){
			swal({
				title:"原有属性样式不能删除",
				type:"error"
			});
		}else{
			$(item).parent().remove();
		} 
		
	
	}
	$("#back").bind("click",function(){
		window.location.href =contextPath+'/admin/optionInfo';
	});
	
	$("#save").bind("click",function(){
		swal({
	        title: "确认要保存？",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "是",
	        cancelButtonText: "否",
	        closeOnConfirm: false,
	        closeOnCancel: true },
	    function (isConfirm) {
	        if (isConfirm) {
	        	if (!$("#form_option").valid()) {
	    			return;
	    		}
	    		var option={};
	    		option.optionValue=new Array();
	    		option.name=$("#name").val();
	    		option.id=$("#id").val();
	    	    option.type=0;
	    		$("#opionValueDiv input[name='optionValue']").each(function(index,v){
	    			var optionValue={};
	    			optionValue.id=$(v).parent().next().val();
	    				/* $("#opionValueDiv input[name='"+index+"']").val(); */
	    			optionValue.name=v.value;
	    			optionValue.optionId=$("#id").val();
	    			option.optionValue.push(optionValue);
	    		});
	    		
	    		$.ajax({
	    			type : "POST",
	    			contentType : "application/json;charset=utf-8", 
	    			url : contextPath + '/admin/saveOption',
	    			data : JSON.stringify(option),
	    			dataType : "json", 
	    			async : false,
	    			success : function(json) {
	    				swal({
	    				    title: "保存成功!",
	    				    text: "",
	    				    type: "success"
	    				});
	    				$("#id").val(json.data.id);
	    				$("#opionValueDiv").empty();
	    				for(i in json.data.optionValue){
	    					var optionValueInput = '<div class="form-group col-lg-12"><label class="col-sm-3 control-label">属性样式</label>';
	    					optionValueInput+='<div class="col-sm-6"><input type="text" name="optionValue" class="form-control" maxlength="50" placeholder="属性样式" value="'+json.data.optionValue[i].name+'"/></div>';
	    					optionValueInput+='<input type="hidden" value="'+json.data.optionValue[i].id +'"><button class="btn btn-success" style="margin-right: 5px;" type="button" onclick="deleteOptionValue(this)"><strong>删除</strong></button></div>';
	    				    $("#opionValueDiv").append(optionValueInput);
	    				};
	    			},
	    			error : function(json) {
	    				existFlag = true;
	    			}
	    		});
	    		
	    	
	        } else {
	       	 return false;
	        }
	    });	
		});
	function formToJson($form) {
		var data = {};
		$($form.serializeArray()).each(function(i, v) {
			data[v.name] = v.value;
		});
		return JSON.stringify(data);
	}
	
	
</script>
</body>
</html>