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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/demo.css" />

	<style> 
.flex-container {
    display: -webkit-flex;
    display: flex;
    width: 100%;
}

.flex-item {
    /* background-color: cornflowerblue; */
    width: 16%;
    height: 650px;
    margin: 5px;
    border:1px solid #ccc;
	border-radius: 5px;
}
ul li{
	list-style-type:none;
}
.tab-content{
overflow: hidden;
}


</style>
</head>
<jsp:include page="../commonJs.jsp"/> 
<body class="blank" style="margin: 0px; padding: 0px;overflow: hidden;">
<jsp:include page="../main/top.jsp"/> 
<jsp:include page="../main/left.jsp"/>
<jsp:include page="modalCate.jsp"/> 
<div id="wrapper">
	<div class="small-header transition">
		<div class="content" style="padding-bottom:0px">
		    <div class="hpanel">
		        <div class="panel-body">
							<div class="hpanel">
								<div id="error_div" class="text-left"></div>
									<!-- 模态框（Modal） -->
							        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="overflow:hidden">
							            <div class="modal-dialog">
							                <div class="modal-content">
							                    <div class="modal-header">
							                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							                            &times;
							                        </button>
							                        <h4 class="modal-title" id="myModalLabel">
							                            	查询
							                        </h4>
							                    </div>
							                    <div class="modal-body" style="overflow: auto;height: 600px;">
							                        <div id="search_result"></div>
							                    </div>
							                   <!--  <div class="modal-footer">
							                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
							                        </button>
							                        <button type="button" class="btn btn-primary">
							                            提交更改
							                        </button>
							                    </div> -->
							                </div><!-- /.modal-content -->
							            </div><!-- /.modal -->
							        </div>
									<!-- <form id="form_productCategory" name="form_productCategory" action="" method="POST"> -->
										<div class="form-group col-lg-6" style="margin-bottom:0px">
												<input type="text"  id="name" name="nameFF" class="form-control" maxlength="50" placeholder="请输入需要查询的分类" />	
										</div>
										<div class="m-t-xs col-lg-6">
										<!--  data-toggle="modal" data-target="#myModal" -->
											<button id="search" onclick="searchSelec()" class="btn btn-success" style="margin-right: 5px;margin-top: -5px" type="button">
												<strong>查询</strong>
											</button>
											<!-- 查询所有 -->
											<button id="reset" class="btn btn-success" style="margin-right: 5px;margin-top: -5px" style="margin-right: 20px;" type="reset">
												<strong>重置</strong>
											</button>
											<button id="delBtn" class="btn btn-danger" style="margin-right: 5px;margin-top: -5px;" type="button">
												<strong>删除</strong>
											</button>
											<button id="add" class="btn btn-success" style="margin-right: 5px;margin-top: -5px" type="button">
												<strong>添加</strong>
											</button>
											<button onclick="mobile()" class="btn btn-success" style="margin-right: 5px;margin-top: -5px" type="button">
												<strong>移动</strong>
											</button>
										</div>
									<!-- </form> -->
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		<div class="content">	
		<div class="row">
			<div class="col-lg-12">
				<div class="hpanel">
					<div class="panel-body">
						<div class="flex-container FoamSelect"><!--  ondrop="drop(event)" ondragover="allowDrop(event)"  -->
						  <div class="flex-item droptarget tab-content" id="cate1"  style="overflow: auto;">
						  <c:forEach items="${cate1s }" var="cate1" varStatus="st">
						  	<div href="#dragtarget1${st.index}cate1" role="tab" style="display:block"  data-toggle="tab" ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget1" onclick="addCate('cate1','${cate1 }',event,'cate1')" id="dragtarget1${st.index}">${cate1 }</div>
						  </c:forEach>
						  </div>
						  <div id="cate2" class="tab-content flex-item droptarget"  style="overflow: auto;width:16%">
								  
						  </div>
						  <div id="cate3" class="tab-content flex-item droptarget"  style="overflow: auto;width:16%">
								  
						  </div>
						  <div id="cate4" class="tab-content flex-item droptarget" style="overflow: auto;width:16%">
								  
						  </div>
						  <div id="cate5" class="tab-content flex-item droptarget" style="overflow: auto;width:16%">
								  
						  </div>
						  <div id="cate6" class="tab-content flex-item droptarget" style="overflow: auto;width:16%;">
								  
						  </div>
						  </div>  
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	
	<!--模态框组件-->
