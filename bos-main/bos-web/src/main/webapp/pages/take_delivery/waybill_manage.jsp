<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>运单管理</title>
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
	<!-- 搜索框 -->
	<div class="datagrid-toolbar" style="height: 25px;align:'center'">
		<form id="searchForm" method="post">
			运单号:<input name="wayBillNum" class="easyui-textbox"
				style="line-height: 26px; border: 1px solid #ccc"> 
			发货地:<input class="easyui-textbox"
				name="sendAddress" style="line-height: 26px; border: 1px solid #ccc">
			收货地:<input name="recAddress" class="easyui-textbox"
				style="line-height: 26px; border: 1px solid #ccc"> <select
				class="easyui-combobox" style="width: 150px" name="sendProNum">
				<option value="">请选择快递产品类型</option>
				<option value="速运当日">速运当日</option>
				<option value="速运次日">速运次日</option>
				<option value="速运隔日">速运隔日</option>
			</select> <select class="easyui-combobox" style="width: 150px" 
				name="signStatus">
				<option value="">请选择运单状态</option>
				<option value="1">待发货</option>
				<option value="2">派送中</option>
				<option value="3">已签收</option>
				<option value="4">异常</option>
			</select>
			<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		</form>
			
		
	</div>

	<!-- 列表 -->
	<table id="list"></table>
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'">修改</a> <a id="cancelBtn" href="#"
			class="easyui-linkbutton" data-options="iconCls:'icon-edit'">作废</a> <a
			id="backBtn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'">还原</a> <a id="printBtn" href="#"
			class="easyui-linkbutton" data-options="iconCls:'icon-remove'">打印查询结果</a>
	</div>
	
	<!-- 还原窗口 -->
	<div id="restore" class="easyui-window" data-options="width:500,height:300,closed:true" ></div>

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

		
						
		//修改
		$("#saveBtn").click(function() {
			//选中一行
			var rows = $("#list").datagrid("getSelections");
			if (rows.length != 1) {
				$.messager.alert("提示", "必须选择一行来进行修改操作", "warning");
				return;
			}
			
			//获取选中行的id
			var id = rows[0].id;
			//订单号
			var orderNum;
			if(rows[0].order!=null && rows[0].order.orderNum!=null) {
				var orderNum = rows[0].order.orderNum;
			}else {
				orderNum = "";
			}
			
			/* var content = "<iframe src='"+"pages/take_delivery/waybill.jsp?id=" + id+"&orderNum="+orderNum+"' style='width:100%;height:100%' framborder=0/>";
			//修改选项卡标题
			var tab = window.parent.$('#tabs').tabs('getTab','运单管理');
			window.parent.$("#tabs").tabs("update",{
				tab:tab,
				options: {
					title: '运单修改',
					//href: "waybill.jsp?id=" + id+"&orderNum="+orderNum 
					content:content
				} */

			//});
			
															
			//页面跳转"&orderNum="+orderNum;
			window.location.href = "waybill.jsp?id=" + id+"&orderNum="+orderNum;
		});
		
		
		
		
		//查询列表
		$("#list").datagrid({
			url : "../../" + action + "/findByPage.action?isDelete=1",
			columns : columns,
			pagination : true,
			pageList : [ 5, 10, 15 ],
			pageSize : 10,
			toolbar : "#toolbar"
		});

		//批量作废
		$("#cancelBtn").click(
				function() {
					var rows = $("#list").datagrid("getSelections");
					if (rows.length == 0) {
						$.messager.alert("提示", "请至少选择一行进行操作！", "info");
						return;
					}

					var delId = "";
					var idArray = new Array();
					//遍历，获取所有id
					$(rows).each(function(i) {
						idArray.push(rows[i].id);
					});
					//alert(rows.length);
					delId = idArray.join(",");

					//确认要删除吗
					$.messager.confirm("确认", "您确认要删除这些记录吗？", function(r) {
						if (r) {
							$.post("../../wayBill/cancel.action", {
								delId : delId
							}, function(data) {
								if (data.success) {
									$.messager.alert("提示", "删除成功！", "info");
									$("#list").datagrid("reload");
								} else {
									$.messager.alert("提示", "删除失败，" + data.msg,
											"error");
								}
							}, "json");
						}
					});

				});
		
		//还原
		$("#backBtn").click(function() {
			
			
			var content = "<iframe src='restore.jsp' style='width:100%;height:100%'  frameborder='0'></iframe>";
			$("#restore").window({
				content:content
			});
			
			$("#restore").window("open");
			
		});
		
		
		// 加载页面数据
		$("#search").click(function(){
			$("#list").datagrid("load", getFormData("searchForm"));
		});
		
	
		
	</script>
</body>
</html>