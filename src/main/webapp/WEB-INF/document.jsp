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
<title>我的文档</title>
<!-- Bootstrap -->
<link href="${path}/bootstrapfile/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${path}/bootstrapfile/css/style.css"
	rel="stylesheet">	
	
	
<link href="${path}/bootstrapfile/css/doc.css" rel="stylesheet">

<script src="${path}/bootstrapfile/js/jquery-3.3.1.min.js"></script>
<script src="${path}/bootstrapfile/js/jquery.contextify.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${path}/bootstrapfile/js/bootstrap.min.js"></script>
<script src="${path}/bootstrapfile/js/document.js"></script>
<script src="${path}/js/basevalue.js"></script>



<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="http://jqueryui.com/resources/demos/style.css">
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
				<a href="${path}/index.html" class="navbar-brand" target="_self">速&nbsp;&nbsp;记</a>
			</div>
			<!-- 小屏幕导航按钮和logo -->
			<!-- 导航 -->
			<div class="navbar-collapse collapse">
				<ul class=" nav navbar-nav navbar-right">
					<li><a href="#home"><span
							class="glyphicon glyphicon-question-sign"></span></a></li>
					<li><a href="#bbs"><span class="glyphicon glyphicon-bell"></span></a></li>
					<li><a href="#contact"><img id="touxiang"
							src="${path}/bootstrapfile/imgs/doc.png"></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 导航-->
	<section id="roof">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<!-- 下拉选框 -->
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="true">新建文件</button>
						<ul class="dropdown-menu" style="min-width: 100px"
							aria-labelledby="dropdownMenu1">
							<li><a href="javascript:void(0)" id="newdoc"><span
									class="glyphicon glyphicon-file"></span>新建文档</a></li>
							<li><a href="javascript:void(0);" id="newfolder"><span
									class="glyphicon glyphicon-folder-open"></span>新建文件夹</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<label><span>我的文档</span></label>
				</div>

				<div class="col-md-4 setleft">

					<div class="input-group">
						<input type="text" class="form-control" /> <span
							class="input-group-addon"> <a href="#"><i
								class="glyphicon glyphicon-search"></i></a>
						</span>
					</div>
				</div>
			</div>
		</div>
		<!-- 主体部分 -->
	</section>
	<section id="home">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="col-md-12">
						<a href="${path}/suji/notebook/doc.do"><span class="glyphicon glyphicon-home"></span>我的文档</a>
					</div>
					<div class="col-md-12">
						<a id="recent_edit"><span class="glyphicon glyphicon-time"></span>最近编辑</a>
					</div>
					<div class="col-md-12">
						<a id="market"><span class="glyphicon glyphicon-tag"></span>标记文档</a>
					</div>
					<div class="col-md-12">
						<a id="del"><span class="glyphicon glyphicon-trash"></span>暂存收站</a>
						
					</div>
					<!-- 悬浮窗口 -->
					<div class="col-md-12">
					<section id="foot">
						<div class="box_lg">
							<div class="box_tit">
								<a class="close" href="#" title="关闭">×</a>
								<h3>新建文件夹</h3>
							</div>

							<div class="box_cont">
								<form class="box_frm">
									<ol>
										<li><input type="text" class="ipt" placeholder="请输入标题" /></li>
										<li><input type="submit" value="确认"
											class="btn btn-primary btn_frm"><input type="reset"
											value="重置" class="btn btn-primary btn_frm"></li>

									</ol>
								</form>
							</div>
						</div>
						<div class="box_bg"></div>
					</section>
						</div>
						<!-- 悬浮窗口 -->
				</div>
				<div class="col-md-8">
					
					<!-- 文件夹 -->
					<c:forEach items="${folder}" var="item">
							<div class="col-md-3">
							<a class="folder">
							 <img src="${path}/bootstrapfile/imgs/download.png"
								class="img-responsive">
								<c:if test="${item.marker==true}">
								<span class="glyphicon glyphicon-star" id="marker" ></span>
								</c:if>
								<c:if test="${item.marker==false}">
								<span class="glyphicon glyphicon-star" id="marker" style="display:none;"></span>  
								</c:if>
								 <span id="fname" contenteditable="true">${item.folder_title }</span>
								<p class="hidden id">${item.folder_id }</p>
							</a>
						</div>
					</c:forEach>
					
					<!-- 文档 -->
					<c:forEach items="${map}" var="item">
						<div class="col-md-3">
							<a class="doc">
							<img src="${path}/bootstrapfile/imgs/doc.png"
								class="img-responsive">
								<c:if test="${item.marker==true}">
								<span class="glyphicon glyphicon-star" id="marker" ></span>
								</c:if>
								<c:if test="${item.marker==false}">
								<span class="glyphicon glyphicon-star" id="marker" style="display:none;"></span>  
								</c:if>
								
								<span id="rname"  contenteditable="true">${item.text_title }</span>
								<p class="hidden id">${item.text_id }</p>
								<p class="hidden marker">${item.marker}</p>
							</a>
						</div>
						

					</c:forEach>
					
						


				</div>
			</div>
		</div>
	</section>

</body>
</html>