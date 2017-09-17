<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>运单快速录入</title>
<script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/default.css">
<script type="text/javascript" src="../../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/form.js"></script>
</head>
<body class="easyui-layout">

	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="addRowBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加一行</a>
		<a id="cancelBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">取消编辑</a>
		<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">保存</a>
		<a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
	</div>
	
	<div region="center" border="false">
			<table id="list"></table>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$("#list").datagrid({
				iconCls : 'icon-forward',
				fit : true,
				border : true,
				striped : true,
				pagination : true,
				toolbar : "#toolbar",
				url :  "",
				idField : 'id',
				columns : columns,
				//设置datagrid只能被选中一行数据
				singleSelect:true,
				//绑定结束编辑行的时间，结束之后
				onAfterEdit:function(index,row,changes){
					//异步提交到后台保存数据
					$.post("../../wayBill/save.action",row,function(data){
							if(data.success){
								alert("保存成功");
							}else{
								alert("保存失败,原因是"+data.msg);
							}
					},"json");
				}
			});
			
			
		});
		
		// 定义列
		var columns = [ [ {
			field : 'wayBillNum',
			title : '运单编号',
			width : 120,
			align : 'center',
			editor:{
				type:'validatebox',
				options:{
					required:true	
				}
			}
						
		}, {
			field : 'arriveCity',
			title : '到达地',
			width : 120,
			align : 'center',
			editor:{
				type:'validatebox',
				options:{
					required:true	
				}
			}
			
		},{
			field : 'goodsType',
			title : '产品',
			width : 120,
			align : 'center',
			editor:{
				type:'validatebox',
				options:{
					required:true	
				}
			}
			
		}, {
			field : 'num',
			title : '件数',
			width : 120,
			align : 'center',
			editor:{
				type:'validatebox',
				options:{
					required:true	
				}
			}
		
		}, {
			field : 'weight',
			title : '重量',
			width : 120,
			align : 'center',
			editor:{
				type:'validatebox',
				options:{
					required:true	
				}
			}
			
		}, {
			field : 'floadreqr',
			title : '配载要求',
			width : 220,
			align : 'center',
			editor:{
				type:'validatebox',
				options:{
					required:true	
				}
			}
			
		}] ];	
		//定义一个全局变量记录当前行索引
		var currentROWIndex;
		//添加一行
		$("#addRowBtn").click(function(){
			 //要判断当正在编辑的时候,不能再添加一行
			 if(currentROWIndex!=undefined){
				  return ;
			 }
			
			//插入一行到0索引的位置
			$("#list").datagrid("insertRow",{
				index:0,
				row:{}
			});
			//将这行修改成可编辑的状态
			$("#list").datagrid("beginEdit",0);
			//将当前行修改为0
			currentROWIndex=0;
		});
		
		//取消编辑
		$("#cancelBtn").click(function(){
			//取消编辑当前行
			$("#list").datagrid("cancelEdit",currentROWIndex);
			//取消编辑之后,应该把当前行置空
			currentROWIndex=undefined;
		});
		
		//保存当前编辑行
		$("#saveBtn").click(function(){
			//结束编辑当前行
			$("#list").datagrid("endEdit",currentROWIndex);
			//异步提交，触发结束编辑行事件，异步访问后台系统保存数据,在datagrid定义时绑定事件
			
			//保存成功后，将当前行置空
			currentROWIndex=undefined;
		});
		
		//修改一行数据
		$("#editBtn").click(function(){
			//alert("开始修改");
			//获取选中行
			var row=$("#list").datagrid("getSelected");
			//获取当前行的索引
			var index=$("#list").datagrid("getRowIndex",row);
			alert(index);
			if(row==null){
				alert("必须要选中一行你要修改的数据");
				return ;
			}
			//开启编辑模式
			$("#list").datagrid("beginEdit",index);
			//修改当前行的值
			currentROWIndex=index
		});
	</script>
</body>
</html>