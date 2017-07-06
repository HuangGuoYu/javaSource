<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=path%>/css/login.css">
<title>登录</title>
</head>
<body>
		
		<div class="container">
			<h1 class="text-center">聊天小应用</h1>
			
			<div id="login_form">
				<form action="<%=path%>/msg/stuLogin" class="form-inline text-center" method="post">
				<div class="form-group">
					<label>用户名：</label>
					<input type="text" name="id" class="form-control">
				</div>
				<br/>
				<div class="form-group">
					<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
					<input type="password" name="password" class="form-control">
				</div>
				<br/>
			<button class="btn btn-info">&nbsp;&nbsp;登录&nbsp;&nbsp;</button>
			<a class="btn btn-info" href="<%=path%>/msg/register">&nbsp;&nbsp;注册&nbsp;&nbsp;</a>		
			
			<br />
					<label style="color: red;">${loginTag}</label>
			</form>
			
			</div>

		</div>
</body>
</html>