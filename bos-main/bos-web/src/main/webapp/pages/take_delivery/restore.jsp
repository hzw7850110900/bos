<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>还原</title>
<script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>
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
</head>
<body>
	
	<!-- 列表 -->
	<table id="list"></table>
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'">还原</a>
	</div>
	
	
	<script type="text/javascript">
		//action访问名称
		var action = "wayBill";

		//列字段
		var columns = [ [ {
			field : 'id',
			checkbox : true,
		}, {
			field : 'wayBillNum',
			title : '运单编号',
			width : 80,
			align : 'center'
		}, {
			field : 'sendName',
			title : '寄件人',
			width : 80,
			align : 'center'
		}, {
			field : 'sendMobile',
			title : '寄件人电话',
			width : 120,
			align : 'center'
		}, {
			field : 'sendCompany',
			title : '寄件人公司',
			width : 120,
			align : 'center'
		}, {
			field : 'sendAddress',
			title : '寄件人详细地址',
			width : 120,
			align : 'center'
		}, {
			field : 'recName',
			title : '收件人',
			width : 120,
			align : 'center'
		}, {
			field : 'recMobile',
			title : '收件人电话',
			width : 120,
			align : 'center'
		}, {
			field : 'recAddress',
			title : '收件人详细地址',
			width : 200,
			align : 'center'
		}, {
			field : 'sendProNum',
			title : '产品类型',
			width : 200,
			align : 'center'
		}, {
			field : 'recAddress',
			title : '收件人详细地址',
			width : 200,
			align : 'center'
		}, {
			field : 'payTypeNum',
			title : '支付类型',
			width : 200,
			align : 'center'
		}, {
			field : 'weight',
			title : '重量',
			width : 200,
			align : 'center'
		}, {
			field : 'num',
			title : '原件数',
			width : 200,
			align : 'center'
		}, {
			field : 'feeitemnum',
			title : '实际件数',
			width : 200,
			align : 'center'
		}, {
			field : 'actlweit',
			title : '实际重量',
			width : 200,
			align : 'center'
		}, {
			field : 'vol',
			title : '体积',
			width : 200,
			align : 'center'
		}, {
			field : 'floadreqr',
			title : '配载要求',
			width : 200,
			align : 'center'
		}, {
			field : 'wayBillType',
			title : '运单类型',
			width : 80,
			align : 'center',
		} ] ];

		//查询列表
		$("#list").datagrid({
			url : "../../" + action + "/listRestore.action",
			columns : columns,
			pagination : true,
			pageList : [ 5, 10, 15 ],
			pageSize : 10,
			toolbar : "#toolbar"
		});
		
		//还原
		$("#saveBtn").click(function() {
			//选中一行
			var rows = $("#list").datagrid("getSelections");
			if (rows.length == 0) {
				$.messager.alert("提示", "请选择至少一行来进行修改操作", "warning");
				return;
			}
			
			var restoreId = "";
			var idArray = new Array();
			$(rows).each(function(i) {
				idArray.push(rows[i].id);
			});
			restoreId = idArray.join(",");
			
			$.post("../../wayBill/restoreWayBill.action",{restoreId:restoreId},function(data) {
				if(data.success) {
					window.parent.$.messager.alert("提示","还原成功！","info");
					window.parent.$("#list").datagrid("reload");
					window.parent.$("#restore").window("close");
				}else {
					$.messager.alert("提示","还原失败，"+data.msg,"error");
				}
			},"json");
			
		});
		
	
	</script>
</body>
</html>