<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   

<%@page session="true"%>
<!DOCTYPE html>
<html style="overflow: hidden;padding:0px;margin:0px">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>集拼</title>

<script src="${pageContext.request.contextPath}/resources/admin/jquery.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/admin/jQuery.print.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/admin/toastr-master/build/toastr.min.js"></script>
<jsp:include page="../commonCss.jsp" />
<script src="${pageContext.request.contextPath}/resources/jss/sweetalert2@9.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/vue/vue-resource.js"></script>
</head>
<body class="blank" >
<jsp:include page="../main/top.jsp" />
<jsp:include page="../main/left.jsp" />
<div id="wrapper">
<jsp:include page="outofPrints.jsp" />
		<div class="small-header transition animated fadeIn">
			<div class="content animate-panel" style="margin-bottom: -40px">
				<div class="hpanel">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="hpanel">
								
									<div class="form-group col-lg-4">
											<label>查询内容</label>
												<input type="text" v-model="selectText" id="orderNum" name="orderNum" class="form-control col-sm-12" placeholder="集拼号/参考编号" style="width: 100%"/>
									</div>
									
									<div class="form-group col-lg-2">
											<label>快捷日期</label>
											<select id="hj_shippingMethods" class="form-control" v-model="shortCutTime" type="text">
							                      	  <option value="">全部</option>
							                      	  <option value="0">今天</option>
							                      	  <option value="1">一天内</option>
													  <option value="2">两天内</option>
													  <option value="3">三天内</option>
													  <option value="5">五天内</option>
													  <option value="7">七天内</option>
							                </select>
									</div>
									
									<div class="form-group col-lg-2">
											<label>物流方式</label>
												<select id="hj_shippingMethods" class="form-control" v-model="logistics" type="text">
							                      	  <option value="">全部</option>
							                      	  <c:forEach items="${logoutlist}" var="item">
							                     	  	<option value="${item.shortName}">${item.logisticsName}</option>
							                     	  </c:forEach>
							                      </select>
									</div>
						
									<div class="form-group col-lg-2">
											<label>开始日期</label>
												<input type="date" v-model="startTime" id="startDatas" name="orderNum" class="form-control col-sm-12"  style="width: 100%"/>
										</div>
										
										<div class="form-group col-lg-2">
											<label>结束日期</label>
												<input type="date" v-model="endTime" id="endDatas" name="orderNum" class="form-control col-sm-12"  style="width: 100%"/>
									</div>
									
									<div class="text-right m-t-xs">
										<nobr style="float:left;margin-left:15px;margin-top:10px">展示 <input min=0 type="number" id="pagsNums" style="width:50px" v-model="limit"> 个</nobr>
									</div>
									<div style="float:right">
									<button id="allInner" class="btn btn-success" style="margin-right: 5px;margin-top: 5px"
												@click="checkAll"	type="button">
												<strong>全选</strong>
									</button>
									<button id="reverse" class="btn btn-success" style="margin-right: 5px;margin-top: 5px"
												@click="checkBack"	type="button">
												<strong>反选</strong>
									</button>
									<button id="search"  class="btn btn-success" style="margin-right: 5px;margin-top: 5px"
												@click="selectAllList(1)"	type="button">
												<strong>查询</strong>
									</button>
									<button id="reset" class="btn btn-success" style="margin-right: 5px;margin-top: 5px"
											    @click="reset"	style="margin-right: 20px;" type="reset">
												<strong>重置</strong>
									</button>
									<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_outof_del')}">
										<button id="delOrder" class="btn btn-error" style="margin-right: 5px;margin-top: 5px"
													@click="dels"	type="button">
													<strong style="color:red;font-weight:200">删除</strong>
										</button>
									</c:if>
									<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_outof_print')}">
										<button id="gettrackList"  class="btn btn-info" style="margin-right: 5px;margin-top: 5px"
													@click="prints"	type="button">
													<strong>打印集拼</strong>
										</button>
									</c:if>
									
									<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_outof_export')}">
										<form id="formtyw2" method="post" style="float: right" action="${pageContext.request.contextPath}/Logistics/outofleft?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
											<input id="idList" name="idList" type="text" v-model="idList" style="display:none"/>
											<button id="tojp" class="btn btn-info" style="margin-right: 5px;margin-top: 5px"
														@click="outofleft"	type="button">
														<strong>导出集拼</strong>
											</button>
										</form>
									</c:if>
									
									<c:if test="${fn:contains(jurisdiction.jurisdiction,'order_outof_post')}">
										<button id="postOut" class="btn btn-info" style="margin-right: 5px;margin-top: 5px"
													@click="postOut"	type="button">
													<strong>发送集拼</strong>
										</button>
									</c:if>
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
						<div class="panel-body" style="height:650px;overflow-y:auto">
						
						<!-- 集拼界面 -->
						 <div v-if="type == 'A'">
							 <div style="text-align: center;margin-bottom:5px">
								<a @click="selectAllList(1)">首页</a>
								<div v-for="(item,i) in indexs()" style="display:inline">
									<a @click="selectAllList(item)" v-if="item == page" style="color:red;margin-left:5px;margin-right:5px">{{item}}</a>
									<a @click="selectAllList(item)" v-if="item != page" style="margin-left:5px;margin-right:5px">{{item}}</a>
								</div>
								
								<a @click="selectAllList(endpage)">尾页</a> &nbsp; 
								<nobr style="color:red">黄色部分未导出，白色部分已导出</nobr>
							</div>
						 	<input type="checkbox" class="CheckALL" style="display:none">
							<div v-for="(item,i) in list">
								<div :class="['col-lg-1','colf_1',item.jpStandby1!=1?'colorr':'']">
								<br>
								<p><a @click="goIn(item.id)">{{item.jpLaks}}</a></p>
								<p>
									{{ellipsis("净重"+item.jpSuttle+"kg"
									+"毛重"+item.jpRoughweight+"kg"
									+"件数"+item.jpLength,20)}}
								</p>
								<p>
									时间:{{item.jpTime}}
								</p>
								<p>{{item.jpAdmin}}
								<input type="checkbox" class="Checks" :id="item.id" style="margin-left:20px" @click="selAll">
								</p>
								</div>
							</div>
						</div>
						<!-- 详情界面 -->
						 <div v-if="type == 'B'">
						 <p style="margin-left:5px"><a class="backs" @click="selectAllList(page)">◄返回</a></p>
						 	<div v-for="(item,i) in list">
						 	<div class="col-lg-1 colf_2">
						 		<p>参考编号:{{item.hjReferenceno}}</p>
						 		<p>订单号:{{item.hjShipperhawbcode}}</p>
						 		<p>跟踪号:{{item.hjStandy7}}</p>
						 		<p>物流方式:{{item.hjShippingmethod}}</p>
						 		<p>价格:{{item.hjInvoiceunitcharge}}</p>
						 		<p>重量:{{item.hjInvoiceweight}}g</p>
						 		<p>更新日期:{{item.hjStandy1}}</p>
							</div>
						 	</div>
						 </div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
