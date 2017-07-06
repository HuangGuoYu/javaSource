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
<style type="text/css">
	th{
		margin: 0px;
		padding: 0px;
	}
	input{
		margin: 0px;
		
	}
	body{
		background: url('<%=path%>/image/back.gif');
	}
</style>
<title>Insert title here</title>

</head>
<body>
	<div class="container">
		<h1 style="text-align: center;">添加学生</h1>
		<form action="<%=path%>/msg/registerstu" method="post"  class="text-center">
			<table class="table table-bordered">
				<tr>
					<th>姓名</th>
					<th><input type="text" name="name"  class="form-control text-center"></th>
				</tr>
				<tr>
					<th>密码</th>
					<th><input type="text" name="password"  class="form-control text-center"></th>
				</tr>
				<tr>
					<th>昵称</th>
					<th><input type="text" name="nickName"  class="form-control text-center"></th>
				</tr>
				<tr>
					<th>年级</th>
					<th><input type="text" name="grade"  class="form-control text-center"></th>
				</tr>
				
			</table>
			<button class="btn btn-info">提交</button>
			<a href="<%=path%>/msg/loginPage" class="btn btn-info">登录</a>
		</form>
			
		<%
		if(request.getAttribute("tag") != null){
			if((Boolean)request.getAttribute("tag")){
		%>
			<div class="text-center" style="color: red">
				<h1>注册账号如下</h1>
				<h2>${id}</h2>
			</div>

		<%
			}
		}
		%>
	</div>
</body>
</html>