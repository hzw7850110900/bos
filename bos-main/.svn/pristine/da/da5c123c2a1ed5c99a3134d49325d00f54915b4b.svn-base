<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加宣传任务</title>
<script type="text/javascript" src="../../js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css"
	href="../../js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../../js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/default.css">
<script type="text/javascript"
	src="../../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!--导入kindEditor编写器的资源  -->
<script type="text/javascript" src="../../js/kindEditor/kindeditor-min.js"></script>
<script type="text/javascript" src="../../js/kindEditor/lang/zh_CN.js"></script>
<!--导入日期格式化功的工具  -->
<script type="text/javascript" src="../../js/date.js"></script>
</head>
<body>
	<div region="north" style="height: 31px; overflow: hidden;"
		split="false" border="false">
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
				plain="true">保存</a> <a id="back" icon="icon-back" href="#"
				class="easyui-linkbutton" plain="true">返回列表</a>
		</div>
	</div>
	<div region="center" style="overflow: auto; padding: 5px;"
		border="false">
		<form id="promotionForm" enctype="multipart/form-data"
			action="../../promotion/save.action" method="post">
			<input type="hidden" id="promotionId" name="id"> 
			<table class="table-edit" width="95%" align="center">
				<tr class="title">
					<td colspan="4">宣传任务</td>
				</tr>
				<tr>
					<td>宣传概要(标题):</td>
					<td colspan="3"><input type="text" name="title" id="title"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>活动范围:</td>
					<td><input type="text" name="activeScope" id="activeScope"
						class="easyui-validatebox" /></td>
					<td>宣传图片:</td>
					<td><input type="file" name="titleImgFile" id="titleImg"
						class="easyui-validatebox" required="true" />
						<img  id="img"  src=""/>	
						<input type="hidden" name="titleImg" id="titleImgTemp"> 
					</td>
				</tr>
				<tr>
					<td>发布时间:</td>
					<td><input type="text" name="startDate" id="startDate"
						class="easyui-datebox" required="true" /></td>
					<td>失效时间:</td>
					<td><input type="text" name="endDate" id="endDate"
						class="easyui-datebox" required="true" /></td>
				</tr>
				<tr>
					<td>宣传内容(活动描述信息):</td>
					<td colspan="3"><textarea id="description" name="description"
							style="width: 100%" rows="20"></textarea></td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
	  $(function() {	
			//判断id是否存在
			if("${param.id}"!=null){
				//获取修改时传递过来的id
				//判断id是否为空 如果不为空 则访问后台回响数据
				$.post("../../promotion/get.action",{uuid:"${param.id}"},
						function(data){
							if(data!=null){
								/*因为修改的表单数据中有时间和图片这样特殊的输入框 
								所以无法用easy表单的load方法来回响,需要我们查询到数据手动回响*/
								
								//标题
								$("#title").val(data.title);
								//
								$("#activeScope").val(data.activeScope);
								
								//发布时间--date属于特殊输入框，这里用的是easy的date-box的输入框，所以无法直接用jquery回响的。
								//我们采用的是easyui的date-box的setvalue的方法来回响,并且注意需要把日期格式化、
								//发布时间
								$("#startDate").datebox("setValue",dateFormat(data.startDate));
								//结束时间
								$("#endDate").datebox("setValue",dateFormat(data.endDate));
								
								//kindEditor编辑框的数据回响--使用kindEditor的编辑器回响数据的方法							
								window.editor.html(data.description);
								
								//图片框的数据回响--在图片那一个单元格加一个img的标签 设置src属性
								$("#img").prop("src",data.titleImg);
								$("#img").prop("style","width:120px;height:80px");
								  /*在图片框下面设置添加一个隐藏域,用来临时保存回响图片的路径，
								  在保存的时候获取这个路径来判断是否有重新选择图片*/
								 $("#titleImgTemp").val(data.titleImg);						
								//设置隐藏域id的值
								$("#promotionId").val(data.id);
							}
					},"json");	
			}
									
																		
			$("#back").click(function() {
				window.location.href = "promotion.jsp";
			});

			//初始化kendEditor资源
			KindEditor.ready(function(K) {
				//把textarea改为编辑器
				window.editor = K.create('#description',{
					//修改图片提交的后台地址
					uploadJson:"../../image/upload.action",
					//修改文件查询管理器的查询地址
					fileManagerJson:"../../image/manager.action",
					//是否开启文件管理器的功能
					allowFileManager:true
				});
			});
		
		//提交表单
		$("#save").click(function(){
			// 将编辑器的HTML数据同步到textarea
			 editor.sync();
			
			$("#promotionForm").submit();
		});	
	}); 	
	</script>
</body>
</html>