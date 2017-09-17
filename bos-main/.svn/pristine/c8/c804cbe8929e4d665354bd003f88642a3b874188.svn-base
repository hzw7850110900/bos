<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>运单录入</title>
<script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/default.css">
<script type="text/javascript" src="../../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/form.js"></script>

</head>
<body>
	
	<!-- 列表 -->
	<table id="list"></table>
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
		<a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">保存</a>
		<a id="delBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
	</div>
	
	<div style="width:95%;margin:10px auto">
			<form id="waybillForm" method="post" 
				action="../../wayBill/saveNormal.action" >
			<div class="table-top">
				<table class="table-edit" width="95%">
					<tr class="title">
						<td colspan="6">单号信息</td>
					</tr>
					<tr>
						<td>订单号</td>
						<td>
							<input type="hidden" name="order.id" id="orderId" />
							<input type="text" name="order.orderNum" id="orderNum"/>
						</td>
						<td>运单号</td>
						<td>
							<input type="text" name="wayBillNum" id="wayBillNum"/>
							<input type="hidden" name="wayBillId" id="wayBillId" />
						</td>
					</tr>
					<tr>
						<td>到达地</td>
						<td><input type="text" name="arriveCity" required="true" /></td>
						<td>产品时限</td>
						<td>
							<select class="easyui-combobox" name="sendProNum">
									<option value="速运当日">速运当日</option>
									<option value="速运次日">速运次日</option>
									<option value="速运隔日">速运隔日</option>
								</select>
						</td>
						<td>配载要求</td>
						<td>
							<select class="easyui-combobox" name="floadreqr">
									<option value="无">无</option>
									<option value="禁航空">禁航空</option>
									<option value="禁铁路航空">禁铁路航空</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>受理单位</td>
						<td><input type="text" name="courier.company" required="true" /></td>
						<td>快递员</td>
						<td><input type="text" id="courierId" name="courier.name" required="true" /></td>
					</tr>
				</table>
			</div>
			<div class="table-center" style="margin-top:15px">
				<div class="col-md-5">
					<table class="table-edit send" width="95%">
						<tr class="title">
							<td colspan="4">寄件人信息</td>
						</tr>
						<tr>
							<td>寄件人</td>
							<td><input type="text" name="sendName" required="true" /></td>
							<td>地址</td>
							<td><input type="text" name="sendAddress" required="true" /></td>
						</tr>
						<tr>
							<td>公司</td>
							<td><input type="text" name="sendCompany" required="true" /></td>
							<td>电话</td>
							<td><input type="text" name="sendMobile" required="true" /></td>
						</tr>
					</table>

					<table class="table-edit receiver" width="95%">
						<tr class="title">
							<td colspan="4">收件人信息</td>
						</tr>
						<tr>
							<td>收件人</td>
							<td><input type="text" name="recName" required="true" /></td>
							<td>地址</td>
							<td><input type="text" name="recAddress" required="true" /></td>
						</tr>
						<tr>
							<td>公司</td>
							<td><input type="text" name="recCompany" required="true" /></td>
							<td>电话</td>
							<td><input type="text" name="recMobile" required="true" /></td>
						</tr>
					</table>
					<table class="table-edit number" width="95%">
						<tr class="title">
							<td colspan="4">货物信息</td>
						</tr>
						<tr>
							<td>原件数</td>
							<td><input type="text" name="num" required="true" /></td>
							<td>实际重量</td>
							<td><input type="text" name="weight" required="true" /></td>
						</tr>

						<tr>
							<td>货物</td>
							<td><input type="text" name="goodsType" required="true" /></td>
							<td>体积</td>
							<td><input type="text" name="vol" required="true" /></td>
						</tr>
					</table>
				</div>
				<div class="col-md-7">
					<table class="table-edit security" width="95%">
						<tr class="title">
							<td colspan="6">包装信息</td>
						</tr>
						<tr>
							<td>保险类型</td>
							<td>
								<select class="easyui-combobox">
									<option value="0">不保险</option>
									<option value="1">委托投保</option>
									<option value="2">自带投保</option>
									<option value="3">保价</option>
								</select>
							</td>
							<td>保险费</td>
							<td><input type="text" name="secuityprice" required="true" /></td>
							<td>声明价值</td>
							<td><input type="text" name="value" required="true" /></td>
						</tr>

						<tr>
							<td>原包装</td>
							<td>
								<select class="easyui-combobox">
									<option value="0">木箱</option>
									<option value="1">纸箱</option>
									<option value="2">快递袋</option>
									<option value="3">其他</option>
								</select>
							</td>
							<td>实际包装</td>
							<td>
								<select class="easyui-combobox">
									<option value="0">木箱</option>
									<option value="1">纸箱</option>
									<option value="2">快递袋</option>
									<option value="3">其他</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>包装费</td>
							<td><input type="text" name="packageprice" required="true" /></td>
							<td>包装要求</td>
							<td><input type="text" name="packageprice" required="true" /></td>
						</tr>
					</table>

					<table class="table-edit max" width="95%">
						<tr>
							<td>实际件数</td>
							<td><input type="text" name="realNum" required="true" /></td>
							<td>计费重量</td>
							<td><input type="text" name="priceWeight" required="true" /></td>
						</tr>
						<tr>
							<td>预收费</td>
							<td><input type="text" name="planprice" required="true" /></td>
							<td><button class="btn btn-default">计算</button></td>
						</tr>
					</table>

					<table class="table-edit money" width="95%">
						<tr class="title">
							<td colspan="6">计费信息</td>
						</tr>
						<tr>
							<td>结算方式</td>
							<td>
								<select class="easyui-combobox">
									<option value="0">现结</option>
									<option value="1">代付</option>
									<option value="2">网络</option>
									
								</select>
							</td>
							<td>代收款</td>
							<td><input type="text" name="priceWeight" required="true" /></td>
							<td>到付款</td>
							<td><input type="text" name="priceWeight" required="true" /></td>
						</tr>

					</table>
					<table class="table-edit feedback" width="95%">
						<tr class="title">
							<td colspan="4">配送信息</td>
						</tr>
						<tr>
							<td><input type="checkbox">反馈签收</td>
							<td><input type="checkbox">节假日可收货</td>
							<td>送达时限</td>
							<td><input type="text" class="easyui-datebox" data-options="editable:false" /></td>
						</tr>

						<tr>
							<td>处理方式</td>
							<td>
								<select class="easyui-combobox">
									<option value="0">无</option>
									<option value="1">不可开箱验货</option>
									<option value="2">开开箱单不可开内包</option>
									<option value="3">可开箱和内包</option>
								</select>
							</td>
							<td>签单返回</td>
							<td>
								<select class="easyui-combobox">
									<option value="0">箱单</option>
									<option value="1">原单</option>
									<option value="2">附原单</option>
									<option value="3">网络派送单</option>
								</select>
							</td>
						</tr>
					</table>
					<table class="table-edit tips" width="95%">
						<tr>
							<td>重要提示</td>
							<td><textarea style="width:250px;height: 80px;"></textarea></td>
						</tr>

					</table>
				</div>
			</div>
			<div class="clearfix"></div>
			</form>			
		</div>