</div>

	<script type="text/javascript">
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
		var vm=new Vue({
			el:'#wrapper',
			data:{
				selectText:"",//查找的文本内容
				page:"1",//第几页
				limit:"50",//每次展示多少个
				shortCutTime:"",//快捷日期
				startTime:"",//开始日期
				endTime:"",//结束日期
				logistics:"",//物流方式
				list:[],//返回的list对象
				type:"",
				idList:"",//所有被选择的产品
				printlist:[],//需要被打印的商品
				endpage:"",//尾页
				totalPage:[],//所有页数显示数据
				logoutlist:[],
				jurisdiction:'',
			},
			created(){
				this.selectAllList(this.page);
				this.$http.post("order/select/list?${_csrf.parameterName}=${_csrf.token}").then(result=>{
				    this.logoutlist=result.body.logoutlist
				    this.jurisdiction=result.body.jurisdiction
				})
			},
			mounted(){
			},
			updated(){
			},
			methods:{
				selectAllList(data){
					//创建一个datas存储值
					let datas={}
					//通过双向绑定实时赋值
					datas["selectText"]=this.selectText
					if(data!=null){
						this.page=data
					}
					datas["page"]=this.page
					datas["limit"]=this.limit
					datas["shortCutTime"]=this.shortCutTime
					datas["startTime"]=this.startTime
					datas["endTime"]=this.endTime
					datas["logistics"]=this.logistics
					//ajiox请求方式
					this.$http.post("Logistics/outoflist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						this.list=result.body.list
						this.endpage=result.body.pages
						// 赋值操作
						for(let val of this.list){
						//此处为重点
						val.jpTime=this.getDate(val.jpTime)
						}
					})
					//更新单选
					this.type="A"
				},indexs(){
					var left=1
					var right=this.endpage
					var ar=[]
					if(this.endpage>=5){
						if(this.page>3 && this.page < this.endpage -2){
							left = this.page -2
							right =this.page +2
						}else{
							if(this.page<=3){
								left = 1
								right = 5
							}else{
								left=this.endpage-4
								right=this.endpage
							}
						}
					}
					while(left<=right){
						ar.push(left)
						left++
					}
					return ar
				},
				getDate (item) {
					var time = new Date(item) 
					var year = time.getFullYear()
					var month = time.getMonth() + 1
					var date = time.getDate()
					var hours=time.getHours()
					var mins=time.getMinutes()
					var sec=time.getSeconds()
					return year + '-' + month + '-' + date+" "+hours + ':' + mins + ":" + sec
				},
				goIn(data){
					//根据id查询所有的订单参考号
					this.$http.post("Logistics/outofLogicsticlist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify({id:data}),{emulateJSON:true}).then(result=>{
						//根据订单参考号，返回对象list
						//遍历对象参数，并上传到界面，该界面只能查看运单的简介
						//添加返回界面
						this.list=result.body
						// 赋值操作
						for(let val of this.list){
						//此处为重点
						val.hjStandy1=this.getDate(val.hjStandy1)
						val.hjShippingmethod=this.hjShippingmethods(val.hjShippingmethod)
						}
					})
					this.type="B"
				},
				ellipsis(value,leng){
		        	  //长度限制
		                if (!value) return '';
		                if (value.length > leng) {
		                    return value.slice(0,leng) + '...'
		                }
		                return value
		          },
				hjShippingmethods(val){
		        	  //转换物流
		        	  let chans=this.logoutlist
		        	  for(let i=0;i<chans.length;i++){
		        		  if(chans[i].shortName==val||chans[i].standy5==val){
		        			  return chans[i].logisticsName
		        		  }
		        	  }
				}
				,
				selAll(){
					//单选
					var checkS = $(".Checks")
					//获取全选勾选框
					let checke = $(".CheckALL")
					//假设全选是选中的
					let isSelectAll=true
					let reslut=""
					for(var i = 0;i<checkS.length;i++){
						if(checkS[i].checked==true){
			                reslut+=checkS[i].id+","
			        	}else{
			        		isSelectAll=false
			        	}
					}
					checke[0].checked=isSelectAll
					this.idList=reslut.substr(0,reslut.length-1)
				},
				checkAll(){
					//全选
					//获取全选勾选框
					let checke = $(".CheckALL");
					//获取所有的勾选框
					let checkS = $(".Checks");
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
				checkBack(){
						//反选
						//获取所有的勾选框
						let checkS = $(".Checks");
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
				reset(){//重置
					this.selectText=""//查找的文本内容
					this.shortCutTime=""//快捷日期
					this.startTime=""//开始日期
					this.endTime=""//结束日期
					this.logistics=""//物流方式
				},
				dels(){//删除
					//创建传输值
					let datas={};
					datas["idList"]=this.idList
					if(this.idList==""){
						 toastr.error("请选择需要删除的商品");
						 return false;
					 }
					
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
							 this.$http.post("Logistics/dellist?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
								 Swal.fire(
									      '删除!',
									      '您已经移除了它.',
									      'success'
									    )
								this.selectAllList()
							})
						  }else{
							  Swal.fire("取消", "点击了取消", "error");
						  }
						})
					
				},
				prints(){
					//选择的勾选框不能为空
					if(this.idList==""){
						 toastr.error("请选择需要打印的集拼");
						 return false;
					 }
					let datas={}
					datas["idList"]=this.idList
					//到后台根据勾选的查询集拼详情
					//ajiox请求方式
					this.$http.post("Logistics/outoflists?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						//遍历返回的集拼单
						console.log(result.body)
						this.printlist=result.body
						for(let val of this.printlist){
							//此处为重点
							val.jpTime=this.getDate(val.jpTime)
							val.jpLogistic=this.hjShippingmethods(val.jpLogistic)
							//val.jpStandby5="<div id='xx"+val.id+"'></div>"
							//this.bars(val.id,val.jpLaks)
						}
					})
					//打印按钮写上面
					$("#myModal_outofPrint").modal("show")
				},outofleft(){//导出集拼
					//选择的勾选框不能为空
					if(this.idList==""){
						 toastr.error("请选择需要导出的集拼");
						 return false;
					 }
					let idlist=this.idList.split(",")
					let list=this.list
					for(let i=0;i<idlist.length;i++){
						for(let a=0;a<list.length;a++){
							if(idlist[i]==list[a].id){
								this.list[a].jpStandby1=1
							}
						}
					}
					$("#formtyw2").submit()
					//传入所有的id集合
					//根据id查询所有符合条件的运单
					//ajiox请求方式
					/* this.$http.post("Logistics/outofleft?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
					}) */
				},
				print2(){
					$("#pring_t").jqprint()
				},
				barcodes(i){//动态id
					return "barcodes"+i
				}
				,bars(id,data){
					$("#xx"+id).barcode(data, "code128",{barWidth:2,barHeight:88,showHRI:true,fontSize:24})
				},
				postOut(){
					//发送集拼
					if(this.idList==""){
						 toastr.error("请选择需要发送的集拼");
						 return false;
					 }
					let datas={}
					datas["idList"]=this.idList
					this.$http.post("Logistics/postOut?${_csrf.parameterName}=${_csrf.token}",JSON.stringify(datas),{emulateJSON:true}).then(result=>{
						if(result.body.status==1){
							toastr.success("集拼成功")	
						}else if(result.body.status==0){
							toastr.error("集拼失败"+result.body.errormsg)	
							console.log(result.body.errormsg)
						}else{
							toastr.warn("集拼发送未获取到结果")	
						}
					})
				}
			},
			updated : function(){


				     this.$nextTick(function(){

				          //在下次 DOM 更新循环结束之后执行这个回调。在修改数据之后立即使用这个方法，获取更新后的DOM.
						let bar=$(".barcodes")
						for(let c=0;c<bar.length;c++){
							$(".sbar").eq(c).barcode(bar.eq(c).val(), "code128",{barWidth:2,barHeight:88,showHRI:true,fontSize:24})
						}
				     })

				 }

		})
		
	</script>
	
<script src="${pageContext.request.contextPath}/resources/admin/jquery-barcode.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/jquery-barcode.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jss/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tanathos-jquery.jqprint-13a4f0e/jquery.jqprint-0.3.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>