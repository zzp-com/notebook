
$(document).ready(function(){
	//获取到文档
	var aes=$("#home .col-md-8 .col-md-3 .doc").each(function(){
		 $(this).click(function(){
			 var title= $(this).find("span").text();
			 //获取到每个文档的id
			 var doc_id=$(this).find(".id").text();
			 post(path+"/suji/notebook/edit.do",{"title":title,"doc_id":doc_id});
		 });
//		//当鼠标悬浮时，获取id
//		 var id="";
//		 $(this).on("mouseover",function(event){
//			 	id=$(this).find(".id").text();	
//		 });
		 
		 showMenu(this);
		 
	});
	
	
	//获取到文件
	$("#home .col-md-8 .col-md-3 .folder").each(function(){
		 $(this).click(function(){
			 //获取到每个文件夹的id
			 var folder_id=$(this).find(".id").text();
			 //使用ajax
			 post(path+"/suji/notebook/doc.do",{"folder_id":folder_id});
		 });
		 showFolderMenu(this);
	});
	
	
	
	
	//点击新建文档
	newdoc();
	
	//点击最近编辑
	recenedit();
	
	//点击标记文件
	clickmarket();
	
});

function clickmarket(){
	$("#home #market").click(function(){
		 post(path+"/suji/notebook/doc.do",{"clickbtn":"marker"});
		
	});
}

function recenedit(){
	$("#home #recent_edit").click(function(){
		 post(path+"/suji/notebook/doc.do",{"clickbtn":"recenedit"});
		
	});
}

//显示文件菜单方法
function showFolderMenu(now){
	//右键菜单，采用jquery.contextify.min.js插件
	 var options = {items:[
		  {header: '右键功能菜单'},
		   {divider: true},
		  {text: '重命名', onclick: function() {
			 //实现具体功能
			  var rname=$(now).find("#fname");
				  rname.addClass("rname");
				  var t=rname.text();
				  rname.text("").focus().text(t);
			  $(document).on("keydown","#home .rname",function (event) {
		            var e = event || window.event ||arguments.callee.caller.arguments[0];
		           //按了enter键
		            if(e && e.keyCode==13){
		            	//取消换行
		            	event.cancelBubble=true;
		            	event.preventDefault();
		            	event.stopPropagation();
		            	//将值传递出去
		            	var text=rname.text();
		            	var id=$(now).find(".id").text();
		            	 sendfMenuData(id,text,now);
		            	
		            	
		            } 
		        });
			  
			  
		  }},
		  {divider: true},
		  {text: '删除', onclick: function() {
			  var text=$(this).text();
			  var id=$(now).find(".id").text();
			  sendfMenuData(id,text,now);
			  
		  }}
		]}
		$(now).contextify(options);
}



//显示文档菜单方法
function showMenu(now){

	//右键菜单，采用jquery.contextify.min.js插件
	 var options = {items:[
		  {header: '右键功能菜单'},
		   {divider: true},
		   
		   {text: "标星", onclick: function() {
				  var text=$(this).text();
				  var id=$(now).find(".id").text();
				  sendMenuData(id,text,now);
			  }},
			  {text: "取消标星", onclick: function() {
				  var text=$(this).text();
				  var id=$(now).find(".id").text();
				  sendMenuData(id,text,now);
			  }},
			  {divider: true},
		  {text: '重命名', onclick: function() {
			 //实现具体功能
			  var rname=$(now).find("#rname");
				  rname.addClass("rname");
			  var t=rname.text();
			  rname.text("").focus().text(t);
			  $(document).on("keydown","#home .rname",function (event) {
		            var e = event || window.event ||arguments.callee.caller.arguments[0];
		           //按了enter键
		            if(e && e.keyCode==13){
		            	//取消换行
		            	event.cancelBubble=true;
		            	event.preventDefault();
		            	event.stopPropagation();
		            	//将值传递出去
		            	var text=rname.text();
		            	var id=$(now).find(".id").text();
		            	sendMenuData(id,text,now);
		            	
		            	
		            } 
		        });
			  
			  
		  }},
		  
		  
		  {text: '删除', onclick: function() {
			  var text=$(this).text();
			  var id=$(now).find(".id").text();
			  sendMenuData(id,text,now);
			  
		  }}
		]}
		$(now).contextify(options);
}

