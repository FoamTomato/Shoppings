<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="searchCates">
<!-- 模态框（Modal） -->
        <div class="modal fade" id="myModalp"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <nobr class="Foam-title" id="myModalLabelp">
                           
                        </nobr>
                        <nobr class="Foam-title"  id="myModalLabelpLast"></nobr>
                    </div>
                    <div class="modal-body" style="height:525px">
                          <div id="modalcate1" class="tab-content flex-item droptargets" style="overflow: auto;" >
								 
						  </div>
                          <div id="modalcate2" class="tab-content flex-item droptargets" style="overflow: auto;">
								  
						  </div>
						  <div id="modalcate3" class="tab-content flex-item droptargets"  style="overflow: auto;">
								  
						  </div>
						  <div id="modalcate4" class="tab-content flex-item droptargets" style="overflow: auto;">
								  
						  </div>
						  <div id="modalcate5" class="tab-content flex-item droptargets" style="overflow: auto;">
								  
						  </div>
						  <div id="modalcate6" class="tab-content flex-item droptargets" style="overflow: auto;">
								  
						  </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" id="modal-change" class="btn btn-primary">
                            	移动
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
</div>
<script type="text/javascript">
/*
 * 移动请求
 */
 function modalCateSelect(value,values){
	 	let p=$(".model-cate-list")
		let foam=new Array();
		let resl;
		let datas={};
		//拿到所有选中的值并存入foam中
		for(let a=0;a<p.length;a++){
			if(p[a].style.background=="rgb(231, 76, 60)"){
				foam.push(p[a].id)
			}
		}
		//拿到的id变为值$("#"+foam[foam.length-1]).html()
		for(let b=0;b<foam.length;b++){
			if(foam[b].includes(6)==false){
			datas["cate"+(b+1)]=$("#"+foam[b]).html();
			}
		}
		let p1=$(".dragtarget")
		let foam1=new Array();
		//获取每个被选中变红的分类id
		for(let a=0;a<p1.length;a++){
			if(p1[a].style.background=="rgb(231, 76, 60)"){
				foam1.push($("#"+p1[a].id).html())
			}
		}
		let selectCate="";
		for(let a=0;a<foam1.length;a++){
			selectCate+=foam1[a]+"/";
		}
		//存入需要移动的值
		//datas["cate"+(getJsonLength(datas)+1)]=value;
		datas["cate"+(getJsonLength(datas)+1)]=selectCate.substr(0,selectCate.lastIndexOf("/"));
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :'${pageContext.request.contextPath}/admin/Foam_Cate_modal?${_csrf.parameterName}=${_csrf.token}',
			data:JSON.stringify(datas),
			dataType : 'json',
			success : function(data) {
				if(data.status == 3){
					swal("移动失败", "错误原因:分类不能超过六级", "error");
					return false;
				}
				$("#myModalp").modal('hide');
				$("#"+foam[foam.length-1]).css("display","none");
				//console.log(datas)
				let resultw="";
				for(let zi=0;zi<foam.length;zi++){
					resultw+=$("#"+foam[zi]).html()+"/";
				}
				resultw+=value;
				//进行后背查询
				//selectOnclick(event,resultw.substr(0,resultw.lastIndexOf("/")))
				console.log(resultw)
				selectOnclick(event,resultw);
				swal("移动成功","'"+value+"'已移动至"+"'"+$("#"+foam[foam.length-1]).html()+"'", "success");
			},
			error : function(json) {
				swal("错误",json, "error");
			}
		});
}
/*
 * 用来存比值
 */
var sellg="";
/*
 * 移动分类
 */
