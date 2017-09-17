<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
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


	<!-- 列表显示 -->
	<table id="list"></table>

	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="add" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'">开始绑定角色</a>
	</div>


	<script type="text/javascript">
		//表格的列数据
		var columns = [ [ {
			field : "id",
			checkbox : true
		}, {
			field : "name",
			title : "角色名",
			width : 120,
			align : "center"
		}, {
			field : "keyword",
			title : "角色关键字",
			width : 120,
			align : "center"
		}, {
			field : "description",
			title : "备注",
			width : 200,
			align : "center"
		} ] ];

		//当前模块的action
		var action = "role";

		//展示角色列表
		$("#list").datagrid({
		url : "../../" + action + "/findByPageByUser.action?userId=${param.userId}",
		columns : columns,
		pagination : true,
		pageList : [5, 10, 15 ],
		pageSize : 10,
		toolbar : "#toolbar",
		//datagrid有一个数据加载完成后的触发事件  
		onLoadSuccess:function(data){
			//遍历数据
			$(data.rows).each(function(i){
				//判断checked属性是否为true  如果为true 则选中				
				if(data.rows[i].checked){
					//选中当前行
					$("#list").datagrid("selectRow",i);
				}
			});
		}
	});
		

		//绑定角色
		$("#add").click(function() {
			//获取当前用户的id
			var userId = "${param.userId}";

			//获取选中的要绑定角色的行
			var rows = $("#list").datagrid("getSelections");
			if (rows.length < 1) {
				$.messager.alert("提示", "您至少要选中一个角色来绑定", "warning");
				return;
			}

			//组合角色id
			var roleIds = "";
			var roleIdArr = new Array();
			//遍历选中的行
			$(rows).each(function(i) {
				roleIdArr.push(rows[i].id);
			});
			//用逗号来组合
			roleIds = roleIdArr.join(",");

			//异步请求绑定角色
			$.post("../../user/bindRole.action", {
				userId : userId,
				roleIds : roleIds
			}, function(data) {

				if (data.success) {
					$.messager.alert("提示", "绑定角色成功", "info");
					//关闭窗口,要关闭绑定资源的window页面。先要获取到当前页面的父页面
					//获取父页面 window.parent
				//	window.parent.$("#bindRoleWin").window("close");
				} else {
					$.messager.alert("提示", "绑定失败,原因是:" + data.msg);
				}
			}, "json")

		});
	</script>
</body>
</html>