<div class="modal fade" id="myModals">
    <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                    <h3>添加</h3>
                    <div style="font-size:16px">用"<nobr style="color:red">/</nobr>"隔开可以批量添加,如果有重复的分类名默认合并</div>
                </div>
                <div class="modal-body" style="font-size:20px">
                	
                    <nobr>请输入添加的分类名</nobr>
                    <input type="text" id="CateName">
                </div>
                <div class="modal-footer">
                    <button class="btn btn-info"  onclick="addCateName()">添加</button>
                </div>
        </div>
    </div>
</div>
<script>
function dragStart(event) {
    event.dataTransfer.setData("Text", event.target.id);
    //document.getElementById("demo").innerHTML = "开始拖动 p 元素";
}
function Fcate2(){
	$("#myTabContent3").show();
}
function Fcate1(){
	$("#myTabContent3").hide();
}
function Fcate3(){
}

/*
 * 获取json长度
 */
function getJsonLength(jsonData){
    var jsonLength = 0;
    for(var item in jsonData){
       jsonLength++;
    }
    return jsonLength;
}
/*
 * 数组去重
 
 */
 function unique (arr) {
	  return Array.from(new Set(arr))
}
/*
 * 添加分类名
 */
function addCateName(){
	let p=$(".dragtarget")
	let foam=new Array();
	let datas={};
	//获取每个被选中变红的分类id
	for(let a=0;a<p.length;a++){
		if(p[a].style.background=="rgb(231, 76, 60)"){
			foam.push(p[a].id)
		}
	} 
	//拿到的id变为值$("#"+foam[foam.length-1]).html()
	for(let b=0;b<foam.length;b++){
		if(foam[b].includes(6)==false){
			datas["cate"+(b+1)]=$("#"+foam[b]).html();
		}else if(foam[b].includes(6)==true){
			/*
			还原输入框
			*/
			$("#CateName").css("border","");
			$("#CateName").attr("placeholder","");
			$("#CateName").val("");
			$('#myModals').modal("hide");
			$("body").css('padding-right','0px');
			swal("添加失败", "错误原因:只最多只支持六级分类,请重新选择", "error");
			return false;
		}
	}
	//添加输入框的值到数组
	//datas.push($("#CateName").val())
	if($("#CateName").val()==""){
		/*
		如果输入框为空，则添加红框，并给予提示
		*/
		$("#CateName").css("border","2px solid red");
		$("#CateName").attr("placeholder","请输入分类名");
		return false;
	}
	
	let resfult=unique($("#CateName").val().split("/"));

	//console.log(unique(resfult))
	if(resfult.length==1){
		//清空所有颜色
		let repetition=$(".dragtarget"+(getJsonLength(datas)+1));
		repetition.css("background","#6495ed");
		datas["cate"+(getJsonLength(datas)+1)]=resfult[0];
		//获取输入框的值，并传入后台
		 $.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :'${pageContext.request.contextPath}/admin/Foam_AddCate_Name?${_csrf.parameterName}=${_csrf.token}',
			data:JSON.stringify(datas),
			async:false,
			dataType : 'json',
			success : function(data) {
				if(data == 0){
					swal("添加失败", "错误原因:"+data, "error");
					return false;
				}
				let cate="'cate"+getJsonLength(datas)+"'";
				let resulte="'"+$("#CateName").val()+"'";
				let d1=0;
				for(let i=0;i<repetition.length;i++){
					if($("#"+repetition[i].id).html()==resfult[0]){
						d1++;
						$("#"+repetition[i].id).css("background","#2e8b57");
					}
				}
				//如果等于0则没有重复，大于0则重复
				if(d1==0){
				$("#cate"+getJsonLength(datas)).append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" style="background: rgb(231, 76, 60);" class="dragtarget dragtarget'+getJsonLength(datas)+'" id="dragtarget'+getJsonLength(datas)+Math.ceil(Math.random()*1000)+'" onclick="addCate('+cate+','+resulte+',event,'+cate+');dragtarget'+getJsonLength(datas)+'(event)">'+$("#CateName").val()+'</div>')
				}
				/*
				还原输入框
				*/
				$("#CateName").css("border","");
				$("#CateName").attr("placeholder","");
				$("#CateName").val("");
				$('#myModals').modal("hide");
				$("body").css('padding-right','0px');
				
				swal("添加成功", "", "success");
			},
			error : function(json) {
				/*
				还原输入框
				*/
				$("#CateName").css("border","");
				$("#CateName").attr("placeholder","");
				$("#CateName").val("");
				$('#myModals').modal("hide");
				$("body").css('padding-right','0px');
				swal("添加失败", "错误原因:"+json, "error");
			}
		}); 
	}else{
	//给批量添加的cate赋值
	let fcatee="cate"+(getJsonLength(datas)+1);
	//清空所有颜色
	let repetition=$(".dragtarget"+(getJsonLength(datas)+1));
	repetition.css("background","#6495ed");
	for(let F_A=0;F_A<resfult.length;F_A++){
		//给datas赋需要添加的值
		datas[fcatee]=resfult[F_A];
		if(resfult[F_A]==null||resfult[F_A]==""){
			continue;
		}
		//获取输入框的值，并传入后台
	 $.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :'${pageContext.request.contextPath}/admin/Foam_AddCate_Name?${_csrf.parameterName}=${_csrf.token}',
		data:JSON.stringify(datas),
		async:false,
		dataType : 'json',
		success : function(data) {
			if(data == 0){
				swal("添加失败", "错误原因:"+data, "error");
				return false;
			}
			//获取需要添加的位置
			let cate="'cate"+getJsonLength(datas)+"'";
			let resulte="'"+resfult[F_A]+"'";
			//判断是否和原有数据重复
			let d1=0;
			for(let i=0;i<repetition.length;i++){
				if($("#"+repetition[i].id).html()==resfult[F_A]){
					d1++;
					$("#"+repetition[i].id).css("background","#2e8b57");
				}
			}
			//如果等于0则没有重复，大于0则重复
			if(d1==0){
				$("#cate"+getJsonLength(datas)).append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" style="background: #2e8b57;" class="dragtarget dragtarget'+getJsonLength(datas)+'" id="dragtarget'+getJsonLength(datas)+Math.ceil(Math.random()*1000)+'" onclick="addCate('+cate+','+resulte+',event,'+cate+');dragtarget'+getJsonLength(datas)+'(event)">'+resfult[F_A]+'</div>')
			}
			/*
			还原输入框
			*/
			$("#CateName").css("border","");
			$("#CateName").attr("placeholder","");
			$("#CateName").val("");
			$('#myModals').modal("hide");
			$("body").css('padding-right','0px');
			
			swal("添加成功", "", "success");
		},
		error : function(json) {
			/*
			还原输入框
			*/
			$("#CateName").css("border","");
			$("#CateName").attr("placeholder","");
			$("#CateName").val("");
			$('#myModals').modal("hide");
			$("body").css('padding-right','0px');
			swal("添加失败", "错误原因:"+json, "error");
		}
	}); 
		}
	}
}