</body>
<script type="text/javascript">
$(function(){
	//运单id
	var id="${param.id}";
	var orderNum = "${param.orderNum}";
	
	if(id!=null && id!=""){
		//根据运单id查询后台数据回显
		
		$.post("../../wayBill/get.action",{uuid:id},function(data) {
			$("#waybillForm").form("load",data);
			$("#wayBillId").val(data.id);
			//alert(data.order.id);
			$("#orderId").val(data.order.id);
			//$.messager.alert("",id);
		},"json");
		
	//传订单号	
	if(orderNum!=null && orderNum!="") {
		//$.messager.alert(orderNum);
		$("#orderNum").val(orderNum);
	}
		
	} 
	//根据订单号自动填空订单信息
	$("#orderNum").blur(function(){
		$.post("../../order/findByorderNum.action",{orderNum:$("#orderNum").val()},function(data){
					//填充表单当中的订单信息
					$("#waybillForm").form("load",data);
					//填充隐藏域ID的信息
					$("#orderId").val(data.id);				
		},"json");
	});
	
	//根据运单号自动填充运单的信息
	$("#wayBillNum").blur(function(){
		//填充运单信息
		$.post("../../wayBill/findBywayBillNum.action",{wayBillNum:$("#wayBillNum").val()},function(data){
				//填充表单当中的运单信息
			$("#waybillForm").form("load",data);
				//填充运单id
				$("#wayBillId").val(data.id);
		},"json");
	});
	
	/* //保存运单
	$("#editBtn").click(function(){		
		 //保存运单
		 $.post("../../wayBill/save.action",$("#waybillForm").serialize(),function(data){
			 if(data.success){
				window.parent.$.messager.alert("提示","保存成功！","info");
				window.location.href="waybill_manage.jsp";
			 }else{
				$.messager.alert("提示","保存失败,原因是:"+data.msg,"error");	 
			 }
		 },"json");
	}); */
	
	//保存运单
	$("#editBtn").click(function(){
		//提交表单
		$("#waybillForm").submit();
	});
	
});
</script>
</html>