//发送文件菜单数据
function sendfMenuData(id,text,now){
	$.ajax({
		cache: false,
		url:path+"/suji/notebook/updateFol.do",
		type:"post",
		data:{"text":text,"id":id},// 传递数组前得将数组转换成json数组
		dataType:"json",
		async: false,
		success:function(model){
		if(model.status =="1"){
			location.reload();
		}else if(model.status =="2"){
			location.reload();
		}
		else{
			alert(model.msg);
		}
			
		},
		error:function(){
			alert("失败");
		}
		
	});
}

//发送菜单数据
function sendMenuData(id,text,now){
	$.ajax({
		cache: false,
		url:path+"/suji/notebook/updateDoc.do",
		type:"post",
		data:{"text":text,"id":id},// 传递数组前得将数组转换成json数组
		dataType:"json",
		async: false,
		success:function(model){
		if(model.status =="1"){
			//显示标星图标
			$(now).find("span.glyphicon.glyphicon-star").show();
		}else if (model.status =="2"){
			//隐藏标星图标
			$(now).find("span.glyphicon.glyphicon-star").hide();
			location.reload();
		}else if(model.status =="5"){
			location.reload();
		}else if(model.status =="3"){
			location.reload();
		}
		else{
			alert(model.msg);
		}
			
		},
		error:function(){
			alert("失败");
		}
		
	});
}




//点击新建文档
function newdoc(){
	
	//点击了新建文档
	$("#roof #newdoc").click(function() {
		$("#foot").hide();
		$("#foot h3").text("新建文档");
		$("#foot").show();
		var url=path+"/suji/notebook/saveTitle.do";
		sendData(url);
	});
	//点击了新建文件夹
	$("#roof #newfolder").click(function() {
		$("#foot").hide();
		$("#foot h3").text("新建文件夹");
		$("#foot").show();
		var url=path+"/suji/notebook/savefile.do";
		sendData(url);
	});
	
	
	//点击关闭
	$(".close").click(function(){  
		$("#foot").hide();
		$("#foot h3").text("");
		
    });
	

}

//提交功能方法
function sendData(url){
	//获取title的数据,
$("#foot .box_frm input[type=submit]").click(function(){
		var title=$("#foot .box_frm input[type=text]").val();
		
		if(title !="" && title != null){
		$.ajax({
			cache: false,
			url:url,
			type:"post",
			data:{"title":title},// 传递数组前得将数组转换成json数组
			dataType:"json",
			async: false,
			success:function(model){
				//返回数据
				if(model.status == "1"){
					//保存成功，刷新页面
					var folder_id=model.folder_id;
					post(path+"/suji/notebook/doc.do",{"folder_id":folder_id});
					
				}else{
					//保存失败，不关闭窗口
					alert(model.msg);
				}
				
				
			},
			error:function(){
				//alert("创建失败");
			}
			
		});
		}
		
	});	
	
	
	
}


//封装为post传输方式
function post(url, params) { 
    // 创建form元素
    var temp_form = document.createElement("form");
    // 设置form属性
    temp_form .action = url;      
    temp_form .target = "_self";
    temp_form .method = "post";      
    temp_form .style.display = "none";
    // 处理需要传递的参数 
    for (var x in params) { 
        var opt = document.createElement("textarea");      
        opt.name = x;      
        opt.value = params[x];      
        temp_form .appendChild(opt);      
    }      
    document.body.appendChild(temp_form);
    // 提交表单      
    temp_form.submit();     
}

