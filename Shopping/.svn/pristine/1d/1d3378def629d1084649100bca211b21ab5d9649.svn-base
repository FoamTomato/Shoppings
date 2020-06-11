<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>商城后台</title>
	<jsp:include page="../commonCss.jsp"/> 
</head>
<body class="blank">
<jsp:include page="../main/top.jsp"/> 
<jsp:include page="../main/left.jsp"/>
<jsp:include page="../commonJs.jsp"/> 
<div id="wrapper">
	<div class="small-header transition animated fadeIn">
		<div class="content animate-panel">
		    <div class="hpanel">
		        <div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="hpanel">
								<div id="error_div" class="text-left"></div>
									<form id="form_option" name="form_option" action="" method="POST">
									<div class="form-group col-lg-6">
											<label>属性编号</label>
												<input type="text" id="id" name="id"
													class="form-control" maxlength="50" placeholder="属性编号"/>	
										
										</div>
										<div class="form-group col-lg-6">
											<label>属性名称</label>
												<input type="text" id="name" name="name"
													class="form-control" maxlength="50" placeholder="属性名称"/>
										</div>
										<div class="text-right m-t-xs">
											<button id="search" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" type="button">
												<strong>查询</strong>
											</button>
											<button id="reset" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" style="margin-right: 20px;" type="reset">
												<strong>重置</strong>
											</button>
											<button id="add" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" type="button">
												<strong>添加</strong>
											</button>
											<button id="delBtn" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" type="button">
												<strong>删除</strong>
											</button>
									</form>
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
					<div class="panel-body">
						<table id="optionList" style="width: 100%;" class="table table-striped table-bordered table-hover"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<form id="forwordform" action='<c:url value="/admin/editOption?${_csrf.parameterName}=${_csrf.token}" />' method='POST'>
	<input type="hidden" name="ids" id="ids"/>
</form>
<script type="text/javascript">
$('#leftMenuProduct').addClass('active');
var oTable;
var header = $("meta[name='_csrf_header']").attr("content");  
var token = $("meta[name='_csrf']").attr("content"); 
var contextPath = '${pageContext.request.contextPath}';
$(function() {
	$('.date').datepicker();
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	optionListData();
});

$('#add').click(function() {
	window.location.href =contextPath+'/admin/addOption?${_csrf.parameterName}=${_csrf.token}';
});

$('#search').click(function() {
	optionListData();
});

$('#delBtn').click(function() {
         swal({
                     title: "确认要删除？",
                     type: "warning",
                     showCancelButton: true,
                     confirmButtonColor: "#DD6B55",
                     confirmButtonText: "是",
                     cancelButtonText: "否",
                     closeOnConfirm: false,
                     closeOnCancel: true },
                 function (isConfirm) {
                     if (isConfirm) {
                    	 var ids = document.getElementsByName("ids");
                    		var strIds = new Array();
                    		var idTemp = "";
                    		for(var i=0;i<ids.length;i++){
                    			if(ids[i].checked){
                    				strIds[strIds.length]=ids[i].value;
                    				idTemp += ids[i].value + "-";
                    			}
                    		}
                    		if(strIds.length == 0){
                    			swal({
                				    title: "请选择操作对象！",
                				    text: "",
                				    type: "error"
                				});
                    			return false;
                    		}
                    		deleteOption(idTemp);
                     } else {
                    	 return false;
                     }
                 });
	
});

function checkedAll(){
	if($("#checkedAll").is(':checked')==true){
		//全选
		$("input[name='ids']:checkbox").prop('checked',true);
	}else{
		//取消全选
		$("input[name='ids']").removeAttr("checked");
	}
}

function delOption(id){
	swal({
        title: "确认要删除？",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: false,
        closeOnCancel: true },
    function (isConfirm) {
        if (isConfirm) {
        	deleteOption(id + '-');
        } else {
       	 return false;
        }
    });	
}

function deleteOption(idTemp){
	$.ajax({
		type : "POST",
		contentType : "application/json;charset=utf-8", 
		url : contextPath + '/admin/removeOption',
		data : idTemp,
		dataType : "json", 
		async : false,
		success : function(json) {
			if(json.error == '0'){
				optionListData();
				swal({
				    title: "删除成功!",
				    text: "",
				    type: "success"
				});
			}else{
				swal({
				    title: "删除失败!",
				    text: "",
				    type: "error"
				});
			}
		},
		error : function(json) {
			existFlag = true;
		}
	});
}

function optionListData() {
	if (typeof oTable == 'undefined') {
		// dataTable load
		oTable = $('#optionList')
				.dataTable(
						{
							'bPaginate' : true, // 翻页功能
							'sServerMethod' : 'POST', // 提交方式
							'bServerSide' : true,
							'bProcessing' : true,
							'bFilter' : false,
							"bSort" : false,
							'sAjaxSource' : contextPath + '/admin/optionDataTable',
							"fnServerParams" : function(aoData) { // 查询条件
								aoData.push({
									"name" : "id",
									"value" : $('[name=id]').val().trim()
								},{
									"name" : "name",
									"value" : $('[name=name]').val().trim()
								});
							},aoColumns : [{
										'mData' : 'id',
										'sTitle' : '<input type="checkbox" id="checkedAll" name="checkedAll" onclick="checkedAll()">',
										'sWidth' : '5%',
										'mRender' : function(data){
					                        var sRendor = '<input type="checkbox" name="ids" value="'+ data +'">';
					                        return sRendor;
					                    }
									},{
										'sTitle' : '属性编号',
										'sWidth' : '30%',
										'mData' : 'id'
									},{
										'sTitle' : '属性名称',
										'sWidth' : '40%',
										'mData' : 'name'
									},
// 									{
// 										'sTitle' : 'option类型',
// 										'sWidth' : '15%',
// 										'mData' : 'type',
// 										'mRender' : function(data){
// 						                     var sRendor = '';
// 						                     if(data == 0){
// 						                    	 sRendor = 'select';
// 						                     }else if(data == 1){
// 						                    	 sRendor = 'radio';
// 						                     }
// 						                     return sRendor;
// 						                }
// 									},
									{
										'sTitle' : '操作',
										'mData' : 'id',
										'sWidth' : '10%',
										'mRender' : function(data) {
											var returnValue = '<button id="update" class="btn btn-default btn-sm" onclick="editOptionr('+data+')"><i class="fa fa-pencil"></i>编辑</button>'
											+ '<button id="update" class="btn btn-default btn-sm" onclick="delOption('+data+')"><i class="fa fa-pencil"></i>删除</button>';
											return returnValue;
										}
									}] 

						});
	} else {
		// oTable.fnClearTable(0);
		var oSettings = oTable.fnSettings();
		oSettings._iDisplayLength = parseInt($(
				'[name=optionList_length] option:selected').val());
		$('.dataTables_length select').val(
				$('[name=optionList_length] option:selected').val());
		oSettings._iDisplayStart = 0;
		oTable.fnDraw();
	}
}
function editOptionr(id){
	$("#ids").val(id);
	$("#forwordform").submit();
}
</script>
</body>
</html>