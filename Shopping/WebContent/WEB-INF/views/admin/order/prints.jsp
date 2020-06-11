<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 模态框（Modal） -->
<div class="modal fade" style="overflow: hidden" id="myModal_Print"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="height:530px;width:1000px;margin-left: -100px;">
		 <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <nobr class="Foam-title" id="myModalLabelp">
                        
                        </nobr>
                    </div>
			<div id="pring_t" class="table_size_1" style="float:left;width: 98mm; height: 98mm;">
				<table style="width: 100%" border="1px solid black" cellspacing="0"
					cellpadding="100">
					<tr>
						<td colspan="2" style="text-align: center;">集拼单号
							<div>
								<div id="barcodes" style=""></div>
							</div>
						</td>
					</tr>
					<tr>
						<td>物流渠道</td>
						<td class="table_size_2">{{changeLogis(S_logistic)}}</td>
					</tr>
					<tr>
						<td>渠道袋序</td>
						<td class="table_size_2" >{{S_pack}}</td>
					</tr>
					<tr>
						<td>袋内件数（个）</td>
						<td class="table_size_2">{{S_num}}</td>
					</tr>
					<tr>
						<td>净重（KG）</td>
						<td class="table_size_2" >{{S_weights}}</td>
					</tr>
					<tr>
						<td>毛重（KG）</td>
						<td class="table_size_2" >{{S_weight2}}</td>
					</tr>
					<tr>
						<td>操作员</td>
						<td class="table_size_2" >{{sadmin}}</td>
					</tr>
					<tr>
						<td>操作时间</td>
						<td class="table_size_2">{{S_time}}</td>
					</tr>
				</table>
			</div>
			<button  class="btn btn-success" style="margin:10px" @click="print2()">打印</button>
		</div>
	</div>
</div>
