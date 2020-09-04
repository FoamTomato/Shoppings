<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<el-dialog
  title="义达物流"
  :visible.sync="dialogVisible"
  width="80%"
  :before-close="handleClose">
  <!-- 表格 -->
  　　<!-- <el-row :gutter="20">
	  <el-col :span="12">国家英文<el-input size="small" style="float:left" v-model="consigneeCountryEname" placeholder="国家英文" ></el-input></el-col>
	  <el-col :span="12">国家中文<el-input v-model="consigneeCountryCname" placeholder="国家中文" size="small" style="float:left"></el-input></el-col>
	</el-row> -->
  　　<el-row :gutter="20">
	  <el-col :span="12">英文<el-input v-model="ydOr.ydInvoiceEnname" placeholder="英文" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12">中文<el-input v-model="ydOr.ydInvoiceCnname" placeholder="中文" size="small" style="float:left"></el-input></el-col>
	</el-row>
  　　<el-row :gutter="20">
	  <el-col :span="12">城市<el-input v-model="ydOr.ydConsigneeCity" placeholder="城市"size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12">省州<el-input v-model="ydOr.ydConsigneeProvince" placeholder="省州"  size="small" style="float:left"></el-input></el-col>
	</el-row>
  　　<el-row :gutter="20">
	  <!-- <el-col :span="12">公司<el-input v-model="company" placeholder="公司"></el-input></el-col> -->
	  <el-col :span="12">单位<el-input v-model="ydOr.ydUnitCode" placeholder="单位" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12">总价<el-input v-model="ydOr.ydInvoiceUnitcharge" :disabled="true" placeholder="总价" size="small" style="float:left"></el-input></el-col>
	</el-row>
  　　<el-row :gutter="20">
	  <!-- <el-col :span="12">币种<el-input v-model="invoiceCurrencycode" placeholder="单位" size="small" style="float:left"></el-input></el-col> -->
	</el-row>
  　　<el-row :gutter="20">
	  <el-col :span="12">sku<el-input v-model="ydOr.ydSku" placeholder="sku" :disabled="true" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12">数量<el-input type="number" v-model="ydOr.ydInvoiceQuantity" placeholder="数量" size="small" style="float:left"></el-input></el-col>
	</el-row>
  　　<el-row :gutter="20">
	  <el-col :span="12">物流<br>
	  <el-select v-model="ydOr.ydShippingMethod" placeholder="请选择"  size="small" style="float:left;width:100%"> 
	    <el-option
	      v-for="item in logoutlist"  v-if="item.status=='3'"
	      :key="item.id"
	      :label="item.logisticsName"
	      :value="item.shortName">
	    </el-option>
	  </el-select>
	  </el-col>
	  <el-col :span="12">重量/g<el-input v-model="ydOr.ydOrderWeight" placeholder="重量/g" size="small" style="float:left"></el-input></el-col>
	  <!-- <el-input v-model="shippingMethod" placeholder="物流" size="small" style="float:left"></el-input></el-col> -->
	</el-row>
  　　<!-- <el-row :gutter="20">
	  <el-col :span="12">海关协制编号<el-input v-model="hsCode" placeholder="海关协制编号" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12">产品销售价格<el-input v-model="salePrice" placeholder="产品销售价格" size="small" style="float:left"></el-input></el-col>
	</el-row> -->
  　　<!-- <el-row :gutter="20">
	  <el-col :span="12">销售结算币种<el-input v-model="saleCurrency" placeholder="销售结算币种" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12">产品中文类目<el-input v-model="categoryName" placeholder="产品中文类目" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12">产品英文类目<el-input v-model="parentEnName" placeholder="产品英文类目" size="small" style="float:left"></el-input></el-col>
	</el-row> 
  　　<el-row :gutter="20">
	</el-row>-->
  　　<el-row :gutter="20">
	  <el-col :span="8">外包装数<el-input v-model="ydOr.ydOrderPieces" placeholder="外包装数" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="8">申报种类<el-input v-model="ydOr.ydMailCargoType" placeholder="申报种类" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="8">货物类型<el-input v-model="ydOr.ydCargotype" placeholder="货物类型" size="small" style="float:left"></el-input></el-col>
	</el-row>
  　　<!-- <el-row :gutter="20">
	  <el-col :span="8">长<el-input v-model="length" placeholder="长" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="8">宽<el-input v-model="width" placeholder="宽" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="8">高<el-input v-model="height" placeholder="高" size="small" style="float:left"></el-input></el-col>
	</el-row> -->
	<!-- 多选框 -->
  　　<!-- <el-row :gutter="20">
	  <el-col :span="12"><el-checkbox v-model="isContainsBattery">是否包含电池</el-checkbox></el-col>
	  <el-col :span="12"><el-checkbox v-model="isAneroidMarkup">非液体化妆品</el-checkbox></el-col>
	</el-row>
  　　<el-row :gutter="20">
	  <el-col :span="12"><el-checkbox v-model="isOnlyBattery">是否纯电池</el-checkbox></el-col>
	  <el-col :span="12"><el-checkbox v-model="isBreakable">是否易碎</el-checkbox></el-col>
	</el-row> -->
  　　<el-row :gutter="20">
	  <el-col :span="12">订单备注<el-input v-model="ydOr.ydOrderInfo" placeholder="订单备注" size="small" style="float:left"></el-input></el-col>
	  <el-col :span="12"><el-checkbox v-model="ydOr.ydReturnSign" style="line-height:80px" false-label="N" true-label="Y">是否退回</el-checkbox></el-col>
	</el-row>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submit()" v-if="optionList == '0'">确 定</el-button>
    <el-button type="primary" @click="submit2()" v-if="optionList == '3'">添加</el-button>
    <el-button type="primary" @click="ydUpdateOrder()" v-if="optionList == '1'">修改</el-button>
    <el-button type="primary" @click="submit()" v-if="optionList == '2'">批量添加</el-button>
  </span>
</el-dialog>