/*
 * 删除分类
 */
$("#delBtn").click(function(){
	let p=$(".dragtarget");
	let foam=new Array();
	let resl;
	let datas={};
	for(let a=0;a<p.length;a++){
		if(p[a].style.background=="rgb(231, 76, 60)"){
			foam.push(p[a].id)
		}
	}
	//删除不能选择空分类，添加可以
	if(foam==""){
		swal("错误", "选择了空分类", "error");
		return false;
	}else{
		resl="是否删除  '"+$("#"+foam[foam.length-1]).html()+"' ?请谨慎操作，其子类也会跟随删除";
	}
	//拿到的id变为值$("#"+foam[foam.length-1]).html()
	for(let b=0;b<foam.length;b++){
		if(foam[b].includes(6)==false){
		datas["cate"+(b+1)]=$("#"+foam[b]).html();
		}
	}
	swal({
		  title: "",
		  text: resl,
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "确认",
		  cancelButtonText: "取消",
		  closeOnConfirm: false,//设置为true就可以取消弹窗了
		  closeOnCancel: false
		},
		function(isConfirm){
		  if (isConfirm) {
			  $.ajax({
					type : "post",
					contentType : "application/json;charset=utf-8", 
					url :'${pageContext.request.contextPath}/admin/Foam_Cate_dete?${_csrf.parameterName}=${_csrf.token}',
					data:JSON.stringify(datas),
					dataType : 'json',
					success : function(data) {
						if(data == 0){
							swal("删除失败", "错误原因:"+data, "error");
							return false;
						}
						$("#cate"+(foam.length+1)).html("");
						$("#"+foam[foam.length-1]).css("display","none");
						swal("成功","'"+$("#"+foam[foam.length-1]).html()+"'已删除", "success");
					},
					error : function(json) {
						swal("错误",json, "error");
					}
				});
		  } else {
			    swal("已取消", "请继续您的操作 :)", "error");
		  }
		});
})
//添加分类
$("#add").click(function(){
	let p=$(".dragtarget")
	let foam=new Array();
	let resl;
	for(let a=0;a<p.length;a++){
		if(p[a].style.background=="rgb(231, 76, 60)"){
			foam.push(p[a].id)
		}
	}
	if(foam==""){
		resl="是否添加到一级目录?";
	}else{
		resl="是否添加到  '"+$("#"+foam[foam.length-1]).html()+"'  目录下?";
	}
	//console.log($("#"+foam[foam.length-1]).html())
	swal({
		  title: "",
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
			  $('#myModals').modal();
			  $("body").css('padding-right','0px');
		  } else {
			    swal("已取消", "请继续您的操作 :)", "error");
		  }
		});
})
function dragtarget1(event){
	//console.log(event.target.id)
	//console.log($("#"+event.target.id).find("input[name='radio']").eq(0).val())
	//$("#"+event.target.id).find("input[name='radio']").eq(0).attr("checked","checked")
	$('.dragtarget1').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#cate2").html("");
	$("#cate3").html("");
	$("#cate4").html("");
	$("#cate5").html("");
	$("#cate6").html("");
}
function dragtarget1s(event){
	//console.log(event.target.id)
	//console.log($("#"+event.target.id).find("input[name='radio']").eq(0).val())
	//$("#"+event.target.id).find("input[name='radio']").eq(0).attr("checked","checked")
	$("#cate2").html("");
	$("#cate3").html("");
	$("#cate4").html("");
	$("#cate5").html("");
	$("#cate6").html("");
}
function dragtarget2(event){
	$("#"+event.target.id).find("input[name='radio']").eq(0).attr("checked","checked")
	$('.dragtarget2').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#cate3").html("");
	$("#cate4").html("");
	$("#cate5").html("");
	$("#cate6").html("");

}