function mobile(){
	sellg="";
	$("#myModalLabelpLast").html("");
	$("#searchCates").show();
	let p=$(".dragtarget");
	let foam=new Array();
	let resl;
	let datas={};
	let Fresults="";
	for(let a=0;a<p.length;a++){
		if(p[a].style.background=="rgb(231, 76, 60)"){
			foam.push(p[a].id);
			Fresults+=$("#"+p[a].id).html()+"/"
		}
	}
	sellg=Fresults.substr(0,Fresults.lastIndexOf("/"));
	//不能移动空分类
	if(foam==""){
		swal("错误", "请选择要移动的分类", "error");
		return false;
	}
	$("#myModalp").modal('show');
	$("body").css('padding-right','0px');
	$("#myModalLabelp").html("移动'<nobr style='color:red'>"+$("#"+foam[foam.length-1]).html()+"</nobr>'至");
	$("#modal-change").removeAttr("onclick");
    $("#modal-change").attr("onclick","modalCateSelect('"+$("#"+foam[foam.length-1]).html()+"','"+Fresults.substr(0,Fresults.lastIndexOf("/"))+"');")
	//获取到每个选中的名称并转换为json格式存入datas
	for(let b=0;b<foam.length;b++){
		if(foam[b].includes(6)==false){
		datas["cate"+(b+1)]=$("#"+foam[b]).html();
		}
	}
	//a是指第几级分类，b是指查询的值
	let datass=JSON.stringify({
		a:"cate1"
	});
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :'${pageContext.request.contextPath}/admin/Foam_Cate1_Set?${_csrf.parameterName}=${_csrf.token}',
			data:datass,
			dataType : 'json',
			success : function(data) {
				$("#modalcate1").html("");
				$("#modalcate2").html("");
				$("#modalcate3").html("");
				$("#modalcate4").html("");
				$("#modalcate5").html("");
				$("#modalcate6").html("");
				for(let a=0;a<data.length;a++){
					let result1="'"+data[a]+"'";
					let result2="'cate1'";
					if(Fresults.substr(0,Fresults.lastIndexOf("/"))==data[a]){
						$("#modalcate1").append('<fieldset disabled><div class="model-cate-list" style="background:#cccccc;text-decoration:line-through" id="modalCate1'+a+'" >'+data[a]+'</div></fieldset>');
					}else{
						$("#modalcate1").append('<div class="model-cate-list modalCate1" id="modalCate1'+a+'" onclick="addCate2('+result2+','+result1+',event,'+result2+');clearCate1(event)">'+data[a]+'</div>');
					}
				}
			},
			error : function(json) {
				swal("错误",json, "error");
			}
		});
}
function modalCate1s(event){
	$('.modalCate1').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#modalcate2").html("");
	$("#modalcate3").html("");
	$("#modalcate4").html("");
	$("#modalcate5").html("");
	$("#modalcate6").html("");
}
function modalCate2s(event){
	$('.modalCate2').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#modalcate3").html("");
	$("#modalcate4").html("");
	$("#modalcate5").html("");
	$("#modalcate6").html("");

}
function modalCate3s(event){
	$('.modalCate3').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#modalcate4").html("");
	$("#modalcate5").html("");
	$("#modalcate6").html("");
}
function modalCate4s(event){
	$('.modalCate4').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#modalcate5").html("");
	$("#modalcate6").html("");
}
function modalCate5s(event){
	$('.modalCate5').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
	$("#modalcate6").html("");
}
function modalCate6s(event){
	$('.modalCate6').css("background","#6495ed");
	$("#"+event.target.id).css("background","#e74c3c");
}
/*
 * 判断移动的两个比值是否相等
 */
