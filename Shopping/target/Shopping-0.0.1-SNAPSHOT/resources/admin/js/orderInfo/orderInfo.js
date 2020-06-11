
$('#leftMenuOrder').addClass('active');
var oTable;
var header = $("meta[name='_csrf_header']").attr("content");  
var token = $("meta[name='_csrf']").attr("content"); 
var contextPath = '${pageContext.request.contextPath}';
$(function() {
	//$('.date').datepicker();
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	//userInfoListData()
});

$('#add').click(function() {
	addAdmin();
});
/*
 * 导入文件
 */
$("#excel_buttonst").click(function(){
	if($("#filename").html()=="未选择文件"){
		toastr.error("请选择文件");
	}else{
		$("#formrs").submit();
	}
})
$("#excel_buttonssss").click(function(){

	if($("#idList").val()!=""){
		for(let i=0;i<$(".stockCheckbox").length;i++){
			if($(".stockCheckbox")[i].checked==true){
				$(".F_logistics_item4"+$(".stockCheckbox")[i].id).css("color","green")
			}
		}
		$("#formtyw").submit();
	}else{
		toastr.error("请选择需要导出的文件");
	}
}) 
/*
 * 导入文件请求后台
 */
function ImportFiles(){
	
	/*
	 * 根据条件查询订单
	 */
	 /* $.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :"${pageContext.request.contextPath}/Logistics/selStatusOrder?${_csrf.parameterName}=${_csrf.token}",
			data:JSON.stringify(datas),
			dataType:"json",
			success:function(data){
				
			},
			error:function(json){
				toastr.error("条件查询失败");
			}
		}); */ 
}
function pages(value) {
	let datas={};
	datas["page"]=value;
	datas["fIds"]=$("#orderNum").val();
	datas["standby6"]=$("#StatusOrder").val();
	datas["limit"]=$("#pagsNums").val();
	datas["startDatas"]=$("#startDatas").val();
	datas["endDatas"]=$("#endDatas").val();
	if($("#allChe").prop("checked")==true){
	datas["standby9"]="1";
	}
	datas["standby13"]=$("input[name='jps']:checked").val();
	datas["standby12"]=$("#hj_shippingMethods").val();
	//console.log(datas)
	/*
	 * 根据条件查询订单
	 */
	 $.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :"${pageContext.request.contextPath}/Logistics/selStatusOrder?${_csrf.parameterName}=${_csrf.token}",
			data:JSON.stringify(datas),
			dataType:"json",
			success:function(data){
				$("#V_pages").html("");
				$("#F-main-body").html("");
				pagesALLda(data);
			},
			error:function(json){
				toastr.error("条件查询失败");
			}
		}); 
}
//根据id去查询商品的信息
function  gzhId(data){
	let ds={};
	ds["ij"]=data;
	let y="";
	$.ajax({
		type : "post",
		contentType : "application/json;charset=utf-8", 
		url :"${pageContext.request.contextPath}/Logistics/ij?${_csrf.parameterName}=${_csrf.token}",
		data:JSON.stringify(ds),
		dataType:"json",
		async:false,
		success:function(data){
			y= data;
		},
		error:function(json){
			toastr.error("查找失败");
		}
	}); 
	console.log(y)
	return y;
}
function lookOrder(userId,orderNum){
	$("#ordernum").val(orderNum);
	$("#userId").val(userId);
	$("#forwordform").submit();
}