function dragtarget3(event){
	$("#"+event.target.id).find("input[name='radio']").eq(0).attr("checked","checked")
	$('.dragtarget3').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#cate4").html("");
	$("#cate5").html("");
	$("#cate6").html("");
}
function dragtarget4(event){
	$("#"+event.target.id).find("input[name='radio']").eq(0).attr("checked","checked")
	$('.dragtarget4').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#cate5").html("");
	$("#cate6").html("");
}
function dragtarget5(event){
	$("#"+event.target.id).find("input[name='radio']").eq(0).attr("checked","checked")
	$('.dragtarget5').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#cate6").html("");
}
function dragtarget7(event){
	$("#"+event.target.id).find("input[name='radio']").eq(0).attr("checked","checked")
	$('.dragtarget6').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
}
function dragtarget6(){
	$("#cate2").html("");
	$("#cate3").html("");
	$("#cate4").html("");
	$("#cate5").html("");
	$("#cate6").html("");
}

function dragtarget8(){
	$('.dragtarget1').css("background","#6495ed");
	$("#cate2").html("");
	$("#cate3").html("");
	$("#cate4").html("");
	$("#cate5").html("");
	$("#cate6").html("");
}
var lists=new Array();
function addCate(a,b,event,c){
	lists=[];
	
	/* let datas=JSON.stringify({
		a:a,
		b:b
	}); */
	if(a=='cate1'){
		dragtarget1(event);
	}else if(a=='cate2'){
		dragtarget2(event);
	}else if(a=='cate3'){
		dragtarget3(event);
	}else if(a=='cate4'){
		dragtarget4(event);
	}else if(a=='cate5'){
		dragtarget5(event);
	}else if(a=='cate6'){
		dragtarget7(event);
	}
	let p=$(".dragtarget")
	let foam=new Array();
	let datae={};
	//获取每个被选中变红的分类id
	for(let a=0;a<p.length;a++){
		if(p[a].style.background=="rgb(231, 76, 60)"){
			foam.push(p[a].id)
		}
	}
	//拿到的id变为值$("#"+foam[foam.length-1]).html()
	for(let b=0;b<foam.length;b++){
		datae["cate"+(b+1)]=$("#"+foam[b]).html();
	}
	if(a!="cate1"){
	//datae["cate"+(getJsonLength(datae)+1)]=$("#"+event.target.id).html();
	}
	//console.log(datae);
	let zhi=b;
	$.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :'${pageContext.request.contextPath}/admin/Foam_Cate_list?${_csrf.parameterName}=${_csrf.token}',
		data:JSON.stringify(datae),
		dataType : 'json',
		success : function(data) {
			//存储结果
			let result=[];
			//存储对比值
			let obj={};
			//存储所有值
			let arr=[];
			switch(a){
			case "cate1":
				for(let i=0;i<data.length;i++){
					//拿到所有值
						arr.push(data[i].cate2);
				}
				break;
			case "cate2":
				for(let i=0;i<data.length;i++){
					//拿到所有值
					arr.push(data[i].cate3);
				}
				break;
			case "cate3":
				for(let i=0;i<data.length;i++){
					//拿到所有值
					arr.push(data[i].cate4);
				}
				break;
			case "cate4":
				for(let i=0;i<data.length;i++){
					//拿到所有值
					arr.push(data[i].cate5);
				}
				break;
			case "cate5":
				for(let i=0;i<data.length;i++){
					//拿到所有值
					arr.push(data[i].cate6);
				}
				break;
			}
			
			for(let p of arr){
				if(!obj[p]){
					//存储不重复值，利用对象的属性不会重复
					result.push(p);
					obj[p]=1;
				}
			}
			//获取所有已经存在的值
			let Fdragtarget=$('.dragtarget');
			//console.log(Fdragtarget[1].id)
			switch(c){
			case "cate1":
				for(let p=0;p<$('.dragtarget1').length;p++){
					if($('.dragtarget1')[p].innerHTML==zhi){
						$("#"+$('.dragtarget1')[p].id).css("background","#e74c3c");
					}
				}
				for(let z=0;z<result.length;z++){
					let cate2="'cate2'";
					let results="'"+result[z]+"'";
					let te=0;
					if(result[z]!=null){
						$("#cate2").append('<div ondragstart="dragStart(event)"  ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget2" id="dragtarget2'+z+'" onclick="addCate('+cate2+','+results+',event,'+cate2+')">'+result[z]+'</div>');
					}//$("#cate2").append('<div class="tab-pane droptarget" id="rule">'+result[z]+'</div>');
				}
				let drag2=$('.dragtarget2');
				for(let p=0;p<drag2.length;p++){
					for(let c=0;c<lists.length;c++){
						if(drag2[p].innerHTML==lists[c]){
							$(drag2).css("background","#6495ed");
							$("#"+drag2[p].id).css("background","#e74c3c");
						}
					}
				}
				break;
			case "cate2":
				for(let z=0;z<result.length;z++){
					let cate3="'cate3'";
					let results="'"+result[z]+"'";
					let te=0;
					if(result[z]!=null){
						$("#cate3").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget3" id="dragtarget3'+z+'" onclick="addCate('+cate3+','+results+',event,'+cate3+')">'+result[z]+'</div>');
					}
				}
				let drag3=$('.dragtarget3');
				for(let d=0;d<drag3.length;d++){
					for(let e=0;e<lists.length;e++){
						if(drag3[d].innerHTML==lists[e]){
							$(drag3).css("background","#6495ed");
							$("#"+drag3[d].id).css("background","#e74c3c");
						}
					}
				}
				break;
			case "cate3":
				
				for(let z=0;z<result.length;z++){
					let cate4="'cate4'";
					let results="'"+result[z]+"'";
					let te=0;
					if(result[z]!=null){
					$("#cate4").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget4" id="dragtarget4'+z+'" onclick="addCate('+cate4+','+results+',event,'+cate4+')">'+result[z]+'</div>');
				
					}
				}
				let drag4=$('.dragtarget4');
				for(let d=0;d<drag4.length;d++){
					for(let e=0;e<lists.length;e++){
						if(drag4[d].innerHTML==lists[e]){
							$(drag4).css("background","#6495ed");
							$("#"+drag4[d].id).css("background","#e74c3c");
						}
					}
				}
				break;
			case "cate4":
				
				for(let z=0;z<result.length;z++){
					let cate5="'cate5'";
					let results="'"+result[z]+"'";
					let te=0;
					if(result[z]!=null){
						$("#cate5").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget5" id="dragtarget5'+z+'" onclick="addCate('+cate5+','+results+',event,'+cate5+')">'+result[z]+'</div>');
					}
				}
				let drag5=$('.dragtarget5');
				for(let d=0;d<drag5.length;d++){
					for(let e=0;e<lists.length;e++){
						if(drag5[d].innerHTML==lists[e]){
							$(drag5).css("background","#6495ed");
							$("#"+drag5[d].id).css("background","#e74c3c");
						}
					}
				}
				break;
			case "cate5":
				
				for(let z=0;z<result.length;z++){
					let cate6="'cate6'";
					let results="'"+result[z]+"'";
					let te=0;
					if(result[z]!=null){
						$("#cate6").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget6" id="dragtarget6'+z+'" onclick="addCate('+cate6+','+results+',event,'+cate6+')">'+result[z]+'</div>');
					}
				}
				let drag6=$('.dragtarget6');
				for(let d=0;d<drag6.length;d++){
					for(let e=0;e<lists.length;e++){
						if(drag6[d].innerHTML==lists[e]){
							$(drag6).css("background","#6495ed");
							$("#"+drag6[d].id).css("background","#e74c3c");
						}
					}
				}
				break;
			}
		},
		error : function(json) {
			alert("失败"+json);
		}
	});
}
function dragEnd(event) {
    event.stopPropagation();
    //用event.target.id获取到目标的id然后进行显示
    //console.log(event.target);
    //document.getElementById("demo").innerHTML = "完成 p 元素的拖动。";
}
function allowDrop(event) {
    event.preventDefault();
}
function drop(event) {
    event.preventDefault();
    var data = event.dataTransfer.getData("Text");
    //console.log(event.target);
    let F_cate=$("#"+data).attr("onclick").substring($("#"+data).attr("onclick").indexOf("cate"),14)
    
    $("#"+data).removeAttr("onclick");
    $("#"+data).attr("onclick","addCate('"+F_cate+"','"+$("#"+data).html()+"',event,'"+event.target.id+"');")
    //console.log(document.getElementById(data));
    $("#"+data).removeClass("tab-pane");
    event.target.appendChild(document.getElementById(data));
}
</script>	
<form id="forwordform" action='<c:url value="/admin/edit?${_csrf.parameterName}=${_csrf.token}" />' method='POST'>
	<input type="hidden" name="id" id="id"/>