function lgfg(value){
	if(sellg==value){
		return false;
	}
	return true;
}
/*
 * 移动的编写
 */
 var list2=new Array();
 var lists=new Array();
 function addCate2 (a,b,event,c) {
 	/* let datas=JSON.stringify({
 		a:a,
 		b:b
 	}); */
 	
 	if(a=='cate1'){
 		modalCate1s(event);
	}else if(a=='cate2'){
		modalCate2s(event);
	}else if(a=='cate3'){
		modalCate3s(event);
	}else if(a=='cate4'){
		modalCate4s(event);
	}else if(a=='cate5'){
		modalCate5s(event);
	}else if(a=='cate6'){
		modalCate6s(event);
	}
 	let p=$(".model-cate-list")
	let foam=new Array();
	let resl;
	let datas={};
	//拿到所有选中的值并存入foam中
	for(let a=0;a<p.length;a++){
		if(p[a].style.background=="rgb(231, 76, 60)"){
			foam.push(p[a].id)
		}
	}
	
	let shows="";
	//拿到的id变为值$("#"+foam[foam.length-1]).html()
	 for(let b=0;b<foam.length;b++){
		datas["cate"+(b+1)]=$("#"+foam[b]).html();
		shows+=$("#"+foam[b]).html()+"/";
	}
	$("#myModalLabelpLast").html("");
	$("#myModalLabelpLast").append("'<nobr style='color:red'>"+shows.substr(0,shows.lastIndexOf("/"))+"</nobr>'");
	//console.log(shows.substr(0,shows.lastIndexOf("/")));
	//console.log(datas);
 	let zhi=b;
 	$.ajax({
 		type : "post",
 		contentType : "application/json;charset=utf-8", 
 		url :'${pageContext.request.contextPath}/admin/Foam_Cate_list?${_csrf.parameterName}=${_csrf.token}',
 		data:JSON.stringify(datas),
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
 			let Fdragtarget=$('.model-cate-list');
 			switch(c){
 			case "cate1":
 				for(let p=0;p<$('.modalCate1').length;p++){
 					if($('.modalCate1')[p].innerHTML==zhi){
 						$("#"+$('.modalCate1')[p].id).css("background","#e74c3c");
 					}
 				}
 				for(let z=0;z<result.length;z++){
 					let result2="'cate2'";
 					let result1="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(lgfg(shows.substr(0,shows.lastIndexOf("/"))+"/"+result[z])==false){
 							$("#modalcate2").append('<div class="model-cate-list" style="background:#cccccc;text-decoration:line-through" id="modalCate2'+z+'">'+result[z]+'</div>');
 						}else{
 							$("#modalcate2").append('<div class="model-cate-list modalCate2" id="modalCate2'+z+'" onclick="addCate2('+result2+','+result1+',event,'+result2+');clearCate2(event)">'+result[z]+'</div>');
 						}
 					}
 				}
 				
 				let drag2=$('.modalCate2');
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
 					let result2="'cate3'";
 					let result1="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(lgfg(shows.substr(0,shows.lastIndexOf("/"))+"/"+result[z])==false){
 							$("#modalcate3").append('<div class="model-cate-list" id="modalCate3'+z+'" style="background:#cccccc;text-decoration:line-through">'+result[z]+'</div>');
 						}else{
 							$("#modalcate3").append('<div class="model-cate-list modalCate3" id="modalCate3'+z+'" onclick="addCate2('+result2+','+result1+',event,'+result2+');clearCate3(event)">'+result[z]+'</div>');
 	 					}
 					}
 				}
 				let drag3=$('.modalCate3');
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
 					let result2="'cate4'";
 					let result1="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(lgfg(shows.substr(0,shows.lastIndexOf("/"))+"/"+result[z])==false){
 							$("#modalcate4").append('<div class="model-cate-list" id="modalCate4'+z+'" style="background:#cccccc;text-decoration:line-through">'+result[z]+'</div>');
 						}else{
 							$("#modalcate4").append('<div class="model-cate-list modalCate4" id="modalCate4'+z+'" onclick="addCate2('+result2+','+result1+',event,'+result2+');clearCate4(event)">'+result[z]+'</div>');
 			 			}
 					}
 				}
 				let drag4=$('.modalCate4');
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
 					let result2="'cate5'";
 					let result1="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if(lgfg(shows.substr(0,shows.lastIndexOf("/"))+"/"+result[z])==false){
 							$("#modalcate5").append('<div class="model-cate-list" id="modalCate5'+z+'" style="background:#cccccc;text-decoration:line-through">'+result[z]+'</div>');
 						}else{
 							$("#modalcate5").append('<div class="model-cate-list modalCate5" id="modalCate5'+z+'" onclick="addCate2('+result2+','+result1+',event,'+result2+');clearCate5(event)">'+result[z]+'</div>');
 						}
 					}
 				}
 				let drag5=$('.modalCate5');
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
 					let result2="'cate6'";
 					let result1="'"+result[z]+"'";
 					let te=0;
 					if(result[z]!=null){
 						if($("#myModalLabelp").html()=="选择分类"){
 							$("#modalcate6").append('<div class="model-cate-list modalCate6" id="modalCate6'+z+'" onclick="addCate2('+result2+','+result1+',event,'+result2+');clearCate6(event)">'+result[z]+'</div>');
 						}else if(lgfg(shows.substr(0,shows.lastIndexOf("/"))+"/"+result[z])==false){
 							$("#modalcate6").append('<div class="model-cate-list" id="modalCate6'+z+'" style="background:#cccccc;text-decoration:line-through">'+result[z]+'</div>');
 						}else{
 							$("#modalcate6").append('<div class="model-cate-list" id="modalCate6'+z+'" style="background:#cccccc;text-decoration:line-through">'+result[z]+'</div>');
 	 					}
 					}
 				}
 				let drag6=$('.modalCate6');
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
 
