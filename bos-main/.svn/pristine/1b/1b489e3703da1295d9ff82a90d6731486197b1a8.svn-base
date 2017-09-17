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
		<a id="save" href="#" class="easyui-linkbutton"	data-options="iconCls:'icon-save'">开始绑定资源</a> 
		
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
				field : "resourceType",
				title : "资源类型",
				width : 120,
				align : "center"
			}] ];
			//treegrid树形表格展示资源数据
			$("#list").treegrid({
				idField:'id',
				treeField:'name',
				//抓取后台数据
				url:"../../resources/findByPageByRole.action?roleId=${param.roleId}",
				columns : columns,
				pagination : true,
				pageList : [50, 200, 100 ],
				pageSize : 100,
				toolbar : "#toolbar",
				//显示复选框
				checkbox:true,
				//取消级联选中
				cascadeCheck:false
			});		
		});
		
	
		//开始绑定资源
		$("#save").click(function(){
			//获取角色的id
			var roleId="${param.roleId}";
			
			//获取所有的行
			var rows=$("#list").treegrid("getChildren");
			
			//定义一个变量判断是否有选中
			var flag=false;
			//定义一个字符串存储所有选择的资源id
			var  resId=""
			var  resIdArray=new Array();
			//遍历所有的行
			$(rows).each(function(i){
				if(rows[i].checked){
					resIdArray.push(rows[i].id);
					//如果有选中的节点就变成true
					flag=true;
				}
			});
			//判断是否有选中的
			if(!flag){
				//如果没有选中项 则提示
				$.messager.alert("提示","您必须选中至少一个资源来绑定","warning");
				return ;
			}
			//id字符串的格式 1,2,3
			resId=resIdArray.join(",");			
		
			//异步请求访问后台让角色绑定资源
			$.post("../../role/bindRes.action",{roleId:roleId,resId:resId},function(data){
				
					if(data.success){				
						//提示
						$.messager.alert("提示","绑定成功","info");	
						//关闭窗口,要关闭绑定资源的window页面。先要获取到当前页面的父页面
						//获取父页面 window.parent
					//	window.parent.$("#bindResWin").window("close");
						
					}else{
						$.messager.alert("提示","绑定失败,原因是+","warning");	
					}
			},"json")
			
		});
	
		
		
	
	</script>
</body>
</html>