</form>
<script type="text/javascript">
$('#leftMenuProduct').addClass('active');
var oTable;
var header = $("meta[name='_csrf_header']").attr("content");  
var token = $("meta[name='_csrf']").attr("content"); 
var contextPath = '${pageContext.request.contextPath}';

 

//数据查询
function searchSelec(){
	//$(".FoamSelect").hide();
	console.log($("#name").val());
	let datas=JSON.stringify({
		"cate1":$("#name").val()
	})
	if($("#name").val()!=null&&$("#name").val()!=""){
	$.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :'${pageContext.request.contextPath}/admin/Foam_Cate_list_search?${_csrf.parameterName}=${_csrf.token}',
		data:datas,
		dataType : 'json',
		success : function(data) {
			if(data.length!=0){
				$("#search_result").html("");
			}
			for(let a=0;a<data.length;a++){
				//console.log(data[a])
				if(data[a].cate6!=null){
					let cate1="'"+data[a].cate1+"/"+data[a].cate2+"/"+data[a].cate3+"/"+data[a].cate4+"/"+data[a].cate5+"/"+data[a].cate6+"'";
					$("#search_result").append('<div class="dragtarget" onclick="selectOnclick(event,'+cate1+')">'+data[a].cate1.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate2.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate3.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate4.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate5.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate6.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+"<br></div>");
				}else
				if(data[a].cate5!=null){
					let cate1="'"+data[a].cate1+"/"+data[a].cate2+"/"+data[a].cate3+"/"+data[a].cate4+"/"+data[a].cate5+"'";
					$("#search_result").append('<div class="dragtarget" onclick="selectOnclick(event,'+cate1+')">'+data[a].cate1.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate2.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate3.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate4.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate5.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+"<br></div>");				
						}else
				if(data[a].cate4!=null){
					let cate1="'"+data[a].cate1+"/"+data[a].cate2+"/"+data[a].cate3+"/"+data[a].cate4+"'";
					$("#search_result").append('<div class="dragtarget" onclick="selectOnclick(event,'+cate1+')">'+data[a].cate1.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate2.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate3.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate4.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+"<br></div>");
					}else
				if(data[a].cate3!=null){
					let cate1="'"+data[a].cate1+"/"+data[a].cate2+"/"+data[a].cate3+"'";
					$("#search_result").append('<div class="dragtarget" onclick="selectOnclick(event,'+cate1+')">'+data[a].cate1.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate2.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate3.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+"<br></div>");
					}else
				if(data[a].cate2!=null){
					let cate1="'"+data[a].cate1+"/"+data[a].cate2+"'";
					$("#search_result").append('<div class="dragtarget" onclick="selectOnclick(event,'+cate1+')">'+data[a].cate1.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+'/'+data[a].cate2.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+"<br></div>");
					}else
				if(data[a].cate1!=null){
					let cate1="'"+data[a].cate1+"'";
					$("#search_result").append('<div class="dragtarget" onclick="selectOnclick(event,'+cate1+')">'+data[a].cate1.replace($("#name").val(),"<nobr style='color:#57ff05'>"+$("#name").val()+"</nobr>")+"<br></div>");
					}
			}
			$("#myModal").modal('show');
			$("body").css('padding-right','0px');
		},
		error : function(json) {
			alert("失败"+json);
		}
	});
	}else{
		$("#search_result").html("")
		$("#search_result").append('<div  style="color:red">未找到您需要的产品</div>')
	}
}
//回车查询
 $('#name').bind('keypress',function(event){  
     if(event.keyCode == "13"){
		searchSelec();
     }  
 }); 
