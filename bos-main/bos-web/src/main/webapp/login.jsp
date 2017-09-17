<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,innitial-scale=1">
<title>BOS管理系统 登陆页面</title>
<script src="js/easyui/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<!-- easyui的js文件 -->
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<!-- 语言js -->
<script type="text/javascript"
	src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 图标css -->
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<!-- 模板的css -->
<link rel="stylesheet" type="text/css"
	href="js/easyui/themes/default/easyui.css">
</head>

<body>
	<div class="loginbox">
		<div class="loginnav">
			<nav class="navbar navbar-default">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="#"><img src="images/logo.png"></a>
						<span class="logintitle">员工登录</span>
					</div>
				</div>
			</nav>
		</div>

		<section class="mainlogin">
			<div class="container">
				<div class="col-md-4 col-md-offset-7 logincontent">
					<h4>员工登录</h4>
					<form class="form-horizontal" id="loginform" name="loginform"
						method="post" action="">
						<div class="form-group" id="idInputLine">
							<label for="inputPassword3" class="col-sm-3 control-label">账号</label>
							<div class="col-sm-8">
								<input id="username" type="text" name="username"
									class="form-control" placeholder="请输入手机号/邮箱/用户名">
							</div>
						</div>
						<div class="form-group" id="pwdInputLine">
							<label id="loginform:pwdInput" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-8">
								<input for="pwdInput" type="password" class="form-control"
									id="password" name="password" placeholder="请输入您的密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputvalidate" class="col-sm-3 control-label">验证码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="validCode"
									name="validCode" placeholder="请输入验证码">
							</div>
							<div class="col-sm-4">
								<img id="loginform:vCode" src="validatecode.jsp"
									onclick="javascript:document.getElementById('loginform:vCode'). src='validatecode.jsp?'+Math.random();" />
							</div>
						</div>
						<div class="form-group">

							<div class="col-sm-offset-3 col-sm-4">
								<input type="checkbox"><span class="size12">
									记住用户名</span>
							</div>
							<div class="col-sm-4">
								<a href="#"><span class="size12 forget">忘记密码</span></a>
							</div>
						</div>
						<div class="col-md-offset-3 col-md-8">
							<a href="javascript:void(0)" id="loginBtn" name="loginform:j_id19"
								class="btn btn-danger">立即登录</a>

						</div>
					</form>
				</div>
			</div>
		</section>

		<footer class="clearfix">
			<div class="container">
				<p class="text-center">地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096
					电话：400-618-4000 传真：010-82935100</p>
				<p class="text-center">京ICP备08001421号京公网安备110108007702</p>
			</div>
		</footer>
	</div>
</body>
<script type="text/javascript">
	$("#loginBtn").click(function() {
		//获取用户名
		var username = $("#username").val();
		//获取密码
		var password = $("#password").val();
		//获取验证码
		var validCode = $("#validCode").val();
		//异步加载
		$.post("user/login.action", {
			username : username,
			password : password,
			validCode : validCode
		}, function(data) {
			if (data.success) {
				window.location.href = "index.jsp";
			} else {
				$.messager.alert("提示", "登录失败,原因是:" + data.msg, "error");
				
			}
		}, "json");
	});
</script>
</html>