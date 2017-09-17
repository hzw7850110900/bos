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
	<form method="post" id="searchForm" action="../../courier/exportcourier.action">
		快递员姓名：<input type="text" name="name" size="6"> 
		快递员工号：<input type="text" name="courierNum" size="6"> 
		手机号:<input type="text" name="telephone" size="6">
		收派标准<input class="easyui-combobox" name="standard.id" 
		data-options="url:'../../standard/list.action',valueField:'id',textField:'name'">
		<input type="button" value="查询" id="search">
	</form>
	

	<div id="toolbar">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-save'"	href="javascript:void()" id="add">添加</a> 
		<a class="easyui-linkbutton"data-options="iconCls:'icon-edit'" href="javascript:void()" id="editbtn">修改</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" href="javascript:void()" id="delbtn">删除</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" href="javascript:void()" id="importBtn">execl导入</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-print'" href="javascript:void()" id="exportBtn">execl导出</a>
	</div>
	<!--使用datagrid展示数据  -->
	<table id="list"></table>
	
	<!--使用window窗口展示添加修改数据  -->
	<div id="editwin" class="easyui-window" data-options="title:'快递员编辑',closed:true" style="width:700px;height:200px;top:50px;left:200px">
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
							<td>快递员工号</td>
							<td>
								<input type="text" name="courierNum" class="easyui-validatebox" required="true" />
							</td>
							<td>姓名</td>
							<td>
								<input type="text" name="name" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>手机</td>
							<td>
								<input type="text" name="telephone" class="easyui-validatebox" required="true" />
							</td>
							<td>所属单位</td>
							<td>
								<input type="text" name="company" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>查台密码</td>
							<td>
								<input type="text" name="checkPwd" class="easyui-validatebox" required="true" />
							</td>
							<td>PDA号码</td>
							<td>
								<input type="text" name="pda" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>取派标准</td>
							<td>
								<!--使用combobox来抓取后台的数据  -->
								<input class="easyui-combobox" name="standard.id"  id="standardId"
								data-options="valueField:'id',textField:'name',url:'../../standard/list.action'">
							</td>
						</tr>
					</table>
		  </form>
		  </div>
	</div>
		 <!--上传的窗口  -->
	 <div id="importWin" class="easyui-window" data-options="title:'区域上传',closed:true" style="width:600px;height:100;top:50px;left:200px">
	 		选择要上传的区域信息文件：<br/><input type="file" style="margin:10px;margin-left: 20px;" id="fileId" name="upload"><br>
	 			<a class="easyui-linkbutton" id="startImport"  style="margin:10px" href="javascript:void()">开始上传</a>
	 </div>
	<script type="text/javascript">
			  /*定义一个变量 标识当前的模块名称  */
			  var action="courier";
			 var columns = [ [ {				 	
					field : 'id',
					checkbox : true,
				},{
					field : 'courierNum',
					title : '工号',
					width : 80,
					align : 'center'
				},{
					field : 'name',
					title : '姓名',
					width : 80,
					align : 'center'
				}, {
					field : 'telephone',
					title : '手机号',
					width : 120,
					align : 'center'
				}, {
					field : 'checkPwd',
					title : '查台密码',
					width : 120,
					align : 'center'
				}, {
					field : 'pda',
					title : 'PDA号',
					width : 120,
					align : 'center'
				}, {
					field : 'standard',
					title : '取派标准',
					width : 120,
					align : 'center',
					formatter:function(value,row,index){
						if(value!=null){
							return value.name;
						}else{
							return "";
						}
					}
				}, {
					field : 'company',
					title : '所属单位',
					width : 200,
					align : 'center'
				} ] ];
			 
	//设置快递员的收货标准的回响
	  function loadEditForm(row) {

		  $("#standardId").combobox("setValue",row.standard.id);
	}		 
	//importBtn 点击上传打开上传的Windows窗口
		$("#importBtn").click(function(){
			$("#importWin").window("open");
		});
		//点击开始上传，上传文件
		$("#startImport").click(function(){	
			//先获取文件名
			var fileName=$("#fileId").val();
			
			//编写匹配的文件名的正则表达式
			var regex = /^.+\.(xls|xlsx)$/;
			//判断选择的文件是否是xls/xlsx文件
			if(!regex.test(fileName)){
				//如果不匹配,则弹出警告框
				$.messager.alert("提示","上传的文件类型必须是xls或xlsx","warning");
				return;
			}
			//如果类型匹配 则开始上传--异步上传
			$.ajaxFileUpload({
				url:"../../courier/batchImport.action",
				fileElementId:"fileId",
				dataType:'json',
				success:function(data){
					if(data.success){
						//上传成功后刷新页面并关闭窗口
						$("#list").datagrid("reload");
						$("#importWin").window("close")
						$.messager.alert("提示","上传成功","info");
					}else{
						$.messager.alert("提示","上传失败!原因是:"+data.msg,"error");
					}
				},
				error:function(e){
					$.messager.alert("提示","上传失败!原因是:"+data.e,"error");
				}
			});
		});
		
		//导出execl--导出数据的时候一定要选用的是同步请求
		//导出execl--导出数据的时候一定要选用的是同步请求
		$("#exportBtn").click(function(){
			$.messager.confirm("下载","你确定要导出表格吗？",function(r){
				if(r){
					//点击导出后,提交搜索条件的表单到后台
					$("#searchForm").submit();
				}
			});
			
		});

	</script>
</body>
</html>