var index=0;//索引
var strs="";//获取文本数据
var i=1;//用于设置保存时间
$(document).ready(function(){
	
	getdata();
	tokeyborder();
	savedata();
	
	
});




//实时获取数据函数
function getdata(){
		//获取到input中的
		$(document).on('input propertychange','#editbody .col-md-12 .input-group .content', function(){
			
			var hh=$("#editbody .row").height();
			if(hh>$("#editbody").height()){
				bgheight();
			}
			
			//实时获取数据进行保存,太耗费内存，不能用
		});

		
	
}
//保存
function savedata(){
	//点击保存按钮进行保存
//$(".collapse .navbar-left ").on("click","#save",function(){
//	clicksave();
//});

$(".collapse .navbar-left #save").click(function(){
	clicksave();
});


//自动保存
setInterval(() => {
	if(i%600==0){
		clicksave();
	}
	i++;	
	}, 1000);
	
}
//点击按钮进行保存
function clicksave(){
	var infos=new Array();
	var o=new Object();
	var str="";
	//获取主题
	var title=$("#editbody #title").val();
	var doc_id=$("#editbody #doc_id").val();
	if(title=="" || title==null){
		title="无标题";
		o.title=title;
	}else{
		o.title=title;
	}
	o.doc_id=doc_id;

	 //alert($.trim(str));去除空格
	
	//获取整个subject存入 
	 $("#editbody .subject").find(".col-md-12").each(function(){
		 str=$.trim($(this).parent().html());
		// alert($(this).parent().html());
	 });

	o.content=str;
	
	infos.push(o);
	ajaxdata(infos);
	
}
//利用ajax传输数据
function ajaxdata(infos){
	$.ajax({
		url:path+"/suji/notebook/save.do",
		type:"post",
		data:{"infos":JSON.stringify(infos)},// 传递数组前得将数组转换成json数组
		dataType:"json",
		success:function(data){
			//返回数据
			if(data.status=="-1"){
				//alert(data.msg);
				$("#infos i").text(data.msg);
				$("#infos").show();
				setInterval(() => {
					$("#infos").hide();
					$("#infos i").text("已保存");
				}, 5000);
				
			}else{
				//alert(data.msg);
				$("#infos").show();
				setInterval(() => {
					$("#infos").hide();
				}, 5000);
				
			}
			
			
		},
		error:function(){
			alert("保存失败");
		}
		
	});
}

//判断键盘事件
function tokeyborder(){
	
		$(document).on("keydown","#editbody .first .input-group .content",function (event) {
            var e = event || window.event ||arguments.callee.caller.arguments[0];
           //按了enter键
            if(e && e.keyCode==13){
            	//取消换行
            	event.cancelBubble=true;
            	event.preventDefault();
            	event.stopPropagation();
            	if($(this).text()==""|| $(this).text()==null){
            		var div=$(this).parent().parent();
                	div.css("margin-left","27px");
                	$(this).css("width","710px");
                	$(this).attr("name","one");
            	}else{
            		//添加新的行
                	creatNoteTitle();
            	}
            	
            	//增加背景的长度
            	if(index>11||$("#editbody .row").height()>$("#editbody").height()){
            		bgheight();
            	}
            	index++;
            }
            //按了tab键,变成第二段落
            if(e && e.keyCode==9){
            	var div=$(this).parent().parent();
            	div.css("margin-left","54px");
            	$(this).css("width","582px");
            	$(this).attr("name","two");
            }
            
            //按了删除键，将判断所在行是否为空，为空
            if(e && e.keyCode==8){
            	
            	if($(this).text()==""|| $(this).text()==null){
            		var div=$(this).parent().parent();
            		var cls=div.attr("class");
            		if(cls.match("two")){
            			div.remove();
            		}
            		
            	}
            }
            
        });

	
}
//创建编辑区
function creatNoteTitle(){
	var sdiv="";
	
	sdiv+='<div class="col-md-12 first two" >';
	sdiv+='<div class="input-group">';
	sdiv+='<span class="input-group-addon glyphicon glyphicon-map-marker" id="sizing-addon2"></span>'; 
	sdiv+='<div class="content" contenteditable="true" name="one">在这里输入</div>'; 
	sdiv+='</div>';
	sdiv+='</div>';
	
	$sdiv=$(sdiv);
	$("#editbody form .subject").append($sdiv);
}


//增加背景高度
function bgheight(){
	var h=$("#editbody").height();
	$("#editbody").height(h+47);
	
}
function getBody(){
	$("#editbody .subject ").on("","#save",function(){
		
	});

}




