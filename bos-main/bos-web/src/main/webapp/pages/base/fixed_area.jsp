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

<!--导入文件上传插件  -->
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>

<body>
	<!-- 查询数据 -->
	<form method="post" id="searchForm">
		定区名称：<input type="text" name="fixedAreaName"> <input
			type="button" value="查询" id="search">
	</form>


	<div id="toolbar">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-save'"
			href="javascript:void()" id="add">添加</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" href="javascript:void()"
			id="editbtn">修改</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-remove'" href="javascript:void()"
			id="delbtn">删除</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-reload'" href="javascript:void()"
			id="associatebtn">关联客户</a>
	</div>
	<!--使用datagrid展示数据  -->
	<table id="list"></table>

	<!--使用window窗口展示添加修改数据  -->
	<!-- 编辑表单 -->
	<div id="editwin" class="easyui-window"
		data-options="title:'定区添加修改',closed:true"
		style="width: 600px; top: 20px; left: 200px">
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
						<td>定区名称</td>
						<td><input type="text" name="fixedAreaName"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>负责人</td>
						<td><input class="easyui-combobox" id="courierId"
							name="courier.id"
							data-options="valueField:'id',textField:'name',url:'../../courier/list.action'">
						</td>
					</tr>
					<tr>
						<td>联系电话</td>
						<td><input name="telephone" class="easyui-validatebox"
							required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!--定区关联客户  -->
	<div class="easyui-window" title="关联客户窗口" id="customerWin" modal="true"
		collapsible="false" closed="true" minimizable="false"
		maximizable="false"
		style="top: 20px; left: 200px; width: 700px; height: 300px;">
		<div style="overflow: auto; padding: 5px;" border="false">
			<form id="customerForm" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="3">关联客户</td>
					</tr>
					<tr>
						<td>
						 <select id="associationSelect" multiple="multiple"
							size="10" style="width: 300px;"></select> 
						
							</td>
						<td><input type="button" value="==>" id="toRight"> <br />
							<input type="button" value="<==" id="toLeft"></td>
						<td><select id="noassociationSelect" name="customerIds"
							multiple="multiple" size="10" style="width: 300px;"></select></td>
					</tr>
					<tr>
						<td colspan="3"><a id="associationBtn" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联客户</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		/*定义一个变量 标识当前的模块名称  */
		var action = "fixedArea";
		//定义区域的列数据
		var columns = [ [ {
			field : 'id',
			title : '编号',
			width : 80,
			align : 'center',
			checkbox : true
		}, {
			field : 'fixedAreaName',
			title : '定区名称',
			width : 120,
			align : 'center'
		}, {
			field : 'courier',
			title : '负责人',
			width : 120,
			align : 'center',
			formatter : function(value, raw, index) {
				if (value != null) {
					return value.name;
				} else {
					return "";
				}
			}
		}, {
			field : 'telephone',
			title : '联系电话',
			width : 120,
			align : 'center'
		} ] ];

		//设置combobox下拉框回响的数据
		function loadEditForm(row) {
			$("#courierId").combobox("setValue", row.courier.id);
		}
		//异步加载定区关联客户的信息
		$("#associatebtn").click(function(){
			//获取选取的行
			var rows = $("#list").datagrid("getSelections");
			if(rows.length<1){
				$.messager.alert("提示信息","至少要选择一个定区","warning");
				return;
			}
			//打开关联客户的window窗口
			 $("#customerWin").window("open");
			//异步加载未关联客户的信息
		 	 $.post("../../fixedArea/findNoAssociationCustomer.action",
				function(data){
					//情况内容
					$("#noassociationSelect").empty();
					$(data).each(function(){
						$("#noassociationSelect").append("<option value='"+this.id+"'>"+this.username+"("+this.address+")"+"</option>")
						;
						});
			},"json"); 
			 //异步加载已被当前定区关联的客户
		 	 $.post("../../fixedArea/findHasAssociationFixedAreaCustomers.action?fixedAreaId="+rows[0].id,
					function(data){
						//情况内容
						$("#associationSelect").empty();
						$(data).each(function(){
							$("#associationSelect").append("<option value='"+this.id+"'>"+this.username+"("+this.address+")"+"</option>")
							;
							});
				},"json");   
		});
		/* 将左边的移动到右边 */
		$("#toRight").click(function(){
			$("#associationSelect option:selected").appendTo("#noassociationSelect");
		});	
		/* 将右边的移动到左边 */
		$("#toLeft").click(function(){
			$("#noassociationSelect option:selected").appendTo("#associationSelect");
		});	
		//定区关联客户
		$("#associationBtn").click(function(){
			//先使左边的选项全部选中
			$("#associationSelect option").prop("selected",true);
			//选中的客户id 格式 1,2,3
			var customerIds="";
			//获取左边选中客户的value值
			var ids=$("#associationSelect").val();
			
			var idArray=new Array();
			if(ids!=null){
				for (var i = 0; i<ids.length; i++) {
					idArray.push(ids[i]);
				}					
			}
			//将id按指定的格式给存储起来
			customerIds=idArray.join(",");
			
			//获取选中的定区的信息
			var rows = $("#list").datagrid("getSelections");
			//获取定区的id
			var fixedAreaId=rows[0].id;
			
			//异步加载后台关联客户
			$.post("../../fixedArea/associateCustomersToFixedArea.action",{customerIds:customerIds,fixedAreaId:fixedAreaId},
					function(data){
						if(data.success){
							alert("关联成功");
						}else{
							alert("关联失败"+data.msg);
						}
			},"json");			
		});
	</script>	
</body>
</html>