function addAdmin(){	
	window.location.href =contextPath+'/admin/adduser' ;
}
//首尾页（带一个参页码参，还有一个从上面获取）
function page(value){
	/*
	 * 查询所有的物流信息/order/checkTheOrder
	 */
	$("#V_pages").html("");
	$("#F-main-body").html("");
	let datas={};
	datas["page"]=value;
	datas["limit"]=$("#pagsNums").val();
	 $.ajax({
			type : "get",
			contentType : "application/json;charset=utf-8", 
			url :"${pageContext.request.contextPath}/Logistics/checkTheOrder",
			data:datas,
			dataType:"json",
			success:function(data){
				$("#V_pages").html("");
				pagesALLda(data);
			},
			error:function(json){
				swal("提示", "查询订单失败!", "error");  
			}
		}); 
}
//分页列表
function pagesALLda(data){
	selInputButton="";
	//$("#F-main-body").html('<input type="checkbox" name="stockCheckboxFid" style="display:block">');
        var content = "<a class='pageSi' onclick='page(1)'>首页</a>";
        var current =  data.pageNum;
        var total = data.pages;
	//总页数大于6时候
    if(total > 6) {
        //当前页数小于5时显示省略号
        if(current < 5) {
            for(var i = 1; i < 6; i++) {
            	 if(current == i) {
                	 content += "<a class='pageSi'style='color:red' onclick='page("+i+")'>"+i+"</a>";
                	// $("#excel_buttonssss").removeAttr("onclick");$("#excel_buttonssss").attr("onclick","pages("+i+")");
				}else{
					 content += "<a class='pageSi' onclick='page("+i+")'>"+i+"</a>";
                }
            }
            content += ". . .";
            content += "<a>"+total+"</a>";
        } else {
             //判断页码在末尾的时候
            if(current < total - 3) {
                for(var i = current - 2; i < current + 3; i++) {
                	 if(current == i) {
                    	 content += "<a class='pageSi'style='color:red' onclick='page("+i+")'>"+i+"</a>";
                    	// $("#excel_buttonssss").removeAttr("onclick");$("#excel_buttonssss").attr("onclick","pages("+i+")");
					}else{
						 content += "<a class='pageSi' onclick='page("+i+")'>"+i+"</a>";
                    }
                }
                content += ". . .";
                content += "<a>"+total+"</a>";
            //页码在中间部分时候    
            } else {
                content += "<a>1</a>";
                content += ". . .";
                for(var i = total - 4; i < total + 1; i++) {
                	 if(current == i) {
                    	 content += "<a class='pageSi'style='color:red' onclick='page("+i+")'>"+i+"</a>";
                    	// $("#excel_buttonssss").removeAttr("onclick");$("#excel_buttonssss").attr("onclick","pages("+i+")");
					}else{
						 content += "<a class='pageSi' onclick='page("+i+")'>"+i+"</a>";
                    }
                }
            }
        }
        //页面总数小于6的时候
    } else {
        for(var i = 1; i < total + 1; i++) {
            if(current == i) {
            	 content += "<a class='pageSi'style='color:red' onclick='page("+i+")'>"+i+"</a>";
            	// $("#excel_buttonssss").removeAttr("onclick");$("#excel_buttonssss").attr("onclick","pages("+i+")");
			}else{
				 content += "<a class='pageSi' onclick='page("+i+")'>"+i+"</a>";
            }
        }
    }
    content += "<a class='pageSi' onclick='page("+data.pages+")'>尾页</a>";    
    $("#V_pages").append(content);
	
	for(let a=0;a<data.list.length;a++){
		let color="color:red";
		if(data.list[a].standby6==1){
			//待发货
			color="color:red";
		}else if(data.list[a].standby6==0&&data.list[a].standby6!=""){
			//已发货
			color="color:blue";
		}else if(data.list[a].standby6==2){
			//已录入erp
			color="color:green";
		}else if(data.list[a].standby6==3){
			//问题件
			color="color:#c925d478";
		}
		//let xie=gzhId(data.list[a].fIds);
		$("#F-main-body").append('<div class="col-lg-3 F_setting"><div class="F_logistics_body">'
								+'<div class="d-flex flex-column">'
								+'   <div class="F_logistics_item1">'
								+'   	<div class="d-flex flex-row">'
								+' 			<div class="F_logistics_item2">订单号：<nobr class="F_logistics_item4 F_logistics_item4'+data.list[a].id+'" style="font-weight:900;margin-right:25px;'+color+'">'+data.list[a].fIds+'</nobr></div>'
								+'   		<div class="F_logistics_item2" onclick="F_logistics_body('+data.list[a].fIds+')"><a>'+data.list[a].fStore+'</a></div>'
								+'   	</div>'
								+'   </div>'
								+'   <div class="F_logistics_item1">'
								+'   	<div class="d-flex flex-column">'
								+'  			<div class=" F_logistics_item3" style="float:left">跟踪号：<nobr class="F_logistics_item4 F_logistics_item4'+data.list[a].id+'" style="'+color+'">'+data.list[a].standby11+'</nobr> <br> 物流：'+CountryEANDCs(data.list[a].standby12)+'</div>'
								+'   	<input type="checkbox" id="'+data.list[a].id+'" class="stockCheckbox" style="float:left;margin-top:8px;margin-left:50px"></div>'
								+'   </div>'
								+' </div>'
								+'</div></div>');
		selInputButton+=data.list[a].id+",";
	}
	selAll();

	if($("#rightright").css("display")=="block"){
	 	//列表变两个
	 	$(".F_setting").removeClass("col-lg-3");
	 	$(".F_setting").addClass("col-lg-6");
		$(".leftleft").css("width","");
		$(".leftleft").css("width","48%");
	}
	$("#V_pages").append("总数:"+data.total+" 当前页数："+data.size);
	$(".stockCheckbox").prop("checked",false);
    $('input[name="stockCheckboxFid"]').prop("checked",false);
   $("#allInner").html("全选");
}
//全选
$("#allInner").on("click",function(){

	 $("#idList").val("");
	 let pii=selInputButton.substr(0,selInputButton.length-1).split(",");
	if($('input[name="stockCheckboxFid"]').is(":checked")==false){
		for(let a=0;a<pii.length;a++){
			$("#"+pii[a]).prop("checked",true);
		}
       $('input[name="stockCheckboxFid"]').prop("checked",true);
       
      $("#allInner").html("全不选");
      $("#idList").val(pii);
   }else{
   	for(let a=0;a<pii.length;a++){
   		$("#"+pii[a]).prop("checked",false);
		}
       $('input[name="stockCheckboxFid"]').prop("checked",false);
       
      $("#allInner").html("全选");
   }
	vlength();
}); 
var selInputButton="";
function selAll(){
	//单选
		var checkS = $(".stockCheckbox");
		for(var i = 0;i<checkS.length;i++){
		    checkS[i].onclick = function(){

		   	 	let reslut="";
		    	$("#idList").val("");
		        var isSelectAll = true;//假设全选是选中状态的自定义变量
		        for(var i = 0;i<checkS.length;i++){
		            if(checkS[i].checked == false){
		                isSelectAll = false;
		                break;
		            }
		        }
		        for(let i=0;i<$(".stockCheckbox").length;i++){
		        	if($(".stockCheckbox")[i].checked==true){
		                reslut+=$(".stockCheckbox")[i].id+",";
		        	}
		        }
		        $("#idList").val(reslut.substr(0,reslut.length-1).split(","));
		        if(isSelectAll){
		            $('input[name="stockCheckboxFid"]').prop("checked",true);
		            $("#allInner").html("全不选");
		        }
		        if(this.checked == false){
		            $('input[name="stockCheckboxFid"]').prop("checked",false);
		            $("#allInner").html("全选");
		        }
		        vlength();
		    }
		}
}
//全选前面小选项框
$('input[name="stockCheckboxFid"]').click(function(){
   if(this.checked){
   	for(let a=0;a<pii.length;a++){
			$("#"+pii[a]).prop("checked",true);
		}
      $("#allInner").html("全不选");
      
   }else{
   	for(let a=0;a<pii.length;a++){
			$("#"+pii[a]).prop("checked",false);
		}
      $("#allInner").html("全选");
   }
   
});
//反选
$("#reverse").on("click",function(){
	var checkSwe = $(".stockCheckbox");
	 $("#idList").val("");
	 let reslut="";
    /*按理说，反选被点击，全选应该为选中状态，
    但是由于再次点击反选的时候，全选还是会是选中的状态，所以在这里我们设为不选中。
    还有一个原因是，反选的时候，如果单选全被选中了，全选也是选中的，所以不会出现错乱。*/
    $('input[name="stockCheckboxFid"]').prop("checked",false);
    $("#allInner").html("全选");
    for(var i = 0;i<checkSwe.length;i++){
    	checkSwe[i].checked = !checkSwe[i].checked;
    }
    var isSelectAll = true;
    for(var i = 0;i<checkSwe.length;i++){
        if(checkSwe[i].checked == false){
            isSelectAll = false;
            break;
        }
    }
    if(isSelectAll){
        $("#allInner").html("全不选");
        $('input[name="stockCheckboxFid"]').prop("checked",true);
    }
    for(let i=0;i<$(".stockCheckbox").length;i++){
    	if($(".stockCheckbox")[i].checked==true){
            reslut+=$(".stockCheckbox")[i].id+",";
    	}
    }
	$("#idList").val(reslut.substr(0,reslut.length-1).split(","));

    vlength();
});
//随时查询
function vlength(){
	let leng=$("#idList").val().split(",");
	if(leng==""){
		$("#V_length").html("");
		$("#V_length").append("  已选中0个");
		return false;
	}
	$("#V_length").html("");
	$("#V_length").append("  已选中"+leng.length+"个");
}
/*
 * 查询所有的物流信息/order/checkTheOrder
 */
 $.ajax({
		type : "get",
		contentType : "application/json;charset=utf-8", 
		url :"${pageContext.request.contextPath}/Logistics/checkTheOrder?${_csrf.parameterName}=${_csrf.token}",
		dataType:"json",
		success:function(data){
			$("#V_pages").html("");
			pagesALLda(data);
		},
		error:function(json){
			swal("提示", "查询订单失败!", "error");  
		}
	}); 
	//翻译物流
	//根据国家简称填写国家英文和中文
