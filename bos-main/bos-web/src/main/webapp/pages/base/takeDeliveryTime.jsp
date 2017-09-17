<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- 导入easyui的资源文件 -->
	<!-- jquery的文件 -->
	<script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>
	<!-- easyui的js文件 -->
	<script type="text/javascript" src="../../js/easyui/jquery.easyui.min.js"></script>
	<!-- 语言js -->
	<script type="text/javascript" src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- 图标css -->
	<link rel="stylesheet" type="text/css" href="../../js/easyui/themes/icon.css">
	<!-- 模板的css -->
	<link rel="stylesheet" type="text/css" href="../../js/easyui/themes/default/easyui.css">
	<!-- load方法加载条件数据，转符合load方法的json格式的数据 -->
	<script type="text/javascript" src="../../js/form.js"></script>
	<!-- 抽取的crud的js页面 -->
	<script type="text/javascript" src="../../js/crud.js"></script>
	<script type="text/javascript" src="../../js/date.js"></script>
	<title>标准产品表</title>
</head>
<body>
	<!-- 条件搜索 -->
	<form id="searchForm" method="post">
		时间名称：<input type="text" name="timeName">
		<input id="search" type="button" value="查询">
	</form>
	
	<!-- 工具条 -->
	<div id="toolbar">
		<a id="add" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
		<a id="editbtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
		<a id="delbtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
	</div>
	
	<!-- 编辑窗口 -->
	<div id="editwin" class="easyui-window" title="收派标准编辑窗口" data-options="width:350,height:300,closed:true">   
   		<!-- 按钮区域 -->
   		<div class="datagrid-toolbar">
   			<a id="save" class="easyui-linkbutton" href="#" icon="icon-save">保存</a>
   			<form id="editForm" method="post">
   			<%-- 修改操作时携带一个隐藏域ID --%>
			<input type="hidden" name="id"/>
	   			<table width="80%" algin="center" class="table-edit">
	   					<tr>
					<td>时间名称：</td>
					<td><input id="a" name="timeName" class="easyui-textbox"
						data-options="required:true"></td>
				</tr>
				<tr>
					<td>平时上班时间：</td>
					<td><input id="a" name="peaWorkingTime"
						class="easyui-timespinner"
						data-options="min:'08:30',showSeconds:false,required:true"></td>
				</tr>
				<tr>
					<td>平时下班时间：</td>
					<td><input id="a" name="peaClosingTime"
						class="easyui-timespinner"
						data-options="min:'08:30',showSeconds:false,required:true"></td>
				</tr>
				<tr>
					<td>周六上班时间:</td>
					<td><input id="a" name="staWorkingTime"
						class="easyui-timespinner"
						data-options="min:'08:30',showSeconds:false,required:true"></td>
				</tr>
				<tr>
					<td>周六下班时间:</td>
					<td><input id="a" name="staClosingTime"
						class="easyui-timespinner"
						data-options="min:'08:30',showSeconds:false,required:true"></td>
				</tr>
				<tr>
					<td>周日上班时间:</td>
					<td><input id="a" name="sunWorkingTime"
						class="easyui-timespinner"
						data-options="min:'08:30',showSeconds:false,required:true"></td>
				</tr>
				<tr>
					<td>周日下班时间:</td>
					<td><input id="a" name="sunClosingTime"
						class="easyui-timespinner"
						data-options="min:'08:30',showSeconds:false,required:true"></td>
				</tr>
				<tr>
					<td>快递员:</td>
					<td>
						<!--使用combobox来抓取后台的数据  --> <input class="easyui-combobox"
						name="courier.id" id="courierId"
						data-options="valueField:'id',textField:'name',url:'../../courier/list.action'">
					</td>
				</tr>
	   			</table>
   			</form>
   		</div>
	</div> 

	<!-- 使用datagrid显示数据 -->
	<table id="list"></table>

	<script type="text/javascript">
		/*定义一个变量 标识当前的模块名称  */
		var action = "takeDeliveryTime";

		var columns = [ [ {
			field : 'id',
			title : '编号',
			width : 100
		}, {
			field : 'timeName',
			title : '时间名称',
			width : 200
		}, {
			field : 'peaWorkingTime',
			title : '平时上班时间',
			width : 100,
		}, {
			field : 'peaClosingTime',
			title : '平时下班时间',
			width : 100
		}, {
			field : 'staWorkingTime',
			title : '周六上班时间',
			width : 100
		}, {
			field : 'staClosingTime',
			title : '周六下班时间',
			width : 100
		}, {
			field : 'sunWorkingTime',
			title : '周日上班时间',
			width : 100
		}, {
			field : 'sunClosingTime',
			title : '周日下班时间',
			width : 100
		}, {
			field : 'courier',
			title : '快递员',
			width : 100,
			formatter:function(value,row,index){
				if(value!=null){
					return value.name;
				}else{
					return "";
				}
			}
		} ] ];

		//设置combobox下拉框回响的数据
		function loadEditForm(row) {
			$("#courierId").combobox("setValue", row.courier.id);
		}
	</script>
</body>
</html>