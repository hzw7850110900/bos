<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		关键字:<input name="keyWords" type="text" size="8"> <input
			type="button" value="查询" id="search">
	</form>


	<div id="toolbar">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-save'"
			href="javascript:void()" id="add">添加</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" href="javascript:void()"
			id="editbtn">修改</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-remove'" href="javascript:void()"
			id="delbtn">删除</a>
	</div>
	<!--使用datagrid展示数据  -->
	<table id="list"></table>

	<!--使用window窗口展示添加修改数据  -->
	<div id="editwin" class="easyui-window"
		data-options="title:'分区编辑',closed:true"
		style="width: 700px; top: 50px; left: 200px">
		<!-- 按钮区域 -->
		<div class="datagrid-toolbar">
			<a id="save" class="easyui-linkbutton" href="#" icon="icon-save">保存</a>
		</div>
		<!-- 编辑区域 -->
		<div>
			<form id="editForm" method="post">
				<!--提供隐藏域 装载id -->
				<input type="hidden" name="id" />
				<table class="table-edit" width="80%" align="center">
					<tr>
						<td>所属区域</td>
						<td><input class="easyui-combobox" name="area.id" id="AreaId"
							data-options="url:'../../area/list.action',valueField:'id',textField:'areaName'"
							size="30"></td>
						<td>所属定区</td>
						<td><input class="easyui-combobox" name="fixedArea.id"
							id="fixedAreaId"
							data-options="url:'../../fixedArea/list.action',valueField:'id',textField:'fixedAreaName'">
						</td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startNum"
							class="easyui-validatebox" required="true" /></td>
						<td>中止号</td>
						<td><input type="text" name="endNum"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>关键词</td>
						<td><input type="text" name="keyWords"
							class="easyui-validatebox" required="true" /></td>
						<td>辅助关键词</td>
						<td><input type="text" name="assistKeyWords"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		/*定义一个变量 标识当前的模块名称  */
		var action = "subArea";
		var columns = [ [ {
			field : 'id',
			checkbox : true,
		}, {
			field : 'area.province',
			title : '省',
			width : 120,
			align : 'center',
			formatter : function(value, row, index) {
				if (row.area.province != null) {
					return row.area.province;
				} else {
					return "";
				}
			}

		}, {
			field : 'area.city',
			title : '市',
			width : 120,
			align : 'center',
			formatter : function(value, row, index) {
				if (row.area.city != null) {
					return row.area.city;
				} else {
					return "";
				}
			}

		}, {
			field : 'area.district',
			title : '区',
			width : 120,
			align : 'center',
			formatter : function(value, row, index) {
				if (row.area.district != null) {
					return row.area.district;
				} else {
					return "";
				}
			}

		}, {
			field : 'fixedArea',
			title : '所属定区',
			width : 120,
			align : 'center',
			formatter : function(value, row, index) {
				if (value!= null) {
					return value.fixedAreaName;
				} else {
					return "";
				}
			}
		}, {
			field : 'startNum',
			title : '起始号',
			width : 100,
			align : 'center'
		}, {
			field : 'endNum',
			title : '终止号',
			width : 100,
			align : 'center'
		}, {
			field : 'keyWords',
			title : '关键字',
			width : 120,
			align : 'center'
		}, {
			field : 'assistKeyWords',
			title : '辅助关键字',
			width : 100,
			align : 'center'
		} ] ];
		
		//重写给下拉框列表赋值的方法
		function loadEditForm(row) {
			if(row.area!=null){
				$("#AreaId").combobox("setValue", row.area.id);
			}
			if(row.fixedArea!=null){
				$("#fixedAreaId").combobox("setValue", row.fixedArea.id); 
			}
		 	
			
		}
	</script>
</body>
</html>