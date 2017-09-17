<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
<script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css"
	href="../../js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../../js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/default.css">
<script type="text/javascript"
	src="../../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/form.js"></script>
<script type="text/javascript" src="../../js/crud.js"></script>
<script type="text/javascript" src="../../js/date.js"></script>
</head>
<body>
	<!-- 搜索框 -->
	<div class="datagrid-toolbar" style="height: 60px;">
		<form id="searchForm" method="post">
		<!--  查询条件：订单号，寄件人姓名，收件人姓名，收件人手机，快递产品类型，订单状态，下单时间-->
		<table>
			<tr>
				<td>订单号:<input type="text" name="orderNum" /></td>
				<td>寄件人姓名:<input type="text" name="sendName"/></td>
				<td>收件人姓名:<input type="text" name="recName"/></td>
				<td>收件人手机:<input type="text" name="recMobile"/></td>
			</tr>
			<tr>
				<td>快递产品类型:<input type="text" name="sendProNum"/></td>
				<td>订单状态:<select name="status"  style="width: 100px">
								<option value="">--请选择--</option>
								<option value="1">已下单</option>
								<option value="2">分单成功</option>
								<option value="3">已取件</option>
								<option value="4">运输中</option>
								<option value="5">派送中</option>
								<option value="6">已签收</option>
								<option value="7">已取消</option>
							</select>
				</td>
				<td>下单时间:<input type="text" name="orderTime" class="easyui-datebox" /></td>
				<td><a id="search" class="easyui-linkbutton" href="javascript:void(0)" icon="icon-search">查询</a></td>
			</tr>	
		</table>
		</form>
	</div>

	
	
	<!-- 列表 -->
	<table id="list"></table>
	
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="cancelBtn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel'">取消订单</a> 
	</div>
	
	<script type="text/javascript">
		
		var action = "order";

		// 定义列
		var columns = [ [ {
			field : 'id',
			title : '订单id',
			width : 100,
			checkbox : true
		}, {
			field : 'orderNum',
			title : '订单号',
			width :300
		},{
			field : 'sendName',
			title : '发件人姓名',
			width : 100
		} ,{
			field : 'recName',
			title : '收件人姓名',
			width : 100
		},{
			field : 'recMobile',
			title : '收件人手机',
			width : 200
		},{
			field : 'sendProNum',
			title : '快递产品类型',
			width : 100
		},{
			field : 'status',
			title : '订单状态',
			width : 100,
			formatter:function(value,row,index){
				if(value=='1'){
					return "已下单";
				}
				if(value=='2'){
					return "分单成功";
				}
				if(value=='3'){
					return "已取件";
				}
				if(value=='4'){
					return "运输中";
				}
				if(value=='5'){
					return "派送中";
				}
				if(value=='6'){
					return "已签收";
				}
				if(value=='7'){
					return "已取消";
				}
				if(value==null){
					return "";
				}
			}
		},{
			field : 'orderTime',
			title : '下单时间',
			width : 200,
			formatter:function(value,row,index){
				if(value!=null){
					//调用工具的方法格式化日期
					 return dateFormat(value);
				}else{
					return "";
				}				
			}
		}] ];
		
		//点击取消订单按钮
		$("#cancelBtn").click(function(){
			//获取选中的行
			var rows = $("#list").datagrid("getSelections");
			//判断是否选择了一行数据来操作
			if(rows.length !=1){
				$.messager.alert("提示","必须且只能选择一行数据来进行修改操作","info");
				return ;
			}
			if(rows[0].status=='7'){
				$.messager.alert("提示","您选择的订单已被取消","error");
				return ;
			}
			$.messager.confirm("操作提示", "您确定要执行操作吗？", function (i) {
				if(i){
					//确认取消订单 获取订单的id  
					var orderId = rows[0].id;
					
					//异步发送请求给后台
					$.post("../../order/cancelOrder.action",{id:orderId},function(data){
						//订单取消成功
						if(data.success){
							$.messager.alert("提示","订单取消成功!","info");	
							//刷新页面
							$("#list").datagrid("reload");
						}else{
							$.messager.alert("提示","取消订单操作失败 原因: "+data.msg,"error");	
						}
					},"json");
				}
			});
		});
	</script>
</body>
</html>