/*
 * 清除内容
 */
 function clearCate1(event){
		$('.modalCate1').css("background","#6495ed");
		$("#"+event.target.id).css("background","#e74c3c");
		$("#modalcate2").html("");
		$("#modalcate3").html("");
		$("#modalcate4").html("");
		$("#modalcate5").html("");
		$("#modalcate6").html("");
	}

	function clearCate2(event){
		$('.modalCate2').css("background","#6495ed");
		$("#"+event.target.id).css("background","#e74c3c");
		$("#modalcate3").html("");
		$("#modalcate4").html("");
		$("#modalcate5").html("");
		$("#modalcate6").html("");

	}

	function clearCate3(event){
		$('.modalCate3').css("background","#6495ed");
		$("#"+event.target.id).css("background","#e74c3c");
		$("#modalcate4").html("");
		$("#modalcate5").html("");
		$("#modalcate6").html("");
	}
	function clearCate4(event){
		$('.modalCate4').css("background","#6495ed");
		$("#"+event.target.id).css("background","#e74c3c");
		$("#modalcate5").html("");
		$("#modalcate6").html("");
	}
	function clearCate5(event){
		$('.modalCate5').css("background","#6495ed");
		$("#"+event.target.id).css("background","#e74c3c");
		$("#modalcate6").html("");
	}
	function clearCate6(event){
		$('.modalCate6').css("background","#6495ed");
		$("#"+event.target.id).css("background","#e74c3c");
	}
	function clearCate7(){
		$("#modalcate2").html("");
		$("#modalcate3").html("");
		$("#modalcate4").html("");
		$("#modalcate5").html("");
		$("#modalcate6").html("");
	}
	/*
	选择分类的改变(商品详情页)
	*/
	function selectCateOnclick(){
		$(".f_kind_select").val($("#myModalLabelpLast nobr").html());
		$("#myModalp").modal('hide');
	}

	/*
	选择分类的改变（商品详情页上方查询）
	*/
	function selectCateOnclick1(){
		$(".f_kind_select1").val($("#myModalLabelpLast nobr").html());
		$(".f_kind_select1").attr("title",$("#myModalLabelpLast nobr").html());
		$("#myModalp").modal('hide');
		$("body").css("padding-right","0px")
	}
	/*
	选择分类的改变（商品查询）
	*/
	function selectCateOnclick2(){
		$("#fcateid").val($("#myModalLabelpLast nobr").html());
		$(".f_kind_select2").val($("#myModalLabelpLast nobr").html());
		$("#myModalp").modal('hide');
	}
	function selectCateOnclick3(vaol){
		$("#"+vaol).val($("#myModalLabelpLast nobr").html());
		$("#myModalp").modal('hide');
	}
	/*
	商品管理详情页的分类选择
	*/
	function f_kind_selects(value,vals){
		sellg="";
		$("#myModalLabelpLast").html("");
		$("#searchCates").show();
		let p=$(".dragtarget");
		let foam=new Array();
		let resl;
		let datas={};
		let Fresults="";
		
		
		$("#myModalp").modal('show');
		$("#myModalLabelp").html("选择分类");
		$("#modal-change").removeAttr("onclick");
		$("#modal-change").html("选择");
		if(value==0){
		    $("#modal-change").attr("onclick","selectCateOnclick()");
		}else if(value==1){
		    $("#modal-change").attr("onclick","selectCateOnclick1()")
		}else if(value==2){
		    $("#modal-change").attr("onclick","selectCateOnclick2()")
		}else if(value==3){
		    $("#modal-change").attr("onclick","selectCateOnclick3("+vals+")")
		}
		
		
			$.ajax({
				type : "post",
				contentType : "application/json;charset=utf-8", 
				url :'${pageContext.request.contextPath}/admin/Foam_Cate1_Set?${_csrf.parameterName}=${_csrf.token}',
				data:JSON.stringify(datas),
				dataType : 'json',
				success : function(data) {
					$("#modalcate1").html("");
					$("#modalcate2").html("");
					$("#modalcate3").html("");
					$("#modalcate4").html("");
					$("#modalcate5").html("");
					$("#modalcate6").html("");
					for(let a=0;a<data.length;a++){
						let result1="'"+data[a]+"'";
						let result2="'cate1'";
						$("#modalcate1").append('<div class="model-cate-list modalCate1" id="modalCate1'+a+'" onclick="addCate2('+result2+','+result1+',event,'+result2+');clearCate1(event)">'+data[a]+'</div>');
					}
				},
				error : function(json) {
					swal("错误",json, "error");
				}
			});
	}
</script>