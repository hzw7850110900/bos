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
	<form method="post" id="searchForm" action="../../area/exportArea.action">
		省份：<input type="text" name="province"> 城市：<input type="text"
			name="city"> 区域：<input type="text" name="district">
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
	<!-- 编辑表单 -->
	<div id="editwin" class="easyui-window" data-options="title:'区域添加修改',closed:true" style="width:600px;top:50px;left:200px">
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
							<td>省</td>
							<td>
								<input type="text" name="province" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>市</td>
							<td>
								<input type="text" name="city" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>区</td>
							<td>
								<input type="text" name="district" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>邮编</td>
							<td>
								<input type="text" name="postcode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>简码</td>
							<td>
								<input type="text" name="shortcode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>城市编码</td>
							<td>
								<input type="text" name="citycode" class="easyui-validatebox" required="true" />
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
			  var action="area";
			  //定义区域的列数据
			  var columns = [ [ {
					field : 'id',
					checkbox : true,
				},{
					field : 'province',
					title : '省',
					width : 120,
					align : 'center'
				}, {
					field : 'city',
					title : '市',
					width : 120,
					align : 'center'
				}, {
					field : 'district',
					title : '区',
					width : 120,
					align : 'center'
				}, {
					field : 'postcode',
					title : '邮编',
					width : 120,
					align : 'center'
				}, {
					field : 'shortcode',
					title : '简码',
					width : 120,
					align : 'center'
				}, {
					field : 'citycode',
					title : '城市编码',
					width : 200,
					align : 'center'
				} ] ];
			  
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
				url:"../../area/batchImport.action",
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
		$("#exportBtn").click(function(){
			//点击导出后,提交搜索条件的表单到后台
			$("#searchForm").submit();
		});
	</script>
</body>
</html>