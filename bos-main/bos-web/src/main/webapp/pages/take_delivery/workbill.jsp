<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工单管理</title>
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
<!--导入日期格式化  -->
<script type="text/javascript" src="../../js/date.js"></script>
<body>
	<!-- 查询数据 -->
	<form method="post" id="searchForm">
		订单号：<input type="text" name="order.orderNum"> 
		创建时间：<input type="text" name="buildtime" id=""buildtime""
						class="easyui-datebox" required="true" />
		 快递员：<input class="easyui-combobox" name="courier.id" 
		data-options="url:'../../courier/list.action',valueField:'id',textField:'name'">
		<input type="button" value="查询" id="search">
	</form>
	

	<div id="toolbar">
		<!-- <a class="easyui-linkbutton" data-options="iconCls:'icon-save'"	href="javascript:void()" id="add">添加</a> 
		<a class="easyui-linkbutton"data-options="iconCls:'icon-edit'" href="javascript:void()" id="editbtn">修改</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" href="javascript:void()" id="delbtn">删除</a> -->
	</div>
	<!--使用datagrid展示数据  -->
	<table id="list"></table>
	
	<!--使用window窗口展示添加修改数据  -->
	<div id="editwin" class="easyui-window" title="工单编辑窗口" style="width:400px;height:250px" data-options="closed:true">
		<!--工单编辑表单  -->
	
		
	<script type="text/javascript">
			  /*定义一个变量 标识当前的模块名称  */
			  var action="workbill";
			  
			  var columns=[ [ {
					field : 'id',
					title : '编号',
					width : 100,
				}, {
					field : 'order',
					title : '所属订单号',
					width : 300,
					formatter:function(value,row,index){
						return row.order.orderNum;
					}
				}, {
					field : 'buildtime',
					title : '工单创建时间',
					width : 100,
					formatter:function(value,row,index){
						return dateFormat(value);
					}
				}, {
					field : 'courier',
					title : '快递员',
					width : 100,
					formatter:function(value,row,index){
						return row.courier.name;
					}
				}, {
					field : 'remark',
					title : '备注',
					width : 200
				}] ];
	  
	</script>
</body>
</html>