//回车添加CateName
$('#CateName').bind('keypress',function(event){  
     if(event.keyCode == "13"){
		addCateName();
     }  
 });
//循环onclick
function selectValue(dragtar,value){
	let dragtarget=$("."+dragtar);
	//console.log(dragtarget)
	for(let a=0;a<dragtarget.length;a++){
		//console.log(dragtarget[a])
		if(dragtarget[a].innerHTML==value){
			$('#'+dragtarget[a].id).click();
		}
	}
	return "成功";
}

function prices(){

	return listsy;
}

/*
 * 查询
 */
 var listsy=new Array();
 function ModalSelectCate(a,b,event,c){
	//console.log(a)
	listsy.push(b);
 	/* let datas=JSON.stringify({
 		a:a,
 		b:b
 	});   */
 	/* if(a=='cate1'){
 		dragtarget1s(event);
 	}else if(a=='cate2'){
 		dragtarget2(event);
 	}else if(a=='cate3'){
 		dragtarget3(event);
 	}else if(a=='cate4'){
 		dragtarget4(event);
 	}else if(a=='cate5'){
 		dragtarget5(event);
 	}else if(a=='cate6'){
 		dragtarget7(event);
 	} */
 	let p=$(".dragtarget")
 	let foam=new Array();
 	let datae={};
 	//获取每个被选中变红的分类id
 	/* for(let as=0;as<p.length;as++){
 		if(p[as].style.background=="rgb(231, 76, 60)"){
 			foam.push(p[as].id)
 		}
 	} */
 	//console.log(foam)
 	//拿到的id变为值$("#"+foam[foam.length-1]).html()
 	/* for(let bc=0;bc<foam.length;bc++){
 		datae["cate"+(bc+1)]=$("#"+foam[bc]).html();
 	} */
 	//if(a!="cate1"){
 	//datae["cate"+(getJsonLength(datae)+1)]=$("#"+event.target.id).html();
 	//}
 	for(let ew=0;ew<listsy.length;ew++){
 	 	datae["cate"+(getJsonLength(datae)+1)]=listsy[ew];
 	}
 	//console.log(datae);
 	//let zhi=b;
 	var listp=new Array();
 	listp=listw;
 	console.log(listp)
 	//console.log(listp)
 	//listp.push(listsy);
 	$.ajax({
 		type : "post",
 		contentType : "application/json;charset=utf-8", 
 		url :'${pageContext.request.contextPath}/admin/Foam_Cate_list?${_csrf.parameterName}=${_csrf.token}',
 		data:JSON.stringify(datae),
 		dataType : 'json',
 		success : function(data) {
 		 //	console.log(listp)
 			//存储结果
 			let result=[];
 			//存储对比值
 			let obj={};
 			//存储所有值
 			let arr=[];
 			switch(a){
 			case "cate1":
 				for(let i=0;i<data.length;i++){
 					//拿到所有值
 						arr.push(data[i].cate2);
 				}
 				break;
 			case "cate2":
 				for(let i=0;i<data.length;i++){
 					//拿到所有值
 					arr.push(data[i].cate3);
 				}
 				break;
 			case "cate3":
 				for(let i=0;i<data.length;i++){
 					//拿到所有值
 					arr.push(data[i].cate4);
 				}
 				break;
 			case "cate4":
 				for(let i=0;i<data.length;i++){
 					//拿到所有值
 					arr.push(data[i].cate5);
 				}
 				break;
 			case "cate5":
 				for(let i=0;i<data.length;i++){
 					//拿到所有值
 					arr.push(data[i].cate6);
 				}
 				break;
 			}
 			
 			for(let p of arr){
 				if(!obj[p]){
 					//存储不重复值，利用对象的属性不会重复
 					result.push(p);
 					obj[p]=1;
 				}
 			}
 			//console.log(result)
 			 //获取所有已经存在的值
 			let Fdragtarget=$('.dragtarget1');
 			switch(a){
 			case "cate1":
 	 			//console.log(listw)
 	 			for(let ie=0;ie<Fdragtarget.length;ie++){
 	 				if($("#"+Fdragtarget[ie].id).html()==listp[0]){
 	 					$('.dragtarget1').css("background","#6495ed");
 	 					$("#"+Fdragtarget[ie].id).css("background","#e74c3c");
 	 				}
 	 			}
 				for(let z=0;z<result.length;z++){
 					let cate2="'cate2'";
 					let results="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(listp[1]==result[z]){
 							$("#cate2").append('<div style="background:#e74c3c" ondragstart="dragStart(event)"  ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget2" id="dragtarget2'+z+'" onclick="addCate('+cate2+','+results+',event,'+cate2+')">'+result[z]+'</div>');
 						}else{
 							$("#cate2").append('<div ondragstart="dragStart(event)"  ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget2" id="dragtarget2'+z+'" onclick="addCate('+cate2+','+results+',event,'+cate2+')">'+result[z]+'</div>');
 						}
 					}
 				}
 				break;
 			case "cate2":
 				for(let z=0;z<result.length;z++){
 					let cate3="'cate3'";
 					let results="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(listp[2]==result[z]){
 							$("#cate3").append('<div style="background:#e74c3c"  ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget3" id="dragtarget3'+z+'" onclick="addCate('+cate3+','+results+',event,'+cate3+')">'+result[z]+'</div>');
 						}else{
 							$("#cate3").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget3" id="dragtarget3'+z+'" onclick="addCate('+cate3+','+results+',event,'+cate3+')">'+result[z]+'</div>');
 						}
 					}
 				}
 				break;
 			case "cate3":
 				for(let z=0;z<result.length;z++){
 					let cate4="'cate4'";
 					let results="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(listp[3]==result[z]){
 							$("#cate4").append('<div  style="background:#e74c3c" ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget4" id="dragtarget4'+z+'" onclick="addCate('+cate4+','+results+',event,'+cate4+')">'+result[z]+'</div>');
 						}else{
 							$("#cate4").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget4" id="dragtarget4'+z+'" onclick="addCate('+cate4+','+results+',event,'+cate4+')">'+result[z]+'</div>');
 		 				}
 					}
 				}
 				break;
 			case "cate4":
 				
 				for(let z=0;z<result.length;z++){
 					let cate5="'cate5'";
 					let results="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(listp[4]==result[z]){
 							$("#cate5").append('<div  style="background:#e74c3c" ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget5" id="dragtarget5'+z+'" onclick="addCate('+cate5+','+results+',event,'+cate5+')">'+result[z]+'</div>');
 						}else{
 							$("#cate5").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget5" id="dragtarget5'+z+'" onclick="addCate('+cate5+','+results+',event,'+cate5+')">'+result[z]+'</div>');
 						}
 					}
 				}
 				
 				break;
 			case "cate5":
 				
 				for(let z=0;z<result.length;z++){
 					let cate6="'cate6'";
 					let results="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(listp[5]==result[z]){
 							$("#cate6").append('<div style="background:#e74c3c" ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget6" id="dragtarget6'+z+'" onclick="addCate('+cate6+','+results+',event,'+cate6+')">'+result[z]+'</div>');
 						}else{
 							$("#cate6").append('<div ondragstart="dragStart(event)" ondragover="dragEnd(event)" draggable="true" class="dragtarget dragtarget6" id="dragtarget6'+z+'" onclick="addCate('+cate6+','+results+',event,'+cate6+')">'+result[z]+'</div>');
 						}
 					}
 				}
 				
 				break;
 			} 
 			
 		},
 		error : function(json) {
 			alert("失败"+json);
 		}
 	});
 }
