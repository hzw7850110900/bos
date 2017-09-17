<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>人工分单</title>
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
<script type="text/javascript" src="../../js/date.js"></script>
</head>
<body>
	<!-- 搜索框 -->



	<!-- 列表 -->
	<table id="list"></table>

	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="houseBill" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'">手动分单</a>

	</div>

	<!--选择要指派取件的快递员  -->
	<div id="houserWin" class="easyui-window"
		data-options="title:'快递员指派',closed:true"
		style="width: 300px; height: 200px; top: 50px; left: 200px">
		<div class="datagrid-toolbar">
			<a id="save" class="easyui-linkbutton" href="#" icon="icon-save">开始分单</a>
		</div>
		<div>
			<form id="houserForm" method="post">
				<input type="hidden" id="orderId" name="id">
				<table>
					<tr>
						<td>快递员指派</td>
						<td>
							<!--使用combobox来抓取后台的数据  --> 
						     <input 
							name="courierId" id="courierId">
							<!--data-options="valueField:'id',textField:'name',url:'../../courier/list.action'"  -->
						</td>
					</tr>
				</table>
			</form>
		</div>
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
			width : 300
		}, {
			field : 'sendName',
			title : '发件人姓名',
			width : 80
		}, {
			field : 'sendArea',
			title : '发件人区域',
			width : 200,
			formatter : function(value, row, index) {
				if (value != null) {
					return value.areaName;
				}
			}
		}, {
			field : 'sendAddress',
			title : '发件人详细地址',
			width : 200
		}, {
			field : 'status',
			title : '订单状态',
			width : 100,
			formatter : function(value, row, index) {
				if (value == '1') {
					return "已下单";
				}
				if (value == '2') {
					return "分单成功";
				}
				if (value == '3') {
					return "已取件";
				}
				if (value == '4') {
					return "运输中";
				}
				if (value == '5') {
					return "派送中";
				}
				if (value == '6') {
					return "已签收";
				}
				if (value == '7') {
					return "已取消";
				}
				if (value == null) {
					return "";
				}
			}
		}, {
			field : 'orderType',
			title : '订单类型',
			width : 100,
			formatter : function(value, row, index) {
				if (value = '2') {
					return '人工分单';
				}
			}
		}, {
			field : 'orderTime',
			title : '下单时间',
			width : 200,
			formatter : function(value, row, index) {
				if (value != null) {
					//调用工具的方法格式化日期
					return dateFormat(value);
				} else {
					return "";
				}
			}
		} ] ];
		//获取已下单单未分单成功的用户
		$("#list").datagrid({
			url : "../../" + action + "/findByPage.action?orderType=2&status=1",
			columns : columns,
			pagination : true,
			pageList : [ 10, 20,30 ],
			pageSize : 10,
			toolbar : "#toolbar"
		});
		//设置全局变量 获取订单id
		var id;
		
		//点击开始分单 
		$("#houseBill").click(function() {
			//判断是否选中一行
			var rows = $("#list").datagrid("getSelections");
			if (rows.length != 1) {
				$.messager.alert("提示", "您只能选择一条订单来分配订单", "warning");
				return;
			}
			//打开窗口 选择快递员
			$("#houserWin").window("open");
			
			//获取当前订单id
			 id=rows[0].id;
			
			$("#orderId").val(id);
			
			$('#courierId').combobox({   
				//通过订单找发件区域，通过区域找分区，通过分区找定区，通过定区找快递员
			    url:'../../order/findCourierByArea.action?id='+id,    
			    valueField:'id',    
			    textField:'name'   
			});  			

		});
		//开始分单
		$("#save").click(function(){
			//获取快递员id			
			alert($("#houserForm").serialize());
			//访问后台
			$.post("../../order/houseOrder.action",$("#houserForm").serialize(),function(data){
				if (data.success) {
					// 如果成功 则清空表单
					$("#houserWin").form("clear");
					// 刷下datagrid的页面数据
					$("#list").datagrid("reload");
					// 关闭窗口
					$("#houserWin").window("close");
					// 提示保存成功
					$.messager.alert("提示", "分单成功", "info");
				} else {
					// 如果失败 则提示失败，
					$.messager.alert("提示", "分单失败,原因:" + data.message, "error");
				}
				
			},"json")
		});
	</script>
</body>
</html>