function CountryEANDCs(abbreviation){
	switch(abbreviation){
	case "cm_dd_sz":
		return "中邮小包/线下平邮(带电)";
		break;
	case "cm_sx_sz":
		return "中邮小包/线下平邮(普货)";
		break;
	case "ghxb_dd_sz":
		return "中邮小包/线下挂号(带电)";
		break;
	case "ghxb_sg_sz":
		return "中邮小包/线下挂号(普货)";
		break;
	case "cm_eub_szyjdd":
		return "中邮E邮宝/线下E邮宝(带电)";
		break;
	case "cm_eub_szyj":
		return "中邮E邮宝/线下E邮宝(普货)";
		break;
	case "cm_rb_shhm":
		return "国际专线/日本专线";
		break;
	}
}
 /*
  * 页面变动
  */
function F_logistics_body(ids){
 	//右边详情页展示显示
 	$("#rightright").css("display","block");
 	
 	//左边变窄
 	$(".leftleft").css("width","48%");
 	
 	//列表变两个
 	$(".F_setting").removeClass("col-lg-3");
 	$(".F_setting").addClass("col-lg-6");
 	
 	//添加详情页选项卡
 	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
	var pp = $('#tt').tabs('getSelected');
	//var tab = pp.panel('options').tab; // 相应的标签页（tab）对象 
	
	//记录是否重复
	var cf=0;
	
	//获取选项卡的总长度
	var tabss=$('#tt').tabs('tabs');
	var t=tabss.length;
	
	//获取到每个标签的标题
	var idqs = document.getElementsByClassName("tabs-title");
	if(idqs.length != 0){
		for(var c=0;c<idqs.length;c++){
		//控制台进行对比	
			if(idqs[c].innerText==ids){
				z=idqs[c].innerText;
				cf++;
			}
			
	}}
	
	 if (cf==0) {
		 $('#tt').tabs('add',{
				title: ids,
				content:'<iframe scrolling="yes" id="mainiframe" name="mainiframe" width="105.5%" height="100%" frameborder="0px" style="margin:-20px;padding-top:20px;overflow:hidden" src="/Shopping/Logistics/selectOrderById?id='+ids+'"></iframe>',
				closable: true
			});
			
		} else {
        	$('#tt').tabs('select', z);//选中并刷新
        } 
}
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
						b+=key;
						for(var key1 in value[0]){
							var value1 = value[0][key1];
							if(value1!=null&&value1!=""){
								b+=key1+value1;

								data3[key1]=value1;
							}
						}
						data1["itemArr"]=[data3];
					}else if(key == "consignee"){
						b+=key;
						for(var key1 in value){
							var value1 = value[key1];
							if(value1!=null&&value1!=""){
								b+=key1+value1;
								data2[key1]=value1;
							}
						}
						data1["consignee"]=data2;
					}else{

						data1[key]=value;
						b+=key+value;
					} 
				}
			} 
		
	return {a:JSON.stringify(data1),b:b};
}