//重置
$("#reset").click(function(){
	//$(".FoamSelect").show();
	$("#searchCates").hide();
	$("#cate2").html("");
	$("#cate3").html("");
	$("#cate4").html("");
	$("#cate5").html("");
	$("#cate6").html("");
	$(".dragtarget1").css("background","#6495ed");
})
//查询选择
var listw=new Array();
function selectOnclick(event,value){
	//console.log(event)
	//$('.dragtarget1').css("background","#6495ed");
	var spi=value.split("/");
	//console.log(spi)
	let dragtarget1=$(".dragtarget1");
	//var sums=new Array();
	//dragtarget8();
	for(let iod=1;iod<spi.length+1;iod++){
		 listw.push(spi[iod-1]);
	}
	dragtarget8();
	for(let iod=1;iod<spi.length+1;iod++){
		//listw.push(spi[iod-1]);
		/* if(iod==1){
			for(let a=0;a<dragtarget1.length;a++){
				if($("#"+dragtarget1[a].id).html()==spi[iod-1]){
					$("#"+dragtarget1[a].id).click()
				}
			}
		} */
		//旧查询
		
		ModalSelectCate("cate"+iod,spi[iod-1],event,'cate'+iod);
		
	}

	/* for(let iod=1;iod<spi.length+1;iod++){
		let iocc=$(".dragtarget"+iod);
		console.log(iocc.length)
		for(let ti=0;ti<iocc.length;ti++){
			console.log($("#"+iocc[ti].id).html())
		}
	} */
	listw=[];
	listsy=[];
	//console.log(listsy)
	//ModalSelectCate(listw)
	$("#myModal").modal('hide');
	$("body").css('padding-right','0px');
	//lists.clear();
}

</script>
</body>
</html>