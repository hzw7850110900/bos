<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源管理</title>
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
		<a id="add" href="#" class="easyui-linkbutton"	data-options="iconCls:'icon-save'">添加</a> 
		<a id="editbtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a> 
		<a id="delbtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
	</div>

	<!-- 编辑表单 -->
	<div id="editWin" class="easyui-window"
		data-options="title:'资源编辑',closed:true"
		style="width: 600px; top: 50px; left: 200px">
		<!-- 按钮区域 -->
		<div class="datagrid-toolbar">
			<a id="save" class="easyui-linkbutton" href="#" icon="icon-save">保存</a>
		</div>
		<!-- 编辑区域 -->
		<div>
			<form id="editForm" method="post">
				<!-- 提供隐藏域ID -->
				<input type="hidden" name="id" />
				<table width="80%" align="center" class="table-edit">
					<tr>
						<td>资源名称</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							data-options="required:true" /></td>
					</tr>
					<tr>
						<td>资源关键词</td>
						<td><input type="text" name="grantKey" class="easyui-validatebox"
							data-options="required:true" /></td>
					</tr>
					
					<tr>
						<td>资源类型</td>
						<td><select name="resourceType" class="easyui-combobox"
							data-options="editable:false,width:150">
								<option value="0">菜单</option>
								<option value="1">按钮</option>
						</select></td>
					</tr>
					<tr>
						<td>菜单页面路径</td>
						<td><input type="text" name="pageUrl"
							class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>菜单图标</td>
						<td><input type="text" name="icon" /></td>
					</tr>
					<tr>
						<td>排序</td>
						<td><input name="seq" value="0" class="easyui-numberspinner"
							required="required" data-options="editable:false"></td>
					</tr>
					<tr>
						<td>是否展示</td>
						<td><input type="radio" name="open" value="true" />展开 <input
							type="radio" name="open" value="false" />不展开</td>
					</tr>
					<tr>
						<td>上级资源</td>
						<td><select id="parentResId" name="pid" class="easyui-combobox" 
						data-options="url:'../../resources/list.action',valueField:'id',textField:'name'" style="width:120px"></select></td>
					</tr>					

				</table>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		$(function(){
			var action = "resources";
			var columns = [ [ {
				field : "id",
				title : "编号",
				width : 120,
				align : "center"
			}, {
				field : "name",
				title : "资源名称",
				width : 150,
			}, {
				field : "grantKey",
				title : "授权关键词",
				width : 120,
				align : "center"
			}, {
				field : "pageUrl",
				title : "访问路径",
				width : 200,
				align : "center"
			}, {
				field : "resourceType",
				title : "资源类型",
				width : 120,
				align : "center"
			}, {
				field : "icon",
				title : "资源图标",
				width : 200,
				align : "center"
			} ] ];
			//treegrid树形表格展示资源数据
			$("#list").treegrid({
				idField:'id',
				treeField:'name',
				url:"../../resources/findByPage.action",
				columns : columns,
				pagination : true,
				pageList : [50, 200, 100 ],
				pageSize : 100,
				toolbar : "#toolbar"
			});
			
		});
		
		//添加资源
		$("#add").click(function(){
			//打开之前清理表单数据
			$("#editForm").form("clear")
			//打开window窗口
			$("#editWin").window("open");
		});
		
		//表单的提交(保存)
		$("#save").click(function(){
			//提交表单
			$("#editForm").form("submit",{
				url:'../../resources/save.action',
				onSubmit:function(){
					//判断 所有表单验证通过才可以提交
					return $("#editForm").form('validate');
				},
				success:function(data){
					// 先将data手动转换成json格式
					data = eval("(" + data + ")");
					if(data.success){
						$.messager.alert("提示","保存成功","info");
						//如果成功 则清空表格数据
						$("#editForm").form("clear");
						//保存成功后 关闭刷口并刷新treegrid数据
						$("#editWin").window("close");
						//刷新
						$("#list").treegrid("reload");
					}else{
						$.messager.alert("提示","保存失败,原因是:"+e.msg,"warning");
					}
				}
			});
		});
		
		//删除
		$("#delbtn").click(function() {
				var rows = $("#list").datagrid("getSelections");
				if (rows.length == 0) {
					$.messager.alert("提示", "修改时必须要选择一行数据来操作", "warning");
					return;
				}
				$.messager.confirm("确认框", "您确定要删除这些数据吗", function(r) {
					// 确认就进去进行删除操作
					if (r) {
						// 获取所有行的id
						var ids = "";
						//定义一个数组存储数据
						var idArray=new Array();
						// 拼接所有的id
						for (var i = 0; i < rows.length; i++) {
							idArray.push(rows[i].id)
						}
						//调用js的方法直接给每个元素加逗号
							ids=idArray.join(",")
						// 使用ajax动态访问后台数据进行删除
						$.post("../../resources/delete.action", {
							"ids" : ids
						}, function(data) {
							if (data.success) {								
								// 提示成功
								$.messager.alert("提示", "删除成功", "info");
								// 删除成功则提示删除成功并刷新页面数据
								$("#list").treegrid("reload");
							} else {
								// 删除失败则提示失败原因
								$.messager.alert("提示", "删除失败,原因是"
										+ data.message, "error");
							}
						}, "json");
					}
				});
			});
		
		
		//修改-数据回响	
	$("#editbtn").click(function() {			
				var rows = $("#list").treegrid("getSelections");
				if (rows.length != 1) {
					$.messager.alert("提示", "修改时必须只能选择一行数据来操作", "warning");
					return;
				}
				// 获取被选中行的id
				var id = rows[0].id;									
				// 从后台数据数据自动填充回响到表单当中--采用form表单的load方法
				$("#editForm").form("load",
						"../../resources/get.action?uuid=" + id);	
				//手动填空父资源的combobox的数据
				$("#parentResId").combobox("setValue",rows[0]._parentId);
				// 打开window窗口
				$("#editWin").window("open");
			});
	</script>
</body>
</html>