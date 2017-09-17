<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>收派标准</title>
</head>

<!-- 导入easyui的资源文件 -->
<!-- jquery的文件 -->
<script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>
<!-- easyui的js文件 -->
<script type="text/javascript"
	src="../../js/easyui/jquery.easyui.min.js"></script>
<!-- 语言js -->
<script type="text/javascript"
	src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 图标css -->
<link rel="stylesheet" type="text/css"
	href="../../js/easyui/themes/icon.css">
<!-- 模板的css -->
<link rel="stylesheet" type="text/css"
	href="../../js/easyui/themes/default/easyui.css">
<script type="text/javascript" src="../../js/form.js"></script>
<!--导入通用代码  -->
<script type="text/javascript" src="../../js/crud.js"></script>

<body>
	<!-- 查询数据 -->
	<form method="post" id="searchForm">
		线路类型：<input type="text" name="lineType"> 车牌号：<input type="text"
			name="plateNumber"> <input type="button" value="查询" id="search">
	</form>


	<div id="toolbar">
		<shiro:hasPermission name="standard_add_btn">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-save'"
				href="javascript:void()" id="add">添加</a>
		</shiro:hasPermission>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'"
			href="javascript:void()" id="editbtn">修改</a> <a
			class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
			href="javascript:void()" id="delbtn">删除</a>
	</div>
	<!--使用datagrid展示数据  -->
	<table id="list"></table>

	<!--使用window窗口展示添加修改数据  -->
	<div id="editwin" class="easyui-window" title="收货标准编辑窗口"
		style="width: 400px; height: 250px" data-options="closed:true">
		<!--收货标准编辑表单  -->
		<form action="/busManager/save.action" id="editForm" method="post">
			<input type="hidden" name="id">
			<table>
				<tr>
					<td>线路类型：</td>
					<td><select name="lineType" class="easyui-combobox"
						data-options="required:true" style="width:150px;">
							<option>全部</option>
							<option>干线</option>
							<option>周边</option>
							<option>省内</option>
							<option>临时</option>
							<option>市内</option>
					</select>
				</tr>
				<tr>
					<td>车牌号：</td>
					<td><input type="text" name="plateNumber"
						class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td>承运商：</td>
					<td><input type="text" name="carrier" class="easyui-textbox"
						data-options="required:true"></td>
				</tr>
				<tr>
					<td>车型：</td>
					<td><input type="text" name="vehicleType"
						class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td>司机:</td>
					<td><input type="text" name="driver" class="easyui-textbox"
						data-options="required:true"></td>
				</tr>
				<tr>
					<td>电话:</td>
					<td><input type="text" name="telephone"
						class="easyui-numberbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td>吨控:</td>
					<td><input type="text" name="tonControl"
						class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input type="text" name="remark" class="easyui-textbox"
						data-options="required:true"></td>
				</tr>

				<tr>
					<td colspan="2"><input type="button" value="保存" id="save">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		/*定义一个变量 标识当前的模块名称  */
		var action = "busManager";

		var columns = [ [ {
			field : 'id',
			title : '编号',
			width : 100
		}, {
			field : 'lineType',
			title : '线路类型',
			width : 200
		}, {
			field : 'plateNumber',
			title : '车牌号',
			width : 100
		}, {
			field : 'carrier',
			title : '承运商',
			width : 100
		}, {
			field : 'vehicleType',
			title : '车型',
			width : 100
		}, {
			field : 'driver',
			title : '司机',
			width : 100
		}, {
			field : 'telephone',
			title : '电话',
			width : 100
		}, {
			field : 'tonControl',
			title : '吨控',
			width : 100
		}, {
			field : 'remark',
			title : '备注',
			width : 100
		} ] ];
		
	</script>
</body>
</html>