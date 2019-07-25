<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>欢迎使用速记笔记</title>
<!-- Bootstrap -->
<link href="${path}/bootstrapfile/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${path}/bootstrapfile/css/edit.css" rel="stylesheet">
<script src="${path}/bootstrapfile/js/jquery-3.3.1.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${path}/bootstrapfile/js/bootstrap.min.js"></script>
<script src="${path}/bootstrapfile/js/edit.js"></script>
<script src="${path}/js/basevalue.js"></script>
</head>
<body>
	<!-- 导航-->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- 小屏幕导航按钮和logo -->
			<div class="navbar-header">
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<!-- 小屏幕导航按钮和logo -->

			<!-- 导航 -->
			<div class="navbar-collapse collapse">
				<ul class=" nav navbar-nav navbar-left">
					<li><a href="${path}/suji/notebook/doc.do" title="退出"><span
							class="glyphicon glyphicon-menu-left"></span></a></li>
					<li><a id="back" title="回退"><i class="iconfont">&#xe659;</i></a></li>
					<li><a id="go" title="前进"><i class="iconfont">&#xe600;</i></a></li>
					<li><a id="save" title="保存"><i class="iconfont">&#xe601;</i></a></li>
					<li id="infos"><a id="info" ><i>已保存</i></a></li>
				</ul>
				<ul class=" nav navbar-nav navbar-right">
					<li><a href="#home" title="帮助"><span
							class="glyphicon glyphicon-question-sign"></span></a></li>
					<li><a href="#bbs" title="分享链接"><span
							class="glyphicon glyphicon-paperclip"></span></a></li>
					<li><a href="#bbs" title="打印"><span
							class="glyphicon glyphicon-print"></span></a></li>
					<li><a href="#bbs" title="下载"><span
							class="glyphicon glyphicon-download-alt"></span></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 导航-->

	<section id="editbody">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form action="#">
						<div class="col-md-12">
							<input type="text" value="${title}" name="title"
								placeholder="无标题" class="form-control" id="title"
								autocomplete="off"> <input type="hidden"
								value="${doc_id}" id="doc_id">
						</div>



						<div class="subject">
							<c:choose>
								<c:when test="${not empty content}">
							${content}
							</c:when>


								<c:when test="${empty content}">
									
										<div class="col-md-12 first">
											<div class="input-group">
												<span
													class="input-group-addon glyphicon glyphicon-map-marker"
													id="sizing-addon2"></span>
												<div class="content" contenteditable="true" name="one">在这里输入:</div>
											</div>
										</div>
									
								</c:when>
							</c:choose>
						</div>




					</form>
				</div>
			</div>

		</div>

	</section>

	<script type="text/javascript">
		//小屏幕导航点击关闭菜单
		$(".navbar-collapse a").click(function() {
			$(".navbar-collapse").collapse("hide");
		});
	</script>
</body>
</html>