<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//使用jsp表达式获取路径
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String socketPath = request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("path", path);
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新建文档</title>
<!-- Bootstrap -->
<link href="${path}/bootstrapfile/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${path}/bootstrapfile/js/jquery-3.3.1.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${path}/bootstrapfile/js/bootstrap.min.js"></script>
<script src="${path}/js/basevalue.js"></script>
<style>
#home form {
	margin-top: 20%;
	font-size: 20px;
}

#home form .row div {
	margin-top: 20px;
}

#home form .row div input[type=button] {
	background: #fff;
	font-size: 16px;
}

#home form .row div input[name=title] {
	outline: none;
	font-size: 16px;
}

#home form .row div #btn1 {
	margin-left: 44px;
	background: #2f2c2c;
	border: none;
	color: #fff;
}
#home form .row div #btn2 {
border: none;
background: #e4dede;
}

</style>
</head>
<body>
	<section id="home">
		<div class="container">
			<form action="#">
				<div class="row">
					<div class="col-md-12  text-center">新建文档</div>
					<div class="col-md-6 col-md-offset-5">
						<input name="title" type="text" placeholder="请输入标题"
							autocomplete="off">
					</div>
					<div class="col-md-6 col-md-offset-5">
						<input type="button" value="确认" id="btn1">&nbsp;&nbsp; <input
							type="button" value="取消" id="btn2">
					</div>
				</div>
			</form>
		</div>
	</section>


</body>
</html>