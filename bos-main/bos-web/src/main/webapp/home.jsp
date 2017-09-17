<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>消息中心</title>
		<link href="favicon.ico" rel="icon" type="image/x-icon" />
		<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
		<script type="text/javascript" src="js/easyui/ext/jquery.portal.js"></script>
		<link rel="stylesheet" type="text/css" href="js/easyui/ext/portal.css">		
		<script type="text/javascript">
			$(function() {
				//创建一个门户
				$("#message").portal({
					//取消边框显示
					border:true
				});
				//设计四个面板
				var panels=[
				          {
				        	  title:'公告栏',
				        	  height:200,
				        	 
				        	  colsable:true,
				        	  collapsible: true,
				        	  href:"pages/portal/gonggao.html"
				          },
				          {
				        	  title:'代办事宜',
				        	  height:200,
				        	  
				        	  colsable:true,
				        	  collapsible: true,
				        	  href:"pages/portal/daiban.html"
				          },
				          {
				        	  title:'预警信息',
				        	  height:200,
				        	 
				        	  colsable:true,
				        	  collapsible: true,
				        	  href:"pages/portal/yujing.html"
				          },
				          {
				        	  title:'系统BUG反馈',
				        	  height:200,
				        	  
				        	  colsable:true,
				        	  collapsible: true,
				        	  href:"pages/portal/bug.html"			
						  } 
				       ];

				//创建面板并将面板追加到body当中
				for (var i = 0; i < 4; i++) {
					//创建一个面板并将面板添加到门户当中
					var p = $("<div></div>").appendTo("body");
					p.panel(panels[i]);

					$("#message").portal("add", {
						panel: p,
						columnIndex: i%2
					});
				}

			});
		</script>
	<body>
		<div id="message" style="width:1000px;height:500px;">   
		    <div style="width:50%"></div>   
		    <div style="width:50%"></div>        
		</div>  
	</body>

</html>