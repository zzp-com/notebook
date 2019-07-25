$(function(){
	 getCookie();//获得cookie
	//登录提交
	$("#submit").click(function(){
		judge();
	});
	
	
	
});
function judge(){
	var infos=new Array();
	var o=new Object();
	var count=0;
	var email=$("#email").val();
	var pwd=$("#pwd").val();
	
	// 邮箱验证
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	
	// 判断邮箱
	if(email==null || email == ""){
		$("#erroremail").val("邮箱不能为空");
		$("#email").css("border", "1px solid red");
	} else if(!(reg.test(email))){
		$("#erroremail").val("邮箱不正确！");
		$("#email").css("border", "1px solid red");
	}else{
		$("#email").css("border", "1px solid #999999");
		$("#erroremail").val("");
		o.email=email;
		count++;
	}
	
	// 判断密码
	if(pwd==null || pwd == ""){
		$("#errorpwd").val("密码不能为空");
		$("#pwd").css("border", "1px solid red");
	}else if(pwd.length>20 || pwd.length<6){
		$("#errorpwd").val("密码长度有误");
		$("#pwd").css("border", "1px solid red");
	}else{
		$("#pwd").css("border", "1px solid #999999");
		$("#errorpwd").val("");
		o.pwd=pwd;
		count++;
	}
	
	if(count == 2){
		infos.push(o);
		sendinfo(infos);
		setCookie(email,pwd);
	}
	
}

function sendinfo(infos){
	// 利用ajax向后台传值
	$.ajax({
		url:path+"/jianshu/verificationController/login.do",
		type:"post",
		data:{"infos":JSON.stringify(infos)},// 传递数组前得将数组转换成json数组
		dataType:"json",
		success:function(data){
			
			if(data.status === "0"){
				$("#erroremail").val(data.msg);
			}
			if(data.status === "-1"){
				$("#errorpwd").val(data.msg);
			}
			if(data.status === "1"){
				var i=3;
				if(confirm(data.msg+":点击确认后跳转")){
					window.location.href=path+"/suji/notebook/doc.do?folder_id=1";//需要修改url
				}
			}
			
			
		},
		error:function(){
			alert("登录失败");
		}
		
	});
	
	
}

function setCookie(email,pwd){ //设置cookie    
    var checked = $("input[name='remberpwd']").prop("checked");//获取“是否记住密码”复选框  

    if(checked){ //判断是否选中了“记住密码”复选框    
       $.cookie("email",email,{ expires: 7 });//调用jquery.cookie.js中的方法设置cookie中的邮箱登录名    
       $.cookie("pwd",$.base64.encode(pwd),{ expires: 7 });//调用jquery.cookie.js中的方法设置cookie中的登陆密码，并使用base64（jquery.base64.js）进行加密    
    }else{     
       $.cookie("pwd",null);     
    }      
}     

function getCookie(){ //获取cookie    
    var email = $.cookie("email"); //获取cookie中的邮箱 
    var pwd =  $.cookie("pwd"); //获取cookie中的登陆密码    
    if(pwd){//密码存在的话把“记住用户名和密码”复选框勾选住    
       $("[name='remberpwd']").attr("checked","true");    
    }    
    if(email){//用户名存在的话把用户名填充到用户名文本框    
    	$("#email").val(email);   
    }    
    if(pwd){//密码存在的话把密码填充到密码文本框    
    	$("#pwd").val($.base64.decode(pwd));   
    }    
}