//关闭清空
function closePanelsY(){
	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
	var pp = $('#tt').tabs('getSelected');
	//获取选项卡的总长度
	var tabss=$('#tt').tabs('tabs');
	var t=tabss.length;
	if (pp){
	//关闭所有的框
	var y=0;
	for(var cd=0;cd<t;cd++){
		if(cd<t){
			$('#tt').tabs('close', 0);
			y++;
		}else if(cd>t){
			$('#tt').tabs('close', 1);
			y++;
		}
	}

	var right=document.getElementById("rightright");
	var left=document.getElementById("wrapper");
	//右边商品少于一个删除并隐藏
	var right=document.getElementById("rightright");
	var select=document.getElementById("selectselect");
	var left=document.getElementById("wrapper");

	$(".leftleft").css("width","90%");
	right.style.display="none";
 	//列表变三个
 	$(".F_setting").removeClass("col-lg-6");
 	$(".F_setting").addClass("col-lg-3");
	}
	
	}
	//发送已选
	
	$("#submitOrder").click(function(){
		
		//拿到所有已经选取的id
		//用上面获取到的id进行商品f_ids查询,后台进行截取
		$("#resultHJ").val("");
		let datas={};
		datas["id"]=$("#idList").val();
		$.ajax({
				type : "post",
				contentType : "application/json;charset=utf-8", 
				url :"${pageContext.request.contextPath}/Logistics/getPostId",
				dataType:"json",
				data:JSON.stringify(datas),
				success:function(data){
					let sun=0;
					let suns=0;
					let num="";
					let num1="";
					for(var key in data){

						sun+=1;
						var value = data[key]; //注意是 [  ]
						let result_hj=getJsonss(value).a;
						$.ajax({
							type : "post",
							contentType : "application/json;charset=utf-8", 
							url :"${pageContext.request.contextPath}/Logistics/getPostYu?${_csrf.parameterName}=${_csrf.token}",//http://vakind.f3322.org:10008/api/logistics/v1/track/create/order
							dataType:"json",
							async:false,
							data:result_hj,//JSON.stringify(datasy)
							success:function(data2){
								console.log(data[key])
								 if(data2.status==1){
										let labls={};
										labls["methosd"]=data[key].shippingMethod;
										labls["id2"]=data[key].shipperHawbcode;
										labls["id"]=data2.result.referenceNo;
										labls["lab"]=data2.result.lableKey;
										labls["num1"]=data2.result.trackNum1;
										labls["num2"]=data2.result.trackNum2;
									 $.ajax({
											type : "post",
											contentType : "application/json;charset=utf-8", 
											url :"${pageContext.request.contextPath}/Logistics/setLableid?${_csrf.parameterName}=${_csrf.token}",//http://vakind.f3322.org:10008/api/logistics/v1/track/create/order
											dataType:"json",
											async:false,
											data:JSON.stringify(labls),//JSON.stringify(datasy)
											success:function(data){
												suns+=1;
												/* $("#resultHJ").val($("#resultHJ").val()+
														 "编号："+data2.result.referenceNo+ "\n"+
														"跟踪号1:"+data2.result.trackNum1+"  跟踪号2："+data2.result.trackNum2+"  唯一标识:"+data2.result.lableKey+"\n"+
														"标签地址:"+data2.result.lableURL+"  消息：:"+data2.result.errorMessage+"  编码：:"+data2.result.errorCode+"  请求状态："+data2.result.status+"\n"+"*****************"+"\n");
												toastr.success("发送成功"); */
											},
											error:function(json){
												toastr.error("添加失败");
											}
										});
								 }else if(data2.status==0){
									 $("#resultHJ").val($("#resultHJ").val()+"发送失败   "+"  消息：:"+data.errormsg+"  编码：:"+data.errorcode+"  请求状态："+data.status+"\n"+"*****************"+"\n");
								 }
								 
							},
							error:function(json){
								$("#resultHJ").val($("#resultHJ").val()+"发送失败   "+"  消息：:"+data.errormsg+"  编码：:"+data.errorcode+"  请求状态："+data.status+"\n"+"*****************"+"\n");
								toastr.error("发送失败");
							}
						});
					}
					$("#postHj").val("");
					$("#conHJ").html("已发送"+sun+"条，其中"+suns+"条发送成功，"+(sun-suns)+"条发送失败");
					$("#postHj").val($("#resultHJ").val());
					console.log(sun)
					PostTrack();
				},
				error:function(json){
					swal("提示", "id查找失败!", "error");  
				}
			}); 
			
	})

                function fakeClick(obj) {
                  var ev = document.createEvent("MouseEvents");
                  ev.initMouseEvent("click", true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                  obj.dispatchEvent(ev);
                }

                function exportRaw(name, data) {
                  var urlObject = window.URL || window.webkitURL || window;
                  var export_blob = new Blob([data]);
                  var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
                  save_link.href = urlObject.createObjectURL(export_blob);
                  save_link.download = name;
                  fakeClick(save_link);
                } 
	/*
	 进行签名
	*/
	function Fsignature(value){
		var sha = hex_hmac_sha1("93a61c53-e6a3-4f13-ad0b-594498a49c45",value).toUpperCase();
		return sha;
	}
	/*
	 获取跟踪号
	*/
	function gettrackList(){
		let idlists=$("#idList").val();
		if(idlists==""){
			toastr.error("请选择需要打印的运单！");
			return false;
		}
		$("#resultHJ").val("");
		let datas={};
		datas["referenceNoList"]=idlists;
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :"${pageContext.request.contextPath}/Logistics/getpdfall",
			dataType:"json",
			async:false,
			data:JSON.stringify(datas),
			success:function(data){
				window.open("${pageContext.request.contextPath}/"+data,"_blank");
				pages("1");
			},
			error:function(json){
				toastr.error("获取失败");
			}
		}); 
		
		toastr.success("获取成功");
	}
	/*
	生成集拼单号
	*/
	function dropinfo(){

		let idlists=$("#idList").val().split(",");
		let listAll=[];
		for(let y=0;y<idlists.length;y++){
			listAll.push(idlists[y]);
		}
		//传送所有单号
		let datas={};
		datas["referenceNoList"]=idlists;
		datas["S_channel"]=$("#S_channel").val();
		datas["S_weight2"]=$("#S_weight2").val();
		/* datas["S_channel1"]=$("#S_channel option:selected").text();
		datas["S_pack"]=$("#S_pack").val();
		datas["S_num"]=idlists.length;
		datas["S_weight"]=$("#S_weight").val();
		datas["S_weight2"]=$("#S_weight2").val();
		datas["S_admin"]=$("#S_admin").val();
		datas["S_time"]=$("#S_time").val(); */
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :"${pageContext.request.contextPath}/Logistics/dropinfo?${_csrf.parameterName}=${_csrf.token}",
			dataType:"json",
			data:JSON.stringify(datas),
			async:false,
			success:function(data){
				console.log(data)
				let a="D41189475634841757603"
				if(data.dropNo==null){
					let a=data.dropNo;
				}
				$("#S_channel2").html($("#S_channel option:selected").text());
				$("#S_pack2").html($("#S_pack").val());
				$("#S_num2").html(idlists.length);
				$("#S_weight2s").html($("#S_weights").val());
				$("#S_weight22").html($("#S_weight2").val());
				$("#S_admin2").html($("#S_admin").val());
				$("#S_time2").html($("#S_time").val());
				
				
				$("#barcodes").barcode(a, "code128",{barWidth:1,barHeight:38,showHRI:true,fontSize:12}); 
				$("#myModal_Print").modal("show"); 
				$("#myModal_summary").modal("hide");
			},
			error:function(json){
				toastr.error("打印失败");
			}
		}); 
	}
	function test(){
		let datas={};
		$.ajax({
			type : "post",
			contentType : "application/json;charset=utf-8", 
			url :"${pageContext.request.contextPath}/Logistics/test?${_csrf.parameterName}=${_csrf.token}",
			dataType:"json",
			data:JSON.stringify(datas),
			async:false,
			success:function(data){
				console.log(data)
			},
			error:function(json){
				toastr.error("打印失败");
			}
		}); 
		
	}