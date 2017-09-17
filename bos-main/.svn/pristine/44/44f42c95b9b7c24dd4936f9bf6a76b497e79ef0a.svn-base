$(function() {
	$("#list").datagrid({
		url : "../../" + action + "/findByPage.action",
		columns : columns,
		pagination : true,
		pageList : [5, 10, 15 ],
		pageSize : 10,
		toolbar : "#toolbar"
	});
	// 点击添加打开window窗口
	$("#add").click(function() {
		$("#editForm").form("clear");
		$("#editwin").window("open");
	});
	// 加载页面数据
	$("#search").click(function(){
		$("#list").datagrid("load", getFormData("searchForm"));
	});
	// 提交表单
	$("#save").click(function() {
		$("#editForm").form("submit", {
			url : "../../"+action+"/save.action",
			onSubmit : function() {
				// 判断是否所有表单项都验证成功 是才可以提交
				return $("#editForm").form("validate");
			},
			success : function(data) {
				// 先将data手动转换成json格式
				data = eval("(" + data + ")");
				// 判断后台回响的数据
				if (data.success) {
					// 如果成功 则清空表单
					$("#editForm").form("clear");
					// 刷下datagrid的页面数据
					$("#list").datagrid("reload");
					// 关闭窗口
					$("#editwin").window("close");
					// 提示保存成功
					$.messager.alert("提示", "保存成功", "info");
				} else {
					// 如果失败 则提示失败，
					$.messager.alert("提示", "保存失败,原因:" + data.message, "error");
				}
			}
		});
	});
	// 修改--回响数据
	$("#editbtn").click(
			function() {
				var rows = $("#list").datagrid("getSelections");
				if (rows.length != 1) {
					$.messager.alert("提示", "修改时必须只能选择一行数据来操作", "warning");
					return;
				}
				// 获取被选中行的id
				var id = rows[0].id;
				//调用方法设置combobox的回响数据的值。				
				loadEditForm(rows[0]);
				// 从后台数据数据自动填充回响到表单当中--采用form表单的load方法
				$("#editForm").form("load",
						"../../" + action + "/get.action?uuid=" + id);
				// 打开window窗口
				$("#editwin").window("open");
			});
	// 删除数据
	$("#delbtn").click(
			function() {
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
						$.post("../../" + action + "/delete.action", {
							"ids" : ids
						}, function(data) {
							if (data.success) {
								// 删除成功则提示删除成功并刷新页面数据
								$("#list").datagrid("reload");
								// 提示成功
								$.messager.alert("提示", "删除成功", "info");
							} else {
								// 删除失败则提示失败原因
								$.messager.alert("提示", "删除失败,原因是"
										+ data.message, "error");
							}
						}, "json");
					}
				});
			});
	
	
});
//空实现
function loadEditForm(row) {}
	
