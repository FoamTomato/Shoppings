<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 模态框（Modal） -->
<div class="modal fade" style="overflow: hidden" id="myModal_outofPrint"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="height: 550px;width:1000px;margin-left: -100px;">
			<div class="modal-header" style="height:60px">
			<button class="btn btn-success" @click="print2">打印</button>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<nobr class="Foam-title" id="myModalLabelp"> </nobr>
			</div>
			<div  style="margin-left:100px;padding-top:20px;height:480px;overflow-y: auto">
				<div id="pring_t">
				<div v-for="(item,i) in printlist">
					<div class="table_size_1"
						style="float: left; width: 98mm; height: 98mm;">
						<table style="width: 100%" border="1px solid black"
							cellspacing="0" cellpadding="525">
							<tr>
								<td colspan="2" style="text-align: center;">集拼单号
									<div>
										<input class="barcodes" type="hidden" :value="item.jpLaks">
										<div class="sbar"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>物流渠道</td>
								<td class="table_size_2" id="S_channel2">{{item.jpLogistic}}</td>
							</tr>
							<tr>
								<td>渠道袋序</td>
								<td class="table_size_2" id="S_pack2">{{i+1}}</td>
							</tr>
							<tr>
								<td>袋内件数（个）</td>
								<td class="table_size_2" id="S_num2">{{item.jpLength}}</td>
							</tr>
							<tr>
								<td>净重（KG）</td>
								<td class="table_size_2" id="S_weight2s">{{item.jpSuttle}}</td>
							</tr>
							<tr>
								<td>毛重（KG）</td>
								<td class="table_size_2" id="S_weight22">{{item.jpRoughweight}}</td>
							</tr>
							<tr>
								<td>操作员</td>
								<td class="table_size_2" id="S_admin2">{{item.jpAdmin}}</td>
							</tr>
							<tr>
								<td>操作时间</td>
								<td class="table_size_2" id="S_time2">{{item.jpTime}}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			</div>
			
		</div>

	</div>
</div>
