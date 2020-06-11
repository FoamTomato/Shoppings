<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<div id="searchCates">
<!-- 模态框（Modal） -->
        <div class="modal fade" style="overflow:hidden" id="myModal_summary"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <nobr class="Foam-title">
                        
                        </nobr>
                    </div>
                    <div class="modal-body summary_body" style="display:block;height:260px">
	                   <el-row :gutter="20">
  						<el-col :span="12">物流渠道
	  						<el-select v-model="S_logistic" placeholder="请选择">
							    <el-option
							      v-for="item in logoutlist"
							      v-if="item.status=='2'"
							      :key="item.id"
							      :label="item.logisticsName"
							      :value="item.shortName">
							    </el-option>
					  		</el-select>
					  	</el-col>
						<el-col :span="12">渠道袋序
						  	<el-input v-model="S_pack"></el-input>
						</el-col>
					  </el-row>
	                  <el-row :gutter="20">
	                  	<el-col :span="12" style="display:none">袋内件数(个)
	                  		<el-input v-model="S_num"></el-input>
	                  	</el-col>
	                  	<el-col :span="12">净重(kg)
	                  		<el-input v-model="S_weights"></el-input>
	                  	</el-col>
	                  	<el-col :span="12">毛重(kg)
	                  		<el-input v-model="S_weight2"></el-input>
	                  	</el-col>
	                  </el-row>
	                  <el-row :gutter="20">
	                  	<el-col :span="12" >操作员
	                  		<el-input v-model="sadmin"></el-input>
	                  	</el-col>
	                  	<el-col :span="12">操作时间
	                  		<el-input v-model="S_time"></el-input>
	                  	</el-col>
	                  </el-row>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" @click="modalCsum()" class="btn btn-primary">
                            	集拼
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
</div>